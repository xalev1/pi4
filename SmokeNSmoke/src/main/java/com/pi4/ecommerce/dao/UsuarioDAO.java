package com.pi4.ecommerce.dao;

import com.pi4.ecommerce.model.Usuario;
import com.pi4.ecommerce.util.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO {
    
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    public void salvarUsuario(Usuario u) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;

    try {
      stmt = con.prepareStatement("insert into usuario(nome,email,sexo,cargo,senha,ativo) values(?,?,?,?,md5(?),?);");

      stmt.setString(1, u.getNome());
      stmt.setString(2, u.getEmail());
      stmt.setString(3, u.getSexo());
      stmt.setString(4, u.getCargo());
      stmt.setString(5, u.getSenha());
      stmt.setBoolean(6, u.isAtivo());

      stmt.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt);
    }
  }
    
    
    public Usuario getLogin(String email,String senha) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Usuario p = new Usuario();

    try {
      stmt = con.prepareStatement("select * from usuario where email = " + email + " and senha = " + senha);
      rs = stmt.executeQuery();

      rs.next();

      p.setEmail(rs.getString("email"));
      p.setSenha(rs.getString("senha"));

    } catch (SQLException ex) {
      Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return p;
  }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
