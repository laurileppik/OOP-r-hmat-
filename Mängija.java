import java.util.ArrayList;

public class Mängija {
    private Ruum asukoht;
    private ArrayList<Asi> seljakott;

    public Mängija(Ruum asukoht) {
        this.asukoht = asukoht;
        this.seljakott = new ArrayList<Asi>();
    }

    public Ruum getAsukoht() {
        return asukoht;
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
        System.out.println();
        if (seljakott.isEmpty())
            System.out.println("Seljakott on tühi.");
        for (Asi asi : seljakott) {
            System.out.println(asi);
        }
    }
}

