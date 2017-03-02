package pojos;

import java.sql.Date;
import java.time.LocalDate;


public class Newsletter {
	private int idnews;
	private String contenu;
	private String titrenews;
	private LocalDate datenews;
	public Newsletter(int int1, String string, String string2, LocalDate date) {
		// TODO Auto-generated constructor stub
		this.idnews=int1;
		this.contenu=string;
		this.titrenews=string2;
		this.datenews=date;
	}
	public int getIdnews() {
		return idnews;
	}
	public void setIdnews(int idnews) {
		this.idnews = idnews;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public String getTitrenews() {
		return titrenews;
	}
	public void setTitrenews(String titrenews) {
		this.titrenews = titrenews;
	}
	public LocalDate getDatenews() {
		return datenews;
	}
	public void setDatenews(LocalDate datenews) {
		this.datenews = datenews;
	}
	
}
