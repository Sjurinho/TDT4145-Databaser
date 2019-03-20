package treningsdagbok;

import java.sql.*;
import java.util.Properties;

public class ConnectController extends DBConn{
    private PreparedStatement regStatement;

    public void ConnectOvelsetoOkt(int OktID, int AntallSett, int AntallKilo, String Ovelsesnavn){
        final String sql = "INSERT INTO TreningsoktOvelse VALUES((?), (?), (?), (?))";
        try {
            regStatement = conn.prepareStatement(sql);
            regStatement.setInt(1, OktID);
            regStatement.setInt(2, AntallSett);
            regStatement.setInt(3,AntallKilo);
            regStatement.setString(4, Ovelsesnavn);
            regStatement.execute();
        }catch (Exception e) {
            System.out.println(e);
        }

    }

    public void ConnectOvelsetoGruppe(String Ovelsegruppenavn, String Ovelsenavn){
        final String sql = "INSERT INTO Ovelsegruppetilhorighet(Ovelsegruppenavn, Ovelsenavn)" + "VALUES((?), (?))";
        try {
            regStatement = conn.prepareStatement(sql);
            regStatement.setString(1, Ovelsegruppenavn);
            regStatement.setString(2, Ovelsenavn);
            regStatement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void ConnectNotattoOkt(int OktID, int NotatID, String Treningsformal){
        try {
            regStatement = conn.prepareStatement("INSERT INTO Notat VALUES ( (?), (?), (?) )");
        } catch (Exception e) {
            System.out.println("db error during prepare of insert into Notat:" + e);
        }
        try {
            regStatement.setInt(1, OktID);
            regStatement.setInt(2, NotatID);
            regStatement.setString(3, Treningsformal);
            regStatement.execute();
        } catch (Exception e) {
            System.out.println("db error during insert of Notat:" + e);
        }
    }


}
