package br.com.appmobile.unikinfo.entidade;

import java.io.Serializable;

public class MesasItens implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer idProduto;
    private String descricao;
    private Double quant;
    private Double precoUnit;
    private Double precoTotal;
    private Character cancelado;
    private Integer idMesaOrigem;
    private String numeroMesaOrig;
    private Integer idMesa;
    private String descricaoDetalhe;

    public MesasItens() {
    }

    public MesasItens(Integer id) {
        this.id = id;
    }

    public MesasItens(Integer id, int idProduto) {
        this.id = id;
        this.idProduto = idProduto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getQuant() {
        return quant;
    }

    public void setQuant(Double quant) {
        this.quant = quant;
    }

    public Double getPrecoUnit() {
        return precoUnit;
    }

    public void setPrecoUnit(Double precoUnit) {
        this.precoUnit = precoUnit;
    }

    public Double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(Double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public Character getCancelado() {
        return cancelado;
    }

    public void setCancelado(Character cancelado) {
        this.cancelado = cancelado;
    }

    public Integer getIdMesaOrigem() {
        return idMesaOrigem;
    }

    public void setIdMesaOrigem(Integer idMesaOrigem) {
        this.idMesaOrigem = idMesaOrigem;
    }

    public String getNumeroMesaOrig() {
        return numeroMesaOrig;
    }

    public void setNumeroMesaOrig(String numeroMesaOrig) {
        this.numeroMesaOrig = numeroMesaOrig;
    }

    public Integer getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getDescricaoDetalhe() {
        return descricaoDetalhe;
    }

    public void setDescricaoDetalhe(String descricaoDetalhe) {
        this.descricaoDetalhe = descricaoDetalhe;
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
        if (!(object instanceof MesasItens)) {
            return false;
        }
        MesasItens other = (MesasItens) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.unik.restauranteapp.entidade.MesasItens[ id=" + id + " ]";
    }
    
}
