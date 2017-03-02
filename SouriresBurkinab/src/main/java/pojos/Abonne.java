package pojos;

public class Abonne {
	private int idAbo;
	private String nom;
	private String mail;
	private boolean newsletterabo;
	
	public Abonne(int id,String nom, String mail, boolean newsletter){
		this.idAbo=id;
		this.nom=nom;
		this.mail=mail;
		this.newsletterabo=newsletter;
		
	}
	public int getIdAbo() {
		return idAbo;
	}
	public void setIdAbo(int idAbo) {
		this.idAbo = idAbo;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public boolean isNewsletterabo() {
		return newsletterabo;
	}
	public void setNewsletterabo(boolean newsletterabo) {
		this.newsletterabo = newsletterabo;
	}
	
	
}
