package adressverwaltungv2;

public class Adresse {
	
	private int ID;
	private String Name;
	private String Stra�e;
	private int PLZ;
	private String Ort;

	public Adresse(String name, String stra�e, int pLZ, String ort) {
		Name = name;
		ID = -1;
		Stra�e = stra�e;
		PLZ = pLZ;
		Ort = ort;
	}

	int getID() {
		return ID;
	}

	void setID(int iD) {
		ID = iD;
	}

	String getName() {
		return Name;
	}

	void setName(String name) {
		Name = name;
	}

	String getStra�e() {
		return Stra�e;
	}

	void setStra�e(String stra�e) {
		Stra�e = stra�e;
	}

	int getPLZ() {
		return PLZ;
	}

	void setPLZ(int pLZ) {
		PLZ = pLZ;
	}

	String getOrt() {
		return Ort;
	}

	void setOrt(String ort) {
		Ort = ort;
	}
	
	String getAddressAsString(){
		return Name+"\n"+Stra�e+"\n"+PLZ+" "+Ort+"\n";
	}
	
}
