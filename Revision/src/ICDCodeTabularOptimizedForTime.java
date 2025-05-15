import java.io.*;
import java.util.*;

public class ICDCodeTabularOptimizedForTime implements ICDCodeTabular {
    private final Map<String, String> icdData = new HashMap<>();

    public ICDCodeTabularOptimizedForTime(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineCount = 0;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                lineCount++;
                if (lineCount >= 88) {
                    String[] parts = line.split(" ", 2);
                    if (parts.length >= 2 && parts[0].matches("^[A-Z]\\d{2}(\\.\\d*)?")) {
                        icdData.put(parts[0], parts[1]);
                    }
                }
            }
        }
    }

    @Override
    public String getDescription(String icdCode) throws IndexOutOfBoundsException {
        if (!icdData.containsKey(icdCode)) {
            throw new IndexOutOfBoundsException("Nie znaleziono kodu ICD: " + icdCode);
        }
        return icdData.get(icdCode);
    }
}
