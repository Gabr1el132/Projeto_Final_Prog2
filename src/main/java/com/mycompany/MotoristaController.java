package com.mycompany;

import dao.Dao;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import modelo.Motorista;
import validacao.ILimpaClasse;
import validacao.IMostraMensagem;
import validacao.IValidaClasse;
import validacao.IVoltaClasse;

public class MotoristaController implements IValidaClasse, ILimpaClasse, IVoltaClasse, IMostraMensagem{
    @FXML
    private TextField campocodigo;
    
    @FXML
    private TextField camponome;
    
    @FXML
    private TextField campocnh;
    
    @FXML
    private TextField camposetor;
    
    @FXML 
    private Label labelMensagem;
    
    private Motorista motorista;
    
    @FXML
    private void initialize(){
        Motorista motorista = new Motorista();          
    }
    
    @FXML
    private void registrarMotorista(){
        labelMensagem.setText("");
        
        if(!validar()){
            mostrarMensagem("Preencha todos os campos", Color.RED);
            return;
        }
        
        String codigo = campocodigo.getText();
        Dao<Motorista> dao = new Dao<Motorista>(Motorista.class);
        Motorista motoristaExistente = dao.buscarPorChave("codigo", codigo);
        
        if (motoristaExistente != null){
            mostrarMensagem("Já existe um motorista com esse codigo!", Color.RED);
            return;
        }
            
        motorista = new Motorista(camponome.getText(), campocnh.getText(), codigo, camposetor.getText());
        dao.inserir(motorista);
        limparCampos();
        mostrarMensagem("  Motorista registrado com sucesso!", Color.GREEN);
    }
    
    @Override
    public boolean validar() {
        return !campocodigo.getText().isEmpty() && 
               !camponome.getText().isEmpty() && 
               !campocnh.getText().isEmpty() &&
               !camposetor.getText().isEmpty();
    }
    
    @Override
    public void limparCampos(){
        campocodigo.clear();
        camponome.clear();
        campocnh.clear();
        camposetor.clear();
    }
    
    @Override
    public void mostrarMensagem(String mensagem, Color cor){
        labelMensagem.setText(mensagem);
        labelMensagem.setTextFill(cor);
    }
    
    @Override
    @FXML
    public void voltar() throws IOException{
        App.setRoot("telafinalidades");
    }
    
}