/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package telaCadastro;

import conexao.Conexao;
import dao.DaoUsuario;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;
import view.FrmLogin;
import view.FrmMenuPrincipal;


public class Aplicativo {

    public static void main(String[] args) {
        FrmLogin login = new FrmLogin();
        login.setVisible(true);
    }
}
