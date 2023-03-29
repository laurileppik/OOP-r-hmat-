import java.util.Arrays;
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
        Asi tõrvik = new Asi("tõrvik", "Ära ennast põlema pane.");

        ArrayList<Asi> asjad = new ArrayList<>();
        ArrayList<Asi> koopasuuAsjad = new ArrayList<>();
        ArrayList<Asi> kaljuääreAsjad = new ArrayList<>();

        koopasuuAsjad.add(oda);
        kaljuääreAsjad.add(tõrvik);

        ruumid = new Ruum[8];
        //Anname tühja arraylisti, sest muidu esimesse ruumi tagasi minnes ei tööta nii nagu peaks, vb peaks fixima.
        ruumid[0] = new Ruum("Koobas", "Oled pimedas koopas.", asjad);
        ruumid[1] = new Ruum("Koopasuu", "Näed koopasuud.", koopasuuAsjad);
        ruumid[2] = new Ruum("Kaljuäär", "Näed kaljuäärt.", kaljuääreAsjad);
        ruumid[3] = new Ruum("Kalju", "Oled kalju ääre peal.", asjad);

        ruumid[4] = new Ruum("Kuristik", "Hüppasid kuristikku.", asjad);
        ruumid[5] = new Ruum("Kaljuäär", "Liigud mööda kaljuäärt.", asjad);
        ruumid[6] = new vihjegaRuum("Pime õõnsus", "Jõuad pimeda koopaõõnsuseni.", asjad, "Kuidas ma küll valgust saaksin.");
        ruumid[7] = new Ruum("Uks kauguses", "Näed kauguses ust. Tundub,et uksel on võtmeauk.", asjad);
    }

    public static void algusEkraan(int n) {// funktsioon algusekraani väljaprintimiseks
        System.out.println("*".repeat(n));
        for (int i = 0; i < n / 2; i++) {
            if (i == 0)
                System.out.println(midaPrintidaAlgusEkraanile("VAJALIKUD KÄSUD:", n));
            else if (i == 2) {
                System.out.println(midaPrintidaAlgusEkraanile("võta _____", n));
            } else if (i == 3) {
                System.out.println(midaPrintidaAlgusEkraanile("vaata ____", n));
            } else if (i == 4) {
                System.out.println(midaPrintidaAlgusEkraanile("seljakott ", n));
            } else if (i == 5) {
                System.out.println(midaPrintidaAlgusEkraanile("edasi ", n));
            } else if (i == 6) {
                System.out.println(midaPrintidaAlgusEkraanile("tagasi", n));
            } else if (i == 7) {
                System.out.println(midaPrintidaAlgusEkraanile("libista ", n));
            } else if (i == 8) {
                System.out.println(midaPrintidaAlgusEkraanile("vihje ", n));
            } else
                System.out.println("*" + " ".repeat(n - 2) + "*");
        }
        System.out.println("*".repeat(n));
    }

    public static String midaPrintidaAlgusEkraanile(String sõna, int kastiSuurus) {
        int sõnaPikkus = sõna.length();
        return "*" + " ".repeat((kastiSuurus - 2 - sõnaPikkus) / 2) + sõna + " ".repeat((kastiSuurus - 2 - sõnaPikkus) / 2) + "*";
    }

    public static void main(String[] args) {
        //Loon vestluspartneri
        ArrayList<String> tegelaseVestlused = new ArrayList<>(Arrays.asList("1. Kes sa oled?", "2. Mida sa tahad? ", "3. Kõnni ära."));//tegelase loomine
        Tegelane tegelane = new Tegelane("Peeter", tegelaseVestlused);

        algusEkraan(24);
        Mäng mäng = new Mäng();
        Mängija mängija = new Mängija(mäng.getRuumid()[0]);

        Scanner in = new Scanner(System.in);

        System.out.println();
        System.out.println("Tere tulemast!");
        System.out.println(mängija.getAsukoht().getRuumiKirjeldus());
        boolean mängKäib = true;
        int hetkeneRuum = 0;

        while (mängKäib) {//siit algab while-loop, kus mäng käib
            String input = in.nextLine();
            String[] sõnad = input.split(" ");
            String käsk = sõnad[0];
            if (käsk.equalsIgnoreCase("jookse")) {
                System.out.println("Üritad joosta ning kukud ennast vigaseks. Sind ootab pikk ja piinarikas surm.");
                break;

            } else if (käsk.equalsIgnoreCase("l")) {
                if (hetkeneRuum == 6) {
                    if (mängija.kasSeljakotis("tõrvik")) {
                        mängija.liigu(mäng.getRuumid()[hetkeneRuum + 1]);
                        hetkeneRuum++;
                        System.out.println(mäng.getRuumid()[hetkeneRuum].toString());
                    } else {
                        System.out.println("Siin on liiga pime...");
                    }
                } else {
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
                    }else if(hetkeneRuum == 1){
                        System.out.println("Näed kedagi seismas");
                    }
                }
            } else if (käsk.equalsIgnoreCase("t")) {
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
                if (hetkeneRuum == 1) {
                    System.out.println("Näed kedagi seismas");
                }

            } else if (käsk.equalsIgnoreCase("seljakott")) {
                mängija.vaataSeljakotti();

            } else if (käsk.equalsIgnoreCase("libista")) {
                if (hetkeneRuum == 4) {
                    if ((int) (Math.random() * 1000) == 420) {
                        System.out.println("Libised sujuvalt kalju äärt mööda alla ning pääsed koopast.");
                    } else {
                        System.out.println("...");
                        try {
                            TimeUnit.SECONDS.sleep(2);
                            System.out.println("Kukud");
                            TimeUnit.SECONDS.sleep(2);
                            System.out.println("Ikka kukud");
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            System.out.println("Nüüd on hilja ennast päästa.");
                        }
                        System.out.println("Maa on päris valus.");
                        System.out.println("Mäng läbi, sa kaotasid.");
                    }
                    mängKäib = false;
                } else
                    System.out.println("Siin pole kuhugi libistada.");

            } else if (käsk.equalsIgnoreCase("võta")) {

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

            } else if (käsk.equalsIgnoreCase("vaata")) {
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

            } else if (käsk.equalsIgnoreCase("vihje")) {
                if (mäng.getRuumid()[hetkeneRuum] instanceof vihjegaRuum) {
                    System.out.println(((vihjegaRuum) mäng.getRuumid()[hetkeneRuum]).getVihje());
                } else
                    System.out.println("Siin pole sulle ühtegi vihjet anda.");
            } else if(käsk.equalsIgnoreCase("vestle")){// tegelasega vestlemine
                if(hetkeneRuum == 1){
                    tegelane.vestle(mängija);
                }else{
                    System.out.println("Siin ei ole kellegagi vestelda.");
                }
            }else if(käsk.equalsIgnoreCase("võti")){
                if(hetkeneRuum == 7){
                    System.out.println("Uks avanes, said koopast välja ning võitsid mängu!");
                    break;
                }else{
                    System.out.println("Siin ei saa võtit kasutada.");
                }
            }else {
                System.out.println("Vigane sisend.");
            }
        }
    }
}
