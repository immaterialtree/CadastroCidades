/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
    
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Cidade;
import conexao.Conexao;
import javax.swing.JOptionPane;

public class DaoCidade {
    Connection con;
    PreparedStatement pstm;
    
    public List<Cidade> getCidades() {
        List<Cidade> lista = new ArrayList<>();
        con = new Conexao().conectarBanco();
        try {
            pstm = con.prepareStatement("SELECT * FROM tb_cidades", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = pstm.executeQuery();
            if(rs.first()) {
                do {
                    Cidade cid = new Cidade();
                    cid.id = rs.getInt("ID");
                    cid.nome = rs.getString("NOME");
                    cid.uf = rs.getString("UF");
                    cid.cep = rs.getString("CEP");
                    
                    lista.add(cid);
                }while(rs.next());
            }
            pstm.close();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar dados na tabela <tb_cidades>"+e);
        }
        return lista;
    }
    
    public void addCidade(Cidade cidade) {
        con = new Conexao().conectarBanco();
        try{
            pstm = con.prepareStatement("INSERT INTO tb_cidades(NOME, UF, CEP) VALUES (?,?,?)");
            pstm.setString(1, cidade.nome);
            pstm.setString(2, cidade.uf);
            pstm.setString(3, cidade.cep);
            pstm.execute();
            System.out.println("BD log: Cidade salva com sucesso");
            pstm.close();
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar dados na tabela tb_cidades\n"+e);
        }
    }
    
    public void updateCidade(Cidade cidade) {
        con = new Conexao().conectarBanco();
        try {
            pstm = con.prepareStatement("UPDATE tb_cidades SET NOME = ?, UF = ?, CEP = ? WHERE ID = ?;");
            pstm.setString(1, cidade.nome);
            pstm.setString(2, cidade.uf);
            pstm.setString(3, cidade.cep);
            pstm.setInt(4, cidade.id);
            pstm.execute();
            System.out.println("BD log: Cidade atualizada com sucesso");
            pstm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar dados da tabela tb_cidades\n");
        }
    }
    public void deleteCidade(int id) {
        try {
            pstm = con.prepareStatement("DELETE FROM tb_cidades WHERE ID = ?");
            pstm.setInt(1, id);
            pstm.execute();
            System.out.println("BD log: Cidade excluída com sucesso");
            pstm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir dados da tabela tb_cidades\n");
        }
    }
}
