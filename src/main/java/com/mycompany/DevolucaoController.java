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
import java.util.stream.Collectors;

public class DevolucaoController implements IValidaClasse, ILimpaClasse, IMostraMensagem, IVoltaClasse {
    
    @FXML
    private ComboBox<Retirada> comboRetiradas;
    @FXML
    private DatePicker datePickerDevolucao;
    @FXML
    private Label labelDetalhes;
    @FXML
    private Label labelMensagem;
    
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    @FXML
    private void initialize() {
        labelMensagem.setText("");
        labelDetalhes.setText("");
        carregarRetiradasAtivas();
    }
    
    private void carregarRetiradasAtivas() {
        Dao<Retirada> daoRetirada = new Dao<>(Retirada.class);
        List<Retirada> todasRetiradas = daoRetirada.listarTodos();
        
        List<Retirada> retiradasAtivas = todasRetiradas.stream()
            .filter(r -> !r.isDevolvido())
            .collect(Collectors.toList());
        
        comboRetiradas.getItems().clear();
        comboRetiradas.getItems().addAll(retiradasAtivas);
    }
    
    @FXML
    private void selecionarRetirada() {
        Retirada retiradaSelecionada = comboRetiradas.getValue();
        if (retiradaSelecionada != null) {
            Dao<Motorista> daoMotorista = new Dao<>(Motorista.class);
            Dao<Veiculo> daoVeiculo = new Dao<>(Veiculo.class);
            
            Motorista motorista = daoMotorista.buscarPorChave("codigo", retiradaSelecionada.getMotoristaId());
            Veiculo veiculo = daoVeiculo.buscarPorChave("emplacamento", retiradaSelecionada.getVeiculoId());
            
            if (motorista != null && veiculo != null) {
                labelDetalhes.setText(
                    "Motorista: " + motorista.getNome() + " (" + motorista.getCodigo() + ")\n" +
                    "Veículo: " + veiculo.getMarca() + " " + veiculo.getModelo() + " (" + veiculo.getEmplacamento() + ")\n" +
                    "Data da Retirada: " + retiradaSelecionada.getData() + "\n" +
                    "Data da Devolução: " + (retiradaSelecionada.getDataDevolucao() != null && !retiradaSelecionada.getDataDevolucao().isEmpty() ? retiradaSelecionada.getDataDevolucao() : "Não devolvido")
                );
            }
        }
    }
    
    @FXML
    private void registrarDevolucao() {
        labelMensagem.setText("");
        
        if (!validar()) {
            mostrarMensagem("Selecione uma retirada e informe a data da devolução!", Color.RED);
            return;
        }
        
        Retirada retiradaSelecionada = comboRetiradas.getValue();
        LocalDate dataDevolucao = datePickerDevolucao.getValue();
        
        if (dataDevolucao == null) {
            mostrarMensagem("Selecione a data da devolução!", Color.RED);
            return;
        }
        
        String dataDevolucaoFormatada = dataDevolucao.format(formatter);
        
        // Marcar como devolvida e salvar data da devolução
        retiradaSelecionada.setDevolvido(true);
        retiradaSelecionada.setDataDevolucao(dataDevolucaoFormatada);
        
        // Atualizar no banco
        Dao<Retirada> daoRetirada = new Dao<>(Retirada.class);
        daoRetirada.alterar("id", retiradaSelecionada.getId(), retiradaSelecionada);
        
        // Remover o item do ComboBox
        comboRetiradas.getItems().remove(retiradaSelecionada);
        
        mostrarMensagem("Devolução registrada com sucesso!", Color.GREEN);
        limparCampos();
    }
    
    @Override
    public boolean validar() {
        return comboRetiradas.getValue() != null && 
               datePickerDevolucao.getValue() != null;
    }
    
    @Override
    public void limparCampos() {
        comboRetiradas.setValue(null);
        datePickerDevolucao.setValue(null);
        labelDetalhes.setText("");
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