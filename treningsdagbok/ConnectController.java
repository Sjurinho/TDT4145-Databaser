package treningsdagbok;

import java.sql.*;
import java.util.Properties;

public class ConnectController extends DBConn{
    private PreparedStatement regStatement;

    public void ConnectOvelsetoOkt(int OktID, string OvNavn){
        String sql = "INSERT INTO TreningsoktOvelse(OktID,OvNavn)" + "VALUES((?), (?))";
        try {
            regStatement = conn.prepareStatement(sql);
            setParameters(OktID, OvNavn);
            regStatement.execute();
        }catch (Exception e) {
            System.out.println(e);
        }

    }

    public void ConnectOvelsetoGruppe(string OvGruppeN, string OvNavn){
        String sql = "INSERT INTO TreningsoktOvelse(OvGruppeN,OvNavn)" + "VALUES((?), (?))";
          try {
                regStatement = conn.prepareStatement(sql);
                setParameters(OvGruppeN, OvNavn);
                regstatement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    
    //public void ConnectBrukertoApparat()

}


