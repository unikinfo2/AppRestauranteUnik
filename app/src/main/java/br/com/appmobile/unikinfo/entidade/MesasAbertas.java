package br.com.appmobile.unikinfo.entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MesasAbertas implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private int idMesa;
    private Date data;
    private Date hora;
    private Double valor;
    private Integer cerConferencia;
    private int status;
    private Integer idMesaOrigem;
    private String numeroMesa;
    private Integer coo;
    private List<MesasItens> mesasItensList;

    public MesasAbertas() {
    }

    public MesasAbertas(Integer id) {
        this.id = id;
    }

    public MesasAbertas(Integer id, int idMesa, Date data, Date hora, int status) {
        this.id = id;
        this.idMesa = idMesa;
        this.data = data;
        this.hora = hora;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getCerConferencia() {
        return cerConferencia;
    }

    public void setCerConferencia(Integer cerConferencia) {
        this.cerConferencia = cerConferencia;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getIdMesaOrigem() {
        return idMesaOrigem;
    }

    public void setIdMesaOrigem(Integer idMesaOrigem) {
        this.idMesaOrigem = idMesaOrigem;
    }

    public String getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(String numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public Integer getCoo() {
        return coo;
    }

    public void setCoo(Integer coo) {
        this.coo = coo;
    }

    public List<MesasItens> getMesasItensList() {
        return mesasItensList;
    }

    public void setMesasItensList(List<MesasItens> mesasItensList) {
        this.mesasItensList = mesasItensList;
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
        if (!(object instanceof MesasAbertas)) {
            return false;
        }
        MesasAbertas other = (MesasAbertas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.unik.restauranteapp.entidade.MesasAbertas[ id=" + id + " ]";
    }
    
}
