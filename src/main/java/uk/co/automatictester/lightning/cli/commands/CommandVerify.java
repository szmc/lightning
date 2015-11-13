package uk.co.automatictester.lightning.cli.commands;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.ParametersDelegate;
import uk.co.automatictester.lightning.cli.delegates.CI;
import uk.co.automatictester.lightning.cli.delegates.CSVFile;
import uk.co.automatictester.lightning.cli.validators.FileValidator;

@Parameters(separators = "=", commandDescription = "Execute Lightning tests against JMeter output")
public class CommandVerify {

    @Parameter(names = "-xml", description = "Lightning XML config file", required = true, validateWith = FileValidator.class)
    private String xmlFile;

    @ParametersDelegate
    private CSVFile csvFile = new CSVFile();

    @ParametersDelegate
    private CI ci = new CI();

    public String getXmlFile() {
        return xmlFile;
    }

    public String getCSVFile() {
        return csvFile.getCSVFile();
    }

    public boolean isCIEqualTo(String ci) {
        return this.ci.isCIEqualTo(ci);
    }
}
