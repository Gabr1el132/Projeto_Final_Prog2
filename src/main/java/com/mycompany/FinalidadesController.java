package com.mycompany;

import java.io.IOException;
import javafx.fxml.FXML;
import validacao.IVoltaClasse;

public class FinalidadesController implements IVoltaClasse{
    @FXML
    private void registrarMotorista() throws IOException{
        App.setRoot("telaregistrarmotorista");
    }
    
    @FXML
    private void registrarVeiculo() throws IOException{
        App.setRoot("telaregistrarveiculo");
    }
    
    @FXML
    private void registrarRetirada() throws IOException{
        App.setRoot("telaregistrarretirada");
    }
    
    @FXML
    private void registrarDevolucao() throws IOException{
        App.setRoot("telaregistrardevolucao");
    }
    
    @FXML
    private void listarRetiradas() throws IOException{
        App.setRoot("telalistaretiradas");
    }
    
    @Override
    @FXML
    public void voltar() throws IOException{
        App.setRoot("telaentrar");
    }
}