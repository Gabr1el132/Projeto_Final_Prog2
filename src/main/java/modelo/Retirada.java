package modelo;

public class Retirada {
    private String id;
    private String motoristaId;
    private String veiculoId;
    private String data;
    private boolean devolvido;
    private String dataDevolucao;
    
    private transient String motoristaNome;
    private transient String motoristaCodigo;
    private transient String motoristaCNH;
    private transient String motoristaSetor;
    private transient String veiculoEmplacamento;
    private transient String veiculoMarca;
    private transient String veiculoModelo;
    private transient String status;
    
    public Retirada() {
        this.id = java.util.UUID.randomUUID().toString();
        this.motoristaId = "";
        this.veiculoId = "";
        this.data = "";
        this.devolvido = false;
        this.dataDevolucao = "";
        
        this.motoristaNome = "";
        this.motoristaCodigo = "";
        this.motoristaCNH = "";
        this.motoristaSetor = "";
        this.veiculoEmplacamento = "";
        this.veiculoMarca = "";
        this.veiculoModelo = "";
        this.status = "ATIVA";
    }
    
    public Retirada(String motoristaId, String veiculoId, String data) {
        this();
        this.motoristaId = motoristaId;
        this.veiculoId = veiculoId;
        this.data = data;
    }
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getMotoristaId() { return motoristaId; }
    public void setMotoristaId(String motoristaId) { this.motoristaId = motoristaId; }
    
    public String getVeiculoId() { return veiculoId; }
    public void setVeiculoId(String veiculoId) { this.veiculoId = veiculoId; }
    
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
    
    public boolean isDevolvido() { return devolvido; }
    public void setDevolvido(boolean devolvido) { this.devolvido = devolvido; }
    
    public String getDataDevolucao() { return dataDevolucao; }
    public void setDataDevolucao(String dataDevolucao) { this.dataDevolucao = dataDevolucao; }
    
    public String getMotoristaNome() { return motoristaNome; }
    public void setMotoristaNome(String motoristaNome) { this.motoristaNome = motoristaNome; }
    
    public String getMotoristaCodigo() { return motoristaCodigo; }
    public void setMotoristaCodigo(String motoristaCodigo) { this.motoristaCodigo = motoristaCodigo; }
    
    public String getMotoristaCNH() { return motoristaCNH; }
    public void setMotoristaCNH(String motoristaCNH) { this.motoristaCNH = motoristaCNH; }
    
    public String getMotoristaSetor() { return motoristaSetor; }
    public void setMotoristaSetor(String motoristaSetor) { this.motoristaSetor = motoristaSetor; }
    
    public String getVeiculoEmplacamento() { return veiculoEmplacamento; }
    public void setVeiculoEmplacamento(String veiculoEmplacamento) { this.veiculoEmplacamento = veiculoEmplacamento; }
    
    public String getVeiculoMarca() { return veiculoMarca; }
    public void setVeiculoMarca(String veiculoMarca) { this.veiculoMarca = veiculoMarca; }
    
    public String getVeiculoModelo() { return veiculoModelo; }
    public void setVeiculoModelo(String veiculoModelo) { this.veiculoModelo = veiculoModelo; }
    
    public String getStatus() { 
        return devolvido ? "DEVOLVIDO" : "ATIVA"; 
    }
    
    public void setStatus(String status) { this.status = status; }
    
    @Override
    public String toString() {
        return "Motorista: " + motoristaId + " | Veículo: " + veiculoId + " | Data: " + data;
    }
}