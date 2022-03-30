package models;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.*;

public class LoggerClass {
    private static final Logger logger = Logger.getLogger("Simulare");
    public static void init() throws IOException {

        for(Handler iHandler:logger.getParent().getHandlers())
        {
            logger.getParent().removeHandler(iHandler);
        }
        FileHandler handler = new FileHandler("C:/Users/Stefi/Documents/AN 2/TP/Simulare.log");
        logger.addHandler(handler);
        CustomRecordFormatter formatter = new CustomRecordFormatter();
        handler.setFormatter(formatter);
    }
    public void appendToLogFile(String message)
    {
        logger.info(message);
    }
}

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
