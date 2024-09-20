import java.awt.*;
import javax.swing.*;

class UneFenetre extends JFrame 
{
    UnMobile TacheMobile;
    private final int LARG=800, HAUT=500;

    public UneFenetre()
    {
        JFrame frame = new JFrame("Une Fenetre");
        frame.setSize(LARG, HAUT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        TacheMobile = new UnMobile(LARG,HAUT);
        frame.add(TacheMobile);
        Thread SupportTache = new Thread (TacheMobile);

        SupportTache.start();
	// TODO 
	// ajouter sonMobile a la fenetre
	// creer une thread laThread avec sonMobile
	// afficher la fenetre
	// lancer laThread 
    }
}
