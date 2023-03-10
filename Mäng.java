import java.util.Random;
import java.util.Scanner;

public class Mäng {
    private Ruum[] ruumid;

    public Ruum[] getRuumid() {
        return ruumid;
    }

    public Mäng() {
        ruumid = new Ruum[3];
        ruumid[0] = new Ruum("Koobas", "Oled pimedas koopas.");
        ruumid[1] = new Ruum("Mets", "Oled metsas.");
        ruumid[2] = new Ruum("Rannik", "Oled rannikul.");
    }

    public static void main(String[] args) {
        Mäng mäng = new Mäng();
        Mängija mängija = new Mängija(mäng.getRuumid()[0]);

        Asi oda = new Asi("oda", "Terava otsaga pead torkama.");
        Asi ori = new Asi("ori", "Saad enda eest tööle panna.");
        Asi tõrvik=new Asi("tõrvik","Ära ennast põlema pane.");

        /**mängija.võtaAsi(oda);
        mängija.võtaAsi(ori);
        mängija.vaataSeljakotti();**/

        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("Tere tulemast!");
        System.out.println();

        boolean mängKäib = true;
        while (mängKäib) {
            System.out.println(mängija.getAsukoht().getRuumiKirjeldus());
            String input=in.nextLine();
            if (input.equals("a"))
                break;
            else if (input.equals("l")) {
                mängija.liigu(mäng.getRuumid()[1]);
            } else if (input.equals("t")) {
                mängija.liigu(mäng.getRuumid()[0]);
            }
        }
    }
}
