
package dao; // Data Access Object
import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import model.Usuario;
import conexao.Conexao;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DaoUsuario {
    
    Connection con;
    PreparedStatement pstm = null;
    
    public List<Usuario> getUsuarios() {
        List<Usuario> lista = new ArrayList< >();
        con = new Conexao().conectarBanco();
        try{
            pstm = con.prepareStatement("SELECT * FROM tb_usuario", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            if(rs.first()) {
                do{
                    Usuario user = new Usuario();
                    user.id = rs.getInt("ID");
                    user.nome = rs.getString(("NOME"));
                    user.senha = rs.getString("SENHA");
                    user.email = rs.getString("EMAIL");
                    user.tipo  = rs.getInt("TIPO");

                    lista.add(user);
                }while(rs.next());
            }
            pstm.close();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar dados na tabela <tb_usuario> "+e);
        }
        return lista;
    }
    public void addUsuario(Usuario u) {
        con = new Conexao().conectarBanco();
        try{
            pstm = con.prepareStatement("INSERT INTO tb_usuario (NOME, SENHA, EMAIL, TIPO) VALUES (?, ?, ?, ?)");
            pstm.setString(1, u.nome);
            pstm.setString(2, u.senha);
            pstm.setString(3, u.email);
            pstm.setInt(4, u.tipo);
            pstm.execute();
            System.out.println("BD log: Usuário adicionado com sucesso");
            pstm.close();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir dado na tabela <tb_usuario>\n"+e);
        }
    }
    public void updateUsuario(Usuario u) {
        con = new Conexao().conectarBanco();
        try{
            pstm = con.prepareStatement("UPDATE tb_usuario SET NOME=?, SENHA=?, EMAIL=?, TIPO=? WHERE ID=?");
            pstm.setString(1, u.nome);
            pstm.setString(2, u.senha);
            pstm.setString(3, u.email);
            pstm.setInt(4, u.tipo);
            pstm.setInt(5, u.id);
            pstm.execute();
            System.out.println("BD log: Usuário atualizado com sucesso");
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao atualizar dados da tabela tb_usuario\n");
        }
    }
    public void deleteUsuario(int id) {
        try {
            pstm = con.prepareStatement("DELETE FROM tb_usuario WHERE ID = ?");
            pstm.setInt(1, id);
            pstm.execute();
            System.out.println("BD log: Usuario excluído com sucesso");
            pstm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir dados da tabela tb_cidades\n");
        }
    }
    public int verificarUsuario(Usuario u) {
        int tipo = 0;
        con = new Conexao().conectarBanco();
        try{
            pstm = con.prepareStatement("SELECT tipo FROM tb_usuario WHERE nome=? AND senha=?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            pstm.setString(1, u.nome);
            pstm.setString(2, u.senha);
            ResultSet rs = pstm.executeQuery();
            if(rs.first()){
                tipo = rs.getInt("tipo");
            }
            pstm.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao verificar dados da tabela tb_cidades\n");
        }
        return tipo;
    }
}
