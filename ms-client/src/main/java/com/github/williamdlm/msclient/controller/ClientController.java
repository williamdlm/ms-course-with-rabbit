package com.github.williamdlm.msclient.controller;

import com.github.williamdlm.msclient.dto.ClientDTO;
import com.github.williamdlm.msclient.model.Client;
import com.github.williamdlm.msclient.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    @PostMapping
    ResponseEntity saveClient(@RequestBody ClientDTO clientDTO){
        clientService.saveClient(clientDTO);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("document={idDocument}")
                .buildAndExpand(clientDTO.idDocument())
                .toUri();
        return ResponseEntity.created(headerLocation).build();

    }

    @GetMapping("/search")
    ResponseEntity<List<Client>> findAllClients(){
        List<Client> clientList = this.clientService.getAllClients();
        return ResponseEntity.ok(clientList);
    }

    @GetMapping("/{id}")
    ResponseEntity<Client> findById(@PathVariable Long id ) throws Exception {
        Client client = this.clientService.findClientById(id);
        return ResponseEntity.ok(client);
    }

    @GetMapping(params = "document")
    ResponseEntity<Client> findByDocument(@RequestParam String document ) throws Exception {
        Client client = this.clientService.findClientByDocument(document);
        return ResponseEntity.ok(client);
    }
}
