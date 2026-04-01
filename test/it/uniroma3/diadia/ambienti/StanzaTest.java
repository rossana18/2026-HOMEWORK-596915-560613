package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StanzaTest {

	 @Test
	    void testimpostaStanzaAdiacente() {
	       
		   Stanza stanza1 = new Stanza("stanza1");
	       Stanza stanza2 = new Stanza("stanza2");

	       stanza1.impostaStanzaAdiacente("nord", stanza2);
	        
	       assertEquals(stanza2, stanza1.getStanzaAdiacente("nord"));
	    }
	
	 @Test
	    void hasAttrezzo() {
	       
	        Stanza stanza1 = new Stanza("stanza1");
	       	       
	        Attrezzo chiave = new Attrezzo("chiave",3);
	        stanza1.addAttrezzo(chiave);

	        assertTrue(stanza1.hasAttrezzo("chiave"));
	 }
	 
	 @Test
	    void removeAttrezzo() {
	       
	        Stanza stanza1 = new Stanza("stanza1");
	       
	        Attrezzo chiave = new Attrezzo("chiave",3);
	        stanza1.addAttrezzo(chiave); 
	       
	        assertTrue(stanza1.removeAttrezzo("chiave"));
	 }
}
