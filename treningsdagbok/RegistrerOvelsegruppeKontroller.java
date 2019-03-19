package treningsdagbok;

import java.sql.*;
import java.util.*;

public class RegistrerOvelsegruppeKontroller extends DBConn {
    private PreparedStatement regStatement;

    public void RegistrerOvelsegruppe(String ovelsegruppenavn){
        try { 
            regStatement = conn.prepareStatement("INSERT INTO Ovelsegruppe VALUES ( (?) )"); 
        } catch (Exception e) { 
            System.out.println("db error during prepare of insert into Ovelsegruppe");
        }
        try {
            regStatement.setString(1, ovelsegruppenavn);
            regStatement.execute();
        } catch (Exception e) {
            System.out.println("db error during insert of Ovelsegruppe ovelsegruppenavn= "+ovelsegruppenavn);
        }
    }
    public void PrintOvelseGruppeOvelser(){
        try {
            Statement stmt = conn.createStatement();
            String query = "SELECT Ovelsegruppenavn, Ovelsesnavn FROM (Ovelsegruppe INNER JOIN Ovelsegruppetilhorighet ON Ovelsegruppe.Ovelsegruppenavn = Ovelsegruppetilhorighet.Ovelsegruppenavn) INNER JOIN Ovelse ON Ovelsegruppetilhorighet.Ovelsesnavn = Ovelse.Ovelsesnavn ORDER BY Ovelsegruppenavn";
            Map<String, List<String>> grupper = new Hashmap<>();
            
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {

                List<String> ovelser = new ArrayList<>();
                ovgr = rs.getString("Ovelsegruppenavn");

                ov = rs.getString("Ovelsenavn");
                if (grupper.containsKey(ov)){

                }
                System.out.println( ovgr+ " " + ov);
            }
             
        } catch (Exception e) {
                System.out.println("db error during select of loper = "+e);
        }
    }
    

}