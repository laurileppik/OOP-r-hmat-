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
    public void setSeljakott(ArrayList<Asi> seljakott) {
        this.seljakott = seljakott;
    }

    public void võtaAsi(Asi asi) {
        seljakott.add(asi);
    }

    public void kasuta(Asi asi) {
        seljakott.remove(asi);
    }

    /** Meetod, mis prindib kõik seljakotis olevad asjad.
     *
     */
    public void vaataSeljakotti() {
        System.out.println("Seljakoti sisu: ");
        if (seljakott.isEmpty())
            System.out.println("Seljakott on tühi.");
        for (Asi asi : seljakott) {
            System.out.println(asi.getNimi());
        }
    }

    /** Meetod, mis tagastab, kas otsitav ese on seljakotis või mitte.
     *
     * @param otsitav Otsitav ese.
     * @return Tõeväärtus vastavalt sellele, kas otsitav ese on seljakotis või mitte.
     */

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

