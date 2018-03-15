package adressverwaltungv2;

public class Adresse {
	
	private int ID;
	private String Name;
	private String Straﬂe;
	private int PLZ;
	private String Ort;

	public Adresse(String name, String straﬂe, int pLZ, String ort) {
		Name = name;
		ID = -1;
		Straﬂe = straﬂe;
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

	String getStraﬂe() {
		return Straﬂe;
	}

	void setStraﬂe(String straﬂe) {
		Straﬂe = straﬂe;
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
		return Name+"\n"+Straﬂe+"\n"+PLZ+" "+Ort+"\n";
	}
	
}
