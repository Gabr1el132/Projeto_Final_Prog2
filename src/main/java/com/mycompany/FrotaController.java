package com.mycompany;

import java.io.IOException;
import javafx.fxml.FXML;

public class FrotaController {
    
    @FXML
    private void registrarUsuario() throws IOException{
        App.setRoot("telaregistrarusuario");
    }
    
    @FXML
    private void entrar() throws IOException{
        App.setRoot("telaentrar");
    }
}