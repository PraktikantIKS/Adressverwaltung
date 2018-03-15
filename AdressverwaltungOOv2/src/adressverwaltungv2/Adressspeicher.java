package adressverwaltungv2;

public class Adressspeicher {

	private static int NUMBER_OF_POSSIBLE_IDS = 10;
//	private static int ID_TO_START_WIDTH = 1;
	
	private static Adresse[] addresses = new Adresse[NUMBER_OF_POSSIBLE_IDS];
	
	static int addAddress(Adresse address) {
		if(calculateID() != -3) {
		// neue ID vergeben
		address.setID(calculateID());
		// address zu addresses hinzufügen
		addresses[calculateID()] = address;
		// neue ID zurückgebenW
		return address.getID();
		}
		return calculateID();
	}
	
	static int removeAddress(int ID) {
		if(ID != -2) {
			addresses[ID] = null;
			return -5;
		}
		return -2;
	}
	
	static int getNUMBER_OF_POSSIBLE_IDS() {
		return NUMBER_OF_POSSIBLE_IDS;
	}

	static Adresse getAddress(int position) {
		return addresses[position];
	}
	
	private static int calculateID(){
		for(int i = 0; i<NUMBER_OF_POSSIBLE_IDS; i++) {
			if(addresses[i] == null) {
				return i;
			}
		}
		return -3;
	}
}
