package modelo;

public class Veiculo {
    private String emplacamento;
    private String marca;
    private String modelo;

    public Veiculo(){
        emplacamento = "";
        marca = "";
        modelo = "";
    }
    
    public Veiculo(String emplacamento, String marca, String modelo) {
        this.emplacamento = emplacamento;
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getEmplacamento() {
        return emplacamento;
    }

    public void setEmplacamento(String emplacamento) {
        this.emplacamento = emplacamento;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    @Override
    public String toString() {
        return emplacamento + " - " + marca + " " + modelo;
    }
}