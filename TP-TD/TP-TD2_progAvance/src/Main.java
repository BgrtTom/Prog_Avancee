import java.io.*;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.lang.String;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Affichage TA = new Affichage("AAAAAAA ");
		Affichage TB = new Affichage("BBBBB ");
		Affichage TC = new Affichage("CCC ");
		Affichage TD = new Affichage("DDDDDD ");

		TB.start();

		TA.start();

		TC.start();

		TD.start();
	}

}
