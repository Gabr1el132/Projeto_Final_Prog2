package modelo;

public class Usuario {
    private String nome;
    private String senha;
    private String login;
    
    public Usuario(){
        nome = "";
        senha = "";
        login = "";
    }

    public Usuario(String nome, String senha, String codigo) {
        this.nome = nome;
        this.senha = senha;
        this.login = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCodigo() {
        return login;
    }

    public void setCodigo(String codigo) {
        this.login = codigo;
    }
    
    
}
