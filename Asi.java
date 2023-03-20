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

    public String getKirjeldus() {
        return kirjeldus;
    }

    public String toString() {
        return nimi + ":" + kirjeldus;
    }
}
