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


        int withoutZeroes = 0;
        for (int i = 1; i < parts.length; i++) {
            if (!parts[i].equals("-") && !parts[i].isEmpty()) {
                withoutZeroes++;
            }
        }

        int[] deaths = new int[withoutZeroes];
        int index = 0;

        for (int i = 1; i < parts.length; i++) {
            if (!parts[i].equals("-") && !parts[i].isEmpty()) {
                deaths[index++] = Integer.parseInt(parts[i].trim());
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
        if (age >= 0 && age <= 18) {
            return new AgeBracketDeaths(0, 18, deaths[0]);
        } else if (age > 18 && age <= 65) {
            return new AgeBracketDeaths(19, 65, deaths[1]);
        } else if (age > 65) {
            return new AgeBracketDeaths(66, 120, deaths[2]);
        } else {
            throw new IllegalArgumentException("Wiek nie moze byc ujemny");
        }
    }

    public class AgeBracketDeaths{
        public final int youong;
        public final int old;
        public final int deathCount;

        public AgeBracketDeaths(int youong, int old, int deathCount) {
            this.youong = youong;
            this.old = old;
            this.deathCount = deathCount;
        }
    }

}
