public class Moniteur
{
    private String lettre;
    private boolean available;

    public Moniteur() {
        lettre = null;
        available = false;
    }

    public synchronized void deposer(String parlettre) throws InterruptedException {
        if (available){
            System.out.println("la boite au lettre est pleine");
            wait();
        }

        lettre = parlettre;
        available = true;
        System.out.println("il depose : " + lettre);
        notifyAll();




    }
    public synchronized void retirer() throws InterruptedException {
        if (!available) {
            System.out.println("la boite au lettre est vide");
            wait();
        }
        System.out.println("il retirer : " + lettre);
        lettre = "";
        available = false;
        notifyAll();
    }


}