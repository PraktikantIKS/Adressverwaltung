package adressverwaltungv2;

public class Adresse {
	
	private int id;
	private String name;
	private String straﬂe;
	private int plz;
	private String ort;

	public Adresse(String name, String straﬂe, int pLZ, String ort) {
		this.name = name;
		this.id = -1;
		this.straﬂe = straﬂe;
		this.plz = pLZ;
		this.ort = ort;
	}

	int getID() {
		return id;
	}

	void setID(int id) {
		this.id = id;
	}

	String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	String getStraﬂe() {
		return straﬂe;
	}

	void setStraﬂe(String straﬂe) {
		this.straﬂe = straﬂe;
	}

	int getPLZ() {
		return plz;
	}

	void setPLZ(int pLZ) {
		this.plz = pLZ;
	}

	String getOrt() {
		return ort;
	}

	void setOrt(String ort) {
		this.ort = ort;
	}
	
	String getAddressAsString(){
		return name+"\n"+straﬂe+"\n"+plz+" "+ort;
	}
	
}
