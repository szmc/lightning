package uk.co.automatictester.lightning.data;

import java.util.ArrayList;
import java.util.Arrays;

public class TestData {

    // Resources
    private static final String RESOURCES = "src/test/resources/";
    private static final String XML_RESOURCES = RESOURCES+ "xml/";
    private static final String CSV_RESOURCES = RESOURCES + "csv/";

    // XML files
    public static final String VALID_XML_FILE = XML_RESOURCES + "XMLSchemaValidatorTest_valid.xml";

    // CSV files
    public static final String CSV_FILE_WITH_MISSING_LABEL_COLUMN = CSV_RESOURCES + "JMeterCSVFileReaderTestWithMissingColumn.csv";
    public static final String VALID_CSV_FILE = CSV_RESOURCES + "ValidJMeterCSVFile.csv";
    public static final String NONEXISTENT_CSV_FILE = CSV_RESOURCES + "nonexistent.csv";
    public static final String EXISTING_CSV_FILE = CSV_RESOURCES + "FileValidatorTest.csv";

    // Transactions
    public static final ArrayList<String> LOGIN_1200_SUCCESS = new ArrayList<>(Arrays.asList("Login","1200","true"));
    public static final ArrayList<String> LOGIN_1200_FAILURE = new ArrayList<>(Arrays.asList("Login","1200","false"));
    public static final ArrayList<String> LOGIN_1000_SUCCESS = new ArrayList<>(Arrays.asList("Login","1000","true"));
    public static final ArrayList<String> LOGIN_3514_SUCCESS = new ArrayList<>(Arrays.asList("Login","3514","true"));

    public static final ArrayList<String> SEARCH_11221_SUCCESS = new ArrayList<>(Arrays.asList("Search","11221","true"));
    public static final ArrayList<String> SEARCH_800_SUCCESS = new ArrayList<>(Arrays.asList("Search","800","true"));

    // Other
    public static final String NONEXISTENT_LABEL = "nonexistent";
    public static final String EXISTING_LABEL = "Login";
    public static final String MISSING_COLUMN_NAME = "label";

}
