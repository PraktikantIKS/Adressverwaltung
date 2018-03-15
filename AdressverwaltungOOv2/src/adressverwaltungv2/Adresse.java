package adressverwaltungv2;

public class Adresse {
	
	private int id;
	private String name;
	private String stra�e;
	private int plz;
	private String ort;

	public Adresse(String name, String stra�e, int pLZ, String ort) {
		this.name = name;
		this.id = -1;
		this.stra�e = stra�e;
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

	String getStra�e() {
		return stra�e;
	}

	void setStra�e(String stra�e) {
		this.stra�e = stra�e;
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
		return name+"\n"+stra�e+"\n"+plz+" "+ort;
	}
	
}
