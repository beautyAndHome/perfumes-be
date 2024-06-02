package com.beautyandhome.profumishop.controller;

import com.beautyandhome.profumishop.model.ApiMessage;
import com.beautyandhome.profumishop.model.OrdineCompleto;
import com.beautyandhome.profumishop.service.OrdineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ordini")
@CrossOrigin(origins = "https://ennarsh.github.io") // Allow requests from specific origin
public class OrdineController {

    private final OrdineService ordineService;

    @Autowired
    public OrdineController(OrdineService ordineService) {
        this.ordineService = ordineService;
    }

    @PostMapping
    public ResponseEntity<ApiMessage> salvaOrdine(@RequestBody OrdineCompleto ordineCompleto) {
        ordineService.salvaOrdineCompleto(ordineCompleto);
        ApiMessage message = new ApiMessage();
        message.setMessage("Ordine salvato con successo");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(message);
    }

}
