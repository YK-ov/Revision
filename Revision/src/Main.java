import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        {

//            DeathCauseStatistic stats = new DeathCauseStatistic("A.25", new int[]{48, 49});
//            System.out.println(stats);
            String line = "A02.1          ,5,-,-,-,-,-,-,-,-,-,-,-,-,1,2,-,1,1,-,-,-";

            DeathCauseStatistic stat = DeathCauseStatistic.fromScvLine(line);
            System.out.println(stat);

            DeathCauseStatisticList statisticList = new DeathCauseStatisticList();
            statisticList.repopulate("zgony.csv");
            List<DeathCauseStatistic> deadly = statisticList.mostDeadlyDiseases(3, 5);
            for (DeathCauseStatistic s : deadly) {
                System.out.println(s);
            }


            ICDCodeTabular timeOptimized = new ICDCodeTabularOptimizedForTime("icd10.txt");
            System.out.println(timeOptimized.getDescription("P07.2"));

            ICDCodeTabular memoryOptimized = new ICDCodeTabularOptimizedForMemory("icd10.txt");
            System.out.println(memoryOptimized.getDescription("B01"));




        }

    }
}
