package com.beautyandhome.profumishop.controller;

import java.util.List;

import com.beautyandhome.profumishop.model.Prodotto;
import com.beautyandhome.profumishop.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prodotti")
@CrossOrigin(origins = "https://ennarsh.github.io") // Allow requests from specific origin
public class ProdottoController {

    private final ProdottoService prodottoService;

    @Autowired
    public ProdottoController(ProdottoService prodottoService) {
        this.prodottoService = prodottoService;
    }

    @GetMapping
    public ResponseEntity<List<Prodotto>> getAllProdotti() {
        List<Prodotto> prodotti = prodottoService.getAllProdotti();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(prodotti);
    }

    @GetMapping("/filtered")
    public ResponseEntity<List<Prodotto>> getAllProdottiFiltered(@RequestParam String categoria) {
        List<Prodotto> prodotti = prodottoService.getAllProdottiFiltered(categoria);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(prodotti);
    }

    @GetMapping("/details")
    public ResponseEntity<List<Prodotto>> getProdottoById(@RequestParam String id) {
        List<Prodotto>  prodotto = prodottoService.getProdottoById(id);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(prodotto);
    }
}
