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
	 * nombre de sièges obtenus
	 */
	private int sieges;
	
	/**
	 * si la liste est éliminée ou pas
	 */
	private boolean elimine;
	
	
	/**
	 * constructeur par défaut
	 */
	public ListeElectorale() {
	}

	/**
	 * Constructeur de ListeElectorale
	 * 
	 * @param id identifiant de la liste
	 * @param nom nom de la liste
	 * @param voix nombre de voix obtenus
	 * @param sieges nombre de sièges obtenus
	 * @param elimine si la liste est éliminée ou pas
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
			throw new ElectionsException("Valeur négative interdite!");
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
			throw new ElectionsException("Le nom ne peut pas être vide!");
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
			throw new ElectionsException("Valeur négative interdite!");
		}

		this.voix = voix;
	}

	/**
	 * 
	 * @return nombre de sièges obtenus
	 */
	public int getSieges() {
		return sieges;
	}

	/**
	 * Modifie le nombre de sièges obtenus
	 * @param sieges nombre de sièges obtenus
	 */
	public void setSieges(int sieges) {
		if(sieges<0) {
			throw new ElectionsException("Valeur négative interdite!");
		}
		
		this.sieges = sieges;
	}

	/**
	 * 
	 * @return si la liste est éliminée ou pas
	 */
	public boolean isElimine() {
		return elimine;
	}

	/**
	 * Modifie l'état de la liste (éliminé ou pas)
	 * @param elimine état de la liste (éliminé ou pas)
	 */
	public void setElimine(boolean elimine) {
		this.elimine = elimine;
	}

	/**
	 * @return identité de la liste
	 */
	@Override
	public String toString() {
		String tmp = "Liste A [1 - 32000 voix, 2 sièges]";
		
		return "Liste "+nom+" [" + id + " - " 
			+ voix + " voix, "
			+ sieges + ( sieges>1 ?  " sièges":" siège") 
			+ (elimine? " /ELIMINE":"")
			+"]";
	}
	
}
 