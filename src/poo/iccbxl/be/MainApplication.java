package poo.iccbxl.be;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class MainApplication {

	public static void main(String[] args) {
		//Lecture des donn�es
	
		//D�finition d'un objet Mock
	int[] dataListeA = { 32000, 0, 0 };
	int[] dataListeB = { 25000, 0, 0 };
	int[] dataListeC = { 16000, 0, 0 };
	int[] dataListeD = { 12000, 0, 0 };
	int[] dataListeE = { 8000, 0, 0 };
	int[] dataListeF = { 4500, 0, 0 };
	int[] dataListeG = { 2500, 0, 0 };
	
	TreeMap<String,int[]> listes = new TreeMap<String, int[]>();
	
	listes.put("A", dataListeA);
	listes.put("B", dataListeB);
	listes.put("C", dataListeC);
	listes.put("D", dataListeD);
	listes.put("E", dataListeE);
	listes.put("F", dataListeF);
	listes.put("G", dataListeG);
	
	int nbSiegesAPourvoir = 6;

		//Traitement des donn�es (M�thode de calcul)
		
	//Mocking du traitement
/*	dataListeA[1] = 2;	//Attribution des di�ges
	dataListeB[1] = 2;
	dataListeC[1] = 1;
	dataListeD[1] = 1;
	dataListeE[1] = 0;
	dataListeF[2] = 1;	//Liste �limin�e
	dataListeG[2] = 1;	//Liste �limin�e
*/
	//Calcul du suffrage total
	int suffrageTotal = 0;
	
	//�limination des listes moins populaires (Barre des 5%)
	Set<String> keys = listes.keySet();
	
	Iterator it = keys.iterator();
	String nom;
	
	while(it.hasNext()) {
		nom = (String) it.next();
		suffrageTotal += listes.get(nom)[0];
	}
	
	int suffrageEffectif = suffrageTotal;
	TreeMap<String,int[]> listesEffectives = new TreeMap<String, int[]>();

	it = keys.iterator();
	
	while(it.hasNext()) {
		nom = (String) it.next();
		if(listes.get(nom)[0]/(double)suffrageTotal<0.05) {
			listes.get(nom)[2] = 1;					//Liste �limin�e
			suffrageEffectif -= listes.get(nom)[0];	//Retrait des voix
		} else {
			listesEffectives.put(nom, listes.get(nom));
		}
	}
	
	
	//Calcul du quotient �lectoral
	int quotientElectoral = (int) Math.floor(suffrageEffectif/(double)nbSiegesAPourvoir);
	
	//Attribution des si�ges sur base du quotient �lectoral
	Set<String> realKeys = listesEffectives.keySet();
	it = realKeys.iterator();
	
	while(it.hasNext()) {
		nom = (String) it.next();
		listesEffectives.get(nom)[1] = listesEffectives.get(nom)[0]/quotientElectoral;	
	}
	
		//Affichage du r�sultat
	int nbSieges = 0;
	
	it = keys.iterator();
	
	while(it.hasNext()) {
		nom = (String) it.next();
		nbSieges = listes.get(nom)[1];
	
		System.out.println("La liste "
				+nom
				+" a obtenu "
				+nbSieges+" "
				+((nbSieges>1)?"si�ges":"si�ge")
				+"."
		);
	}
	
		
		

	}

}
