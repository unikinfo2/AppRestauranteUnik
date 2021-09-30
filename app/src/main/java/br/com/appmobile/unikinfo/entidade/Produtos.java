package br.com.appmobile.unikinfo.entidade;

import java.io.Serializable;
import java.util.Date;

public class Produtos implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String descricao;
    private String referencia;
    private String ean13;
    private String dun14;
    private Integer grupo;
    private Integer subgrupo;
    private String unidade;
    private Double quantCaixa;
    private Double aliquotaIcms;
    private Double aliquotaIpi;
    private Double estoque;
    private Double precoCompra;
    private Double precoVenda;
    private Integer tipoprod;
    private Double percDesc;
    private Double percCom;
    private Character ativo;
    private Integer tipoProduto;
    private Double margemLucro;
    private Character controlarEstoque;
    private Integer tributacao;
    private Character fracionado;
    private String codigoNcm;
    private Double reducaoBase;
    private Character editaDescPed;
    private String md5registro;
    private Character garantia;
    private Integer garantiaMeses;
    private Date dataEstoque;
    private Date horaEstoque;
    private Character combustivel;
    private Character ippt;
    private Character iat;
    private Double mva;
    private Double aliquotaPis;
    private Double aliquotaCofins;
    private Character sincronizado;
    private Integer excecaoNcm;
    private String indicea;
    private String cstInterno;
    private String cfopInterno;
    private Character bensConsumo;
    private Character prodComponente;
    private Integer idprodprincipal;
    private String prodFinalidade;
    private Integer vidautilMeses;
    private String codCest;
    private Character pesavel;
    private String cstPis;
    private String cstCofins;
    private Date dtIniPromo;
    private Date dtFimPromo;
    private Double precoPromocao;
    private Integer idCor;
    private Integer idTamanho;
    private String pathImagem;
    private String csosnInterno;
    private String visibilidade;
    private String descricaoDetalhe;
    private String impressoraPedido;

    public Produtos() {
    }

    public Produtos(Integer id) {
        this.id = id;
    }

    public Produtos(Integer id, String descricao, Character ativo, String codigoNcm, Date dataEstoque, Date horaEstoque) {
        this.id = id;
        this.descricao = descricao;
        this.ativo = ativo;
        this.codigoNcm = codigoNcm;
        this.dataEstoque = dataEstoque;
        this.horaEstoque = horaEstoque;
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

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getEan13() {
        return ean13;
    }

    public void setEan13(String ean13) {
        this.ean13 = ean13;
    }

    public String getDun14() {
        return dun14;
    }

    public void setDun14(String dun14) {
        this.dun14 = dun14;
    }

    public Integer getGrupo() {
        return grupo;
    }

    public void setGrupo(Integer grupo) {
        this.grupo = grupo;
    }

    public Integer getSubgrupo() {
        return subgrupo;
    }

    public void setSubgrupo(Integer subgrupo) {
        this.subgrupo = subgrupo;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Double getQuantCaixa() {
        return quantCaixa;
    }

    public void setQuantCaixa(Double quantCaixa) {
        this.quantCaixa = quantCaixa;
    }

    public Double getAliquotaIcms() {
        return aliquotaIcms;
    }

    public void setAliquotaIcms(Double aliquotaIcms) {
        this.aliquotaIcms = aliquotaIcms;
    }

    public Double getAliquotaIpi() {
        return aliquotaIpi;
    }

    public void setAliquotaIpi(Double aliquotaIpi) {
        this.aliquotaIpi = aliquotaIpi;
    }

    public Double getEstoque() {
        return estoque;
    }

    public void setEstoque(Double estoque) {
        this.estoque = estoque;
    }

    public Double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(Double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public Integer getTipoprod() {
        return tipoprod;
    }

    public void setTipoprod(Integer tipoprod) {
        this.tipoprod = tipoprod;
    }

    public Double getPercDesc() {
        return percDesc;
    }

    public void setPercDesc(Double percDesc) {
        this.percDesc = percDesc;
    }

    public Double getPercCom() {
        return percCom;
    }

    public void setPercCom(Double percCom) {
        this.percCom = percCom;
    }

    public Character getAtivo() {
        return ativo;
    }

    public void setAtivo(Character ativo) {
        this.ativo = ativo;
    }

    public Integer getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(Integer tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public Double getMargemLucro() {
        return margemLucro;
    }

    public void setMargemLucro(Double margemLucro) {
        this.margemLucro = margemLucro;
    }

    public Character getControlarEstoque() {
        return controlarEstoque;
    }

    public void setControlarEstoque(Character controlarEstoque) {
        this.controlarEstoque = controlarEstoque;
    }

    public Integer getTributacao() {
        return tributacao;
    }

    public void setTributacao(Integer tributacao) {
        this.tributacao = tributacao;
    }

    public Character getFracionado() {
        return fracionado;
    }

    public void setFracionado(Character fracionado) {
        this.fracionado = fracionado;
    }

    public String getCodigoNcm() {
        return codigoNcm;
    }

    public void setCodigoNcm(String codigoNcm) {
        this.codigoNcm = codigoNcm;
    }

    public Double getReducaoBase() {
        return reducaoBase;
    }

    public void setReducaoBase(Double reducaoBase) {
        this.reducaoBase = reducaoBase;
    }

    public Character getEditaDescPed() {
        return editaDescPed;
    }

    public void setEditaDescPed(Character editaDescPed) {
        this.editaDescPed = editaDescPed;
    }

    public String getMd5registro() {
        return md5registro;
    }

    public void setMd5registro(String md5registro) {
        this.md5registro = md5registro;
    }

    public Character getGarantia() {
        return garantia;
    }

    public void setGarantia(Character garantia) {
        this.garantia = garantia;
    }

    public Integer getGarantiaMeses() {
        return garantiaMeses;
    }

    public void setGarantiaMeses(Integer garantiaMeses) {
        this.garantiaMeses = garantiaMeses;
    }

    public Date getDataEstoque() {
        return dataEstoque;
    }

    public void setDataEstoque(Date dataEstoque) {
        this.dataEstoque = dataEstoque;
    }

    public Date getHoraEstoque() {
        return horaEstoque;
    }

    public void setHoraEstoque(Date horaEstoque) {
        this.horaEstoque = horaEstoque;
    }

    public Character getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(Character combustivel) {
        this.combustivel = combustivel;
    }

    public Character getIppt() {
        return ippt;
    }

    public void setIppt(Character ippt) {
        this.ippt = ippt;
    }

    public Character getIat() {
        return iat;
    }

    public void setIat(Character iat) {
        this.iat = iat;
    }

    public Double getMva() {
        return mva;
    }

    public void setMva(Double mva) {
        this.mva = mva;
    }

    public Double getAliquotaPis() {
        return aliquotaPis;
    }

    public void setAliquotaPis(Double aliquotaPis) {
        this.aliquotaPis = aliquotaPis;
    }

    public Double getAliquotaCofins() {
        return aliquotaCofins;
    }

    public void setAliquotaCofins(Double aliquotaCofins) {
        this.aliquotaCofins = aliquotaCofins;
    }

    public Character getSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(Character sincronizado) {
        this.sincronizado = sincronizado;
    }

    public Integer getExcecaoNcm() {
        return excecaoNcm;
    }

    public void setExcecaoNcm(Integer excecaoNcm) {
        this.excecaoNcm = excecaoNcm;
    }

    public String getIndicea() {
        return indicea;
    }

    public void setIndicea(String indicea) {
        this.indicea = indicea;
    }

    public String getCstInterno() {
        return cstInterno;
    }

    public void setCstInterno(String cstInterno) {
        this.cstInterno = cstInterno;
    }

    public String getCfopInterno() {
        return cfopInterno;
    }

    public void setCfopInterno(String cfopInterno) {
        this.cfopInterno = cfopInterno;
    }

    public Character getBensConsumo() {
        return bensConsumo;
    }

    public void setBensConsumo(Character bensConsumo) {
        this.bensConsumo = bensConsumo;
    }

    public Character getProdComponente() {
        return prodComponente;
    }

    public void setProdComponente(Character prodComponente) {
        this.prodComponente = prodComponente;
    }

    public Integer getIdprodprincipal() {
        return idprodprincipal;
    }

    public void setIdprodprincipal(Integer idprodprincipal) {
        this.idprodprincipal = idprodprincipal;
    }

    public String getProdFinalidade() {
        return prodFinalidade;
    }

    public void setProdFinalidade(String prodFinalidade) {
        this.prodFinalidade = prodFinalidade;
    }

    public Integer getVidautilMeses() {
        return vidautilMeses;
    }

    public void setVidautilMeses(Integer vidautilMeses) {
        this.vidautilMeses = vidautilMeses;
    }

    public String getCodCest() {
        return codCest;
    }

    public void setCodCest(String codCest) {
        this.codCest = codCest;
    }

    public Character getPesavel() {
        return pesavel;
    }

    public void setPesavel(Character pesavel) {
        this.pesavel = pesavel;
    }

    public String getCstPis() {
        return cstPis;
    }

    public void setCstPis(String cstPis) {
        this.cstPis = cstPis;
    }

    public String getCstCofins() {
        return cstCofins;
    }

    public void setCstCofins(String cstCofins) {
        this.cstCofins = cstCofins;
    }

    public Date getDtIniPromo() {
        return dtIniPromo;
    }

    public void setDtIniPromo(Date dtIniPromo) {
        this.dtIniPromo = dtIniPromo;
    }

    public Date getDtFimPromo() {
        return dtFimPromo;
    }

    public void setDtFimPromo(Date dtFimPromo) {
        this.dtFimPromo = dtFimPromo;
    }

    public Double getPrecoPromocao() {
        return precoPromocao;
    }

    public void setPrecoPromocao(Double precoPromocao) {
        this.precoPromocao = precoPromocao;
    }

    public Integer getIdCor() {
        return idCor;
    }

    public void setIdCor(Integer idCor) {
        this.idCor = idCor;
    }

    public Integer getIdTamanho() {
        return idTamanho;
    }

    public void setIdTamanho(Integer idTamanho) {
        this.idTamanho = idTamanho;
    }

    public String getPathImagem() {
        return pathImagem;
    }

    public void setPathImagem(String pathImagem) {
        this.pathImagem = pathImagem;
    }

    public String getCsosnInterno() {
        return csosnInterno;
    }

    public void setCsosnInterno(String csosnInterno) {
        this.csosnInterno = csosnInterno;
    }

    public String getVisibilidade() {
        return visibilidade;
    }

    public void setVisibilidade(String visibilidade) {
        this.visibilidade = visibilidade;
    }

    public String getDescricaoDetalhe() {
        return descricaoDetalhe;
    }

    public void setDescricaoDetalhe(String descricaoDetalhe) {
        this.descricaoDetalhe = descricaoDetalhe;
    }

    public String getImpressoraPedido() {
        return impressoraPedido;
    }

    public void setImpressoraPedido(String impressoraPedido) {
        this.impressoraPedido = impressoraPedido;
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
        if (!(object instanceof Produtos)) {
            return false;
        }
        Produtos other = (Produtos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.unik.restauranteapp.entidade.Produtos[ id=" + id + " ]";
    }
    
}
