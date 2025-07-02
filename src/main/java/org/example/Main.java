package org.example;

import org.example.dao.*;
import org.example.model.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        PessoaDAO pessoaDAO = new PessoaDAO();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        ProjetoDAO projetoDAO = new ProjetoDAO();

        while (true) {
            System.out.println("\nMenu");
            System.out.println("1 Pessoa");
            System.out.println("2 Funcionário");
            System.out.println("3 Projeto");
            System.out.println("0 Sair");
            System.out.print("Escolha uma opçao: ");
            int opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            if (opcao == 0) {
                System.out.println("Finalizando...");
                break;
            }

            System.out.println("\nOperações");
            System.out.println("1 Inserir");
            System.out.println("2 Listar");
            System.out.println("3 Atualizar");
            System.out.println("4 Excluir");
            System.out.print("Escolha uma opção ");
            int comando = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch (opcao) {

                case 1: // Pessoa
                    switch (comando) {
                        case 1: // Inserir
                            System.out.print("Nome: ");
                            String nome = sc.nextLine();
                            System.out.print("Email: ");
                            String email = sc.nextLine();
                            pessoaDAO.inserir(new Pessoa(nome, email));
                            break;
                        case 2: // Listar
                            List<Pessoa> pessoas = pessoaDAO.listar();
                            for (Pessoa p : pessoas) {
                                System.out.println(p.getId() + " - " + p.getNome() + " - " + p.getEmail());
                            }
                            break;
                        case 3: // Atualizar
                            System.out.print("ID pessoa atualizado: ");
                            int idPessoa = sc.nextInt(); sc.nextLine();
                            System.out.print("Novo nome: ");
                            String novoNome = sc.nextLine();
                            System.out.print("Novo email: ");
                            String novoEmail = sc.nextLine();
                            pessoaDAO.atualizar(new Pessoa(idPessoa, novoNome, novoEmail));
                            break;
                        case 4: // Excluir
                            System.out.print("ID pessoa excluido: ");
                            int idExclusao = sc.nextInt();
                            pessoaDAO.excluir(idExclusao);
                            break;
                        default:
                            System.out.println(" Inválido.");
                    }
                    break;

                case 2: // Funcionário
                    switch (comando) {
                        case 1: // Inserir
                            System.out.print("ID pessoa existente: ");
                            int id = sc.nextInt(); sc.nextLine();
                            System.out.print("Matrícula (F000): ");
                            String matricula = sc.nextLine();
                            System.out.print("Departamento: ");
                            String departamento = sc.nextLine();
                            System.out.print("Nome: ");
                            String nomeFunc = sc.nextLine();
                            System.out.print("Email: ");
                            String emailFunc = sc.nextLine();
                            funcionarioDAO.inserir(new Funcionario(id, nomeFunc, emailFunc, matricula, departamento));
                            break;
                        case 2: // Listar
                            List<Funcionario> funcionarios = funcionarioDAO.listar();
                            for (Funcionario f : funcionarios) {
                                System.out.println(f.getId() + " - " + f.getNome() + " - " + f.getMatricula() + " - " + f.getDepartamento());
                            }
                            break;
                        case 3: // Atualizar
                            System.out.print("ID funcionário: ");
                            int idFunc = sc.nextInt(); sc.nextLine();
                            System.out.print("Nova matrícula: ");
                            String novaMat = sc.nextLine();
                            System.out.print("Novo departamento: ");
                            String novoDep = sc.nextLine();
                            funcionarioDAO.atualizar(new Funcionario(idFunc, "", "", novaMat, novoDep));
                            break;
                        case 4: // Excluir
                            System.out.print("ID funcionário: ");
                            int idExcluir = sc.nextInt();
                            funcionarioDAO.excluir(idExcluir);
                            break;
                        default:
                            System.out.println(" Inválido.");
                    }
                    break;

                case 3: // Projeto
                    switch (comando) {
                        case 1: // Inserir
                            System.out.print("Nome projeto: ");
                            String nomeProj = sc.nextLine();
                            System.out.print("Descrição: ");
                            String desc = sc.nextLine();
                            System.out.print("ID funcionário responsável: ");
                            int idResp = sc.nextInt();
                            projetoDAO.inserir(new Projeto(nomeProj, desc, idResp));
                            break;
                        case 2: // Listar
                            List<Projeto> projetos = projetoDAO.listar();
                            for (Projeto p : projetos) {
                                System.out.println(p.getId() + " - " + p.getNome() + " - " + p.getDescricao() + " - Resp ID: " + p.getIdFuncionario());
                            }
                            break;
                        case 3: // Atualizar
                            System.out.print("ID projeto: ");
                            int idProjeto = sc.nextInt(); sc.nextLine();
                            System.out.print("Novo nome: ");
                            String novoNomeProj = sc.nextLine();
                            System.out.print("Nova descrição: ");
                            String novaDesc = sc.nextLine();
                            System.out.print("Novo ID do funcionário: ");
                            int novoFunc = sc.nextInt();
                            Projeto atualizado = new Projeto(novoNomeProj, novaDesc, novoFunc);
                            atualizado.setId(idProjeto);
                            projetoDAO.atualizar(atualizado);
                            break;
                        case 4: // Excluir
                            System.out.print("ID projeto: ");
                            int idExcluirProj = sc.nextInt();
                            projetoDAO.excluir(idExcluirProj);
                            break;
                        default:
                            System.out.println(" Inválido.");
                    }
                    break;

                default:
                    System.out.println(" Inválida.");
            }
        }
        sc.close();
    }
}
