package pojos;

import java.time.LocalDate;
import java.util.Date;


public class Article {
	private int idArticle;
	private String Titre;
	private String Contenu;
	private LocalDate DatePubli;
	private Date DateModif;
	private Date DateSupp;
	private String Type;
	
	public Article(int id, String type, String titre, String contenu, LocalDate date){
		this.idArticle=id;
		this.Titre=titre;
		this.Contenu=contenu;
		this.DatePubli=date;
		this.DateModif=null;
		this.DateSupp=null;
		this.Type=type;
		
	}
	
	public int getId() {
		return idArticle;
	}
	public void setId(int id) {
		this.idArticle = id;
	}
	public String getTitre() {
		return Titre;
	}
	public void setTitre(String titre) {
		this.Titre = titre;
	}
	public String getContenu() {
		return Contenu;
	}
	public void setContenu(String contenu) {
		this.Contenu = contenu;
	}
	public LocalDate getDatePubli() {
		return DatePubli;
	}
	public void setDatePubli(LocalDate datePubli) {
		DatePubli = datePubli;
	}
	public Date getDateModif() {
		return DateModif;
	}
	public void setDateModif(Date dateModif) {
		DateModif = dateModif;
	}
	public Date getDateSupp() {
		return DateSupp;
	}
	public void setDateSupp(Date dateSupp) {
		DateSupp = dateSupp;
	}
	public String getType(){
		return Type;
	}
	public void setType(String type){
		Type = type;
	}
	
}
