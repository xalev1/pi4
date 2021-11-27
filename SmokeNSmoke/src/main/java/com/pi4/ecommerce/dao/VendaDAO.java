package com.pi4.ecommerce.dao;


import com.pi4.ecommerce.model.Venda;
import com.pi4.ecommerce.util.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VendaDAO {

  public void salvarVenda(Venda v) {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;

    try {
      stmt = con.prepareStatement("insert into vendas (cliente_id,endereco_id,meio_pagamento_id,status_id,total, obs) values ( ?, ?, ?, ?, ?, ?);");

      stmt.setInt(1, v.getCliente_id());
      stmt.setInt(2, v.getEndereco_id());
      stmt.setInt(3, v.getMeio_pagamento_id());
      stmt.setInt(4, 1);
      stmt.setDouble(5, v.getTotal());
      stmt.setString(6, v.getObs());

      stmt.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt);
    }
  }

  public int getUltimaVenda() {
    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    int id = 0;

    try {
      stmt = con.prepareStatement("SELECT MAX(id) as id FROM vendas;");
      rs = stmt.executeQuery();

      while (rs.next()) {
        id = rs.getInt("id");

      }
    } catch (SQLException ex) {
      Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return id;
  }

}
