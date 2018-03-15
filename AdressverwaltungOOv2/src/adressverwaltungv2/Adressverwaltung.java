package adressverwaltungv2;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Adressverwaltung {

	private static String MENU_OPTION_PATTERN = "[1-6]";
	private static String ID_PATTERN = "[0-" + (Adressspeicher.getNUMBER_OF_POSSIBLE_IDS() - 1) + "]";
	private static String addressPattern = "[^,]+,[^,]+,[^,]+,[^,]+";
	private static String namePattern = "\\s*[A-Z][a-zﬂ¸‰ˆ]+\\s[A-Z][a-zﬂ¸‰ˆ]+\\s*";
	private static String streetPattern = "\\s*[A-Z][a-zﬂ¸‰ˆ]+(-[A-Z][a-zﬂ¸‰ˆ]+)*\\s[0-9]+[A-Za-zﬂ¸‰ˆ]*\\s*";
	private static String plzPattern = "\\s*\\d{5}\\s*";
	private static String porPattern = "\\s*[A-Z][a-zﬂ¸‰ˆ]+(-[A-Z][a-zﬂ¸‰ˆ]+)*\\s*";
	private static String MISSING_ID_MESSAGE = "Die ID wurde nicht gefunden.";
	private static String WRONG_ADDRESS_PATTERN_MESSAGE = "Die Adresse konnte nicht ¸bernommen werden, da sie falsch eingegeben wurde.";
	private static String FULL_ADDRESS_MEMORY = "Der Speicher ist voll und kann keine weiteren Adressen aufnehmen.";

	private static boolean stopProgram = false;

	public static void main(String[] args) {
		// Adressspeicher as = new Adressspeicher();
		Adressspeicher.initAdresses();
		Scanner sc = new Scanner(System.in);
		menuOptions();
		while (sc.hasNextLine()) {
			menu(sc);
			if (stopProgram) {
				break;
			}
			waitForInput(sc);
			menuOptions();
		}

	}

	// wartet darauf, dass der Nutzer fortfahren will
	private static void waitForInput(Scanner sc) {
		System.out.println("Zum fortfahren die Enter-Taste dr¸cken");
		sc.nextLine();
	}

	// gibt aus, was der Nutzer machen kann
	private static void menuOptions() {
		System.out.println("Dr¸cke die Zahl f¸r das, was du tun mˆchtest.");
		System.out.println("1 eine Adresse hinzuf¸gen");
		System.out.println("2 eine Adresse anzeigen lassen");
		System.out.println("3 alle Adressen anzeigen lassen");
		System.out.println("4 eine Adresse lˆschen");
		System.out.println("5 eine Adresse ‰ndern");
		System.out.println("6 das Programm beenden");
	}

	// verarbeitet die Eingabe und f¸hrt entsprechend Methoden aus
	private static void menu(Scanner sc) {
		String option = sc.nextLine();
		if (Pattern.matches(MENU_OPTION_PATTERN, option)) {
			switch (Integer.parseInt(option)) {
			case 1:
			System.out.println("Gib eine Adresse ein. Trenne Name, Straﬂe, PLZ und Ort durch ein Komma");
			// Adressspeicher.addAddress(sc, Adressspeicher.calculateID());
			 break;
			// case 2:
			// Adressspeicher.showAddress(sc);
			// break;
			 case 3:
			 showAllAddresses();
			 break;
			// case 4:
			// Adressspeicher.removeAddress(sc);
			// break;
			// case 5:
			// Adressspeicher.changeAddress(sc);
			// break;
			case 6:
				closeProgram();
				break;
			default:
				break;
			}
		} else {
			System.out.println("Gib eine Zahl von 1 bis 6 ein.");
		}
	}

	// setzt die Variable stopProgram true und beendet damit auch
	// die Schleife sowie das Programm an sich
	private static void closeProgram() {
		stopProgram = true;
	}
	
	// pr¸ft, ob die eingegebene ID existiert und gibt in dem
		// Fall true zur¸ck, sonst false
	static boolean checkIfIDExists(String addressID) {
		if (Adressspeicher.getAddress(Integer.parseInt(addressID)) != null) {
			return true;
		}
		System.out.println("Die eingegebene ID '" + addressID+"' existiert nicht.");
		return false;
	}
	
	static void message(int messageNumber) {
		int switchNumber = messageNumber;
		String message = "";
		if(messageNumber>=0) {
			switchNumber = 1;
		}
		switch(switchNumber) {
		case 1: message ="Adresse zu der ID " + messageNumber + ":\n" + Adressspeicher.getAddress(messageNumber).getName()
				+ "\n" + Adressspeicher.getAddress(messageNumber).getStraﬂe() + "\n"
				+ Adressspeicher.getAddress(messageNumber).getPLZ() + " " + Adressspeicher.getAddress(messageNumber).getOrt()
				+ "\n"; break;
		case -1: message = MISSING_ID_MESSAGE; break;
		case -2: message = "Die Adresse wurde erfolgreich eingetragen."; break;
		case -3: message = FULL_ADDRESS_MEMORY; break;
		case -4: message = WRONG_ADDRESS_PATTERN_MESSAGE; break;
		}
		System.out.println(message);
	}
	
	static void showAllAddresses() {
		String allAddresses = "";
		for (int i = 0; i < Adressspeicher.getNUMBER_OF_POSSIBLE_IDS(); i++) {
			if (Adressspeicher.getAddress(i).getName() != null && !Adressspeicher.getAddress(i).getName().isEmpty()) {
				allAddresses += Adressspeicher.getAddress(i).getID() + "\n" + Adressspeicher.getAddress(i).getName() + "\n" + Adressspeicher.getAddress(i).getPLZ() + "\n"
						+ Adressspeicher.getAddress(i).getPLZ() + " " + Adressspeicher.getAddress(i).getOrt() + "\n"; // Ausgabe aller Adressen
			}
		}
		if (!allAddresses.isEmpty()) {
			System.out.println("Alle Adressen:");
			System.out.println(allAddresses);
		} else {
			System.out.println("Es gibt keine eingetragenen Adressen.");
		}
	}
	
	static boolean checkAddressPattern(String addressLine) {
		if (addressLine != null && !addressLine.isEmpty() && Pattern.matches(addressPattern, addressLine)) {
			String[] address = addressLine.split(",", 4);

			boolean checkIfEverythingMatches = false;
			if (Pattern.matches(namePattern, address[0]) && Pattern.matches(streetPattern, address[1])
					&& Pattern.matches(plzPattern, address[2]) && Pattern.matches(porPattern, address[3])) {
				checkIfEverythingMatches = true;
			}
			if (checkIfEverythingMatches) {
				return true;
			} else {
				if (!Pattern.matches(namePattern, address[0])) {
					System.out.println("Du hast einen ung¸ltigen Namen eingegeben.");
				}
				if (!Pattern.matches(streetPattern, address[1])) {
					System.out.println("Du hast eine ung¸ltige Straﬂe eingegeben.");
				}
				if (!Pattern.matches(plzPattern, address[2])) {
					System.out.println("Du hast eine ung¸ltige PLZ eingegeben.");
				}
				if (!Pattern.matches(porPattern, address[3])) {
					System.out.println("Du hast eine ung¸ltige Adresse eingegeben.");
				}
			}
		}
		return false;
	}

}
