public class DeathCauseStatistic {
    private int icdCode;
    private int[] deaths;

    public DeathCauseStatistic(int icdCode, int[] deaths) {
        this.icdCode = icdCode;
        this.deaths = deaths;
    }

    public int getIcdCode() {
        return icdCode;
    }

    public static DeathCauseStatistic fromScvLine(String line) {
        int icdCode = 0;
        int[] deaths = null;

        String[] parts = line.trim().split("\\t");



        DeathCauseStatistic statisticsFromScv = new DeathCauseStatistic(icdCode, deaths);

        return statisticsFromScv;
    }




}
