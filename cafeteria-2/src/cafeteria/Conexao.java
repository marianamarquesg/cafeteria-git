package cafeteria;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static String strConexao = "jdbc:postgresql://localhost:5432/cafeteria";
	private static String usuario = "postgres";
	private static String senha = "12345";
	
		public static Connection getConnection() {
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(strConexao, usuario, senha);
			System.out.println("Conexao estabelecida com sucesso!");
		} catch (SQLException e) {
			System.err.println("Erro de SQL na conexao: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Erro na conexao: " + e.getMessage());
		}
		return conn;
	}

	public static void close(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			System.err.println("Erro de SQL no encerramento da conexao: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Erro no encerramento da conexao: " + e.getMessage());
		}
	}
}