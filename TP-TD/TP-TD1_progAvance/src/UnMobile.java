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
    
    UnMobile(int telleLargeur, int telleHauteur)
    {
	super();
	saLargeur = telleLargeur;
	saHauteur = telleHauteur;
	sonTemps = sonTemps;
	setSize(telleLargeur, telleHauteur);
    }

    public void run()
    {
	Thread th = new Thread(this);
	for (sonDebDessin=0; sonDebDessin < saLargeur - 8*sonPas ; sonDebDessin+= sonPas)
	    {

		if (paused){
			th.suspend();
		}
			repaint();
		try{
			Thread.sleep((int) (Math.random()*75));
		}
		catch (InterruptedException telleExcp)
		    {telleExcp.printStackTrace();}
	    }

	for (sonDebDessin = saLargeur - sonCote; sonDebDessin > 0; sonDebDessin-= sonPas)
	{
		if (paused){
			th.suspend();
		}
		repaint();
		try{Thread.sleep((int) (Math.random()*75));}
		catch (InterruptedException telleExcp)
		{telleExcp.printStackTrace();}
	}
	}


    public void paintComponent(Graphics telCG)
    {
	super.paintComponent(telCG);
	telCG.fillRect(sonDebDessin, saHauteur/2, sonCote, sonCote);
    }

	public void suspend()
	{
		paused = true;
	}
	public void resume()
	{
		paused = false;
	}


}