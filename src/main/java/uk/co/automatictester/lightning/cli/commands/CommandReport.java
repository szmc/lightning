package uk.co.automatictester.lightning.cli.commands;

import com.beust.jcommander.Parameters;
import com.beust.jcommander.ParametersDelegate;
import uk.co.automatictester.lightning.cli.delegates.CSVFile;

@Parameters(separators = "=", commandDescription = "Generate report on JMeter output")
public class CommandReport {

    @ParametersDelegate
    private CSVFile csvFile = new CSVFile();

    public String getCSVFile() {
        return csvFile.getCSVFile();
    }
}
