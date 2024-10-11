import java.awt.*;
import java.util.Random;
import java.util.random.RandomGenerator;
import javax.swing.*;

class UnMobile extends JPanel implements Runnable
{
    int saLargeur, saHauteur, sonDebDessin;
    final int sonPas = 10, sonCote=40;
	int sonTemps;
	boolean paused = false;
    SemaphoreGeneralTP sem;
    
    UnMobile(int telleLargeur, int telleHauteur, int telleTemps,SemaphoreGeneralTP parSem)
    {
	super();
	saLargeur = telleLargeur;
	saHauteur = telleHauteur;
	sonTemps = telleTemps;
    sem = parSem;
	setSize(telleLargeur, telleHauteur);
    }

    public void run()
    {
	Thread th = new Thread(this);
    while (true){
	for (sonDebDessin=0; sonDebDessin < saLargeur/3 ; sonDebDessin+= sonPas)
	    {
			repaint();
        try{Thread.sleep(sonTemps);}//(int) (Math.random()*75));}
		catch (InterruptedException telleExcp)
		    {telleExcp.printStackTrace();}
	    }

    sem.syncWait();

    for (sonDebDessin = saLargeur/3 ; sonDebDessin < 2*saLargeur/3; sonDebDessin+= sonPas)
        {
            repaint();
            try{Thread.sleep(sonTemps);}//(int) (Math.random()*75));}
            catch (InterruptedException telleExcp)
            {telleExcp.printStackTrace();}
        }
    sem.syncSignal();
    for (sonDebDessin = 2*saLargeur/3 ; sonDebDessin < saLargeur - sonCote; sonDebDessin+= sonPas)
        {
            repaint();
            try{Thread.sleep(sonTemps);}//(int) (Math.random()*75));}
            catch (InterruptedException telleExcp)
            {telleExcp.printStackTrace();}
        }


	for (sonDebDessin = saLargeur - sonCote; sonDebDessin > 2*saLargeur/3; sonDebDessin-= sonPas)
	{
		repaint();
        try{Thread.sleep(sonTemps);}//(int) (Math.random()*75));}
		catch (InterruptedException telleExcp)
		{telleExcp.printStackTrace();}
	}

        sem.syncWait();
    for (sonDebDessin = 2*saLargeur/3; sonDebDessin > saLargeur/3; sonDebDessin-= sonPas)
    {
        repaint();
        try{Thread.sleep(sonTemps);}//(int) (Math.random()*75));}
        catch (InterruptedException telleExcp)
        {telleExcp.printStackTrace();}
    }
    sem.syncSignal();

    for (sonDebDessin = saLargeur/3; sonDebDessin > 0; sonDebDessin-= sonPas)
    {
        repaint();
        try{Thread.sleep(sonTemps);}//(int) (Math.random()*75));}
        catch (InterruptedException telleExcp)
        {telleExcp.printStackTrace();}
    }
    }

	}


    public void paintComponent(Graphics elem) {
        super.paintComponent(elem);

        if (sonDebDessin >= (saLargeur / 3) && sonDebDessin <= (saLargeur / 3 * 2)) {
            elem.setColor(Color.RED);
        } else {
            elem.setColor(Color.LIGHT_GRAY);
        }

        elem.fillRect(sonDebDessin, saHauteur / 2, sonCote, sonCote);
    }
}




