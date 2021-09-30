package br.com.appmobile.unikinfo.entidade;

public class ProdutoAdicional {
    private Long id;
    private Long idProduto;
    private String descricaoCompl;
    private Double valorAdicional;

    public ProdutoAdicional() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public String getDescricaoCompl() {
        return descricaoCompl;
    }

    public void setDescricaoCompl(String descricaoCompl) {
        this.descricaoCompl = descricaoCompl;
    }

    public Double getValorAdicional() {
        return valorAdicional;
    }

    public void setValorAdicional(Double valorAdicional) {
        this.valorAdicional = valorAdicional;
    }

    @Override
    public String toString() {
        return "ProdutosAdicionais{" +
                "id=" + id +
                ", idProduto=" + idProduto +
                ", descricaoCompl='" + descricaoCompl + '\'' +
                ", valorAdicional=" + valorAdicional +
                '}';
    }
}
