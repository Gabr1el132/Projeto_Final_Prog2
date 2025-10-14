package modelo;

public class Motorista {
    private String nome;
    private String cnh;
    private String codigo;
    private String setor;

    public Motorista(String nome, String cnh, String codigo, String setor) {
        this.nome = nome;
        this.cnh = cnh;
        this.codigo = codigo;
        this.setor = setor;
    }
    
    public Motorista(){
        nome = "";
        cnh = "";
        codigo = "";
        setor = "";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }
    
    
}
