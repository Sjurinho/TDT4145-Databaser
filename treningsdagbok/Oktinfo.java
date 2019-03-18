package treningsdagbok; 

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class Oktinfo extends DBConn{
    public void PrintOkt(int N){
        try {
            Statement stmt = conn.createStatement();
            String query = "select * from Treningsokt left join Notat on Treningsokt.OktID = Notat.OktID order by Dato desc";
            //System.out.println(query);
            
            ResultSet rs = stmt.executeQuery(query);
            int nr = 1;
            String leftAlignFormat = "| %-5d | %-10s | %-23s | %-8s | %-14s | %-10d | %-7d | %-20s |%n";
            System.out.println("Resultatliste for klasse Treningsokt");
            System.out.format("+-------+------------+-------------------------+----------+----------------+------------+---------+----------------------+%n");
            System.out.format("| OktID | Dato       | Tidspunkt               | Varighet | Personlig form | Prestasjon | NotatID | Formal               |%n");
            System.out.format("+-------+------------+-------------------------+----------+----------------+------------+---------+----------------------+%n");
            while (nr <= N) {
                if (!rs.next()){
                    break;
                }
            System.out.format(leftAlignFormat, rs.getInt("OktID"), rs.getDate("Dato"), rs.getTimestamp("Tidspunkt"), rs.getTime("Varighet"), rs.getInt("PersonligForm"), rs.getInt("Prestasjon"), rs.getInt("NotatID"), rs.getString("Treningsformal"));
         }
         System.out.format("+-------+------------+-------------------------+----------+----------------+------------+---------+----------------------+%n");
          } catch (Exception e) {
             System.out.println("db error during select of treningsokt = "+e);
         }
    }
}



