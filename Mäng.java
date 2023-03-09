public class Mäng {
    private Ruum ruumid[];
    //Siia võiks erinevad ruumid kuidagi tekkida
    //ruumid[0]=new Ruum("Koobas","Oled pimedas koopas.");



    public static void main(String[] args) {


        Mängija mängija1=new Mängija(null);
        mängija1.vaataSeljakotti();

        Asi nuga=new Asi("oda","Terava otsaga pead torkama.");
        mängija1.võtaAsi(nuga);
        mängija1.vaataSeljakotti();
    }
}
