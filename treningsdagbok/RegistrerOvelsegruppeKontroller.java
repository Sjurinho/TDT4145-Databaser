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

    public void PrintOvelseGruppeOvelser(String Ovelsegruppenavn){
        try{
            System.out.println("Her er alle treningsovelsene i gruppen " + Ovelsegruppenavn + ":");
            String leftAlignFormat = "| %-20s |%n";
            System.out.format("+----------------------+%n");
            System.out.format("| Ovelsesnavn          |%n");
            System.out.format("+----------------------+%n");
            final String query = "select Ovelse.Ovelsesnavn from Ovelse inner join Ovelsegruppetilhorighet on Ovelse.Ovelsesnavn = Ovelsegruppetilhorighet.Ovelsesnavn where Ovelsegruppetilhorighet.Ovelsegruppenavn = '"+Ovelsegruppenavn+"'";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (true) {
                if (!rs.next()){
                    break;
                }
            System.out.format(leftAlignFormat, rs.getString("Ovelsesnavn"));
         }
         System.out.format("+----------------------+%n");
            stmt.close();
        }catch (Exception e) {
            System.out.println("db error during select of treningsokt = "+e);
        }

    }
    /*public void PrintOvelseGruppeOvelser(){

        
        try {
            Statement stmt = conn.createStatement();
            //usikker paa om ORDER BY Ovelsegruppenavn er nodvendig. tror kanskje denne kan droppes
            //kan bruke left join for aa faa med grupper uten ovelser
            String query = "SELECT Ovelsegruppenavn, Ovelsesnavn FROM (Ovelsegruppe INNER JOIN Ovelsegruppetilhorighet ON Ovelsegruppe.Ovelsegruppenavn = Ovelsegruppetilhorighet.Ovelsegruppenavn) INNER JOIN Ovelse ON Ovelsegruppetilhorighet.Ovelsesnavn = Ovelse.Ovelsesnavn ORDER BY Ovelsegruppenavn";
            Map<String, List<String>> grupper = new Hashmap<>();
            
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {

                List<String> ovelser = new ArrayList<>();
                String ovgr = rs.getString("Ovelsegruppenavn");
                String ov = rs.getString("Ovelsenavn");
                ovelser.add(ov);
                if (grupper.containsKey(ovgr)){
                    ovelser.add(grupper.get(ovgr));
                }
                grupper.put(ovgr,ovelser);
                System.out.println( ovgr+ " " + ov);
            }
            for (String key: grupper.keySet()){
                System.out.println("____________________________"); 
                System.out.println("____________________________"); 
                List<String> ovelser = new ArrayList<>();
                System.out.println(key); 
                System.out.println("----------------------------"); 
                ovelser = grupper.get(ovgr);
                for (String ovelse : ovelser) {
                    System.out.println(ovelse); 
                }
            }
             
        } catch (Exception e) {
                System.out.println("db error during select of loper = "+e);
        }
    }*/
    

}