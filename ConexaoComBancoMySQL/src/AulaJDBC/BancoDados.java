package AulaJDBC;

import java.sql.*;
import java.util.Scanner;

public class BancoDados {
    public Connection conexao;

    public String conectar(String URL, String Driver) {
        try {
            Class.forName(Driver);
            conexao = (Connection) DriverManager.getConnection(URL);
            return "Conectado";
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return "Não foi Possível Conectar ";
        }
    }
    public int inserirAlterarExcluir(String query) {
        int linhas = 0;
        try {
            Statement st = conexao.createStatement();
            linhas = st.executeUpdate(query);
            System.out.println("Operação Efetudada com Sucesso");
            return linhas;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("Operação Não Efetuada");
        }
        return linhas;
    }
    public ResultSet consultar(String query) {
        Statement st;
        ResultSet resultado = null;
        try {
            st = conexao.createStatement();
            resultado = st.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }
    public static String inserirAutor(){
        Scanner entradaUSER = new Scanner(System.in);
        System.out.print("Digite no nome do Autor: ");
        String nome = entradaUSER.nextLine();
        System.out.print("Digite a nacionalidade do Autor: ");
        String nacionalidade = entradaUSER.nextLine();

        return ("INSERT INTO autor values (NULL,'"+nome+"', '"+nacionalidade+"')");
    }
    public static String inserirCategoria(){
        Scanner entradaUSER = new Scanner(System.in);
        System.out.print("Digite no nome da Categoria: ");
        String nome = entradaUSER.nextLine();

        return ("INSERT INTO Categoria values (NULL,'"+nome+"')");
    }
    public static String inserirEditora(){
        Scanner entradaUSER = new Scanner(System.in);
        System.out.print("Digite no nome da Editora: ");
        String nome = entradaUSER.nextLine();

        return ("INSERT INTO Editora values (NULL,'"+nome+"')");
    }
    public static String inserirLivro(){
        Scanner entradaUSER = new Scanner(System.in);
        System.out.print("Digite o ISBN: ");
        String isbn = entradaUSER.nextLine();
        System.out.print("Digite o titulo do Livro: ");
        String titulo = entradaUSER.nextLine();
        System.out.print("Digite o ano do Livro: ");
        String ano = entradaUSER.nextLine();
        System.out.print("Digite o ID da editora: ");
        String fk_editora = entradaUSER.nextLine();
        System.out.print("Digite o ID da categoria: ");
        String fk_categoria = entradaUSER.nextLine();

        return ("INSERT INTO Livro values ('"+isbn+"','"+titulo+"','"+ano+"',"+fk_editora+","+fk_categoria+")");
    }
    public static String inserirLivroAutor(){
        Scanner entradaUSER = new Scanner(System.in);
        System.out.print("Digite o ID do Autor: ");
        String fk_autor = entradaUSER.nextLine();
        System.out.print("Digite o ISBN do Livro: ");
        String fk_livro = entradaUSER.nextLine();

        return ("INSERT INTO LivroAutor values (NULL,'"+fk_autor+"', '"+fk_livro+"')");
    }
}