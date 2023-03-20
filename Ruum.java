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
    public String getRuumiKirjeldus() {
        return ruumiKirjeldus;
    }

    //Hetkel kui ruumi läheme siis ütleb, et ei ole midagi mida kasutada saaksin, vb oleks hea teha nii et mitte midagi returniks.
    public String toString() {
        if (!asjadRuumis.isEmpty()) {
            StringBuilder kirjeldustetaAsjad= new StringBuilder();
            int asjadeLuger=1;
            for (Asi asjad :asjadRuumis) {
                kirjeldustetaAsjad.append(asjad.getNimi());
                if (asjadeLuger!=asjadRuumis.size())
                    kirjeldustetaAsjad.append(", ");
                asjadeLuger++;
            }
            return "Ruumis " + nimi + " on järgnevad asjad: " + kirjeldustetaAsjad;
        }
        return "Ruumis ei ole midagi mida sa kasutada saaksid.";
    }
}
