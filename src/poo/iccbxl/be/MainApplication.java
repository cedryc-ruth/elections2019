package poo.iccbxl.be;

import java.util.TreeMap;

public class MainApplication {

	public static void main(String[] args) {
		//Lecture des donn�es
	
		//D�finition d'un objet Mock
	int[] dataListeA = { 32000, 2, 0 };
	int[] dataListeB = { 25000, 2, 0 };
	int[] dataListeC = { 16000, 1, 0 };
	int[] dataListeD = { 12500, 1, 0 };
	int[] dataListeE = { 8000, 0, 0 };
	int[] dataListeF = { 4500, 0, 1 };
	int[] dataListeG = { 2500, 0, 1 };
	
	TreeMap<String,int[]> listes = new TreeMap<String, int[]>();
	
	listes.put("A", dataListeA);
	listes.put("B", dataListeB);
	listes.put("C", dataListeC);
	listes.put("D", dataListeD);
	listes.put("E", dataListeE);
	listes.put("F", dataListeF);
	listes.put("G", dataListeG);
	

		//Traitement des donn�es (M�thode de calcul)
		
		
		//Affichage du r�sultat
		
		

	}

}