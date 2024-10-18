import java.util.Random;

public  class Mangeur  implements Runnable {
    Boulangerie boulangerie;
    Random rand;

    public Mangeur(Boulangerie parBoulangerie, Random parRand) {
        this.boulangerie = parBoulangerie;
        this.rand = parRand;
    }

    public  void run() {

        try {

            while (true) {

                Thread.sleep(this.rand.nextInt(3000)) ;
                Pain pain = boulangerie.achete() ;
                if (pain != null) {
                    System.out.println("[" + Thread.currentThread().getName() +  "]" +
                            "[" + boulangerie.getStock() +  "] miam miam") ;
                }  else {
                    System.out.println("[" + Thread.currentThread().getName() +  "]" +
                            "[" + boulangerie.getStock() +  "] j'ai faim") ;
                }
            }

        }  catch (InterruptedException e) {
            System.out.println("[" + Thread.currentThread().getName() +  "] je m'arrête") ;
        }
    }
}
