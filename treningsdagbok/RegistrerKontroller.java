package treningsdagbok;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class RegistrerKontroller extends DBConn{
    private PreparedStatement regStatement;

    //Treningsokt
    public void regTreningsokt(int OktID, java.sql.Date Dato, java.sql.Timestamp Tidspunkt, java.sql.Time Varighet, int PersonligForm, int Prestasjon){
        try {
            regStatement = conn.prepareStatement("INSERT INTO Treningsokt VALUES ( (?), (?), (?), (?), (?), (?) )");
        } catch (Exception e) {
            System.out.println("db error during prepare of insert into Reg");
        }
        if (OktID > 0) {
            try {
                regStatement.setInt(1, OktID);
                regStatement.setDate(2, Dato);
                regStatement.setTimestamp(3, Tidspunkt);
                regStatement.setTime(4, Varighet);
                regStatement.setInt(5, PersonligForm);
                regStatement.setInt(6, Prestasjon);
                regStatement.execute();
            } catch (Exception e) {
                System.out.println(e.toString());
                System.out.println("db error during insert of Treningsokt");
            }
        }
    }

    //friOvelse
    public void regOvelse(String Ovelsesnavn, String beskrivelse){
        //Ovelse(Ovelsesnavn)
        try {
            regStatement = conn.prepareStatement("INSERT INTO Ovelse VALUES ((?))");
        } catch (Exception e) {
            System.out.println("db error during prepare of insert into Ovelse");
        }
        try {
            regStatement.setString(1, Ovelsesnavn);
            regStatement.execute();
        } catch (Exception e) {
            System.out.println("db error during insert of Ovelse");
        }
        //FriOvelse(beskrivelse)
        try {
            regStatement = conn.prepareStatement("INSERT INTO FriOvelse VALUES ((?))");
        } catch (Exception e) {
            System.out.println("db error during prepare of insert into FriOvelse");
        }
        try {
            regStatement.setString(1, beskrivelse);
            regStatement.execute();
        } catch (Exception e) {
            System.out.println("db error during insert of FriOvelse");
        }
    }

    //ApparatOvelse
    public void regOvelse(String Ovelsesnavn, int ApparatID){
        //Ovelse(Ovelsesnavn)
        try {
            regStatement = conn.prepareStatement("INSERT INTO Ovelse VALUES ((?))");
        } catch (Exception e) {
            System.out.println("db error during prepare of insert into Ovelse");
        }
        try {
            regStatement.setString(1, Ovelsesnavn);
            regStatement.execute();
        } catch (Exception e) {
            System.out.println("db error during insert of Ovelse");
        }
        //ApparatOvelse(ApparatID)
        try {
            regStatement = conn.prepareStatement("INSERT INTO Apparatovelse VALUES ((?), (?))");
        } catch (Exception e) {
            System.out.println("db error during prepare of insert into Apparatovelse");
        }
        try {
            regStatement.setString(1, Ovelsesnavn);
            regStatement.setInt(2, ApparatID);
            regStatement.execute();
        } catch (Exception e) {
            System.out.println("db error during insert of Apparatovelse: "+ e);
        }
    }

    //Apparat
    public void regApparat(int ApparatID, String Apparatnavn, String Apparatbeskrivelse){
        try {
            regStatement = conn.prepareStatement("INSERT INTO Apparat VALUES ((?), (?), (?))");
        } catch (Exception e) {
            System.out.println("db error during prepare of insert into Apparat:" + e);
        }

        try {
            regStatement.setInt(1, ApparatID);
            regStatement.setString(2, Apparatnavn);
            regStatement.setString(3, Apparatbeskrivelse);
            regStatement.execute();
        } catch (Exception e) {
            System.out.println("db error during insert of Apparat:" + e);
        }

    }
    public void regOvelsegruppetilhorighet(String Ovelsegruppenavn, String OvelsesNavn){
        try {
            regStatement = conn.prepareStatement("INSERT INTO Ovelsegruppetilhorighet VALUES ( (?), (?) )");
        } catch (Exception e) {
            System.out.println("db error during prepare of insert into Ovelsegruppetilhorighet:" + e);
        }
        try {
            regStatement.setString(1, Ovelsegruppenavn);
            regStatement.setString(2, OvelsesNavn);
            regStatement.execute();
        } catch (Exception e) {
            System.out.println("db error during insert of Ovelsegruppetilhorighet:" + e);
        }
    }
    public void regTreningsoktOvelse(int OktID, int AntallKilo, int AntallSett, String Ovelsesnavn){
        try {
            regStatement = conn.prepareStatement("INSERT INTO Treningsoktovelse VALUES ( (?), (?), (?), (?) )");
        } catch (Exception e) {
            System.out.println("db error during prepare of insert into TreningsoktOvelse:" + e);
        }
        try {
            regStatement.setInt(1, OktID);
            regStatement.setInt(2, AntallKilo);
            regStatement.setInt(3, AntallSett);
            regStatement.setString(4, Ovelsesnavn);
            regStatement.execute();
        } catch (Exception e) {
            System.out.println("db error during insert of TreningsoktOvelse:" + e);
        }
    }
    public void regNotat(int OktID, int NotatID, String Treningsformal){
        try {
            regStatement = conn.prepareStatement("INSERT INTO Notat VALUES ( (?), (?), (?) )");
        } catch (Exception e) {
            System.out.println("db error during prepare of insert into Notat:" + e);
        }
        try {
            regStatement.setInt(1, OktID);
            regStatement.setInt(2, NotatID);
            regStatement.setString(3, Treningsformal);
            regStatement.execute();
        } catch (Exception e) {
            System.out.println("db error during insert of Notat:" + e);
        }
    }
    public static void main(String[] args) {
        RegistrerOvelsegruppeKontroller reg2 = new RegistrerOvelsegruppeKontroller();
        reg2.connect();
        reg2.RegistrerOvelsegruppe("Kropp");
        RegistrerKontroller reg = new RegistrerKontroller();
        reg.connect();
        String[] apparatnavn = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        String[] ovelsenavn = new String[]{"Benk", "kneboy", "Markloft", "Hammercurls", "Hantelcurls", "Dips", "Pushups", "Situps", "French press", "Leg extensions"};
        for (int i = 1; i<10; i++){
            reg.regApparat(i, apparatnavn[i], "a");
            reg.regOvelse(ovelsenavn[i], i);
            reg.regTreningsokt(i, new Date(i+5, i+1, i*2), new Timestamp(i*1000), new Time(i*1, i+5, i*3), i, i);
            reg.regNotat(i, i, "Myah");
            reg.regTreningsoktOvelse(i,i+10,i*2, ovelsenavn[i]);
            reg.regOvelsegruppetilhorighet("Kropp", ovelsenavn[i]);
        }
    }
}