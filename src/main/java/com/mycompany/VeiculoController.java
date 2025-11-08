package com.mycompany;

import dao.Dao;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import modelo.Veiculo;
import validacao.ILimpaClasse;
import validacao.IMostraMensagem;
import validacao.IValidaClasse;
import validacao.IVoltaClasse;

public class VeiculoController implements IValidaClasse, ILimpaClasse, IVoltaClasse, IMostraMensagem{
    @FXML
    private TextField campoemplacamento;
    
    @FXML
    private TextField campomarca;
    
    @FXML
    private TextField campomodelo;
    
    private Veiculo veiculo;
    
    @FXML 
    private Label labelMensagem;
    
    private void initialize(){
        Veiculo veiculo = new Veiculo();
    }
    
    @FXML
    private void registrarVeiculo(){
        labelMensagem.setText("");
        
        if(!validar()){
            mostrarMensagem("Preencha todos os campos", Color.RED);
            return;
        }
        
        String emplacamento = campoemplacamento.getText();
        Dao<Veiculo> dao = new Dao<Veiculo>(Veiculo.class);
        Veiculo veiculoExistente = dao.buscarPorChave("emplacamento", emplacamento);
        
        if (veiculoExistente != null){
            mostrarMensagem("Já existe um carro com essa placa!", Color.RED);
            return;
        }
        
        veiculo = new Veiculo(campoemplacamento.getText(), campomarca.getText(), campomodelo.getText());
        dao.inserir(veiculo);
        limparCampos();
        mostrarMensagem("Veículo registrado com sucesso!", Color.GREEN);
    }    
    
     @Override
    public boolean validar() {
        return !campoemplacamento.getText().isEmpty() && 
               !campomarca.getText().isEmpty() && 
               !campomodelo.getText().isEmpty();
    }
    
    @Override
    public void limparCampos(){
        campoemplacamento.clear();
        campomarca.clear();
        campomodelo.clear();
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