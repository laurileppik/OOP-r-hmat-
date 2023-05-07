import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Mäng {
    private Ruum[] ruumid;

    public Ruum[] getRuumid() {
        return ruumid;
    }

    public static int käskeKokku = 0;

    public static int getKäskeKokku() {
        return käskeKokku;
    }

    public static void setKäskeKokku(int käskeKokku) {
        Mäng.käskeKokku = käskeKokku;
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

        ruumid[0] = new Ruum("Koobas", "Oled pimedas koopas.", asjad);
        ruumid[1] = new VihjegaRuum("Koopasuu", "Näed koopasuud.", koopasuuAsjad, "Äkki saad kellegagi siin ruumis suhelda.");
        ruumid[2] = new Ruum("Kaljuäär", "Näed kaljuäärt.", kaljuääreAsjad);
        ruumid[3] = new Ruum("Kalju", "Oled kalju ääre peal.", asjad);

        ruumid[4] = new Ruum("Kuristik", "Hüppasid kuristikku.", asjad);
        ruumid[5] = new Ruum("Kaljuäär", "Liigud mööda kaljuäärt.", asjad);
        ruumid[6] = new VihjegaRuum("Pime õõnsus", "Jõuad pimeda koopaõõnsuseni.", asjad, "Kuidas ma küll valgust saaksin.");
        ruumid[7] = new VihjegaRuum("Uks kauguses", "Näed kauguses ust. Tundub,et uksel on võtmeauk.", asjad, "Huvitav, mis küll sinna võtmeauku läheb.");
    }

    /**
     * Tekitab suurusega n*(n/2+2) suurusega ristküliku mänguks vajalike käskude jaoks.
     *
     * @param n Kasti suurus.
     */
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
            } else if (i == 9) {
                System.out.println(midaPrintidaAlgusEkraanile("vestle", n));
            } else
                System.out.println("*" + " ".repeat(n - 2) + "*");
        }
        System.out.println("*".repeat(n));
    }

    /**
     * Abifunktsioon keskmiste käskude väljastamiseks.
     *
     * @param sõna        Vastava käsu instruktsioonidesse lisamine (töötab hetkel ainult paarisarvulise suurusega sõnede korral).
     * @param kastiSuurus ´*dest kasti suurus.
     * @return Vastavalt reale õige vahega sõne.
     */
    public static String midaPrintidaAlgusEkraanile(String sõna, int kastiSuurus) {
        int sõnaPikkus = sõna.length();
        return "*" + " ".repeat((kastiSuurus - 2 - sõnaPikkus) / 2) + sõna + " ".repeat((kastiSuurus - 2 - sõnaPikkus) / 2) + "*";
    }

    public static void main(String[] args) throws IOException {
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
        boolean kasVõitsid = false;

        long algusAeg = System.nanoTime();
        while (mängKäib) {//siit algab while-loop, kus mäng käib

            //Võtab kasutaja sisendi ja teeb selle osadeks, kasutades lahutajana tühikut.
            String input = in.nextLine();
            String[] sõnad = input.split(" ");
            String käsk = sõnad[0];
            if (käsk.equalsIgnoreCase("jookse") || käsk.equalsIgnoreCase("põgene")) {
                //Tehtud juhuks kui keegi üritab põgeneda.
                System.out.println("Üritad joosta ning kukud ennast vigaseks. Sind ootab pikk ja piinarikas surm.");
                break;

                //Iga else ifi juures 1 käsk, mida mängija saab kasutada.
                //Juhul kui on võimalik, liigutakse edasi.
            } else if (käsk.equalsIgnoreCase("edasi")) {
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
                    } else if (hetkeneRuum == 1) {
                        System.out.println("Näed kedagi seismas");
                    }
                }

                //Juhul kui on võimalik, liigutakse tagasi.
            } else if (käsk.equalsIgnoreCase("tagasi")) {
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

                //Lisatud võimalus väga hea õnne korral varajaselt põgeneda.
            } else if (käsk.equalsIgnoreCase("libista")) {
                if (hetkeneRuum == 4) {
                    if ((int) (Math.random() * 1000) == 420) {
                        kasVõitsid = true;
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

                //Saad võtta mingi eseme.
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

                //Saad vaadata mingit juba seljakotis olevat asja.
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

                //Saad küsida vihjet kui antud ruumis vihje eksisteerib.
            } else if (käsk.equalsIgnoreCase("vihje")) {
                if (mäng.getRuumid()[hetkeneRuum] instanceof VihjegaRuum) {
                    System.out.println(((VihjegaRuum) mäng.getRuumid()[hetkeneRuum]).getVihje());
                } else
                    System.out.println("Siin pole sulle ühtegi vihjet anda.");

                //Saad tegelasega vestelda kui selline võimalus on ruumis olemas.
            } else if (käsk.equalsIgnoreCase("vestle")) {
                käskeKokku++;
                if (hetkeneRuum == 1) {
                    tegelane.vestle(mängija);
                } else {
                    System.out.println("Siin ei ole kellegagi vestelda.");
                }

                //Saad kasutada võtit pääsemiseks kui võimalik.
            } else if (käsk.equalsIgnoreCase("võti")) {
                if (hetkeneRuum == 7) {
                    System.out.println("Uks avanes, said koopast välja ning võitsid mängu!");
                    kasVõitsid = true;
                    break;
                } else {
                    System.out.println("Siin ei saa võtit kasutada.");
                }
            } else if (käsk.equalsIgnoreCase("e")) {
                break;
            } else {
                System.out.println("Vigane sisend.");
            }
            käskeKokku++;
        }

        long lõpuAeg = System.nanoTime();
        long kokkuAeg = lõpuAeg - algusAeg;

        ArrayList<String> statistikad = new ArrayList<>();
        statistikad.add("Tegid kokku " + käskeKokku + " käiku mängu lõpetamiseks.");
        statistikad.add("Aega läks " + TimeUnit.MINUTES.convert(kokkuAeg, TimeUnit.NANOSECONDS) + " minutit " + TimeUnit.SECONDS.convert(kokkuAeg, TimeUnit.NANOSECONDS) + " sekundit.");
        statistikad.add("Kogusid mängu jooksul " + mängija.getSeljakott().size() + " eset.");
        if (kasVõitsid)
            statistikad.add("Võitsid mängu.");
        else
            statistikad.add("Kaotasid mängu.");

        statistikaLisamine("statistika.txt", statistikad);
        Map<String, List<String>> statistikaFailist = loeStatistika("statistika.txt");

        //Väljastab mängija statistika mängus.
        for (String statistika: statistikad)
            System.out.println(statistika);

        //Leiame klassi BigDecimal abiga keskmise mängitud aja loetaval kujul.
        double keskmineMänguAeg=keskmineMänguaeg(statistikaFailist);
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(keskmineMänguAeg));
        int täisarv = bigDecimal.intValue();
        System.out.println("Kõigi mängijate keskmine mänguaeg on: " + täisarv + " minutit ja " + Double.parseDouble(bigDecimal.subtract(new BigDecimal(täisarv)).setScale(1, RoundingMode.HALF_UP).toPlainString())*60 + " sekundit.");
    }

    public static void statistikaLisamine(String fail, ArrayList<String> statistikad) throws IOException {
        String nimi;
        try (BufferedReader kasutajaSisend = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Sisesta oma nimi või kasutajanimi: ");
            nimi = kasutajaSisend.readLine();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fail, true))) {
            bw.write(nimi + ":");
            bw.newLine();
            for (String statistika : statistikad) {
                bw.write(statistika);
                bw.newLine();
            }
            bw.write("");
        }
    }

    private static Map<String, List<String>> loeStatistika(String fail) throws IOException {
        Map<String, List<String>> andmed = new HashMap<>();
        String nimi = null;
        int luger = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fail)))) {
            while (true) {
                String rida = bufferedReader.readLine();
                if (rida == null)
                    return andmed;
                luger++;
                if (luger == 1)
                    nimi = rida;
                try {if (andmed.get(nimi).size()>=5)
                    andmed.remove(nimi);} catch (Exception ignored) {}

                if (!andmed.containsKey(nimi))
                    andmed.put(nimi, new ArrayList<>());
                andmed.get(nimi).add(rida);
                if (luger == 5)
                    luger = 0;
            }
        }
    }

    private static double keskmineMänguaeg(Map<String, List<String>> andmed) {
        double manguaeg=0;
        for (List<String> tulemused:andmed.values()) {
            String aeg=tulemused.get(2);

            //Teen kaheks pooleks, sest vastasel juhul tekitaks programm ainult ühe numbri.
            String esimenePoolAjast=aeg.substring(0,18);
            String teinePoolAjast=aeg.substring(18);
            String minutid= esimenePoolAjast.replaceAll("[^0-9]", "");
            String sekundid= teinePoolAjast.replaceAll("[^0-9]", "");
            manguaeg+=Integer.parseInt(minutid) + (Double.parseDouble(sekundid)/60.0);
        }
        return manguaeg/andmed.size();
    }
}
