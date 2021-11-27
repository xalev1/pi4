package com.pi4.ecommerce.dao;

import com.pi4.ecommerce.model.PedidoResumido;
import com.pi4.ecommerce.util.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PedidoResumidoDAO {

  public List<PedidoResumido> getPedidos(int cliente_id) {

    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    List<PedidoResumido> pedidos = new ArrayList<>();

    try {
      stmt = con.prepareStatement("SELECT vendas.id, vendas.total, status.status FROM vendas, status where cliente_id = " + cliente_id + " and status.id = vendas.status_id");
      rs = stmt.executeQuery();

      while (rs.next()) {
        PedidoResumido p = new PedidoResumido();
        p.setId(rs.getInt("vendas.id"));
        p.setTotal(rs.getDouble("vendas.total"));
        p.setStatus(rs.getString("status.status"));
        pedidos.add(p);
      }
    } catch (SQLException ex) {
      Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
      ConexaoDB.fecharConexao(con, stmt, rs);
    }
    return pedidos;
  }

}
