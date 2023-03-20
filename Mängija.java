import java.util.ArrayList;

public class Mängija {
    private Ruum asukoht;
    private ArrayList<Asi> seljakott;

    public Mängija(Ruum asukoht) {
        this.asukoht = asukoht;
        this.seljakott = new ArrayList<>();
    }

    public Ruum getAsukoht() {
        return asukoht;
    }

    public void setAsukoht(Ruum asukoht) {
        this.asukoht = asukoht;
    }

    public ArrayList<Asi> getSeljakott() {
        return seljakott;
    }

    public void võtaAsi(Asi asi) {
        seljakott.add(asi);
    }

    public void kasuta(Asi asi) {
        seljakott.remove(asi);
    }

    //Saad vaadata seljakotti
    public void vaataSeljakotti() {
        System.out.println("Seljakoti sisu: ");
        if (seljakott.isEmpty())
            System.out.println("Seljakott on tühi.");
        for (Asi asi : seljakott) {
            System.out.println(asi.getNimi());
        }
    }

    public boolean kasSeljakotis(String otsitav) {
        for (Asi asi: seljakott) {
            if (asi.getNimi().equals(otsitav))
                return true;
        }
        return false;
    }

    public void liigu(Ruum uusAsukoht) {
        asukoht = uusAsukoht;
        System.out.println(asukoht.getRuumiKirjeldus());
    }
}

