import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

public class Mäng {
    private Ruum[] ruumid;

    public Ruum[] getRuumid() {
        return ruumid;
    }

    public Mäng() {
        Asi oda = new Asi("oda", "Terava otsaga pead torkama.");
        Asi ori = new Asi("ori", "Saad enda eest tööle panna.");
        Asi tõrvik = new Asi("tõrvik", "Ära ennast põlema pane.");

        ArrayList<Asi> asjad = new ArrayList<>();

        ruumid = new Ruum[6];
        ruumid[0] = new Ruum("Koobas", "Oled pimedas koopas.", asjad);
        asjad.add(oda);
        asjad.add(tõrvik);
        ruumid[1] = new Ruum("Koopasuu", "Näed koopasuud.", asjad);
        ruumid[2] = new Ruum("Kaljuäär", "Näed kaljuäärt.", asjad);
        ruumid[3] = new Ruum("Kalju", "Oled kalju ääre peal.", asjad);

        ruumid[4] = new Ruum("Kuristik", "Hüppasid kuristikku.", asjad);
        ruumid[5] = new Ruum("Kaljuäär", "Liigud mööda kaljuäärt", asjad);
    }

    public static void main(String[] args) {
        Mäng mäng = new Mäng();
        Mängija mängija = new Mängija(mäng.getRuumid()[0]);

        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("Tere tulemast!");
        System.out.println();
        System.out.println(mängija.getAsukoht().getRuumiKirjeldus());
        boolean mängKäib = true;
        int hetkeneRuum = 0;

        while (mängKäib) {
            String input = in.nextLine();
            String[] sõnad = input.split(" ");
            String käsk = sõnad[0];
            if (käsk.equals("jookse")) {
                System.out.println("Üritad joosta ning kukud ennast vigaseks. Sind ootab pikk ja piinarikas surm.");
                break;

            } else if (käsk.equals("l")) {
                try {
                    mängija.liigu(mäng.getRuumid()[hetkeneRuum + 1]);
                    hetkeneRuum++;
                    System.out.println(mäng.getRuumid()[hetkeneRuum].toString());
                } catch (Exception e) {
                    System.out.println("Sein on ees.");
                }
                if (hetkeneRuum == 3) {
                    System.out.println("Sul on 2 valikut: Saad minna mööda kaljuäärt edasi või proovida ennast alla libistada.");
                    hetkeneRuum++;
                }

            } else if (käsk.equals("t")) {
                if (hetkeneRuum == 5) {
                    hetkeneRuum--;
                }
                try {
                    mängija.liigu(mäng.getRuumid()[hetkeneRuum - 1]);
                    hetkeneRuum--;
                    System.out.println(mäng.getRuumid()[hetkeneRuum].toString());
                } catch (Exception e) {
                    System.out.println("Sein on ees.");
                }
                if (hetkeneRuum == 3) {
                    System.out.println("Sul on 2 valikut: Saad minna mööda kaljuäärt edasi või proovida ennast alla libistada.");
                }

            } else if (käsk.equals("v")) {
                mängija.vaataSeljakotti();

            } else if (hetkeneRuum == 4 && käsk.equals("libista")) {
                System.out.println("...");
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("Kukud");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("Ikka kukud");
                    TimeUnit.SECONDS.sleep(2);
                } catch (Exception e) {
                    System.out.println("Nüüd on hilja ennast päästa.");
                }
                System.out.println("Maa on päris valus.");
                System.out.println("Mäng läbi, sa kaotasid.");
                mängKäib = false;

            } else if (käsk.equals("võta")) {

                if (sõnad.length < 2) {
                    System.out.println("Mida sa võtta tahad?");
                    continue;
                }

                String asiMidaVõtta = sõnad[1];
                ArrayList<Asi> asjad = mängija.getAsukoht().getAsjadRuumis();
                Asi asi = null;
                for (Asi a : asjad) {
                    if (a.getNimi().equalsIgnoreCase(asiMidaVõtta)) {
                        asi = a;
                        break;
                    }
                }

                if (asi != null) {
                    asjad.remove(asi);
                    mängija.võtaAsi(asi);
                    System.out.println("Võtsid eseme " + asi.getNimi() + ".");
                } else {
                    System.out.println("Siin ei ole sellist asja.");
                }

            } else if (käsk.equals("vaata")) {
                if (sõnad.length < 2) {
                    System.out.println("Mida sa vaadata tahad?");
                    continue;
                }

                String asiMidaVaadata = sõnad[1];
                ArrayList<Asi> asjad = mängija.getSeljakott();
                Asi asi = null;
                for (Asi a : asjad) {
                    if (a.getNimi().equalsIgnoreCase(asiMidaVaadata)) {
                        asi = a;
                        break;
                    }
                }

                if (asi != null) {
                    System.out.println(asi.getKirjeldus());
                } else {
                    System.out.println("Sul ei ole sellist asja seljakotis.");
                }
            } else {
                System.out.println("Vigane sisend.");
            }
        }
    }
}
