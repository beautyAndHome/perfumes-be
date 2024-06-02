package com.beautyandhome.profumishop.model;

public class OrdineProdotto {

    private Integer ordineId;

    private Integer prodottoId;

    private Integer quantita;

    // Costruttori, getters e setters

    public Integer getOrdineId() {
        return ordineId;
    }

    public void setOrdineId(Integer ordineId) {
        this.ordineId = ordineId;
    }

    public Integer getProdottoId() {
        return prodottoId;
    }

    public void setProdottoId(Integer prodottoId) {
        this.prodottoId = prodottoId;
    }

    public Integer getQuantita() {
        return quantita;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }
}
