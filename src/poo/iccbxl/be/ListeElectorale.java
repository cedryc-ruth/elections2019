package poo.iccbxl.be;

import javax.xml.bind.Validator;

public class ListeElectorale {
	/**
	 * identifiant de la liste
	 */
	private int id;
	
	/**
	 * nom de la liste
	 */
	private String nom;
	
	/**
	 * nombre de voix obtenues
	 */
	private int voix;
	
	/**
	 * nombre de si�ges obtenus
	 */
	private int sieges;
	
	/**
	 * si la liste est �limin�e ou pas
	 */
	private boolean elimine;
	
	
	/**
	 * constructeur par d�faut
	 */
	public ListeElectorale() {
	}

	/**
	 * Constructeur de ListeElectorale
	 * 
	 * @param id identifiant de la liste
	 * @param nom nom de la liste
	 * @param voix nombre de voix obtenus
	 * @param sieges nombre de si�ges obtenus
	 * @param elimine si la liste est �limin�e ou pas
	 */
	public ListeElectorale(int id, String nom, int voix, int sieges, boolean elimine) {
		super();
		this.setId(id);
		this.setNom(nom);
		this.setVoix(voix);
		this.setSieges(sieges);
		this.elimine = elimine;
	}

	/**
	 * 
	 * @return identifiant de la liste
	 */
	public int getId() {
		return id;
	}

	/**
	 * Modifie l'identifiant de la liste
	 * @param id identifiant de la liste
	 */
	public void setId(int id) {
		if(id<1) {
			throw new ElectionsException("Valeur n�gative interdite!");
		}
		
		this.id = id;
	}

	/**
	 * 
	 * @return nom de la liste
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Modifie le nom de la liste
	 * @param nom nom de la liste
	 */
	public void setNom(String nom) {
		if(nom == null || nom.trim().equals("")) {
			throw new ElectionsException("Le nom ne peut pas �tre vide!");
		}
				
		this.nom = nom.trim();
	}

	/**
	 * 
	 * @return nombre de voix obtenus
	 */
	public int getVoix() {	
		return voix;
	}

	/**
	 * Modifie le nombre de voix obtenues
	 * @param voix nombre de voix obtenues
	 */
	public void setVoix(int voix) {
		if(voix<0) {
			throw new ElectionsException("Valeur n�gative interdite!");
		}

		this.voix = voix;
	}

	/**
	 * 
	 * @return nombre de si�ges obtenus
	 */
	public int getSieges() {
		return sieges;
	}

	/**
	 * Modifie le nombre de si�ges obtenus
	 * @param sieges nombre de si�ges obtenus
	 */
	public void setSieges(int sieges) {
		if(sieges<0) {
			throw new ElectionsException("Valeur n�gative interdite!");
		}
		
		this.sieges = sieges;
	}

	/**
	 * 
	 * @return si la liste est �limin�e ou pas
	 */
	public boolean isElimine() {
		return elimine;
	}

	/**
	 * Modifie l'�tat de la liste (�limin� ou pas)
	 * @param elimine �tat de la liste (�limin� ou pas)
	 */
	public void setElimine(boolean elimine) {
		this.elimine = elimine;
	}

	/**
	 * @return identit� de la liste
	 */
	@Override
	public String toString() {
		String tmp = "Liste A [1 - 32000 voix, 2 si�ges]";
		
		return "Liste "+nom+" [" + id + " - " 
			+ voix + " voix, "
			+ sieges + ( sieges>1 ?  " si�ges":" si�ge") 
			+ (elimine? " /ELIMINE":"")
			+"]";
	}
	
}
 