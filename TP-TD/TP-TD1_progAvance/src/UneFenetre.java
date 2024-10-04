import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

class UneFenetre extends JFrame 
{
    UnMobile TacheMobile;
    private final int LARG=1000, HAUT=700;

    public UneFenetre(int nbMobile)
    {

        Container leConteneur = getContentPane();
        leConteneur.setLayout (new GridLayout(nbMobile, 1));

        JFrame frame = new JFrame("Une Fenetre");
        frame.setSize(LARG, HAUT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        for (int i = 0; i < nbMobile; i++) {
            TacheMobile = new UnMobile(LARG,HAUT/nbMobile);
            leConteneur.add(TacheMobile);

            Thread SupportTache = new Thread (TacheMobile);
            SupportTache.start();
        }


        //JButton button= new JButton ("Start/Stop");
        //frame.add(button, BorderLayout.WEST);
        frame.add(leConteneur);





        /*
        button.addActionListener(e -> {
            if(button.getText().equals("Start/Stop")) {
                button.setText("Start/Stop");
                TacheMobile.suspend();
            } else {
                button.setText("Start/Stop");
                TacheMobile.resume();
            }
        });*/

        frame.setVisible(true);



	// TODO 
	// ajouter sonMobile a la fenetre
	// creer une thread laThread avec sonMobile
	// afficher la fenetre
	// lancer laThread 
    }
}
