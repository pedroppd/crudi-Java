
package usuarioDAO;


import Conexao.Conexao;
import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import tabelaBanco.Usuario;


public class usuarioDAO {
   
 //INSERIR DADOS NO BANCO DE DADOS    
 public void inserir(Usuario u){
    Connection con = (Connection) Conexao.getConnection();
    
    PreparedStatement stmt = null;
    
     try {
         stmt = con.prepareStatement("INSERT INTO usuario (nome_usuario, email_usuario, login_usuario, senha_usuario)VALUES(?,?,?,?)");
         stmt.setString(1, u.getNome());
         stmt.setString(2, u.getEmail());
         stmt.setString(3, u.getLogin());
         stmt.setString(4, u.getSenha());
         stmt.executeUpdate();
         
         JOptionPane.showMessageDialog(null, "Usuario inserido com sucesso");
     } catch (SQLException ex) {
         
         JOptionPane.showMessageDialog(null, "Não foi possivel inserir o usuario");
     }finally{
         Conexao.closeConnetion(con, (com.mysql.jdbc.PreparedStatement) stmt);
     }
 }   


//LISTAR USUARIOS VINDOS DO BANCO DE DADOS

public List<Usuario> listar(){
   Connection con = (Connection) Conexao.getConnection();
   PreparedStatement stmt = null;
   ResultSet rs =null;
   List<Usuario> usuario = new ArrayList<>();
     try {
         stmt=con.prepareStatement("SELECT * FROM usuario");
         rs=stmt.executeQuery();
         
         while(rs.next()){
             Usuario u = new Usuario();
             u.setNome(rs.getString("login_usuario"));
             u.setEmail(rs.getString("email_usuario"));
             u.setLogin(rs.getString("login_usuario"));
             u.setSenha(rs.getString("senha_usuario"));
             
             usuario.add(u);
         }
         
     } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "ERRO :(");
     }finally{
         Conexao.closeConnetion(con, (com.mysql.jdbc.PreparedStatement) stmt, rs);
     }
   

      return usuario;
}

//DELETAR USUARIOS VINDO DO BANCO DE DADOS
public void delete(Usuario u){
  Connection con = (Connection) Conexao.getConnection();
  PreparedStatement stmt = null;
  
     try {
         stmt = con.prepareStatement("DELETE FROM usuario WHERE idusuario=?");
         stmt.setInt(u.getId(), 1);
         
         stmt.executeUpdate();
     } catch (SQLException ex) {
       JOptionPane.showMessageDialog(null, "Não foi possivel excluir esse usuario");
     }finally{
         Conexao.closeConnetion(con, (com.mysql.jdbc.PreparedStatement) stmt);
     }
  
}
//FAZER MUDANÇAS NO REGISTRO
public void update(Usuario u){
     Connection con = (Connection) Conexao.getConnection();
    
    PreparedStatement stmt = null;
    
     try {
         stmt = con.prepareStatement("UPDATE usuario SET nome_usuario=?, email_usuario=?, login_usuario=?, senha_usuario=? WHERE idusuario =?");
         stmt.setInt(5, u.getId());
         stmt.setString(1, u.getNome());
         stmt.setString(2, u.getEmail());
         stmt.setString(3, u.getLogin());
         stmt.setString(4, u.getSenha());
         stmt.executeUpdate();
         
         JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
     } catch (SQLException ex) {
         
         JOptionPane.showMessageDialog(null, "Erro ao atualizar!");
     }finally{
         Conexao.closeConnetion(con, (com.mysql.jdbc.PreparedStatement) stmt);
     }
}

public boolean checkLogin(String login, String senha){
     java.sql.Connection con = Conexao.getConnection();
     PreparedStatement stmt = null;
     ResultSet rs=null;
     boolean check = false;
     
     try {
         stmt = con.prepareStatement("SELECT * FROM usuario WHERE login=? AND senha=?");
         stmt.setString(1, login);
         stmt.setString(2, senha);
         
         rs=stmt.executeQuery();
         
         if (rs.next()) {
             check = true;
         }
         
         JOptionPane.showMessageDialog(null, "Login efetuado com sucesso!!");
     } catch (SQLException ex) {
         JOptionPane.showMessageDialog(null, "Login ou senha incorretos !!");
     }finally{
         Conexao.closeConnetion(con, (com.mysql.jdbc.PreparedStatement) stmt, rs);
     }
     return check;
}


public List<Usuario> buscar(String desc){
   
    Connection con = (Connection) Conexao.getConnection();
   PreparedStatement stmt = null;
   ResultSet rs =null;
   List<Usuario> usuario = new ArrayList<>();
     try {
         stmt=con.prepareStatement("SELECT * FROM usuario LIKE ?");
         stmt.setString(1, "%"+desc+"%");
         rs=stmt.executeQuery();
         
         while(rs.next()){
             Usuario u = new Usuario();
             u.setNome(rs.getString("login_usuario"));
             u.setEmail(rs.getString("email_usuario"));
             u.setLogin(rs.getString("login_usuario"));
             u.setSenha(rs.getString("senha_usuario"));
             
             usuario.add(u);
         }
         
     } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "ERRO :(");
     }finally{
         Conexao.closeConnetion(con, (com.mysql.jdbc.PreparedStatement) stmt, rs);
     }
   

      return usuario;
}


}


