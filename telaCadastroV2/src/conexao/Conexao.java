package conexao;

import java.sql.*;
import javax.swing.JOptionPane;
public class Conexao {
    Connection conexao;
    
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/telaCadastroV2";
    private final String usuario = "root";
    private final String senha = "";
    
    public Connection conectarBanco() {
        
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println( "BD log: Conectado no banco de dados com sucesso");
        } catch(ClassNotFoundException drive) {
            JOptionPane.showMessageDialog(null, "Driver não encontrado"+drive);
        } catch(SQLException fonte) {
            JOptionPane.showMessageDialog(null, "Não foi possível se conectar com o Banco de Dados\n"+fonte);
        }
        return conexao;
    }
}
