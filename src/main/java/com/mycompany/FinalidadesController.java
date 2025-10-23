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
    private void registrarRetirada() throws IOException{
        App.setRoot("telaregistrarretirada");
    }
    
    @Override
    @FXML
    public void voltar() throws IOException{
        App.setRoot("telaentrar");
    }
}