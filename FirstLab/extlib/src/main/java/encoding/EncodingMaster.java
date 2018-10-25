package encoding;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class EncodingMaster {

    private InputStream inputStream;
    private String result;

    public EncodingMaster setStream(InputStream is) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException ex) { //lazy }
            }
        }

        inputStream = is;
        return this;
    }

    public void encodeTo(String encoding) {
        InputStreamReader bufferedInputStream = null;
        StringBuilder sb = new StringBuilder();
        if (inputStream != null) {
            try {
                bufferedInputStream = new InputStreamReader(inputStream, encoding);
                final Scanner scanner = new Scanner(bufferedInputStream);

                while (scanner.hasNextLine()) {
                    sb.append(scanner.nextLine());
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        result = sb.toString();
    }

    public String getResult() {
        setStream(null);
        return result;
    }
}
