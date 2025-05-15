import java.util.Arrays;

public class DeathCauseStatistic {
    private String icdCode;
    private int[] deaths;

    public DeathCauseStatistic(String icdCode, int[] deaths) {
        this.icdCode = icdCode;
        this.deaths = deaths;
    }

    public String getIcdCode() {
        return icdCode;
    }

    public int[] getDeaths() {
        return deaths;
    }

    public static DeathCauseStatistic fromScvLine(String line) {
        String[] parts = line.trim().split(",");
        String icdCode = parts[0].trim();


        int[] deaths = new int[20];
        int j = 0;

        for (int i = 2; i < parts.length; i++) {
            if (!parts[i].equals("-")) {
                deaths[j++] = Integer.parseInt(parts[i].trim());
            }
            else{
                deaths[j++] = 0;
            }
        }

        return new DeathCauseStatistic(icdCode, deaths);
    }


    @Override
    public String toString() {
        return "DeathCauseStatistic{" +
                "icdCode='" + icdCode + '\'' +
                ", deaths=" + Arrays.toString(deaths) +
                '}';
    }

    public AgeBracketDeaths ageBracket(int age) {
        int index = age/5;
        if (index >= 20){
            index = 19;
        }
        return new AgeBracketDeaths(index*5, index*5+4, this.deaths[index]);
    }

    public class AgeBracketDeaths{
        public final int young;
        public final int old;
        public final int deathCount;

        public AgeBracketDeaths(int young, int old, int deathCount) {
            this.young = young;
            this.old = old;
            this.deathCount = deathCount;
        }
    }

}
