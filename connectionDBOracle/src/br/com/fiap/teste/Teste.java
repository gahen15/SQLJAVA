package br.com.fiap.teste;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.Usuario;
import br.com.fiap.repository.UsuarioDAO;

public class Teste {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Testar conexão e realizar operações de CRUD
        try (Connection connection = ConnectionFactory.getConnection()) {
            // Criar o DAO de usuário
            UsuarioDAO usuarioDAO = new UsuarioDAO(connection);

            int opcao;
            do {
                // Exibir o menu
                exibirMenu();
                
                // Capturar a opção do usuário
                opcao = scanner.nextInt();
                scanner.nextLine(); // Consumir a quebra de linha

                switch (opcao) {
                    case 1:
                        // Inserir um novo usuário
                        inserirUsuario(usuarioDAO, scanner);
                        break;
                    case 2:
                        // Listar todos os usuários
                        listarUsuarios(usuarioDAO);
                        break;
                    case 3:
                        // Atualizar um usuário
                        atualizarUsuario(usuarioDAO, scanner);
                        break;
                    case 4:
                        // Excluir um usuário
                        excluirUsuario(usuarioDAO, scanner);
                        break;
                    case 5:
                        // Sair
                        System.out.println("Saindo do sistema...");
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } while (opcao != 5);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static void exibirMenu() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1 - Inserir um novo usuário");
        System.out.println("2 - Listar todos os usuários");
        System.out.println("3 - Atualizar um usuário");
        System.out.println("4 - Excluir um usuário");
        System.out.println("5 - Sair");
        System.out.print("Opção: ");
    }

    private static void inserirUsuario(UsuarioDAO usuarioDAO, Scanner scanner) {
        System.out.print("Informe o nome do usuário: ");
        String nome = scanner.nextLine();
        
        System.out.print("Informe o email do usuário: ");
        String email = scanner.nextLine();
        
        Usuario novoUsuario = new Usuario(nome, email);
        
        try {
            usuarioDAO.insert(novoUsuario);
            System.out.println("Usuário inserido com ID: " + novoUsuario.getId());
        } catch (SQLException e) {
            System.out.println("Erro ao inserir o usuário: " + e.getMessage());
        }
    }

    private static void listarUsuarios(UsuarioDAO usuarioDAO) {
        try {
            List<Usuario> usuarios = usuarioDAO.selectAll();
            if (usuarios.isEmpty()) {
                System.out.println("Nenhum usuário encontrado.");
            } else {
                System.out.println("\nLista de Usuários:");
                for (Usuario u : usuarios) {
                    System.out.println(u.getId() + ": " + u.getNome() + " - " + u.getEmail());
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar os usuários: " + e.getMessage());
        }
    }

    private static void atualizarUsuario(UsuarioDAO usuarioDAO, Scanner scanner) {
        System.out.print("Informe o ID do usuário que deseja atualizar: ");
        long id = scanner.nextLong();
        scanner.nextLine(); // Consumir a quebra de linha
        
        try {
            Usuario usuario = usuarioDAO.selectById(id);
            if (usuario == null) {
                System.out.println("Usuário não encontrado.");
                return;
            }

            System.out.print("Informe o novo nome do usuário: ");
            String nome = scanner.nextLine();
            System.out.print("Informe o novo email do usuário: ");
            String email = scanner.nextLine();

            usuario.setNome(nome);
            usuario.setEmail(email);
            usuarioDAO.update(usuario);
            System.out.println("Usuário atualizado com sucesso: " + usuario.getNome());

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o usuário: " + e.getMessage());
        }
    }

    private static void excluirUsuario(UsuarioDAO usuarioDAO, Scanner scanner) {
        System.out.print("Informe o ID do usuário que deseja excluir: ");
        long id = scanner.nextLong();
        scanner.nextLine(); // Consumir a quebra de linha
        
        try {
            usuarioDAO.delete(id);
            System.out.println("Usuário excluído com sucesso! ID: " + id);
        } catch (SQLException e) {
            System.out.println("Erro ao excluir o usuário: " + e.getMessage());
        }
    }
}
