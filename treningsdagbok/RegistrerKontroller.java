package treningsdagbok;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class RegistrerKontroller extends DBConn{
    private PreparedStatement regStatement;

    //Treningsøkt
    public void regTreningsokt(int OktID, Date Dato, Time Tidspunkt, int Varighet, int PersonligForm, int Prestasjon){
        try {
            regStatement = conn.prepareStatement("INSERT INTO Treningsokt VALUES ((?), (?), (?), (?), (?), (?))");
        } catch (Exception e) {
            System.out.println("db error during prepare of insert into Reg");
        }
        if (OktID > 0) {
            try {
                regStatement.setInt(1, OktID);
                regStatement.setDate(2, Dato);
                regStatement.setTime(3, Tidspunkt);
                regStatement.setInt(4, Varighet);
                regStatement.setInt(5, PersonligForm);
                regStatement.setInt(6, Prestasjon);
                regStatement.execute();
            } catch (Exception e) {
                System.out.println("db error during insert of Treningsokt");
            }
        }
    }

    //friØvelse
    public void regOvelse(String Ovelsesnavn, String beskrivelse){
        //Ovelse(Ovelsesnavn)
        try {
            regStatement = conn.prepareStatement("INSERT INTO Ovelse VALUES ((?))");
        } catch (Exception e) {
            System.out.println("db error during prepare of insert into Ovelse");
        }
        try {
            regStatement.setString(1, Ovelsesnavn);
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

    //ApparatØvelse
    public void regOvelse(String Ovelsesnavn, int ApparatID){
        //Ovelse(Ovelsesnavn)
        try {
            regStatement = conn.prepareStatement("INSERT INTO Ovelse VALUES ((?))");
        } catch (Exception e) {
            System.out.println("db error during prepare of insert into Ovelse");
        }
        try {
            regStatement.setString(1, Ovelsesnavn);
        } catch (Exception e) {
            System.out.println("db error during insert of Ovelse");
        }
        //ApparatOvelse(ApparatID)
        try {
            regStatement = conn.prepareStatement("INSERT INTO ApparatOvelse VALUES ((?))");
        } catch (Exception e) {
            System.out.println("db error during prepare of insert into ApparatOvelse");
        }
        try {
            regStatement.setInt(1, ApparatID);
            regStatement.execute();
        } catch (Exception e) {
            System.out.println("db error during insert of ApparatOvelse");
        }
    }

    //Apparat
    public void regApparat(int ApparatID, String Apparatnavn, String Apparatbeskrivelse){
        try {
            regStatement = conn.prepareStatement("INSERT INTO Apparat VALUES ((?), (?), (?))");
        } catch (Exception e) {
            System.out.println("db error during prepare of insert into Apparat");
        }

        try {
            regStatement.setInt(1, ApparatID);
            regStatement.setString(2, Apparatnavn);
            regStatement.setString(3, Apparatbeskrivelse);
            regStatement.execute();
        } catch (Exception e) {
            System.out.println("db error during insert of Apparat");
        }

    }

}