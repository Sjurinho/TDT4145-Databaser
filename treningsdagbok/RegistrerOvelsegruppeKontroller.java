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
            //System.out.println(query);
            
            ResultSet rs = stmt.executeQuery(query);
            int nr = 1;
            System.out.println("Resultatliste for klasse "+klasseNavn);
            while (rs.next()) {
             System.out.println(" " + nr++ + " "+ rs.getString("navn") + " " + rs.getString("klubb") + " " + rs.getInt("lopstid"));
         }
             
          } catch (Exception e) {
             System.out.println("db error during select of loper = "+e);
         }
    }
}