/**
 * 
 */
import java.io.*;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.lang.String;

class Exclusion{};
public class Affichage extends Thread{
	String texte;
	static Exclusion exclusionImpression = new Exclusion();
	SemaphoreTPBin sem;

	public Affichage (String txt, SemaphoreTPBin parSem){
		texte=txt;
		sem = parSem;
	}

	public void run(){
		/*
		synchronized (System.out){
		for  (int i=0; i<texte.length(); i++){
		    System.out.print(texte.charAt(i));
		    try {sleep(100);} catch(InterruptedException e){};
		}}*/


		sem.syncWait();
		for  (int i=0; i<texte.length(); i++){
			System.out.print(texte.charAt(i));
			try {sleep(100);} catch(InterruptedException e){};
		}
		sem.syncSignal();

	}



}
