package treningsdagbok; 

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class Rekord extends DBConn{
    public void PrintRekord(String Ovelsesnavn){
        try {
            Statement stmt = conn.createStatement();
            String query = "SELECT AntallKilo FROM ApparatOvelse ORDER BY AntallKilo DESC";
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("The personal record in " + Ovelsesnavn + " is:");
            System.out.println(rs.getInt("AntallKilo"));
        } catch (Exception e) {
             System.out.println("db error during select of  ApparatOvelse= "+e);
        }
    }
}