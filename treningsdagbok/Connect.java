package treningsdagbok; 

import java.sql.*;
import java.util.*;


public class Controller extends DBConn{
	private PreparedStatement regStatement;
	//private Connection conn;

	public void MenyController() {
		//this.conn = conn;

		hjelp(); 

		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();

	
		switch(a){
			case 1:
				registrer(); 
				break; 
			case 2:
				hentInfo(); 
				break; 
			case 3:
				//resultatlogg();
				break;
			case 4:
				//grupper();
				break;
			case 5:
				//pr(); 
				break;
			default: 
				System.out.println("Oi shit du maa skrive noe");
				hjelp();
				break; 
		}

	}
	
	private void hjelp(){
		System.out.println("Her kan du velge mellom 5 ulike use-cases. Disse er:");
		System.out.println("1. Registrere apparater, ovelser og treningsokter med tilhorende data.");
		System.out.println("2. Faa opp informasjon om et antall n sist gjennomforte treningsokter med notater, der n spesifiseres av brukeren.");
		System.out.println("3. For hver enkelt ovelse skal det vaere mulig aa se en resultatlogg i et gitt tidsintervall spesifisert av brukeren.");
		System.out.println("4. Lage ovelsegrupper og finne ovelser som er i samme gruppe.");
		System.out.println("5. Hente ut personlig rekord i en valgt ovelse");

		System.out.println("Skriv nummeret du onsker aa utfore");
	}

	private void registrer(){
		System.out.println("Hva onsker du aa registrere?");
		System.out.println("Skriv 1 for apparat, 2 for ovelse eller 3 for en ny treningsokt");

		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();


		switch(a){
			case 1:
				registrerApp(); 
				break; 
			case 2:
				registrerOvelse(); 
				break; 
			case 3:
				registrerOkt();
				break;
			default: 
				System.out.println("Oi shit du maa skrive noe");
				hjelp();
				break;
		}
	}
	
	private void registrerApp(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Skriv ID paa apparatet");
        int aID = scanner.nextInt(); 
		System.out.println("Skriv navnet paa det nye apparatet");
		String a = scanner.next(); 
		System.out.println("Skriv en beskrivelse paa apparatet");

		Scanner scanner2 = new Scanner(System.in);
        String b = scanner2.nextLine();
        System.out.println(b);


		//Todo: Kjor funksjon med nytt apparat, input a og b
		RegistrerKontroller regKtrl = new RegistrerKontroller();
		regKtrl.connect();
        regKtrl.regApparat(aID, a, b); 
	}

	private void registrerOvelse(){
		Scanner scanner = new Scanner(System.in);

		System.out.println("Skriv navnet paa ovelsen");
		String a = scanner.next(); 

		System.out.println("Skriv 1 om ovelsen gjores med frivekter og 2 om ovelsen er paa et apparat.");
		int b = scanner.nextInt(); 
		switch(b){
			case 1:
				nyFriOvelse(a); 
				break; 
			case 2:
				nyAppOvelse(a); 
				break; 
			default: 
				System.out.println("Oi shit du maa skrive gyldig");
				hjelp();
				break;
		}
	}

	private void nyFriOvelse(String navn){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Skriv en beskrivelse paa ovelsen");
		Scanner scanner2 = new Scanner(System.in);
        String a = scanner2.nextLine();
		
		RegistrerKontroller regKtrl = new RegistrerKontroller();
		regKtrl.connect();
        regKtrl.regOvelse(navn, a);
	}

	private void nyAppOvelse(String navn){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Skriv inn ApparatID");
        int a = scanner.nextInt();

        RegistrerKontroller regKtrl = new RegistrerKontroller();
        regKtrl.connect();
        regKtrl.regOvelse(navn, a);
	}

