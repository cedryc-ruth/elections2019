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

public class MainApplication2 {
	//TODO 1- remplacer le TreeMap par un ArrayList de ListeElectorale
	
	//TODO 2- convertir l'ArrayList de ListeElectorale en tableau statique

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
		ArrayList<ListeElectorale> listes = new ArrayList<ListeElectorale>();
		
		String nomListe = null;
		int nbVoix = -1;
		
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
			
			ListeElectorale le = new ListeElectorale(i+1, nomListe,nbVoix,0,false);
								
			listes.add(le);
			
			nomListe = null;
			nbVoix = -1;
		}	
	
	//== Traitement des données ====================
		
		//Calcul du suffrage total
		int suffrageTotal = 0;
		
		//Élimination des listes moins populaires (Barre des 5%)	
		Iterator<ListeElectorale> it = listes.iterator();
		ListeElectorale le;
		
		while(it.hasNext()) {
			le = it.next();
			suffrageTotal += le.getVoix();
		}
		
		int suffrageEffectif = suffrageTotal;
		ArrayList<ListeElectorale> listesEffectives = new ArrayList<ListeElectorale>();
	
		it = listes.iterator();
		
		while(it.hasNext()) {
			le = it.next();
			if(le.getVoix()/(double)suffrageTotal<0.05) {
				le.setElimine(true);					//Liste éliminée
				suffrageEffectif -= le.getVoix();	//Retrait des voix
			} else {
				listesEffectives.add(le);
			}
		}
		
		
		//Calcul du quotient électoral
		int quotientElectoral = (int) Math.floor(suffrageEffectif/(double)nbSiegesAPourvoir);
		int nbSiegesObtenus = 0;
		int nbSiegesRestant = nbSiegesAPourvoir;
		
		//Attribution des sièges sur base du quotient électoral
		it = listesEffectives.iterator();
		
		while(it.hasNext()) {
			le = it.next();
			nbSiegesObtenus = le.getVoix()/quotientElectoral;
			le.setSieges(nbSiegesObtenus);
			nbSiegesRestant -= nbSiegesObtenus;
		}
		
		//Répartition des restes
		int nbVoixListe = 0;
		int nbSiegesListe = 0;
		double moyenne = 0d;
		double moyenneMax = 0d;
		ListeElectorale listeCandidat = null;
		
		while(nbSiegesRestant>0){
			it = listesEffectives.iterator();
			
			while(it.hasNext()) {
				le = it.next();
				nbVoixListe = le.getVoix();
				nbSiegesListe = le.getSieges();
				moyenne = nbVoixListe/(nbSiegesListe+1);
				
				if(moyenne>moyenneMax) {
					moyenneMax = moyenne;
					listeCandidat = le;
				}
			}
			
			//Attribution d'un siège restant
			listeCandidat.setSieges(listeCandidat.getSieges()+1);;
			nbSiegesRestant--;
			
			moyenneMax = 0d;
			listeCandidat = null;
		}
		
		//Tri des listes sur base du nombre de sièges obtenus
		ArrayList<ListeElectorale> listesTriees = new ArrayList<ListeElectorale>();
		
		listesTriees.addAll(listes);
		listesTriees.sort(new Comparator<ListeElectorale>() {
	
			public int compare(ListeElectorale le1, ListeElectorale le2) {
				if(le1.getSieges()>le2.getSieges()) {
					return -1;
				}
				
				if(le1.getSieges()<le2.getSieges()) {
					return 1;
				}
				
				return 0;
			}
		});
		
	//== Affichage du résultat ====================
		int nbSieges = 0;
		String nom = null;
		it = listes.iterator();
		
		while(it.hasNext()) {
			le = it.next();
			nom = le.getNom();
			nbSieges = le.getSieges();
		
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
