import java.io.*;
import java.util.*;

public class DeathCauseStatisticList {
    private List<DeathCauseStatistic> statistics = new ArrayList<>();

    public void repopulate(String filePath) throws IOException {
        statistics.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineCount = 0;

            while ((line = reader.readLine()) != null) {
                lineCount++;

                if (lineCount >= 88) {
                    String[] parts = line.split(" ", 2);
                    if (parts.length >= 2 && parts[0].matches("^[A-Z]\\d{2}.*")) {
                        DeathCauseStatistic statistic = DeathCauseStatistic.fromScvLine(line);
                        if (statistic != null) {
                            statistics.add(statistic);
                            }
                    }
                }
            }
        }
    }

    public List<DeathCauseStatistic> mostDeadlyDiseases(int age, int n) {
        List<DeathCauseStatistic> deadlyDiseases = new ArrayList<>();

        for (DeathCauseStatistic stat : statistics) {
            DeathCauseStatistic.AgeBracketDeaths bracket = stat.ageBracket(age);
            deadlyDiseases.add(new DeathCauseStatistic(stat.getIcdCode(), new int[]{bracket.deathCount}));
        }

        int size = deadlyDiseases.size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (deadlyDiseases.get(j).getDeaths()[0] < deadlyDiseases.get(j + 1).getDeaths()[0]) {
                    DeathCauseStatistic temp = deadlyDiseases.get(j);
                    deadlyDiseases.set(j, deadlyDiseases.get(j + 1));
                    deadlyDiseases.set(j + 1, temp);
                }
            }
        }

        List<DeathCauseStatistic> deadlyDiseasesSorted = new ArrayList<>();
        for (int i = 0; i < Math.min(n, deadlyDiseases.size()); i++) {
            deadlyDiseasesSorted.add(deadlyDiseases.get(i));
        }

        return deadlyDiseasesSorted;
    }


}
