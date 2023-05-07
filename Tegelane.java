import java.util.ArrayList;
import java.util.Scanner;

public class Tegelane {//Tegelase klass, millega luuakse tegelane, kellega saab vestelda
    private String nimi;
    private ArrayList<String> vestlused;

    public String getNimi() {
        return nimi;
    }

    public ArrayList<String> getVestlused() {
        return vestlused;
    }

    public void setVestlused(ArrayList<String> vestlused) {
        this.vestlused = vestlused;
    }

    public Tegelane(String nimi, ArrayList vestlused) {
        this.nimi = nimi;
        this.vestlused = vestlused;
    }

    /** Meetod, mis kutsutakse välja, kui mängija on tegelasega samas ruumis ja soovib vestelda.
     *
     * @param mängija Mängija isend, kellega hakatakse vastavalt sisendile tegevusi tegema.
     */
    public void vestle(Mängija mängija) {
        ArrayList<Asi> tempSeljakott = mängija.getSeljakott();
        Scanner sc = new Scanner(System.in);
        Asi võti = new Asi("võti", "Sellega saab midagi avada.");
        while (true) {
            for (String vestlus : vestlused) {
                System.out.println(vestlus);
            }
            System.out.println("(Sisesta vastav number)");

            try {
                int sisend = Integer.parseInt(sc.nextLine());
                System.out.println(sisend);
                if (sisend > vestlused.size() && sisend < 1) {
                    System.out.println("Sisesta arv väärtusega 1 kuni " + vestlused.size() + ".");
                } else if (sisend == 1) {
                    System.out.println("Mu nimi on " + nimi + ".");
                } else if (sisend == 2) {
                    System.out.println("Vasta mu mõistatusele ja annan sulle võtme.");
                    vestlused.add("4. Küsi mõistatust.");
                } else if (sisend == 3) {
                    System.out.println("Head aega!");
                    break;
                } else if (sisend == 4) {
                    Mäng.setKäskeKokku(Mäng.getKäskeKokku()+1);
                    System.out.println("Silmad paneb nutma, aga südant ei liiguta.");
                    String vastus = sc.nextLine();
                    if (vastus.equalsIgnoreCase("sibul")) {
                        System.out.println("Õige vastus, siin on sulle võti.");
                        System.out.println("(Võtit saab nüüd kusagil kasutada kirjutades \"võti\")");
                        tempSeljakott.add(võti);
                        vestlused.remove(3);
                        vestlused.remove(1);
                        mängija.setSeljakott(tempSeljakott);
                    } else {
                        System.out.println("Vale vastus!");
                    }
                } else {
                    System.out.println("VALI NUMBER 1 KUNI " + vestlused.size() + ".");
                }
            } catch (Exception e) {
                System.out.println("Vale sisend.");
            }
            Mäng.setKäskeKokku(Mäng.getKäskeKokku()+1);
        }
    }
}
