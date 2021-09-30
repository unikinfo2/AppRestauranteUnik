package br.com.appmobile.unikinfo.entidade;

import java.io.Serializable;

public class Usuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String usuario;
    private String senha;
    private Character ativo;
    private int tipoUsuario;
    private String nomeUsuaio;
    private int idPerfil;
    private Integer numChave;
    private Integer idFunc;
    private String funcao;

    public Usuarios() {
    }

    public Usuarios(Integer id) {
        this.id = id;
    }

    public Usuarios(Integer id, Character ativo, int tipoUsuario, int idPerfil) {
        this.id = id;
        this.ativo = ativo;
        this.tipoUsuario = tipoUsuario;
        this.idPerfil = idPerfil;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Character getAtivo() {
        return ativo;
    }

    public void setAtivo(Character ativo) {
        this.ativo = ativo;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuaio;
    }

    public void setNomeUsuario(String nomeUsuaio) {
        this.nomeUsuaio = nomeUsuaio;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Integer getNumChave() {
        return numChave;
    }

    public void setNumChave(Integer numChave) {
        this.numChave = numChave;
    }

    public Integer getIdFunc() {
        return idFunc;
    }

    public void setIdFunc(Integer idFunc) {
        this.idFunc = idFunc;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.unik.restauranteapp.entidade.Usuarios[ id=" + id + " ]";
    }
    
}
