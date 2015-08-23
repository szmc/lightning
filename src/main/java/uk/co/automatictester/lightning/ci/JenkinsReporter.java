package uk.co.automatictester.lightning.ci;

import uk.co.automatictester.lightning.JMeterTransactions;
import uk.co.automatictester.lightning.TestSet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class JenkinsReporter extends CIReporter {

    public void setJenkinsBuildName(TestSet testSet) {
        writeJenkinsFile(getVerifySummary(testSet));
    }

    public void setJenkinsBuildName(JMeterTransactions jmeterTransactions) {
        writeJenkinsFile(getReportSummary(jmeterTransactions));
    }

    private void writeJenkinsFile(String summary) {
        try {
            Properties props = new Properties();
            props.setProperty("result.string", summary);
            File f = new File("lightning-jenkins.properties");
            OutputStream out = new FileOutputStream(f);
            props.store(out, "In Jenkins Build Name Setter Plugin, define build name as: ${PROPFILE,file=\"lightning-jenkins.properties\",property=\"result.string\"}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
