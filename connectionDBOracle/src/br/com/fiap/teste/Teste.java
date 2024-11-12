package br.com.fiap.teste;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.Usuario;
import br.com.fiap.repository.UsuarioDAO;

public class Teste {

    public static void main(String[] args) {
        // Testar conexão e realizar operações de CRUD
        try (Connection connection = ConnectionFactory.getConnection()) {
            // Criar o DAO de usuário
            UsuarioDAO usuarioDAO = new UsuarioDAO(connection);

            int opcao;
            do {
                // Exibir o menu
                opcao = exibirMenu();
                
                switch (opcao) {
                    case 1:
                        // Inserir um novo usuário
                        inserirUsuario(usuarioDAO);
                        break;
                    case 2:
                        // Listar todos os usuários
                        listarUsuarios(usuarioDAO);
                        break;
                    case 3:
                        // Atualizar um usuário
                        atualizarUsuario(usuarioDAO);
                        break;
                    case 4:
                        // Excluir um usuário
                        excluirUsuario(usuarioDAO);
                        break;
                    case 5:
                        // Sair
                        JOptionPane.showMessageDialog(null, "Saindo do sistema...");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida! Tente novamente.");
                }
            } while (opcao != 5);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de conexão com o banco de dados: " + e.getMessage());
        }
    }

    private static int exibirMenu() {
        // Exibir o menu usando uma caixa de diálogo de opção
        String menu = "Escolha uma opção:\n" +
                      "1 - Inserir um novo usuário\n" +
                      "2 - Listar todos os usuários\n" +
                      "3 - Atualizar um usuário\n" +
                      "4 - Excluir um usuário\n" +
                      "5 - Sair";

        String opcaoString = JOptionPane.showInputDialog(null, menu, "Menu", JOptionPane.INFORMATION_MESSAGE);
        try {
            return Integer.parseInt(opcaoString);
        } catch (NumberFormatException e) {
            return -1; // Caso o usuário digite algo que não seja um número
        }
    }

    private static void inserirUsuario(UsuarioDAO usuarioDAO) {
        String nome = JOptionPane.showInputDialog("Informe o nome do usuário:");
        String email = JOptionPane.showInputDialog("Informe o email do usuário:");

        if (nome != null && email != null && !nome.trim().isEmpty() && !email.trim().isEmpty()) {
            Usuario novoUsuario = new Usuario(nome, email);

            try {
                usuarioDAO.insert(novoUsuario);
                JOptionPane.showMessageDialog(null, "Usuário inserido com ID: " + novoUsuario.getId());
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao inserir o usuário: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nome e email não podem ser vazios.");
        }
    }

    private static void listarUsuarios(UsuarioDAO usuarioDAO) {
        try {
            List<Usuario> usuarios = usuarioDAO.selectAll();
            if (usuarios.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum usuário encontrado.");
            } else {
                StringBuilder lista = new StringBuilder("Lista de Usuários:\n");
                for (Usuario u : usuarios) {
                    lista.append(u.getId()).append(": ").append(u.getNome()).append(" - ").append(u.getEmail()).append("\n");
                }
                JOptionPane.showMessageDialog(null, lista.toString());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar os usuários: " + e.getMessage());
        }
    }

    private static void atualizarUsuario(UsuarioDAO usuarioDAO) {
        String idString = JOptionPane.showInputDialog("Informe o ID do usuário que deseja atualizar:");
        
        try {
            long id = Long.parseLong(idString);
            Usuario usuario = usuarioDAO.selectById(id);

            if (usuario == null) {
                JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
                return;
            }

            String nome = JOptionPane.showInputDialog("Informe o novo nome do usuário:");
            String email = JOptionPane.showInputDialog("Informe o novo email do usuário:");

            if (nome != null && email != null && !nome.trim().isEmpty() && !email.trim().isEmpty()) {
                usuario.setNome(nome);
                usuario.setEmail(email);
                usuarioDAO.update(usuario);
                JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso: " + usuario.getNome());
            } else {
                JOptionPane.showMessageDialog(null, "Nome e email não podem ser vazios.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o usuário: " + e.getMessage());
        }
    }

    private static void excluirUsuario(UsuarioDAO usuarioDAO) {
        String idString = JOptionPane.showInputDialog("Informe o ID do usuário que deseja excluir:");
        
        try {
            long id = Long.parseLong(idString);
            usuarioDAO.delete(id);
            JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso! ID: " + id);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID inválido.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir o usuário: " + e.getMessage());
        }
    }
}
