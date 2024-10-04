import java.io.*;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.lang.String;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SemaphoreTPBin sem = new SemaphoreTPBin(1);

		Affichage TA = new Affichage("AAAAAAA\n",sem);
		Affichage TB = new Affichage("BBBBB\n",sem);
		Affichage TC = new Affichage("CCC\n",sem);
		Affichage TD = new Affichage("DDDDDD\n",sem);

		TB.start();

		TA.start();

		TC.start();

		TD.start();
	}

}
