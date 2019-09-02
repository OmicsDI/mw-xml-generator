package uk.ac.ebi.ddi.task.mwxmlgenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uk.ac.ebi.ddi.api.readers.utils.Constants;
import uk.ac.ebi.ddi.api.readers.utils.Transformers;
import uk.ac.ebi.ddi.api.readers.ws.AbstractWsConfig;
import uk.ac.ebi.ddi.ddifileservice.services.IFileSystem;
import uk.ac.ebi.ddi.ddifileservice.type.ConvertibleOutputStream;
import uk.ac.ebi.ddi.task.mwxmlgenerator.client.DatasetWsClient;
import uk.ac.ebi.ddi.task.mwxmlgenerator.client.MWWsConfigProd;
import uk.ac.ebi.ddi.task.mwxmlgenerator.configuration.MwXmlGeneratorTaskProperties;
import uk.ac.ebi.ddi.task.mwxmlgenerator.model.*;
import uk.ac.ebi.ddi.xml.validator.parser.marshaller.OmicsDataMarshaller;
import uk.ac.ebi.ddi.xml.validator.parser.model.Database;
import uk.ac.ebi.ddi.xml.validator.parser.model.Entry;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class MwXmlGeneratorApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(MwXmlGeneratorApplication.class);

    private AbstractWsConfig config = new MWWsConfigProd();

    @Autowired
    private MwXmlGeneratorTaskProperties taskProperties;

    @Autowired
    private IFileSystem fileSystem;

    public static void main(String[] args) {
        SpringApplication.run(MwXmlGeneratorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        DatasetWsClient datasetWsClient = new DatasetWsClient(config);
        DatasetList datasets = datasetWsClient.getAllDatasets();
        TissueList tissueList = datasetWsClient.getTissues();
        SpecieList specieList = datasetWsClient.getSpecies();
        DiseaseList diseaseList = datasetWsClient.getDiseases();
        List<Entry> entries = new ArrayList<>();
        AtomicInteger fileCount = new AtomicInteger(0);

        if (datasets == null) {
            LOGGER.error("Dataset not found");
            return;
        }
        for (MWDataSetDetails dataset : datasets.getDatasets().values()) {
            try {
                if (dataset != null && dataset.getIdentifier() != null) {
                    dataset.setMetabolites(datasetWsClient.getMataboliteList(dataset.getIdentifier()));
                    dataset.setFactors(datasetWsClient.getFactorList(dataset.getIdentifier()));
                    dataset.setTissues(tissueList.getTissuesByDataset(dataset.getIdentifier()));
                    dataset.setSpecies(specieList.getSpeciesByDataset(dataset.getIdentifier()));
                    dataset.setDiseases(diseaseList.getDiseasesByDataset(dataset.getIdentifier()));
                    dataset.setAnalysis(datasetWsClient.getAnalysisInformantion(dataset.getIdentifier()));
                    entries.add(Transformers.transformAPIDatasetToEntry(dataset));

                    if (entries.size() % taskProperties.getEntriesPerFile() == 0) {
                        writeDatasetsToFile(entries, datasets.getDatasets().size(), fileCount.getAndIncrement());
                    }
                }
            } catch (Exception e) {
                LOGGER.info("Exception occurred when processing dataset {}, ", dataset.getIdentifier(), e);
            }
        }
        writeDatasetsToFile(entries, datasets.getDatasets().size(), fileCount.getAndIncrement());
    }

    private void writeDatasetsToFile(List<Entry> entries, int total, int fileCount) throws IOException {
        if (entries.size() < 1) {
            return;
        }

        String releaseDate = new SimpleDateFormat("yyyyMMdd").format(new Date());

        ConvertibleOutputStream outputStream = new ConvertibleOutputStream();
        try (Writer w = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
            OmicsDataMarshaller mm = new OmicsDataMarshaller();

            Database database = new Database();
            database.setDescription(Constants.METABOLOMICS_WORKBENCH_DESCRIPTION);
            database.setName(Constants.METABOLOMICS_WORKBENCH);
            database.setRelease(releaseDate);
            database.setEntries(entries);
            database.setEntryCount(total);
            mm.marshall(database, w);
        }

        String filePath = taskProperties.getOutputDir() + "/" + taskProperties.getPrefix() + fileCount + ".xml";
        LOGGER.info("Attempting to write data to {}", filePath);
        fileSystem.saveFile(outputStream, filePath);
        entries.clear();
    }
}
