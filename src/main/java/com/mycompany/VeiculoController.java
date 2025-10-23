package com.mycompany;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import modelo.Veiculo;
import validacao.ILimpaClasse;
import validacao.IValidaClasse;
import validacao.IVoltaClasse;

public class VeiculoController implements IValidaClasse, ILimpaClasse, IVoltaClasse{
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
    
    private void registrarVeiculo(){
        //código
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
    
    private void mostrarMensagem(String mensagem, Color cor){
        labelMensagem.setText(mensagem);
        labelMensagem.setTextFill(cor);
    }
    
    @Override
    @FXML
    public void voltar() throws IOException{
        App.setRoot("telafinalidades");
    }
}
