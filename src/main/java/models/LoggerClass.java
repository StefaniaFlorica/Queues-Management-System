package models;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.*;

public class LoggerClass {
    private static final Logger logger = Logger.getLogger("Simulare");
    public static void init() throws IOException {

        logger.setUseParentHandlers(false);
        FileHandler handler = new FileHandler("C:/Users/Stefi/Documents/AN 2/TP/Simulare.log");
        logger.addHandler(handler);
        handler.setFormatter(new CustomRecordFormatter());
    }
    public void appendToLogFile(String message)
    {
        logger.info(message);
    }
}
//pentru scrierea simplificata in fisierul .log
//source: https://stackoverflow.com/questions/2950704/java-util-logging-how-to-suppress-date-line
class CustomRecordFormatter extends Formatter {
    @Override
    public String format(final LogRecord r) {
        StringBuilder sb = new StringBuilder();
        sb.append(formatMessage(r)).append(System.getProperty("line.separator"));
        if (null != r.getThrown()) {
            sb.append("Throwable occurred: ");
            Throwable t = r.getThrown();
            PrintWriter pw = null;
            try {
                StringWriter sw = new StringWriter();
                pw = new PrintWriter(sw);
                t.printStackTrace(pw);
                sb.append(sw.toString());
            } finally {
                if (pw != null) {
                    try {
                        pw.close();
                    } catch (Exception e) {
                    }
                }
            }
        }
        return sb.toString();
    }
}
