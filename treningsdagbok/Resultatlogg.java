package treningsdagbok; 

import java.sql.*;
import java.sql.Date;
import java.util.*;

import treningsdagbok.RegistrerKontroller;

public class Resultatlogg extends DBConn{
    public void PrintResultatlogg(Date t1, Date t2, String Ovelsesnavn){
        try {
            Statement stmt = conn.createStatement();
            String query = "select t.Dato, t.PersonligForm, o.Ovelsesnavn, treningsdagbok.Treningsoktovelse.AntallKilo, treningsdagbok.Treningsoktovelse.AntallSett from Treningsokt t, Ovelse o, treningsdagbok.Treningsoktovelse where treningsdagbok.Treningsoktovelse.OktID = t.OktID and treningsdagbok.Treningsoktovelse.Ovelsesnavn = o.Ovelsesnavn and o.Ovelsesnavn = '"+Ovelsesnavn+"' and t.Dato >= cast('"+t1+"' as date) and t.Dato <= cast('"+t2+"' as date);";
            System.out.println(query);
            
            ResultSet rs = stmt.executeQuery(query);
            //int nr = 1;
            String leftAlignFormat = "| %-10s | %-20s | %-11d | %-11d | %-14d |%n";
            System.out.println("Resultatliste for klasse Treningsokt");
            System.out.format("+------------+----------------------+-------------+-------------+----------------+%n");
            System.out.format("| Dato       | Ovelsesnavn          | Antall Sett | Antall Kilo | Personlig form |%n");
            System.out.format("+------------+----------------------+-------------+-------------+----------------+%n");
            while (true) {
                if (!rs.next()){
                    break;
                }
                //nr++;
                System.out.format(leftAlignFormat, rs.getDate("Dato"), rs.getString("Ovelsesnavn"), rs.getInt("AntallSett"), rs.getInt("AntallKilo"), rs.getInt("PersonligForm"));
            }
            System.out.format("+------------+----------------------+-------------+-------------+----------------+%n");
        } catch (Exception e) {
             System.out.println("db error during select of treningsoktovelse = "+e);
        }
    }
    public static void main(String[] args) {
        RegistrerKontroller reg = new RegistrerKontroller();
        reg.connect();
        reg.regApparat(3, "Benk", "Pumpe pumpe pumpe");
        Date t1 = new Date(0, 0, 0);
        Date t2 = new Date(100,10,10);
        System.out.println(t1);
        System.out.println(t2);
        reg.regOvelse("Benkpress", 3);
        reg.regTreningsokt(11, new Date(5,2, 1), new Timestamp(50000), new Time(10, 10, 10), 3, 10);
        Resultatlogg rl = new Resultatlogg();
        rl.connect();
        rl.PrintResultatlogg(t1, t2, "Pushups");
    }
}