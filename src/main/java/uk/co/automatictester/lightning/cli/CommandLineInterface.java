package uk.co.automatictester.lightning.cli;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import uk.co.automatictester.lightning.cli.validators.CIServerValidator;
import uk.co.automatictester.lightning.cli.validators.FileValidator;

@Parameters(separators = "=")
public class CommandLineInterface {

    @Parameter(names = "-xml", description = "Lightning XML config file", required = true, validateWith = FileValidator.class)
    private String xmlFile;

    @Parameter(names = "-csv", description = "JMeter CSV result file", required = true, validateWith = FileValidator.class)
    private String csvFile;

    @Parameter(names = "-ci", description = "CI server (jenkins or teamcity)", required = false, validateWith = CIServerValidator.class)
    private String ci;

    @Parameter(names = {"-h", "--help"}, help = true, hidden = true)
    private boolean help;

    private JCommander jc;

    public CommandLineInterface(String[] args) {
        jc = new JCommander(this, args);
    }

    public String getXmlFile() {
        return xmlFile;
    }

    public String getCSVFile() {
        return csvFile;
    }

    public boolean isHelpRequested() {
        return help;
    }

    public boolean isCIEqualTo(String ci) {
        if (this.ci == null) {
            return false;
        } else {
            return (this.ci.toLowerCase().equals(ci));
        }
    }

    public void printHelp() {
        jc.setProgramName("java -jar lightning-<version_number>.jar");
        jc.usage();
    }

}
