package org.example.dao;

import org.example.model.Projeto;
import org.example.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO {

    public void inserir(Projeto projeto) {
        try (Connection conn = Conexao.conectar()) {

            // Regra 2: Verificar se funcionário existe
            String verificar = "SELECT id FROM Funcionario WHERE id = ?";
            PreparedStatement check = conn.prepareStatement(verificar);
            check.setInt(1, projeto.getIdFuncionario());
            ResultSet rs = check.executeQuery();

            if (!rs.next()) {
                System.out.println("Erro: Funcionário com ID " + projeto.getIdFuncionario() + " não existe.");
                return;
            }

            String sql = "INSERT INTO Projeto (nome, descricao, id_funcionario) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, projeto.getNome());
            stmt.setString(2, projeto.getDescricao());
            stmt.setInt(3, projeto.getIdFuncionario());
            stmt.executeUpdate();
            System.out.println("Projeto inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir projeto: " + e.getMessage());
        }
    }

    public List<Projeto> listar() {
        List<Projeto> projetos = new ArrayList<>();
        String sql = "SELECT * FROM Projeto";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Projeto p = new Projeto(
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getInt("id_funcionario")
                );
                p.setId(rs.getInt("id"));
                projetos.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar projetos: " + e.getMessage());
        }

        return projetos;
    }

    public void excluir(int idProjeto) {
        String sql = "DELETE FROM Projeto WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Projeto excluído com sucesso!");
            } else {
                System.out.println("Erro: Projeto com ID " + idProjeto + " não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir projeto: " + e.getMessage());
        }
    }


    public void atualizar(Projeto projeto) {
        try (Connection conn = Conexao.conectar()) {

            // Verifica se o funcionário existe (boa prática)
            String verificarFuncionario = "SELECT id FROM Funcionario WHERE id = ?";
            PreparedStatement check = conn.prepareStatement(verificarFuncionario);
            check.setInt(1, projeto.getIdFuncionario());
            ResultSet rs = check.executeQuery();

            if (!rs.next()) {
                System.out.println("Erro: Funcionário com ID " + projeto.getIdFuncionario() + " não existe.");
                return;
            }

            String sql = "UPDATE Projeto SET nome = ?, descricao = ?, id_funcionario = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, projeto.getNome());
            stmt.setString(2, projeto.getDescricao());
            stmt.setInt(3, projeto.getIdFuncionario());
            stmt.setInt(4, projeto.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Projeto atualizado com sucesso!");
            } else {
                System.out.println("Erro: Projeto com ID " + projeto.getId() + " não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar projeto: " + e.getMessage());
        }
    }

}
