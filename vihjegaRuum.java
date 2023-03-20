import java.util.ArrayList;

public class vihjegaRuum extends Ruum{
    private String vihje;
    public vihjegaRuum(String nimi, String ruumiKirjeldus, ArrayList<Asi> asjadRuumis,String vihje) {
        super(nimi, ruumiKirjeldus, asjadRuumis);
        this.vihje=vihje;
    }

    public String getVihje() {
        return vihje;
    }
}
