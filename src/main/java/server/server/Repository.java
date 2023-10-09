package server.server;

import java.io.FileReader;
import java.io.FileWriter;

public class Repository implements RepositiryInterfase {


    public static final String LOG_PATH = "D:\\studies\\JAVA\\Java Development Kit\\server\\src\\main\\java\\server\\log.txt";


    @Override
    public String getHistory() {
        return readLog();
    }

    @Override
    public void saveInLog(String text) {
        try (FileWriter writer = new FileWriter(LOG_PATH, true)) {
            writer.write(text);
            writer.write("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readLog() {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(LOG_PATH);) {
            int c;
            while ((c = reader.read()) != -1) {
                stringBuilder.append((char) c);
            }
            if (stringBuilder.length() != 0) {
                stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
