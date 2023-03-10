import java.util.ArrayList;

public class NäidisTegelane implements Npc {
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

    public void vestle() {
        for(String vestlus: vestlused){
            System.out.println(vestlus);
        }
    }


}
