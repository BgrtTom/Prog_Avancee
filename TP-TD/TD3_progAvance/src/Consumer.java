public class Consumer extends Thread {
    private Moniteur chMoniteur;
    private String bufferContent;

    public Consumer(Moniteur chMoniteur) {
        this.chMoniteur = chMoniteur;
    }

    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                String lettre = "Lettre " + i;
                chMoniteur.retirer();
                Thread.sleep((int) (Math.random()*100));
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
