
package Conexao;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conexao {
    //DADOS DO BANCO
    private static final  String DRIVER="com.mysql.jdbc.Driver";
    private static final  String URL="jdbc:mysql://localhost:3306/cadastro";
    private static final  String USER="root";
    private static final  String PASS="";
    
    //METODOS DA CONEXAO
    
    public static Connection getConnection(){
        try {
            Class.forName(DRIVER);
            
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("ERRO ao se conectar :(", ex);
        }
      
    }
    
    public static void closeConnetion(Connection con){
        
            try {
                if (con != null) {
                   con.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
      
    }
    
    public static void closeConnetion(Connection con, PreparedStatement stmt){
        closeConnetion(con);
        
       
            try {
               if (stmt != null) {
                  stmt.close();
               }
            } catch (SQLException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
       
    }
    
    public static void closeConnetion(Connection con, PreparedStatement stmt, ResultSet rs){
        closeConnetion(con, stmt);
        
      
            try {
              if (rs != null) {
                 rs.close();
              }
            } catch (SQLException ex) {
                Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            }
       
    }
    
}
