package adressverwaltungv2;

public class Adressspeicher {

	private static int NUMBER_OF_POSSIBLE_IDS = 10;
	// private static int ID_TO_START_WITH = 1; //muss noch eingefügt werden
	// static int getID_TO_START_WITH(){ return ID_TO_START_WITH; }

	private static Adresse[] addresses = new Adresse[NUMBER_OF_POSSIBLE_IDS];

	//fügt dem addresses Array eine Adresse hinzu, falls diese korrekt
	//ist und noch Platz vorhanden
	static int addAddress(Adresse address) {
		if (address != null && calculateID() != -3) {
			// neue ID vergeben
			address.setID(calculateID());
			// address zu addresses hinzufügen
			addresses[calculateID()] = address;
			// neue ID zurückgeben
			return address.getID();
		} else if (calculateID() == -3) {
			return calculateID();
		} else {
			return -4;
		}
	}

	//entfernt die Adresse mit der angegebenen ID, sofern vorhanden
	static int removeAddress(int ID) {
		if (ID != -2) {
			addresses[ID] = null;
			return -5;
		}
		return -2;
	}

	//ändert die Adresse mit der angegebenen ID in eine neue angegebene Adresse,
	//sofern die Daten richtig eingegeben wurden
	static int changeAddress(int ID, Adresse address) {
		if (address != null && ID != -2) {
			address.setID(ID);
			addresses[ID] = address;
			return -7;
		} else if (address == null && ID == -2) {
			return -6;
		} else if (address == null) {
			return -4;
		}
		return -2;
	}

	//gibt die Anzahl möglicher IDs zurück
	static int getNUMBER_OF_POSSIBLE_IDS() {
		return NUMBER_OF_POSSIBLE_IDS;
	}

	//gibt die Adresse an der jeweiligen Position im Array addresses zurück
	static Adresse getAddress(int position) {
		return addresses[position];
	}

	//berechnet die ID, welche einer neuen Adresse vergeben wird
	private static int calculateID() {
		for (int i = 0; i < NUMBER_OF_POSSIBLE_IDS; i++) {
			if (addresses[i] == null) {
				return i;
			}
		}
		return -3;
	}
}
