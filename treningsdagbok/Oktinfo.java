package treningsdagbok; 

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class Oktinfo extends DBConn{
    public void PrintOkt(int N){
        try {
            Statement stmt = conn.createStatement();
            String query = "select * from Treningsokt inner join Notat on Treningsokt.OktID = Notat.OktID";
            //System.out.println(query);
            
            ResultSet rs = stmt.executeQuery(query);
            int nr = 1;
            System.out.println("Resultatliste for klasse "+klasseNavn);
            while (rs.next() < N) {
             System.out.println(" " + nr++ + " "+ rs.getInt("OktID") + " " + rs.getDate("Dato") + " " + rs.getTimestamp("Tidspunkt"));
             System.out.println(" " + rs.getTime("Varighet") + " " + rs.getInt("PersonligForm") + " " + rs.getInt("Prestasjon"));
             System.out.println(" " + rs.getInt("NotatID") + " " + rs.getString("Treningsformal"));
         }
             
          } catch (Exception e) {
             System.out.println("db error during select of treningsokt = "+e);
         }
    }
}



