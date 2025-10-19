package com.mycompany;

import dao.Dao;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import modelo.Usuario;

public class LoginController {
    @FXML
    private TextField campologin;
    
    @FXML
    private TextField camposenha;
    
    @FXML 
    private Label labelMensagem;
    
    @FXML
    private void initialize(){
        labelMensagem.setText("");
    }
    
    @FXML
    private void entrar(){
        String login = campologin.getText();
        String senha = camposenha.getText();
        
        if (login.isEmpty()||senha.isEmpty()){
            mostrarMensagem("Preencha login e senha!", Color.RED);
            return;
        }
        
        Dao<Usuario> dao = new Dao<>(Usuario.class);
        Usuario usuario = dao.buscarPorChave("login", login);
        
        if (usuario == null){
            mostrarMensagem("Usuário não encontrado!", Color.RED);
            return;
        }
        
        if (usuario.getSenha().equals(senha)){
            //App.setRoot("telafuncionalidades");
        }
        else{
            mostrarMensagem("Senha incorreta!", Color.RED);
        }
    }
    
    @FXML
    private void voltar() throws IOException{
        App.setRoot("telacontroledefrota");
    }
    
    private void mostrarMensagem(String mensagem, Color cor){
    labelMensagem.setText(mensagem);
    labelMensagem.setTextFill(cor);
    }
}