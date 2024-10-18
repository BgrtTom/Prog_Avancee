import java.util.Random;

public  class Main{
    public static void main(String[] args) {

        // on initialise une boulangerie, et une variable aléatoire pour nos client
        final Boulangerie boulangerie =  new Boulangerie() ;
        final Random rand =  new Random() ;

        // notre boulanger est un runnable
        Boulanger boulanger =  new Boulanger(boulangerie) ;

        // notre mangeur est aussi un runnable
        Mangeur mangeur =  new Mangeur(boulangerie,rand) ;

        Thread [] boulangers =  new Thread[5] ;
        Thread [] mangeurs =  new Thread[2] ;

        // préparation des boulangers
        for (int i =  0 ; i < boulangers.length ; i++) {
            boulangers[i] =  new Thread(boulanger) ;
        }

        // préparation des mangeurs
        for (int i =  0 ; i < mangeurs.length ; i++) {
            mangeurs[i] =  new Thread(mangeur) ;
        }

        // lancement des boulangers
        for (int i =  0 ; i < boulangers.length ; i++) {
            boulangers[i].start() ;
        }

        // lancement des mangeurs
        for (int i =  0 ; i < mangeurs.length ; i++) {
            mangeurs[i].start() ;
        }
    }
}