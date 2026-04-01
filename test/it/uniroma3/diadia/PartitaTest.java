package it.uniroma3.diadia;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.ambienti.Stanza;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



class PartitaTest {

	@Test
	void testVinta() {
		IOConsole inOut= new IOConsole();
		Partita p =new Partita(inOut);
		Stanza stanzaCorrente = new Stanza("Biblioteca");
      
        assertEquals(stanzaCorrente.getNome(), p.getNomeStanzaVincente());
	}

	@Test
	void testPartitaFinitaSovraccarico() {
		IOConsole inOut= new IOConsole();
		Partita p = new Partita(inOut);
		p.setFinita(true);
		
		assertTrue(p.isFinita());	
	}
	
	@Test
	void testPartitaFinita() {
		IOConsole inOut= new IOConsole();
		Partita p = new Partita(inOut);
		p.setFinita();
		
		assertTrue(p.isFinita());
		//assertFalse(p.isFinita());  //NO è sempre true per definizione
	}
	
	
	@Test
	void testPartitaCfu() {
		IOConsole inOut= new IOConsole();
		Partita p = new Partita(inOut);
		Giocatore g= new Giocatore("Alice");
		p.setGiocatore(g);
		g.setCfu(0);
		
		assertTrue(p.isFinita());
	}
	
	@Test
	void testPartitaVinta() {
		IOConsole inOut= new IOConsole();
		Partita p = new Partita(inOut);
		Stanza vincente = p.getStanzaVincente(); // deve restituire la stanza
        p.setStanzaCorrente(vincente);
        
        assertTrue(p.isFinita());
	}
	
	

	
}
