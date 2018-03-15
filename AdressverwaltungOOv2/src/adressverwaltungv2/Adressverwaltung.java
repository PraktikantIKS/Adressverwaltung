package adressverwaltungv2;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Adressverwaltung {

	private static String MENU_OPTION_PATTERN = "[1-6]";
	private static String NUMBER_OF_OPTIONS_STRING = "Gib eine Zahl von 1 bis 6 ein.";
	private static String ID_PATTERN = "[0-" + (Adressspeicher.getNUMBER_OF_POSSIBLE_IDS() - 1) + "]";
	// min. ein Zeichen aber kein Komma, Komma, min. ein Zeichen aber kein Komma,
	// Komma, min. ein Zeichen aber kein Komma, Komma, min. ein Zeichen aber
	// kein Komma
	private static String ADDRESS_PATTERN = "[^,]+,[^,]+,[^,]+,[^,]+";
	// Leerzeichen,"Name",genau ein Leerzeichen,"Name",Leerzeichen
	private static String NAME_PATTERN = "\\s*[A-Z][a-zﬂ¸‰ˆ]+\\s[A-Z][a-zﬂ¸‰ˆ]+\\s*";
	// Leerzeichen,"Wort"mit mˆglichem "-Wort",exakt ein
	// Leerzeichen,Nummer,Buchstabenfolge,Leerzeichen
	private static String STREET_PATTERN = "\\s*[A-Z][a-zﬂ¸‰ˆ]+(-[A-Z][a-zﬂ¸‰ˆ]+)*\\s[0-9]+[A-Za-zﬂ¸‰ˆ]*\\s*";
	// Leerzeichen,Zahl aus f¸nf Ziffern,Leerzeichen
	private static String PLZ_PATTERN = "\\s*\\d{5}\\s*";
	// Leerzeichen, "Wort" mit mˆglichem "-Wort", Leerzeichen
	private static String POR_PATTERN = "\\s*[A-Z][a-zﬂ¸‰ˆ]+(-[A-Z][a-zﬂ¸‰ˆ]+)*\\s*";
	private static String MISSING_ID_MESSAGE = "Die ID wurde nicht gefunden.";
	private static String WRONG_ADDRESS_PATTERN_MESSAGE = "Die Adresse konnte nicht ¸bernommen werden,\nda sie falsch eingegeben wurde.";
	private static String FULL_ADDRESS_MEMORY = "Der Speicher ist voll und kann keine weiteren Adressen aufnehmen.";
	private static String SUCCESSFULL_REMOVED_ADDRESS = "Die Adresse wurde erfolgreich entfernt.";
	private static String WRONG_ID_AND_ADDRESS = "Es wurde eine falsche ID und Adresse eingegeben.";
	private static String SUCCESSFUL_CHANGED_ADDRESS = "Die Adresse wurde erfolgreich ge‰ndert";

	private static boolean stopProgram = false;

	public static void main(String[] args) {
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
				message(Adressspeicher.addAddress(checkAddressPattern(sc.nextLine())));
				break;
			case 2:
				System.out.println("Gib die ID der Adresse ein, die du angezigt bekommen mˆchtest.");
				showAddress(checkIfIDExists(sc.nextLine()));
				break;
			case 3:
				showAllAddresses();
				break;
			case 4:
				System.out.println("Gib die ID der Adresse ein, die gelˆscht werden soll.");
				message(Adressspeicher.removeAddress(checkIfIDExists(sc.nextLine())));
				break;
			case 5:
				System.out.println(
						"Gib die ID der Adresse ein, die ge‰ndert werden soll, best‰tige und gib dann die neue Adresse ein.");
				message(Adressspeicher.changeAddress(checkIfIDExists(sc.nextLine()),
						checkAddressPattern(sc.nextLine())));
				break;
			case 6:
				closeProgram();
				break;
			default:
				break;
			}
		} else {
			System.out.println(NUMBER_OF_OPTIONS_STRING);
		}
	}

	// setzt die Variable stopProgram true und beendet damit auch
	// die Schleife sowie das Programm an sich
	private static void closeProgram() {
		stopProgram = true;
	}

	// gibt entsprechende Meldungen aus, abh‰ngig von der Nummer
	private static void message(int messageNumber) {
		int switchNumber = messageNumber;
		String message = "";
		if (messageNumber >= 0) {
			switchNumber = 1;
		}
		switch (switchNumber) {
		case 1:
			message = "Die Adresse wurde erfolgreich mit der ID " + messageNumber + " eingetragen:\n"
					+ Adressspeicher.getAddress(messageNumber).getName() + "\n"
					+ Adressspeicher.getAddress(messageNumber).getStraﬂe() + "\n"
					+ Adressspeicher.getAddress(messageNumber).getPLZ() + " "
					+ Adressspeicher.getAddress(messageNumber).getOrt() + "\n";
			break;
		case -2:
			message = MISSING_ID_MESSAGE;
			break;
		case -3:
			message = FULL_ADDRESS_MEMORY;
			break;
		case -4:
			message = WRONG_ADDRESS_PATTERN_MESSAGE;
			break;
		case -5:
			message = SUCCESSFULL_REMOVED_ADDRESS;
			break;
		case -6:
			message = WRONG_ID_AND_ADDRESS;
			break;
		case -7:
			message = SUCCESSFUL_CHANGED_ADDRESS;
			break;
		}
		System.out.println(message);
	}

	// gibt alle Adressen aus, falls vorhanden
	// falls nicht, wird eine entsprechende Nachricht ausgegeben
	private static void showAllAddresses() {
		String allAddresses = "";
		for (int i = 0; i < Adressspeicher.getNUMBER_OF_POSSIBLE_IDS(); i++) {
			if (Adressspeicher.getAddress(i) != null) {
				allAddresses += "ID: " + Adressspeicher.getAddress(i).getID() + "\n"
						+ Adressspeicher.getAddress(i).getAddressAsString() + "\n\n"; // Ausgabe aller Adressen
			}
		}
		if (!allAddresses.isEmpty()) {
			System.out.println("Alle Adressen:");
			System.out.println(allAddresses);
		} else {
			System.out.println("Es gibt keine eingetragenen Adressen.");
		}
	}

	// gibt die Adresse zur angegebenen ID aus, sofern vorhanden
	// falls nicht, wird eine entsprechende Nachricht ausgegeben
	private static void showAddress(int ID) {
		if (ID != -2) {
			System.out.println(Adressspeicher.getAddress(ID).getAddressAsString());
		} else {
			message(-2);
		}
	}

	// ¸berpr¸ft, ob die Eingabe des Nutzers eine g¸ltige Adresse ist
	// falls nicht, wird eine entsprechende Nachricht ausgegeben
	static Adresse checkAddressPattern(String addressLine) {
		if (addressLine != null && !addressLine.isEmpty() && Pattern.matches(ADDRESS_PATTERN, addressLine)) {
			String[] address = addressLine.split(",", 4);

			boolean checkIfEverythingMatches = false;
			if (Pattern.matches(NAME_PATTERN, address[0]) && Pattern.matches(STREET_PATTERN, address[1])
					&& Pattern.matches(PLZ_PATTERN, address[2]) && Pattern.matches(POR_PATTERN, address[3])) {
				checkIfEverythingMatches = true;
			}
			if (checkIfEverythingMatches) {
				return new Adresse(address[0].trim(), address[1].trim(), Integer.parseInt(address[2].trim()),
						address[3].trim());
			} else {
				if (!Pattern.matches(NAME_PATTERN, address[0])) {
					System.out.println("Du hast einen ung¸ltigen Namen eingegeben.");
				}
				if (!Pattern.matches(STREET_PATTERN, address[1])) {
					System.out.println("Du hast eine ung¸ltige Straﬂe eingegeben.");
				}
				if (!Pattern.matches(PLZ_PATTERN, address[2])) {
					System.out.println("Du hast eine ung¸ltige PLZ eingegeben.");
				}
				if (!Pattern.matches(POR_PATTERN, address[3])) {
					System.out.println("Du hast einen ung¸ltigen Ort eingegeben.");
				}
			}
		}
		return null;
	}

	// pr¸ft, ob die eingegebene ID existiert und gibt in dem
	// Fall die ID zur¸ck sonst -2
	static int checkIfIDExists(String addressID) {
		if (addressID != null && !addressID.isEmpty() && Pattern.matches(ID_PATTERN, addressID)
				&& Adressspeicher.getAddress(Integer.parseInt(addressID)) != null) {
			return Integer.parseInt(addressID);
		}
		return -2;
	}
}
