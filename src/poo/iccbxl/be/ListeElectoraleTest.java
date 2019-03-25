package poo.iccbxl.be;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ListeElectoraleTest {

	private ListeElectorale l1;

	@Before
	public void setUp() throws Exception {
		l1 = new ListeElectorale(1,"A",32000,2,false);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testListeElectoraleIntStringIntIntBoolean() {	
		assertNotNull(l1);
		
		assertEquals(1, l1.getId());
		assertEquals("A", l1.getNom());
		assertEquals(32000, l1.getVoix());
		assertEquals(2, l1.getSieges());
		
		assertFalse(l1.isElimine());
	}

	@Test
	public void testSetId() {
		assertEquals(1, l1.getId());
		
		l1.setId(5);
		
		assertEquals(5, l1.getId());
		
		boolean erreur = false;
		
		try {
			l1.setId(0);
		} catch(ElectionsException e) {
			erreur = true;
			assertEquals(5, l1.getId());
			assertEquals("Valeur négative interdite!", e.getMessage());
		}
		
		assertTrue(erreur);
		
		erreur = false;
		try {
			l1.setId(-7);
		} catch(ElectionsException e) {
			erreur = true;
			assertEquals(5, l1.getId());
			assertEquals("Valeur négative interdite!", e.getMessage());
		}
		
	}

	@Test
	public void testSetNom() {
		//Cas normal 1
		assertEquals("A", l1.getNom());
		
		l1.setNom("B");
		
		assertEquals("B", l1.getNom());
		
		//Cas normal 2: nom avec plusieurs caractères
		l1.setNom("Bleu");
		
		assertEquals("Bleu", l1.getNom());
		
		//Cas normal 3: espaces intérieurs autorisés
		l1.setNom("Bleu clair");
		
		assertEquals("Bleu clair", l1.getNom());
		
		//Cas normal 4: effacement des espaces avant et après
		l1.setNom("  Bleu   ");
		
		assertEquals("Bleu", l1.getNom());
		
		//Cas d'erreurs
		l1.setNom("B");
		boolean erreur = false;
		
		try {
			l1.setNom(null);
		} catch(ElectionsException e) {
			erreur = true;
			assertEquals("B", l1.getNom());
			assertEquals("Le nom ne peut pas être vide!", e.getMessage());
		}
		
		assertTrue(erreur);
		
		//
		erreur = false;
		
		try {
			l1.setNom("");
		} catch(ElectionsException e) {
			erreur = true;
			assertEquals("B", l1.getNom());
			assertEquals("Le nom ne peut pas être vide!", e.getMessage());
		}
		
		assertTrue(erreur);
		
		//
		erreur = false;
		
		try {
			l1.setNom("   ");
		} catch(ElectionsException e) {
			erreur = true;
			assertEquals("B", l1.getNom());
			assertEquals("Le nom ne peut pas être vide!", e.getMessage());
		}
		
		assertTrue(erreur);
	}

	@Test
	public void testSetVoix() {
		assertEquals(32000, l1.getVoix());
		
		l1.setVoix(12000);
		
		assertEquals(12000, l1.getVoix());
		
		boolean erreur = false;
		
		try {
			l1.setVoix(-1500);
		} catch(ElectionsException e) {
			erreur = true;
			assertEquals(12000, l1.getVoix());
			assertEquals("Valeur négative interdite!", e.getMessage());
		}
		
		assertTrue(erreur);
	}

	@Test
	public void testSetSieges() {
		assertEquals(2, l1.getSieges());
		
		l1.setSieges(5);
		
		assertEquals(5, l1.getSieges());
		
		boolean erreur = false;
		
		try {
			l1.setSieges(-3);
		} catch(ElectionsException e) {
			erreur = true;
			assertEquals(5, l1.getSieges());
			assertEquals("Valeur négative interdite!", e.getMessage());
		}
		
		assertTrue(erreur);
	}

	@Test
	public void testToString() {
		String tmp = "Liste A [1 - 32000 voix, 2 sièges]";
		
		assertEquals(tmp, l1.toString());
		
		//
		ListeElectorale l2 = new ListeElectorale(2,"B",500,0,true);
			
		tmp = "Liste B [2 - 500 voix, 0 siège /ELIMINE]";
		
		assertEquals(tmp, l2.toString());
		
		//
		l2 = new ListeElectorale(2,"B",500,1,true);
			
		tmp = "Liste B [2 - 500 voix, 1 siège /ELIMINE]";
		
		assertEquals(tmp, l2.toString());
	}

}






