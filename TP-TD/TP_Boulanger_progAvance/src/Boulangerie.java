import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public  class Boulangerie {

    // plus prosaïquement, une boulangerie est une file d'attente de 20 cases
    private BlockingQueue<Pain> queue =  new ArrayBlockingQueue<Pain>(20) ;

    // on peut y déposer du pain, mais le boulanger n'est pas patient
    // si le panier de vente est plein, il s'en va
    public  boolean depose(Pain pain)  throws InterruptedException {
        return queue.offer(pain,  200, TimeUnit.MILLISECONDS) ;
    }

    // on peut en acheter, et le client n'est pas plus patient
    // que le boulanger
    public Pain achete ()  throws InterruptedException {
        return queue.poll(200, TimeUnit.MILLISECONDS) ;
    }

    // on peut interroger le stock
    public  int getStock() {
        return queue.size() ;
    }
}