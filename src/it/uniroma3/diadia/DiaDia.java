package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;

import java.util.Scanner;






/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};

	private Partita partita;

	public DiaDia(IOConsole inOut) {
		this.partita = new Partita(inOut);
	}

	public void gioca(IOConsole inOut) {
		String istruzione; 
		Scanner scannerDiLinee;

		//System.out.println(MESSAGGIO_BENVENUTO);
		inOut.mostraMessaggio(MESSAGGIO_BENVENUTO);
		scannerDiLinee = new Scanner(System.in);		
		do		
			istruzione = scannerDiLinee.nextLine();
		while (!processaIstruzione(istruzione, inOut));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione, IOConsole inOut) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro(), inOut);
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto(inOut);
		
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		
		else if (comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		
		else
			inOut.mostraMessaggio("Comando sconosciuto"); //**************
			//System.out.println("Comando sconosciuto");
		
		if (this.partita.vinta()) {
			inOut.mostraMessaggio("Hai vinto!");  //*******************
			//System.out.println("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto(IOConsole inOut) {
		for(int i=0; i< elencoComandi.length; i++) {
			inOut.mostraMessaggio(elencoComandi[i]+ " ");  //******************
		}
		
		//System.out.print(elencoComandi[i]+" ");
		//System.out.println();
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione, IOConsole inOut) {
		if(direzione==null)
			System.out.println("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			System.out.println("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			
			Giocatore giocatore=this.partita.getGiocatore();
			int cfu= giocatore.getCfu();   //ore.getCfu() - 1);
			this.partita.getGiocatore().setCfu(--cfu);     //MODIFICATO da me(cfu--)
			//int cfu = this.partita.getCfu();
		}
		
		inOut.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		//System.out.println(partita.getStanzaCorrente().getDescrizione());
	}

	/**
	 * prende un attrezzo dalla stanza corrente e lo aggiunge alla borsa del giocatore:
	 * Recupera la stanza corrente, Cerca l’attrezzo nella stanza
	 * se esiste: lo rimuove dalla stanza e lo aggiunge alla borsa
	 * altrimenti  messaggio di errore
	 * @param nomeAttrezzo
	 */
	private void prendi (String nomeAttrezzo) {
		if(nomeAttrezzo==null)
			System.out.println("Quale attrezzo vuoi prendere?");
		Stanza corrente = this.partita.getStanzaCorrente();  
		if(corrente!=null) 
			if(corrente.hasAttrezzo(nomeAttrezzo)) {  
				Attrezzo a = new Attrezzo(nomeAttrezzo, corrente.getAttrezzo(nomeAttrezzo).getPeso());
				System.out.println(a);
				corrente.removeAttrezzo(nomeAttrezzo);
				
				this.partita.getGiocatore().getBorsa().addAttrezzo(a);
			}
			else {
				System.out.println("Attrezzo inesistente");
			}
	}
	/**
	 * Rimuove un attrezzo dalla borsa del giocatore e lo aggiunge alla stanza:
	 * 	Recupera la borsa, cerca l’attrezzo nella borsa
	 * se esiste: lo rimuove dalla borsa e lo aggiunge alla stanza corrente
	 * altrimenti  messaggio di errore
	 * @param nomeAttrezzo
	 */
	private void posa (String nomeAttrezzo) {
		if(nomeAttrezzo==null)
			System.out.println("Quale attrezzo vuoi posare?");
		Borsa borsaCorrente = this.partita.getGiocatore().getBorsa();
		if(borsaCorrente!=null) 
			if(borsaCorrente.hasAttrezzo(nomeAttrezzo)) {
	            Attrezzo a = new Attrezzo(nomeAttrezzo, borsaCorrente.getAttrezzo(nomeAttrezzo).getPeso());
				borsaCorrente.removeAttrezzo(nomeAttrezzo);
				
				this.partita.getStanzaCorrente().addAttrezzo(a);
			}
			else {
				System.out.println("Attrezzo inesistente");
			}
			
	}
			
	/**
	 * Comando "Fine".
	 */
	private void fine() {
		System.out.println("Grazie di aver giocato!");  // si desidera smettere
	}

	
	public static void main(String[] argc) {
		
		IOConsole inOut = new IOConsole();  //************************
		
		DiaDia gioco = new DiaDia(inOut);
		gioco.gioca(inOut);
	}
}