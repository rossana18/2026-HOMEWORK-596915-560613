package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LabirintoTest {

	Labirinto labir = new Labirinto();
	
	@Test
	public void testStanzaIniziale() {
		
		assertEquals("Atrio", labir.getStanzaIniziale().getNome());
	}
	
	@Test
	public void testStanzaFinale() {
		
		assertEquals("Biblioteca", labir.getStanzaFinale().getNome());
	}

	@Test
	public void testCollegamentoBiblioteca() {
		
	assertEquals("Biblioteca", 
		    labir.getStanzaIniziale().getStanzaAdiacente("nord").getNome());
	}
	
	@Test
	public void testCollegamentoLabBiblioteca() {
		
	assertEquals("Aula N11", 
		    labir.getStanzaIniziale().getStanzaAdiacente("ovest").getStanzaAdiacente("ovest").getNome());
	}
}
