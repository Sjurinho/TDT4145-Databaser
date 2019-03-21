package treningsdagbok; 

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class Rekord extends DBConn{
    public void PrintRekord(String Ovelsesnavn){
        try {
            Statement stmt = conn.createStatement();
            String query = "select AntallKilo from ((Apparatovelse inner join Ovelse on Apparatovelse.Ovelsesnavn = '"+ Ovelsesnavn +"') inner join Treningsoktovelse) order by AntallKilo desc;";
            ResultSet rs = stmt.executeQuery(query);
            rs.beforeFirst();
            rs.next();
            System.out.println("The personal record in '" + Ovelsesnavn + "' is:");
            System.out.println(rs.getInt("AntallKilo"));
        } catch (Exception e) {
             System.out.println("db error during select of  ApparatOvelse= "+e);
        }
    }
}