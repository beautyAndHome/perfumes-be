package com.beautyandhome.profumishop.model;

import java.util.List;

public class OrdineCompleto {
    Ordine ordine;
    List<OrdineProdottoRequest> ordineProdotti;

    public Ordine getOrdine() {
        return ordine;
    }

    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }

    public List<OrdineProdottoRequest> getOrdineProdotti() {
        return ordineProdotti;
    }

    public void setOrdineProdotti(List<OrdineProdottoRequest> ordineProdotti) {
        this.ordineProdotti = ordineProdotti;
    }
}
