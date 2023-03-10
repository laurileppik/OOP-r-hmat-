import java.util.ArrayList;

public class Ruum {
    private String nimi;
    private String ruumiKirjeldus;
    private ArrayList<Asi> asjadRuumis;

    public Ruum(String nimi, String ruumiKirjeldus,ArrayList<Asi> asjadRuumis) {
        this.nimi = nimi;
        this.ruumiKirjeldus = ruumiKirjeldus;
        this.asjadRuumis=asjadRuumis;
    }

    public ArrayList<Asi> getAsjadRuumis() {
        return asjadRuumis;
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

    public String toString() {
        if (!asjadRuumis.isEmpty())
            return "Ruumis " + nimi + " on järgnevad asjad: " +asjadRuumis;
        return "Ruumis ei ole midagi mida sa kasutada saaksid.";
    }
}
