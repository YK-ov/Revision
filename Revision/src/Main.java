import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        {
            System.out.println("Hello World");

            DeathCauseStatistic stats = new DeathCauseStatistic("A.25", new int[]{48, 49});
            System.out.println(stats);
            String line = "A02.1          ,5,-,-,-,-,-,-,-,-,-,-,-,-,1,2,-,1,1,-,-,-";

            System.out.println(DeathCauseStatistic.fromScvLine(line));

            String filePath = "icd10.txt";

            ICDCodeTabular timeOptimized = new ICDCodeTabularOptimizedForTime(filePath);
            System.out.println(timeOptimized.getDescription("A00"));

            ICDCodeTabular memoryOptimized = new ICDCodeTabularOptimizedForMemory(filePath);
            System.out.println(memoryOptimized.getDescription("A00"));
        }

    }
}
