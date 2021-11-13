package com.pi4.ecommerce.dao;

import com.pi4.ecommerce.model.Produto;
import com.pi4.ecommerce.util.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdutoDAO {

  public List<Produto> getProdutos() {

    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    List<Produto> produtos = new ArrayList<>();

    try {
      stmt = con.prepareStatement("SELECT * FROM PRODUTOS where registro_deletado = false;");
      rs = stmt.executeQuery();

      while (rs.next()) {
        Produto p = new Produto();
        p.setId(rs.getInt("id"));
        p.setNome(rs.getString("nome"));
        p.setDescricao(rs.getString("descricao"));
        p.setPreco_custo(rs.getDouble("preco_custo"));
        p.setPreco_venda(rs.getDouble("preco_venda"));
        p.setQuantidade(rs.getInt("quantidade"));
        p.setAtivo(rs.getBoolean("ativo"));
        produtos.add(p);
      }
    } catch (SQLException ex) {
      Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return produtos;
  }


  public void salvarProduto(Produto p) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;

    try {
      stmt = con.prepareStatement("insert into produtos (nome,descricao,preco_custo,preco_venda,quantidade,ativo,registro_deletado) values ( ?, ?, ?, ?, ?, ?, false);");

      stmt.setString(1, p.getNome());
      stmt.setString(2, p.getDescricao());
      stmt.setDouble(3, p.getPreco_custo());
      stmt.setDouble(4, p.getPreco_venda());
      stmt.setInt(5, p.getQuantidade());
      stmt.setBoolean(6, p.isAtivo());

      stmt.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt);
    }
  }

  public int getUltimoProduto() {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    int produto_id = 0;

    try {
      stmt = con.prepareStatement("SELECT MAX(id) as id FROM PRODUTOS;");
      rs = stmt.executeQuery();

      while (rs.next()) {
        produto_id = rs.getInt("id");

      }
    } catch (SQLException ex) {
      Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return produto_id;
  }

  public Produto getProdutos(int id) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Produto p = new Produto();

    try {
      stmt = con.prepareStatement("SELECT * FROM PRODUTOS WHERE id = " + id);
      rs = stmt.executeQuery();

      rs.next();

      p.setId(rs.getInt("id"));
      p.setNome(rs.getString("nome"));
      p.setDescricao(rs.getString("descricao"));
      p.setPreco_custo(rs.getDouble("preco_custo"));
      p.setPreco_venda(rs.getDouble("preco_venda"));
      p.setQuantidade(rs.getInt("quantidade"));
      p.setAtivo(rs.getBoolean("ativo"));

    } catch (SQLException ex) {
      Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return p;
  }

  public void alterarProduto(Produto p) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;

    try {
      stmt = con.prepareStatement("update produtos set nome = ?, descricao = ?, preco_custo = ?, preco_venda = ?, quantidade = ?, ativo = ? where id = ?;");

      stmt.setString(1, p.getNome());
      stmt.setString(2, p.getDescricao());
      stmt.setDouble(3, p.getPreco_custo());
      stmt.setDouble(4, p.getPreco_venda());
      stmt.setInt(5, p.getQuantidade());
      stmt.setBoolean(6, p.isAtivo());
      stmt.setInt(8, p.getId());
      stmt.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt);
    }
  }

}
