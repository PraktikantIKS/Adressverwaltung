package adressverwaltungv2;

import java.util.Scanner;
import java.util.regex.Pattern;

import adressverwaltung.Adresse;

public class Adressspeicher {

	private static int NUMBER_OF_POSSIBLE_IDS = 10;
	
	private static Adresse[] addresses = new Adresse[NUMBER_OF_POSSIBLE_IDS];
	
	static int addAddress(Adresse address) {
		// neue ID vergeben
		// address zu addresses hinzufügen
		// neue ID zurückgeben
		return address.getID();
	}
	
	static int addAddress(Scanner sc, int ID) {
		String addressLine = sc.nextLine();
		if (ID != -1 && Adressverwaltung.checkAddressPattern(addressLine)) {
			String[] addressParts = addressLine.split(",", 4);
				addresses[ID].setName(addressParts[0].trim());
				addresses[ID].setStraße(addressParts[1].trim());
				addresses[ID].setPLZ(Integer.parseInt(addressParts[2].trim()));
				addresses[ID].setOrt(addressParts[3].trim());
				return -2;
		} else if (ID == -1) {
			return -3;
		} else {
			return -4;
		}
	}
	
	static int showAddress(Scanner sc) {
		String addressID = sc.nextLine();
		if (Adressverwaltung.checkIfIDExists(addressID)) {
			return Integer.parseInt(addressID);
		} else {
			return -1;
		}
	}
	
	static int getNUMBER_OF_POSSIBLE_IDS() {
		return NUMBER_OF_POSSIBLE_IDS;
	}

	static Adresse getAddress(int position) {
		return addresses[position];
	}
}
