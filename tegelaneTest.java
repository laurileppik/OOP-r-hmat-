import java.util.ArrayList;
import java.util.Arrays;

public class tegelaneTest {
    public static void main(String[] args) {
        ArrayList<String> tegelaseVestlused = new ArrayList<>(Arrays.asList("1. Kes sa oled?", "2. Mida sa tahad? ", "3. Kõnni ära."));
        Tegelane tegelane = new Tegelane("Peeter", tegelaseVestlused);

        Asi oda = new Asi("oda", "Terava otsaga pead torkama.");

        ArrayList<Asi> testlist = new ArrayList<>();
        testlist.add(oda);
        System.out.println(testlist.contains(oda));

    }
}
