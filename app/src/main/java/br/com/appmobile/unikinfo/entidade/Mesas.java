package br.com.appmobile.unikinfo.entidade;

import java.io.Serializable;

public class Mesas implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String numeroMesa;
    private String descricao;
    private String status;
    private Integer idMesaAberta;

    public Mesas() {
    }

    public Mesas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdMesaAberta() {
        return idMesaAberta;
    }

    public void setIdMesaAberta(Integer idMesaAberta) {
        this.idMesaAberta = idMesaAberta;
    }

    public String getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(String numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        if (!(object instanceof Mesas)) {
            return false;
        }
        Mesas other = (Mesas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.unik.restauranteapp.entidade.Mesas[ id=" + id + " ]";
    }
    
}
