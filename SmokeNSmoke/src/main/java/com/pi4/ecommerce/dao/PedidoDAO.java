package com.pi4.ecommerce.dao;

import com.pi4.ecommerce.model.Pedido;
import com.pi4.ecommerce.util.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PedidoDAO {
    
    public List<Pedido> getPedidos(int cliente_id) {

    Connection con = ConexaoDB.obterConexao();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    List<Pedido> pedidos = new ArrayList<>();

    try {
      stmt = con.prepareStatement("SELECT * FROM vendas_produtos where cliente_id = " + cliente_id + " and status.id = vendas.status_id");
      rs = stmt.executeQuery();

      while (rs.next()) {
        Pedido p = new Pedido();
        p.setId(rs.getInt("vendas_produto.id"));
        p.setPedido(rs.getInt("vendas_produto.produto_id"));
        p.setQuantidade(rs.getInt("vendas_produto.qtd"));
        p.setValor(rs.getInt("vendas_produto.valor"));
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
