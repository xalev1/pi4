package com.pi4.ecommerce.dao;


import com.pi4.ecommerce.model.Endereco;
import com.pi4.ecommerce.util.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnderecoDAO {

  public void salvarEnderecoCliente(int cliente_id, Endereco e) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;

    try {
      stmt = con.prepareStatement("insert into enderecos(cliente_id, cep, logradouro, numero, complemento, bairro, cidade, estado, is_faturamento) values(" + cliente_id + ", '" + e.getCep() + "', '" + e.getLogradouro() + "', '" + e.getNumero() + "', '" + e.getComplemento() + "', '" + e.getBairro() + "', '" + e.getCidade() + "', '" + e.getEstado() + "', " + e.isIs_faturamento() + ");");
      stmt.executeUpdate();

    } catch (SQLException ex) {
      Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt);
    }
  }

  public Endereco getEnderecoFaturamento(int id) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Endereco e = new Endereco();

    try {
      stmt = con.prepareStatement("select * from enderecos where cliente_id = " + id + " and is_faturamento = true;");
      rs = stmt.executeQuery();

      rs.next();

      e.setId(rs.getInt("id"));
      e.setCliente_id(id);
      e.setCep(rs.getString("cep"));
      e.setLogradouro(rs.getString("logradouro"));
      e.setNumero(rs.getString("numero"));
      e.setComplemento(rs.getString("complemento"));
      e.setBairro(rs.getString("bairro"));
      
      e.setCidade(rs.getString("cidade"));
      e.setEstado(rs.getString("estado"));
      e.setIs_faturamento(true);
    } catch (SQLException ex) {
      Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return e;
  }

  public Endereco getEnderecoEntrega(int id) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Endereco e = new Endereco();

    try {
      stmt = con.prepareStatement("select * from enderecos where cliente_id = " + id + " and is_faturamento = false;");
      rs = stmt.executeQuery();

      rs.next();

      e.setId(rs.getInt("id"));
      e.setCliente_id(id);
      e.setCep(rs.getString("cep"));
      e.setLogradouro(rs.getString("logradouro"));
      e.setNumero(rs.getString("numero"));
      e.setComplemento(rs.getString("complemento"));
      e.setBairro(rs.getString("bairro"));
      e.setCidade(rs.getString("cidade"));
      e.setEstado(rs.getString("estado"));
      e.setIs_faturamento(false);
    } catch (SQLException ex) {
      Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return e;
  }
  
  public Endereco getEnderecoEntregaPagamento(int id) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Endereco e = new Endereco();

    try {
      stmt = con.prepareStatement("select * from enderecos where id = " + id + " and is_faturamento = false;");
      rs = stmt.executeQuery();

      rs.next();

      e.setId(rs.getInt("id"));
      e.setCliente_id(id);
      e.setCep(rs.getString("cep"));
      e.setLogradouro(rs.getString("logradouro"));
      e.setNumero(rs.getString("numero"));
      e.setComplemento(rs.getString("complemento"));
      e.setBairro(rs.getString("bairro"));
      e.setCidade(rs.getString("cidade"));
      e.setEstado(rs.getString("estado"));
      e.setIs_faturamento(false);
    } catch (SQLException ex) {
      Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return e;
  }

  public List<Endereco> getEnderecos(int id) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    List<Endereco> listaEnderecos = new ArrayList<>();

    try {
      stmt = con.prepareStatement("select * from enderecos where cliente_id = " + id + " and is_faturamento = false;");
      rs = stmt.executeQuery();
      rs.next();
      while (rs.next()) {
        Endereco e = new Endereco();
        e.setId(rs.getInt("id"));
        e.setCliente_id(id);
        e.setCep(rs.getString("cep"));
        e.setLogradouro(rs.getString("logradouro"));
        e.setNumero(rs.getString("numero"));
        e.setComplemento(rs.getString("complemento"));
        e.setBairro(rs.getString("bairro"));
        e.setCidade(rs.getString("cidade"));
        e.setEstado(rs.getString("estado"));
        e.setIs_faturamento(false);
        listaEnderecos.add(e);
      }
    } catch (SQLException ex) {
      Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return listaEnderecos;
  }

  public void removeEnderecos(int id) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      stmt = con.prepareStatement("DELETE FROM enderecos where cliente_id = ?;");
      stmt.setInt(1, id);
      stmt.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
  }

}
