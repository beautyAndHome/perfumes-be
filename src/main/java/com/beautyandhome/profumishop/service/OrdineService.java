package com.beautyandhome.profumishop.service;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import com.beautyandhome.profumishop.model.Ordine;
import com.beautyandhome.profumishop.model.OrdineCompleto;
import com.beautyandhome.profumishop.model.OrdineProdottoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrdineService {
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    private final OrdineProdottoService dettaglioOrdineService;

    @Autowired
    public OrdineService(OrdineProdottoService ordineProdottoService) {
        this.dettaglioOrdineService = ordineProdottoService;
    }



    @Transactional
    public void salvaOrdineCompleto(OrdineCompleto ordineCompleto) {
        Ordine ordine = ordineCompleto.getOrdine();
        List<OrdineProdottoRequest> ordineProdotti = ordineCompleto.getOrdineProdotti();

        try {
            // Salva l'ordine nella tabella Ordine
            Integer idOrdine = salvaOrdine(ordine);
            for(OrdineProdottoRequest prodotto: ordineProdotti){
                dettaglioOrdineService.salvaDettaglioOrdine(idOrdine, prodotto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Errore durante il salvataggio dell'ordine e dei suoi prodotti associati.");
        }
    }


    public Integer salvaOrdine(Ordine ordine) {
        String queryOrdine = "INSERT INTO ordini (nome, cognome, indirizzo, cap, provincia, comune, telefono, email, data_ordine, totale, metodo_pagamento) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement statementOrdine = conn.prepareStatement(queryOrdine, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statementOrdine.setString(1, ordine.getNome());
            statementOrdine.setString(2, ordine.getCognome());
            statementOrdine.setString(3, ordine.getIndirizzo());
            statementOrdine.setString(4, ordine.getCap());
            statementOrdine.setString(5, ordine.getProvincia());
            statementOrdine.setString(6, ordine.getComune());
            statementOrdine.setString(7, ordine.getTelefono());
            statementOrdine.setString(8, ordine.getEmail());
            statementOrdine.setObject(9, LocalDate.now());
            statementOrdine.setBigDecimal(10, ordine.getTotale());
            statementOrdine.setString(11, ordine.getMetodoPagamento());

            int affectedRows = statementOrdine.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Salvataggio dell'ordine fallito, nessuna riga inserita.");
            }

            try (ResultSet generatedKeys = statementOrdine.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idOrdine = generatedKeys.getInt(1);
                    return idOrdine;
                } else {
                    throw new SQLException("Salvataggio dell'ordine fallito, nessun ID generato.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
