package treningsdagbok;

import java.security.Timestamp;
import java.security.cert.CertPath;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import treningsdagbok.RegistrerKontroller;

public class main_test {
    public static void main(String[] args) {
        /*RegistrerKontroller reg = new RegistrerKontroller();
        reg.connect();
        java.sql.Date date = new java.sql.Date(2019, 10, 31);
        java.sql.Time varighet = new java.sql.Time(17, 10, 36);
        java.sql.Timestamp tidspunkt = new java.sql.Timestamp(10000000);
        reg.regTreningsokt(2, date, tidspunkt, varighet, 3, 10);*/
        Oktinfo okt = new Oktinfo();
        okt.connect();
        okt.PrintOkt(10);
    }
}