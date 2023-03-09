public class Ruum {
    private String nimi;
    private String ruumiKirjeldus;

    public Ruum(String nimi, String ruumiKirjeldus) {
        this.nimi = nimi;
        this.ruumiKirjeldus = ruumiKirjeldus;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getRuumiKirjeldus() {
        return ruumiKirjeldus;
    }

    public void setRuumiKirjeldus(String ruumiKirjeldus) {
        this.ruumiKirjeldus = ruumiKirjeldus;
    }
}
