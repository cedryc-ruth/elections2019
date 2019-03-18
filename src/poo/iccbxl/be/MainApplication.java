package poo.iccbxl.be;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class MainApplication {

	public static void main(String[] args) {
	//== Lecture des données ====================
		
		//Lecture du nombre de sièges à pourvoir
		Scanner s = new Scanner(System.in);
		
		int nbSiegesAPourvoir = 0;
		
		while(nbSiegesAPourvoir == 0) {
			try {
				System.out.print("Nombre de sièges à pourvoir: (Tapez -1 pour quitter)");
				nbSiegesAPourvoir = s.nextInt(); s.nextLine();
				
				if(nbSiegesAPourvoir==-1) {
					System.out.println("Au revoir!");
					System.exit(0);
				}
				
				if(nbSiegesAPourvoir<0) {
					nbSiegesAPourvoir = 0;
					throw new InputMismatchException();
				}
			} catch (InputMismatchException e) {
				s.nextLine();	//Vider le tampon 
				//e.printStackTrace();
			}
		}
		
		System.out.println(nbSiegesAPourvoir);
		
		//Lecture du nombre de liste à définir
		int nbListes = 0;
		
		while(nbListes == 0) {
			try {
				System.out.print("Nombre de listes à définir: (Tapez -1 pour quitter)");
				nbListes = s.nextInt(); s.nextLine();
				
				if(nbListes==-1) {
					System.out.println("Au revoir!");
					System.exit(0);
				}
				
				if(nbListes<0) {
					nbListes = 0;
					throw new InputMismatchException();
				}
			} catch (InputMismatchException e) {
				s.nextLine();	//Vider le tampon 
				//e.printStackTrace();
			}
		}
		
		System.out.println(nbListes);
		
		//Lecture des listes (nom + voix obtenues)
		TreeMap<String,int[]> listes = new TreeMap<String, int[]>();
		
		String nomListe = null;
		int nbVoix = -1;
		int[] dataListe = {0,0,0}; 
		
		for(int i=0; i<nbListes ;i++) {
			while (nomListe == null) {
				try {
					System.out.print("Nom de la liste: ");
					nomListe = s.nextLine();
				} catch (InputMismatchException e) {
	
				} 
			}
			
			while(nbVoix < 0) {
				try {
					System.out.print("nombre de voix: ");
					nbVoix = s.nextInt(); s.nextLine();
				} catch (InputMismatchException e) {
					s.nextLine();
				}
			}
			
			dataListe[0] = nbVoix;
			
			listes.put(nomListe, dataListe);
			
			dataListe = new int[3];
			nomListe = null;
			nbVoix = -1;
		}
		
		//Définition d'un objet Mock
	/*	int[] dataListeA = { 32000, 0, 0 };
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
		*/
	
	//== Traitement des données ====================
			
		//Mocking du traitement
	/*	dataListeA[1] = 2;	//Attribution des dièges
		dataListeB[1] = 2;
		dataListeC[1] = 1;
		dataListeD[1] = 1;
		dataListeE[1] = 0;
		dataListeF[2] = 1;	//Liste éliminée
		dataListeG[2] = 1;	//Liste éliminée
	*/
		//Calcul du suffrage total
		int suffrageTotal = 0;
		
		//Élimination des listes moins populaires (Barre des 5%)
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
				listes.get(nom)[2] = 1;					//Liste éliminée
				suffrageEffectif -= listes.get(nom)[0];	//Retrait des voix
			} else {
				listesEffectives.put(nom, listes.get(nom));
			}
		}
		
		
		//Calcul du quotient électoral
		int quotientElectoral = (int) Math.floor(suffrageEffectif/(double)nbSiegesAPourvoir);
		int nbSiegesObtenus = 0;
		int nbSiegesRestant = nbSiegesAPourvoir;
		
		//Attribution des sièges sur base du quotient électoral
		Set<String> realKeys = listesEffectives.keySet();
		it = realKeys.iterator();
		
		while(it.hasNext()) {
			nom = (String) it.next();
			nbSiegesObtenus = listesEffectives.get(nom)[0]/quotientElectoral;
			listesEffectives.get(nom)[1] = nbSiegesObtenus;
			nbSiegesRestant -= nbSiegesObtenus;
		}
		
		//Répartition des restes
		int nbVoixListe = 0;
		int nbSiegesListe = 0;
		double moyenne = 0d;
		double moyenneMax = 0d;
		String listeCandidat = null;
		
		while(nbSiegesRestant>0){
			it = realKeys.iterator();
			
			while(it.hasNext()) {
				nom = (String) it.next();
				nbVoixListe = listesEffectives.get(nom)[0];
				nbSiegesListe = listesEffectives.get(nom)[1];
				moyenne = nbVoixListe/(nbSiegesListe+1);
				
				if(moyenne>moyenneMax) {
					moyenneMax = moyenne;
					listeCandidat = nom;
				}
			}
			
			//Attribution d'un siège restant
			listesEffectives.get(listeCandidat)[1] += 1;
			nbSiegesRestant--;
			
			moyenneMax = 0d;
			listeCandidat = null;
		}
		
		//Tri des listes sur base du nombre de sièges obtenus
		ArrayList<Map.Entry<String, int[]>> listesTriees = new ArrayList<Map.Entry<String, int[]>>();
		
		listesTriees.addAll(listes.entrySet());
		listesTriees.sort(new Comparator<Map.Entry<String, int[]>>() {
	
			public int compare(Entry<String, int[]> e1, Entry<String, int[]> e2) {
				if(e1.getValue()[1]>e2.getValue()[1]) {
					return -1;
				}
				
				if(e1.getValue()[1]<e2.getValue()[1]) {
					return 1;
				}
				
				return 0;
			}
		});
		
	//== Affichage du résultat ====================
		int nbSieges = 0;
		
		it = keys.iterator();
		
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