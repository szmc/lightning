package uk.co.automatictester.lightning.cli.delegates;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import uk.co.automatictester.lightning.cli.validators.FileValidator;

@Parameters(separators = "=")
public class CSVFile {

    @Parameter(names = "-csv", description = "JMeter CSV result file", required = true, validateWith = FileValidator.class)
    private String csvFile;

    public String getCSVFile() {
        return csvFile;
    }

}
