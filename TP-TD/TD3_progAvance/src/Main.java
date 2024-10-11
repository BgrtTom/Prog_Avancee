public class Main {
    public static void main(String[] args) {
        Moniteur moniteur = new Moniteur();
        Thread prodMoniteur = new Thread(new Producer(moniteur));
        Thread consMoniteur = new Thread(new Consumer(moniteur));

        prodMoniteur.start();
        consMoniteur.start();
    }
}