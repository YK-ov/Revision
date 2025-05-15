import java.io.*;

public class ICDCodeTabularOptimizedForMemory implements ICDCodeTabular {
    private final String filePath;

    public ICDCodeTabularOptimizedForMemory(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getDescription(String icdCode) throws IndexOutOfBoundsException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineCount = 0;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                lineCount++;
                if (lineCount >= 88) {
                    String[] parts = line.split(" ", 2);
                    if (parts.length >= 2 && parts[0].equals(icdCode)) {
                        return parts[1];
                    }
                }
            }
        } catch (IOException exception) {
            throw new RuntimeException("Plik nie istnieje: " + exception.getMessage());
        }
        throw new IndexOutOfBoundsException("Nie znaleziono kodu ICD: " + icdCode);
    }
}
