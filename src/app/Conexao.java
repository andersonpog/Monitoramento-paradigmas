package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String url = "jdbc:postgresql://localhost:5432/Monitoramento";
    private static final String usuario = "postgres";

    private static final String senha = "paradigmas";

    private static Connection conexao;

    public static Connection conectar() throws SQLException {
        conexao = DriverManager.getConnection(url, usuario, senha);
        return conexao;
    }

    public static void desconectar() throws SQLException {
        conexao.close();
    }
}
