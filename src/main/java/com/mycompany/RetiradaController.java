package com.mycompany;

import dao.Dao;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import modelo.Motorista;
import modelo.Retirada;
import modelo.Veiculo;
import validacao.IValidaClasse;
import validacao.ILimpaClasse;
import validacao.IMostraMensagem;
import validacao.IVoltaClasse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class RetiradaController implements IValidaClasse, ILimpaClasse, IMostraMensagem, IVoltaClasse {
    
    @FXML
    private ComboBox<Motorista> comboMotoristas;
    @FXML
    private ComboBox<Veiculo> comboVeiculos;
    @FXML
    private DatePicker datePickerData;
    @FXML
    private Label labelMensagem;
    
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    @FXML
    private void initialize() {
        labelMensagem.setText("");
        carregarMotoristas();
        carregarVeiculos();
    }
    
    private void carregarMotoristas() {
        Dao<Motorista> daoMotorista = new Dao<>(Motorista.class);
        List<Motorista> motoristas = daoMotorista.listarTodos();
        comboMotoristas.getItems().addAll(motoristas);
    }
    
    private void carregarVeiculos() {
        Dao<Veiculo> daoVeiculo = new Dao<>(Veiculo.class);
        List<Veiculo> veiculos = daoVeiculo.listarTodos();
        comboVeiculos.getItems().addAll(veiculos);
    }
    
    @FXML
    private void registrarRetirada() {
        labelMensagem.setText("");
        
        if (!validar()) {
            mostrarMensagem("Selecione um motorista, um veículo e uma data!", Color.RED);
            return;
        }
        
        Motorista motoristaSelecionado = comboMotoristas.getValue();
        Veiculo veiculoSelecionado = comboVeiculos.getValue();
        LocalDate dataSelecionada = datePickerData.getValue();
        
        String dataFormatada = dataSelecionada.format(formatter);
        
        Dao<Retirada> daoRetirada = new Dao<>(Retirada.class);
        List<Retirada> todasRetiradas = daoRetirada.listarTodos();
        for (Retirada r : todasRetiradas) {
            if (r.getVeiculoId().equals(veiculoSelecionado.getEmplacamento()) && 
                r.getData().equals(dataFormatada) && 
                !r.isDevolvido()) {
                mostrarMensagem("Este veículo já está alugado nesta data!", Color.RED);
                return;
            }
        }
        
        Retirada retirada = new Retirada(
            motoristaSelecionado.getCodigo(), 
            veiculoSelecionado.getEmplacamento(), 
            dataFormatada
        );
        daoRetirada.inserir(retirada);
        
        mostrarMensagem("Retirada registrada com sucesso!", Color.GREEN);
        limparCampos();
    }
    
    @Override
    public boolean validar() {
        return comboMotoristas.getValue() != null && 
               comboVeiculos.getValue() != null && 
               datePickerData.getValue() != null;
    }
    
    @Override
    public void limparCampos() {
        comboMotoristas.setValue(null);
        comboVeiculos.setValue(null);
        datePickerData.setValue(null);
    }
    
    @Override
    public void mostrarMensagem(String mensagem, Color cor) {
        labelMensagem.setText(mensagem);
        labelMensagem.setTextFill(cor);
    }
    
    @Override
    @FXML
    public void voltar() throws IOException {
        App.setRoot("telafinalidades"); 
    }
}