package br.com.geodrone.resource;

public class UsuarioResource {

    private Long id;
    private String nome;
    private Long cpfCnpj;
    private String email;
    private String telefone;
    private String senha;
    private String flagPerfil;
    private Long idCliente;
    private Integer indAtivo;
    private Integer indAceiteGeomonitora;
    private Integer indAceiteGeoClima;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(Long cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFlagPerfil() {
        return flagPerfil;
    }

    public void setFlagPerfil(String flagPerfil) {
        this.flagPerfil = flagPerfil;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIndAtivo() {
        return indAtivo;
    }

    public void setIndAtivo(Integer indAtivo) {
        this.indAtivo = indAtivo;
    }

    public Integer getIndAceiteGeomonitora() {
        return indAceiteGeomonitora;
    }

    public void setIndAceiteGeomonitora(Integer indAceiteGeomonitora) {
        this.indAceiteGeomonitora = indAceiteGeomonitora;
    }

    public Integer getIndAceiteGeoClima() {
        return indAceiteGeoClima;
    }

    public void setIndAceiteGeoClima(Integer indAceiteGeoClima) {
        this.indAceiteGeoClima = indAceiteGeoClima;
    }
}
