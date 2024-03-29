package uk.ac.ebi.ddi.task.mwxmlgenerator.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("mw")
public class MwXmlGeneratorTaskProperties {
    private int entriesPerFile = 30;
    private String prefix = "MW-";
    private String outputDir;

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getEntriesPerFile() {
        return entriesPerFile;
    }

    public void setEntriesPerFile(int entriesPerFile) {
        this.entriesPerFile = entriesPerFile;
    }
}
