package uk.ac.ebi.ddi.task.mwxmlgenerator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import uk.ac.ebi.ddi.ddifileservice.services.IFileSystem;
import uk.ac.ebi.ddi.task.mwxmlgenerator.configuration.MwXmlGeneratorTaskProperties;

import java.io.File;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MwXmlGeneratorApplication.class,
		initializers = ConfigFileApplicationContextInitializer.class)
@TestPropertySource(properties = {
		"mw.output_dir=/tmp/mw/output",
		"mw.entries_per_file=2",
		"file.provider=local"
})
public class ITMwXmlGeneratorApplication {

	@Autowired
	private MwXmlGeneratorTaskProperties taskProperties;

	@Autowired
	private MwXmlGeneratorApplication application;

	@Autowired
	private IFileSystem fileSystem;

	@Before
	public void setUp() throws Exception {
		new File(taskProperties.getOutputDir()).mkdirs();
	}

	@Test
	public void contextLoads() throws Exception {
		application.run();
		List<String> files = fileSystem.listFilesFromFolder(taskProperties.getOutputDir());
		Assert.assertTrue(files.size() > 0);
	}

	@After
	public void tearDown() throws Exception {
		fileSystem.cleanDirectory(taskProperties.getOutputDir());
	}

}
