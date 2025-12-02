package com.mycompany;

import dao.Dao;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import modelo.Motorista;
import modelo.Retirada;
import modelo.Veiculo;
import validacao.IMostraMensagem;
import validacao.IVoltaClasse;
import java.util.List;

public class ListaRetiradasController implements IMostraMensagem, IVoltaClasse {
    
    @FXML
    private TableView<Retirada> tabelaRetiradas;
    
    @FXML
    private TableColumn<Retirada, String> colunaMotoristaNome;
    @FXML
    private TableColumn<Retirada, String> colunaMotoristaCodigo;
    @FXML
    private TableColumn<Retirada, String> colunaMotoristaCNH;
    @FXML
    private TableColumn<Retirada, String> colunaMotoristaSetor;
    
    @FXML
    private TableColumn<Retirada, String> colunaVeiculoEmplacamento;
    @FXML
    private TableColumn<Retirada, String> colunaVeiculoMarca;
    @FXML
    private TableColumn<Retirada, String> colunaVeiculoModelo;
    
    @FXML
    private TableColumn<Retirada, String> colunaDataRetirada;
    @FXML
    private TableColumn<Retirada, String> colunaDataDevolucao;
    @FXML
    private TableColumn<Retirada, String> colunaStatus;
    
    @FXML
    private Label labelMensagem;
    @FXML
    private Button botaoAtualizar;
    
    @FXML
    private void initialize() {
        labelMensagem.setText("");
        configurarColunas();
        carregarTodasRetiradas();
    }
    
    private void configurarColunas() {
        colunaMotoristaNome.setCellValueFactory(new PropertyValueFactory<>("motoristaNome"));
        colunaMotoristaCodigo.setCellValueFactory(new PropertyValueFactory<>("motoristaCodigo"));
        colunaMotoristaCNH.setCellValueFactory(new PropertyValueFactory<>("motoristaCNH"));
        colunaMotoristaSetor.setCellValueFactory(new PropertyValueFactory<>("motoristaSetor"));
        
        colunaVeiculoEmplacamento.setCellValueFactory(new PropertyValueFactory<>("veiculoEmplacamento"));
        colunaVeiculoMarca.setCellValueFactory(new PropertyValueFactory<>("veiculoMarca"));
        colunaVeiculoModelo.setCellValueFactory(new PropertyValueFactory<>("veiculoModelo"));
        
        colunaDataRetirada.setCellValueFactory(new PropertyValueFactory<>("data"));
        colunaDataDevolucao.setCellValueFactory(new PropertyValueFactory<>("dataDevolucao"));
        colunaStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }
    
    private void carregarTodasRetiradas() {
        try {
            Dao<Retirada> daoRetirada = new Dao<>(Retirada.class);
            Dao<Motorista> daoMotorista = new Dao<>(Motorista.class);
            Dao<Veiculo> daoVeiculo = new Dao<>(Veiculo.class);
            
            List<Retirada> todasRetiradas = daoRetirada.listarTodos();
            
            tabelaRetiradas.getItems().clear();
            
            if (todasRetiradas.isEmpty()) {
                mostrarMensagem("Nenhuma retirada registrada", Color.GRAY);
                return;
            }
            
            for (Retirada retirada : todasRetiradas) {
                Motorista motorista = daoMotorista.buscarPorChave("codigo", retirada.getMotoristaId());
                if (motorista != null) {
                    retirada.setMotoristaNome(motorista.getNome());
                    retirada.setMotoristaCodigo(motorista.getCodigo());
                    retirada.setMotoristaCNH(motorista.getCnh());
                    retirada.setMotoristaSetor(motorista.getSetor());
                } else {
                    retirada.setMotoristaNome("NÃO ENCONTRADO");
                    retirada.setMotoristaCodigo(retirada.getMotoristaId());
                }
                
                Veiculo veiculo = daoVeiculo.buscarPorChave("emplacamento", retirada.getVeiculoId());
                if (veiculo != null) {
                    retirada.setVeiculoEmplacamento(veiculo.getEmplacamento());
                    retirada.setVeiculoMarca(veiculo.getMarca());
                    retirada.setVeiculoModelo(veiculo.getModelo());
                } else {
                    retirada.setVeiculoEmplacamento(retirada.getVeiculoId());
                }
                
                tabelaRetiradas.getItems().add(retirada);
            }
            
            mostrarMensagem("Carregadas " + todasRetiradas.size() + " retiradas", Color.GREEN);
            
        } catch (Exception e) {
            mostrarMensagem("Erro ao carregar retiradas", Color.RED);
        }
    }
    
    @FXML
    private void atualizarLista() {
        carregarTodasRetiradas();
        mostrarMensagem("Lista atualizada!", Color.GREEN);
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