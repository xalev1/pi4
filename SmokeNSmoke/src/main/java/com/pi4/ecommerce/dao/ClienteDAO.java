package com.pi4.ecommerce.dao;

import com.pi4.ecommerce.model.Cliente;
import com.pi4.ecommerce.util.ConexaoDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAO {

    public List<Cliente> getCliente() {

        Connection con = ConexaoDB.obterConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Cliente> cliente = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM CLIENTES where registro_deletado = false;");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setTelefone(rs.getString("telefone"));
                c.setEmail(rs.getString("email"));
                c.setSenha(rs.getString("senha"));
                c.setGenero(rs.getString("genero"));
                c.setData_nascimento(rs.getDate("data_nascimento"));
                c.setRegistro_deletado(rs.getBoolean("registro_deletado"));
                cliente.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexaoDB.fecharConexao(con, stmt, rs);
        }
        return cliente;
    }

    public void salvarCliente(Cliente c) {
        Connection con = ConexaoDB.obterConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("insert into clientes (nome,cpf,telefone,email,senha,genero,data_nascimento) values ( ?, ?, ?, ?, ?, ?, ?);");

            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getCpf());
            stmt.setString(3, c.getTelefone());
            stmt.setString(4, c.getEmail());
            stmt.setString(5, c.getSenha());
            stmt.setString(6, c.getGenero());
            stmt.setDate(7, c.getData_nascimento());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexaoDB.fecharConexao(con, stmt);
        }
    }

    public int getUltimoCliente() {
        Connection con = ConexaoDB.obterConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int cliente_id = 0;

        try {
            stmt = con.prepareStatement("SELECT MAX(id) as id FROM CLIENTES;");
            rs = stmt.executeQuery();

            while (rs.next()) {
                cliente_id = rs.getInt("id");

            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexaoDB.fecharConexao(con, stmt, rs);
        }
        return cliente_id;
    }

    public Cliente getCliente(int id) {
        Connection con = ConexaoDB.obterConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente c = new Cliente();

        try {
            stmt = con.prepareStatement("SELECT * FROM CLIENTES WHERE id = " + id);
            rs = stmt.executeQuery();

            rs.next();

            c.setId(rs.getInt("id"));
            c.setNome(rs.getString("nome"));
            c.setCpf(rs.getString("cpf"));
            c.setTelefone(rs.getString("telefone"));
            c.setEmail(rs.getString("email"));
            c.setSenha(rs.getString("senha"));
            c.setGenero(rs.getString("genero"));
            c.setData_nascimento(rs.getDate("data_nascimento"));

        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexaoDB.fecharConexao(con, stmt, rs);
        }
        return c;
    }

    public void alterarCliente(Cliente c) {
        Connection con = ConexaoDB.obterConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("update clientes set nome = ?, telefone = ?, senha = ?, genero = ?, data_nascimento = ?  where id = ?;");

            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getTelefone());
            stmt.setString(3, c.getSenha());
            stmt.setString(4, c.getGenero());
            stmt.setDate(5, c.getData_nascimento());
            stmt.setInt(6, c.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexaoDB.fecharConexao(con, stmt);
        }
    }

    public Cliente getCliente(String email, String senha) {
        Connection con = ConexaoDB.obterConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cliente c = null;

        try {
            stmt = con.prepareStatement("select * from clientes where email = '" + email + "' and senha = '" + senha + "';");
            rs = stmt.executeQuery();

            rs.next(); //Vá para a última linha do resultSet:
            int rows = rs.getRow(); //Pegue o número da linha

            if (rows == 1) {
                c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setEmail(rs.getString("email"));
                c.setSenha(rs.getString("senha"));
                c.setTelefone(rs.getString("telefone"));
                c.setGenero(rs.getString("genero"));
                c.setData_nascimento(rs.getDate("data_nascimento"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConexaoDB.fecharConexao(con, stmt, rs);
        }
        return c;
    }
}
