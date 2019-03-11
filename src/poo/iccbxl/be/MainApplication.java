package poo.iccbxl.be;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class MainApplication {

	public static void main(String[] args) {
		//Lecture des données
	
		//Définition d'un objet Mock
	int[] dataListeA = { 32000, 0, 0 };
	int[] dataListeB = { 25000, 0, 0 };
	int[] dataListeC = { 16000, 0, 0 };
	int[] dataListeD = { 12500, 0, 0 };
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
	

		//Traitement des données (Méthode de calcul)
		
		
		//Affichage du résultat
	String nom;
	int nbSieges = 0;
	
	Set<String> keys = listes.keySet();
	
	Iterator it = keys.iterator();
	
	while(it.hasNext()) {
		nom = (String) it.next();
		nbSieges = listes.get(nom)[1];
	
		System.out.println("La liste "
				+nom
				+" a obtenu "
				+nbSieges+" "
				+((nbSieges>1)?"sièges":"siège")
				+"."
		);
	}
	
		
		

	}

}
