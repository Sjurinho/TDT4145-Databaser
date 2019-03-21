package treningsdagbok; 

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class Meny extends DBConn{
    private PreparedStatement regStatement;

    public void meny(){
        menyvalg();

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
				resultatlogg();
				break;
			case 4:
				grupper();
				break;
			case 5:
				pr();
				break;
			default: 
				System.out.println("Oi shit du maa skrive noe");
                menyvalg();
                meny();
                break;
        }

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
				menyvalg();
				registrer();
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

		RegistrerKontroller regKtrl = new RegistrerKontroller();
		regKtrl.connect();
        regKtrl.regApparat(aID, a, b); 
    }
    
	private void registrerOvelse(){
		Scanner scanner = new Scanner(System.in);

		System.out.println("Skriv navnet pa ovelsen");
		String a = scanner.next(); 
		System.out.println("Finnes denne i en ovelsesgruppe? (1/0)");
		int d = scanner.nextInt();
		if (d == 0){
			leggtilnygruppe();
		}
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
				menyvalg();
				break;
        }
        System.out.println("Skriv navnet paa ovelsesgruppen:");
		String c = scanner.next();
		ConnectController connCtrlN = new ConnectController();
	    connCtrlN.connect();
		connCtrlN.ConnectOvelsetoGruppe(c, a);
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
		System.out.println("Finnes apparatet i databasen? (1/0)");
		int b = scanner.nextInt();
		RegistrerKontroller regKtrl = new RegistrerKontroller();
		regKtrl.connect();
		if(b == 0){
			registrerApp();
		}
		regKtrl.regOvelse(navn, a);
    }
    
    	//Kommenterer ut denne fordi jeg vet ikke hvordan jeg skal faa scannet sql
	private void registrerOkt(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Sett en ID paa okten");
        int OktID = scanner.nextInt();

		System.out.println("Skriv aar paa okten");
		int dato1 = scanner.nextInt();
		int datoy = dato1-1900;

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


		System.out.println("Vil du legge til et notat? Skriv 1 for ja");
		int n = scanner.nextInt(); 



		java.sql.Timestamp tidspunkt = new java.sql.Timestamp(10000000);

		RegistrerKontroller regKtrl = new RegistrerKontroller();
		regKtrl.connect();
		regKtrl.regTreningsokt(OktID, date, tidspunkt, varighet, c, d);
        if (n == 1){
			Scanner scannern = new Scanner(System.in);
			System.out.println("Skriv litt om formaalet for denne okten");
			String notat = scannern.nextLine();

			ConnectController connCtrlN = new ConnectController();
			connCtrlN.connect();
			connCtrlN.ConnectNotattoOkt(OktID,OktID,notat);
		}
        System.out.println("Du skal naa legge til ovelser til denne okten, Skriv 0 for aa avslutte, et annet tall for aa legge til ny");



        
		int leggtil = scanner.nextInt(); 

		while(leggtil != 0){
			
			System.out.println("Skriv inn navnet paa ovelsen");
			Scanner scanner1 = new Scanner(System.in);
			String navnov = scanner1.next();

			System.out.println("Skriv vekten du tok");
			int vekt = scanner1.nextInt();

			System.out.println("Skriv antall sett");
			int sett = scanner1.nextInt();

			ConnectController connCtrl = new ConnectController();
			connCtrl.connect();
			connCtrl.ConnectOvelsetoOkt(OktID,sett,vekt, navnov);

			System.out.println("0 for aa avslutte, annet tall for aa legge til fler");
			leggtil = scanner.nextInt();

		}

	}


	private void hentInfo(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Hvor mange okter vil du hente ut?");
		int n = scanner.nextInt();
		Oktinfo info = new Oktinfo();
		info.connect();
        info.PrintOkt(n);
	}

	private void resultatlogg(){


		Scanner scanner = new Scanner(System.in);

		System.out.println("Skriv ovelsen du vil hente ut info paa");

		String ovelse = scanner.next();

		System.out.println("Start med aa skrive stardato, aar->mnd->dato");
		System.out.println("aar:");

		int dato1 = scanner.nextInt();
		int st_datoy = dato1-1900;

		System.out.println("Maned");
		int st_datom = scanner.nextInt();

		System.out.println("Dato");
		int st_datod = scanner.nextInt();

		System.out.println("Skriv deretter sluttdato, aar->mnd->dato");
		System.out.println("aar:");

		int dato2 = scanner.nextInt();
		int sl_datoy = dato2-1900;

		System.out.println("Maned");
		int sl_datom = scanner.nextInt();

		System.out.println("Dato");
		int sl_datod = scanner.nextInt();

		java.sql.Date date_start = new java.sql.Date(st_datoy,st_datom,st_datod);
		java.sql.Date date_slutt = new java.sql.Date(sl_datoy,sl_datom,sl_datod);


		Resultatlogg log = new Resultatlogg();
		log.connect();
		log.PrintResultatlogg(date_start,date_slutt,ovelse);
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
		String gruppe = scanner.next();

		RegistrerOvelsegruppeKontroller reg = new RegistrerOvelsegruppeKontroller();
		reg.connect();
		reg.RegistrerOvelsegruppe(gruppe);
	}

	private void sjekkgruppe(){

		Scanner scanner = new Scanner(System.in);
		System.out.println("Skriv navnet paa gruppen du onsker aa sjekke");
		String gruppe = scanner.next();
		RegistrerOvelsegruppeKontroller reg = new RegistrerOvelsegruppeKontroller();
		reg.connect();
		reg.PrintOvelseGruppeOvelser(gruppe);

	}

	private void pr(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Skriv navnet paa ovelsen du vil finne rekorden i");
		String ovelse = scanner.next();

		//Todo: kjor func som henter pr

		Rekord pers = new Rekord();
		pers.connect();
		pers.PrintRekord(ovelse);
	}

    private void menyvalg(){
		System.out.println("Her kan du velge mellom 5 ulike use-cases. Disse er:");
		System.out.println("1. Registrere apparater, ovelser og treningsokter med tilhorende data.");
		System.out.println("2. Faa opp informasjon om et antall n sist gjennomforte treningsokter med notater, der n spesifiseres av brukeren.");
		System.out.println("3. For hver enkelt ovelse skal det vaere mulig aa se en resultatlogg i et gitt tidsintervall spesifisert av brukeren.");
		System.out.println("4. Lage ovelsegrupper og finne ovelser som er i samme gruppe.");
		System.out.println("5. Hente ut personlig rekord i en valgt ovelse");

		System.out.println("Skriv nummeret du onsker aa utfore");
    }
    
    public static void main(String[] args) {
        Meny menu = new Meny();
        menu.meny();
    }
}