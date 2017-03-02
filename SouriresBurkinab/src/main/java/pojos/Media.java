package pojos;

public class Media {
	private int idmedia;
	private String type;
	private String url;
	
	public Media(int id, String type, String url){
		this.idmedia=id;
		this.type=type;
		this.url=url;

	}
	public int getIdmedia() {
		return idmedia;
	}
	public void setIdmedia(int idmedia) {
		this.idmedia = idmedia;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
