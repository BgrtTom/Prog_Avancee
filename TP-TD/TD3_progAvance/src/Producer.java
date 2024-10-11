public class Producer extends Thread {
    private Moniteur chMoniteur;
    private String bufferContent;

    public Producer(Moniteur chMoniteur) {
        this.chMoniteur = chMoniteur;
    }

    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                String lettre = "Lettre " + i;
                chMoniteur.deposer(lettre);
                Thread.sleep((int) (Math.random()*100));
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}