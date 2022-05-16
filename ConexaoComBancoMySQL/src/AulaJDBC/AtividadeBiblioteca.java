package AulaJDBC;

import javax.management.Query;
import java.sql.*;
import java.util.Scanner;

public class AtividadeBiblioteca {

    public static void main(String[] args) throws SQLException {
        // Criação da Classe BancoDados
        BancoDados bd = new BancoDados();

        // Endereço para conexão ao BD
        String urlBD = "jdbc:mysql://localhost:3306/Biblioteca2?user=root&password=banco";

        // Driver utilizado para acesso ao BD
        String driver = "com.mysql.cj.jdbc.Driver";

        // Conexão ao BD
        System.out.println(bd.conectar(urlBD, driver));

        System.out.println("\nMenu Inicial;");
        System.out.println("Qual ação deseja realizar no Banco de Dados:");
        System.out.println("Inserir dados = 1");
        System.out.println("Consulta completa = 2");
        Scanner entradaUSER = new Scanner(System.in);
        System.out.print("Digite o numero referente ao menu: ");
        int escolhaInicial = Integer.parseInt(entradaUSER.nextLine());

        if (escolhaInicial == 1){
            System.out.println("Escolha 1");
            System.out.println("Menu de Insert;\n");
            System.out.println("Em qual tabela deseja inserir os dados:");
            System.out.println("Autor = 1");
            System.out.println("Categoria = 2");
            System.out.println("Editora = 3");
            System.out.println("Livro = 4");
            System.out.println("LivroAutor = 5");
            System.out.println("Digite o numero referente a tabela: ");
            Scanner escolhaTabela = new Scanner(System.in);
            int escolhaTb = Integer.parseInt(escolhaTabela.nextLine());

            if (escolhaTb == 1){
                String scanAutor = bd.inserirAutor();
                System.out.println(scanAutor);
                bd.inserirAlterarExcluir(scanAutor);
            } else if (escolhaTb == 2) {
                String scanCategoria = bd.inserirCategoria();
                System.out.println(scanCategoria);
                bd.inserirAlterarExcluir(scanCategoria);
            } else if (escolhaTb == 3) {
                String scanEditora = bd.inserirEditora();
                System.out.println(scanEditora);
                bd.inserirAlterarExcluir(scanEditora);
            } else if (escolhaTb == 4) {
                String scanLivro = bd.inserirLivro();
                System.out.println(scanLivro);
                bd.inserirAlterarExcluir(scanLivro);
            } else if (escolhaTb == 5) {
                String scanLivroAutor = bd.inserirLivroAutor();
                System.out.println(scanLivroAutor);
                bd.inserirAlterarExcluir(scanLivroAutor);
            }else {
                System.out.println("Escolha insiponivel!");
            }

        }else if(escolhaInicial == 2){
            System.out.println("Consulta completa:");
            String query=
                    "select livro.isbn as 'ISBN', livro.titulo as 'Título', livro.ano as 'Ano', editora.nome as 'Editora', \n" +
                    "concat(autor.nome, ' (' ,autor.nacionalidade, ')') as  'Autor/Nascionalidade',categoria.tipo_categoria as 'Categoria'\n" +
                    "from livro, editora, categoria, autor, livroautor\n" +
                    "where livro.fk_editora = editora.id\n" +
                    "and livro.fk_categoria = categoria.id\n" +
                    "and livroautor.fk_autor = autor.id\n" +
                    "and livroautor.fk_livro = livro.isbn\n" +
                    "order by livro.titulo";
            ResultSet resultado = bd.consultar(query);
            if (resultado != null){
                while (resultado.next()) {
                    System.out.println("IBSN: "+resultado.getString("isbn")
                            +"\t Título: "+resultado.getString("Título")
                            +"\t Ano: "+resultado.getString("ano")
                            +"\t Editora: "+ resultado.getString("editora")
                            +"\t Autor/Nascionalidade: "+ resultado.getString("Autor/Nascionalidade")
                            +"\t Categoria: "+ resultado.getString("Categoria"));
                }
            }
        }else {
            System.out.println("Escolha insiponivel!");
        }
    }
}

