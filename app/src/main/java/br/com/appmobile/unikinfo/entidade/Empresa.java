package br.com.appmobile.unikinfo.entidade;

import java.io.Serializable;

public class Empresa implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String razaoSocial;
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private String cnpjCpf;
    private String inscricaoRg;
    private String telefone;
    private String telefax;
    private String contato;
    private String email;
    private String site;
    private String inscMunic;
    private String fantasia;
    private String codMunicipio;
    private Integer idcontador;
    private int crt;
    private String codSuframa;
    private String cnae;
    private String nire;
    private String nomeResp;
    private int codAssin;
    private String cpfResp;
    private String cepResp;
    private String endResp;
    private String numResp;
    private String complResp;
    private String bairroResp;
    private String foneResp;
    private String faxResp;
    private String emailResp;
    private Character perfilPafecf;
    private Integer crtIssqn;
    private Character rateioIssqn;

    public Empresa() {
    }

    public Empresa(Integer id) {
        this.id = id;
    }

    public Empresa(Integer id, int crt, int codAssin, Character perfilPafecf) {
        this.id = id;
        this.crt = crt;
        this.codAssin = codAssin;
        this.perfilPafecf = perfilPafecf;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCnpjCpf() {
        return cnpjCpf;
    }

    public void setCnpjCpf(String cnpjCpf) {
        this.cnpjCpf = cnpjCpf;
    }

    public String getInscricaoRg() {
        return inscricaoRg;
    }

    public void setInscricaoRg(String inscricaoRg) {
        this.inscricaoRg = inscricaoRg;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefax() {
        return telefax;
    }

    public void setTelefax(String telefax) {
        this.telefax = telefax;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getInscMunic() {
        return inscMunic;
    }

    public void setInscMunic(String inscMunic) {
        this.inscMunic = inscMunic;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public String getCodMunicipio() {
        return codMunicipio;
    }

    public void setCodMunicipio(String codMunicipio) {
        this.codMunicipio = codMunicipio;
    }

    public Integer getIdcontador() {
        return idcontador;
    }

    public void setIdcontador(Integer idcontador) {
        this.idcontador = idcontador;
    }

    public int getCrt() {
        return crt;
    }

    public void setCrt(int crt) {
        this.crt = crt;
    }

    public String getCodSuframa() {
        return codSuframa;
    }

    public void setCodSuframa(String codSuframa) {
        this.codSuframa = codSuframa;
    }

    public String getCnae() {
        return cnae;
    }

    public void setCnae(String cnae) {
        this.cnae = cnae;
    }

    public String getNire() {
        return nire;
    }

    public void setNire(String nire) {
        this.nire = nire;
    }

    public String getNomeResp() {
        return nomeResp;
    }

    public void setNomeResp(String nomeResp) {
        this.nomeResp = nomeResp;
    }

    public int getCodAssin() {
        return codAssin;
    }

    public void setCodAssin(int codAssin) {
        this.codAssin = codAssin;
    }

    public String getCpfResp() {
        return cpfResp;
    }

    public void setCpfResp(String cpfResp) {
        this.cpfResp = cpfResp;
    }

    public String getCepResp() {
        return cepResp;
    }

    public void setCepResp(String cepResp) {
        this.cepResp = cepResp;
    }

    public String getEndResp() {
        return endResp;
    }

    public void setEndResp(String endResp) {
        this.endResp = endResp;
    }

    public String getNumResp() {
        return numResp;
    }

    public void setNumResp(String numResp) {
        this.numResp = numResp;
    }

    public String getComplResp() {
        return complResp;
    }

    public void setComplResp(String complResp) {
        this.complResp = complResp;
    }

    public String getBairroResp() {
        return bairroResp;
    }

    public void setBairroResp(String bairroResp) {
        this.bairroResp = bairroResp;
    }

    public String getFoneResp() {
        return foneResp;
    }

    public void setFoneResp(String foneResp) {
        this.foneResp = foneResp;
    }

    public String getFaxResp() {
        return faxResp;
    }

    public void setFaxResp(String faxResp) {
        this.faxResp = faxResp;
    }

    public String getEmailResp() {
        return emailResp;
    }

    public void setEmailResp(String emailResp) {
        this.emailResp = emailResp;
    }

    public Character getPerfilPafecf() {
        return perfilPafecf;
    }

    public void setPerfilPafecf(Character perfilPafecf) {
        this.perfilPafecf = perfilPafecf;
    }

    public Integer getCrtIssqn() {
        return crtIssqn;
    }

    public void setCrtIssqn(Integer crtIssqn) {
        this.crtIssqn = crtIssqn;
    }

    public Character getRateioIssqn() {
        return rateioIssqn;
    }

    public void setRateioIssqn(Character rateioIssqn) {
        this.rateioIssqn = rateioIssqn;
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
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.unik.restauranteapp.entidade.Empresa[ id=" + id + " ]";
    }
    
}
