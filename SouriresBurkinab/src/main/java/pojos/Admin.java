package pojos;

public class Admin {
	private int idadmin;
	private String name;
	private String mdp;
	
public Admin(int id, String name, String pass){
	this.idadmin=id;
	this.name=name;
	this.mdp=pass;
	
}
	public int getIdadmin() {
		return idadmin;
	}
	public void setIdadmin(int idadmin) {
		this.idadmin = idadmin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	
}
