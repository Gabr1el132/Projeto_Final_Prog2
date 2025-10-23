package com.mycompany;

import dao.Dao;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import modelo.Usuario;
import validacao.ILimpaClasse;
import validacao.IValidaClasse;
import validacao.IVoltaClasse;

public class UsuarioController implements IValidaClasse, ILimpaClasse, IVoltaClasse{        
    @FXML
    private TextField campologin;
    
    @FXML
    private TextField camponome;
    
    @FXML
    private TextField camposenha;
    
    @FXML 
    private Label labelMensagem;
    
    private Usuario usuario;
    
    @FXML
    private void initialize(){
        Usuario usuario = new Usuario();
    }
    
    @FXML
    private void registrarUsuario(){
        
        labelMensagem.setText("");
            
        if(!validar()){
            mostrarMensagem("Todos os campos devem ser preenchidos!", Color.RED);
            return;
        }
            
        String login = campologin.getText();
        Dao<Usuario> dao = new Dao<Usuario>(Usuario.class);
        Usuario usuarioExistente = dao.buscarPorChave("login", login);
        
        if (usuarioExistente != null){
            mostrarMensagem("Já existe um usuário com esse login!", Color.RED);
            return;
        }
            
        usuario = new Usuario(camponome.getText(), camposenha.getText(), login);
        dao.inserir(usuario);
        limparCampos();
        mostrarMensagem("  Usuário registrado com sucesso!", Color.GREEN);
    }
    
    @Override
    public boolean validar() {
        return !campologin.getText().isEmpty() && 
               !camponome.getText().isEmpty() && 
               !camposenha.getText().isEmpty();
    }
    
    @Override
    @FXML
    public void voltar() throws IOException{
        App.setRoot("telacontroledefrota");
    }
    
    @Override
    public void limparCampos(){
        campologin.clear();
        camponome.clear();
        camposenha.clear();
    }
    
    private void mostrarMensagem(String mensagem, Color cor){
        labelMensagem.setText(mensagem);
        labelMensagem.setTextFill(cor);
    }
}