package emias.tests.model;

public class LoginData {
    private final String oms;
    private final String oms1;
    private final String oms2;

    public LoginData(String oms, String oms1, String oms2) {
        this.oms = oms;
        this.oms1 = oms1;
        this.oms2 = oms2;
    }

    public String getOms() {
        return oms;
    }

    public String getOms1() {
        return oms1;
    }

    public String getOms2() {
        return oms2;
    }
}
