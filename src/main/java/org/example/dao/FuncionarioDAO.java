package org.example.dao;

import org.example.model.Funcionario;
import org.example.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {

    public void inserir(Funcionario funcionario) {
        try (Connection conn = Conexao.conectar()) {

            // Regra 1: Verificar se a pessoa existe
            String verificarPessoa = "SELECT id FROM Pessoa WHERE id = ?";
            PreparedStatement stmtVerifica = conn.prepareStatement(verificarPessoa);
            stmtVerifica.setInt(1, funcionario.getId());
            ResultSet rs = stmtVerifica.executeQuery();

            if (!rs.next()) {
                System.out.println("Erro: Pessoa com ID " + funcionario.getId() + " não existe.");
                return;
            }

            String sql = "INSERT INTO Funcionario (id, matricula, departamento) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, funcionario.getId());
            stmt.setString(2, funcionario.getMatricula());
            stmt.setString(3, funcionario.getDepartamento());
            stmt.executeUpdate();

            System.out.println("Funcionário inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir funcionário: " + e.getMessage());
        }
    }

    public void excluir(int idFuncionario) {
        try (Connection conn = Conexao.conectar()) {

            // Regra 3: Não excluir funcionário com projeto vinculado
            String verifica = "SELECT * FROM Projeto WHERE id_funcionario = ?";
            PreparedStatement stmtVerifica = conn.prepareStatement(verifica);
            stmtVerifica.setInt(1, idFuncionario);
            ResultSet rs = stmtVerifica.executeQuery();

            if (rs.next()) {
                System.out.println("Erro: Não é possível excluir funcionário com projetos vinculados.");
                return;
            }

            String sql = "DELETE FROM Funcionario WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idFuncionario);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Funcionário excluído com sucesso");
            } else {
                System.out.println("Erro: Funcionário com ID " + idFuncionario + " nao encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir funcionário: " + e.getMessage());
        }
    }


    public List<Funcionario> listar() {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT f.id, f.matricula, f.departamento, p.nome, p.email " +
                "FROM Funcionario f JOIN Pessoa p ON f.id = p.id";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Funcionario f = new Funcionario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("matricula"),
                        rs.getString("departamento")
                );
                funcionarios.add(f);
            }

        } catch (SQLException e) {
            System.out.println("Erro na listagem de funcionários: " + e.getMessage());
        }

        return funcionarios;
    }

    public void atualizar(Funcionario funcionario) {
        String sql = "UPDATE Funcionario SET matricula = ?, departamento = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, funcionario.getMatricula());
            stmt.setString(2, funcionario.getDepartamento());
            stmt.setInt(3, funcionario.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Funcionário atualizado");
            } else {
                System.out.println("Erro: Funcionário com ID " + funcionario.getId() + " não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro na atualização do funcionário: " + e.getMessage());
        }
    }
}
