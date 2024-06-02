package com.beautyandhome.profumishop.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.beautyandhome.profumishop.model.OrdineProdottoRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrdineProdottoService {
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    public void salvaDettaglioOrdine(Integer idOrdine, OrdineProdottoRequest ordineProdotto) {
        String query = "INSERT INTO ordine_prodotto (ordine_id, prodotto_id, quantita) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setInt(1, idOrdine);
                statement.setInt(2, ordineProdotto.getProdottoId());
                statement.setInt(3, ordineProdotto.getQuantita());
                statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
