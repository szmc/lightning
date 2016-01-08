package uk.co.automatictester.lightning.exceptions;

public class CSVFileNoTransactionsException extends RuntimeException {
    public CSVFileNoTransactionsException() {
        super("No transactions found in CSV file");
    }
}
