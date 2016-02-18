package uk.co.automatictester.lightning.cli.delegates;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import uk.co.automatictester.lightning.cli.validators.FileValidator;

@Parameters(separators = "=")
public class JmeterCsvFile {

    @Parameter(names = "--jmeter-csv", description = "JMeter CSV result file", required = true, validateWith = FileValidator.class)
    private String jmeterCsvFile;

    public String getJmeterCsvFile() {
        return jmeterCsvFile;
    }

}
