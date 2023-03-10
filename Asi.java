public class Asi {
    private String nimi;
    private String kirjeldus;

    public Asi(String nimi, String kirjeldus) {
        this.nimi = nimi;
        this.kirjeldus = kirjeldus;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getKirjeldus() {
        return kirjeldus;
    }

    public void setKirjeldus(String kirjeldus) {
        this.kirjeldus = kirjeldus;
    }

    public String toString() {
        return nimi + ":" + kirjeldus;
    }
}