	//Kommenterer ut denne fordi jeg vet ikke hvordan jeg skal faa scannet sql
	private void registrerOkt(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Sett en ID paa okten");
        int OktID = scanner.nextInt();

		System.out.println("Skriv aar paa okten");
		int datoy = scanner.nextInt();

		System.out.println("Skriv maaned paa okten");
		int datom = scanner.nextInt();

		System.out.println("Skriv dato paa okten");
		int datod = scanner.nextInt();

		System.out.println("Skriv antall timer");
		int varigheth = scanner.nextInt();

		System.out.println("Skriv antall min");
		int varighetm = scanner.nextInt();

		System.out.println("Skriv antall sec");
		int varighets = scanner.nextInt();


		java.sql.Date date = new java.sql.Date(datoy, datom, datod);
		java.sql.Time varighet = new java.sql.Time(varigheth, varighetm, varighets);


		System.out.println("Vurder din personlige form (1-10)");
		int c = scanner.nextInt(); 

		System.out.println("Vurder din prestasjon (1-10)");
		int d = scanner.nextInt(); 

        /*
		System.out.println("Vil du legge til et notat? Skriv 1 for ja");
		int n = scanner.nextInt(); 

		if (n == 1){
			System.out.println("Skriv litt om formaalet for denne okten");
			String[] notat = scanner.next(); 
			//Todo: Kjor funksjon som legger til notat
		}

		System.out.println("Du skal naa legge til ovelser til denne okten, Skriv 0 for aa avslutte, et annet tall for aa legge til ny");

        
		int leggtil = scanner.nextInt(); 

		while(leggtil > 0){
			
			System.out.println("Skriv inn navnet paa ovelsen");

			String[] navnov = scanner.next(); 
			// todo: if ovelse exist, hvis ikke legg til ny
			
			System.out.println("Skriv vekt paa ovelsen"); 
			int vekt = scanner.nextInt();
			
			System.out.println("Skriv antall sett paa ovelsen"); 
			int sett = scanner.nextInt();

			//Todo: kjor dette inn i en funksjon som legger til ovelsen

			System.out.println("0 for aa avslutte, annet tall for aa legge til fler");
			leggtil = scanner.nextInt();
		}
        
        // Tar vekk det over forelopig
		*/
		java.sql.Timestamp tidspunkt = new java.sql.Timestamp(10000000);

		RegistrerKontroller regKtrl = new RegistrerKontroller();
        regKtrl.connect();
        regKtrl.regTreningsokt(OktID, date, tidspunkt, varighet, c, d);

	}


	private void hentInfo(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Hvor mange okter vil du hente ut?");
		int n = scanner.nextInt(); 
		//Todo: Kjore func med usecase 2 med n i antall
		Oktinfo info = new Oktinfo();
		info.connect();
        info.PrintOkt(n);
	}
    /*
	private void resultatlogg(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Skriv navnet paa ovelsen du vil hente ut");
		String[] navn = scanner.next(); 

		System.out.println("Skriv startdatoen fra naar du vil hente ut resultatene:");
		Date start = scanner.next();

		System.out.println("Skriv sluttdatoen fra naar du vil hente ut resultatene:");
		Date slutt = scanner.next();

		//Todo: Kjor en funksjon med navn, start og slutt til usecase nr3
		break;
		
	}

	private void grupper(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Skriv 1 om du vil lage en ny ovelsesgruppe eller 2 om du vil finne ovelser i samme gruppe");
		int a = scanner.nextInt(); 

		switch(a){
			case 1: 
				leggtilnygruppe(); 
				break; 
			case 2: 
				sjekkgruppe(); 
				break; 
			default: 
				System.out.println("skriv riktig gruppe");
				break; 
			
		}
	
	}
	
	private void leggtilnygruppe(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Skriv navnet paa gruppen du onsker aa legge til");
		String[] gruppe = scanner.next(); 

		//Todo: Kjor funksjon som legger til gruppe

		// Kanskje spor bruker om hvilke ovelser som skal legges til i denne gruppen? 
		break;
	}

	private void sjekkgruppe(){

		Scanner scanner = new Scanner(System.in);
		System.out.println("Skriv navnet paa gruppen du onsker aa sjekke");
		String[] gruppe = scanner.next(); 

		//Todo: Kjor funksjon som sjekker gruppe
		break; 

	}

	private void pr(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Skriv navnet paa ovelsen du vil finne rekorden i");
		String[] ovelse = scanner.next(); 

		//Todo: kjor func som henter pr

		break; 
		
    }
    */



}




