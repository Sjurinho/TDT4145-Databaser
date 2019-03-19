package treningsdagbok; 

import java.sql.*;
import java.sql.Date;
import java.util.*;

import treningsdagbok.RegistrerKontroller;

public class Resultatlogg extends DBConn{
    public void PrintResultatlogg(Timestamp t1, Timestamp t2){
        try {
            Statement stmt = conn.createStatement();
            String query = "select t.PersonligForm, oit.AntallSett, oit.AntallKilo from Treningsokt t, Treningsoktovelse oit where t.Tidspunkt > " + t1 + " and t.Tidspunkt < " + t2;
            System.out.println(query);
            
            ResultSet rs = stmt.executeQuery(query);
            int nr = 1;
            String leftAlignFormat = "| %-5d | %-10s | %-23s | %-8s | %-14s | %-10d | %-7d | %-20s |%n";
            System.out.println("Resultatliste for klasse Treningsokt");
            System.out.format("+-------+------------+-------------------------+----------+----------------+------------+---------+----------------------+%n");
            System.out.format("| OktID | Dato       | Tidspunkt               | Varighet | Personlig form | Prestasjon | NotatID | Formal               |%n");
            System.out.format("+-------+------------+-------------------------+----------+----------------+------------+---------+----------------------+%n");
            while (true) {
                if (!rs.next()){
                    break;
                }
                nr++;
                System.out.format(leftAlignFormat, rs.getInt("OktID"), rs.getDate("Dato"), rs.getTimestamp("Tidspunkt"), rs.getTime("Varighet"), rs.getInt("PersonligForm"), rs.getInt("Prestasjon"), rs.getInt("NotatID"), rs.getString("Treningsformal"));
         }
         System.out.format("+-------+------------+-------------------------+----------+----------------+------------+---------+----------------------+%n");
          } catch (Exception e) {
             System.out.println("db error during select of treningsokt = "+e);
         }
    }
    public static void main(String[] args) {
        RegistrerKontroller reg = new RegistrerKontroller();
        reg.connect();
        //reg.regApparat(3, "Benk", "Pumpe pumpe pumpe");
        Timestamp t1 = new Timestamp(60000);
        Timestamp t2 = new Timestamp(37000);
        //reg.regOvelse("Benkpress", 3);
        //reg.regTreningsokt(11, new Date(5,2, 1), new Timestamp(50000), new Time(10, 10, 10), 3, 10);
        Resultatlogg rl = new Resultatlogg();
        rl.PrintResultatlogg(t1, t2);
    }
}