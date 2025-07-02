package org.example.dao;

import org.example.model.Pessoa;
import org.example.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    public void inserir(Pessoa pessoa) {
        String sql = "INSERT INTO Pessoa (nome, email) VALUES (?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getEmail());
            stmt.executeUpdate();
            System.out.println("Pessoa inserida com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir pessoa: " + e.getMessage());
        }
    }

    public List<Pessoa> listar() {
        List<Pessoa> pessoas = new ArrayList<>();
        String sql = "SELECT * FROM Pessoa";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Pessoa p = new Pessoa(rs.getInt("id"), rs.getString("nome"), rs.getString("email"));
                pessoas.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar pessoas: " + e.getMessage());
        }
        return pessoas;
    }

    public void atualizar(Pessoa pessoa) {
        String sql = "UPDATE Pessoa SET nome = ?, email = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getEmail());
            stmt.setInt(3, pessoa.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Pessoa atualizada com sucesso");
            } else {
                System.out.println("Erro Pessoa com ID " + pessoa.getId() + " não encontrada.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar pessoa " + e.getMessage());
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM Pessoa WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Pessoa excluída com sucesso!");
            } else {
                System.out.println("Erro Pessoa com ID " + id + " não encontrada.");
            }

        } catch (SQLException e) {
            System.out.println("Erro na exclusão da pessoa: " + e.getMessage());
        }
    }

}
