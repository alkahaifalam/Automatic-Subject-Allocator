package Models;

import java.sql.*;
import java.io.Serializable;

public class Dao implements Serializable{
    private Dao(){}
    private static Dao md = null;
    public synchronized static Dao getInstance(){
        if(md == null){
            md = new Dao();
        }
        return md;
    }
    public Statement getConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/subjectAllocator?useSSL=false","suhaib","integral");
        return con.createStatement();
    }
    public boolean storeData(Statement st, String query) throws SQLException{
        return st.execute(query);
    }
    public ResultSet getData(Statement st, String query) throws SQLException{
        return st.executeQuery(query);
    }
}

