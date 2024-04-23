package com.github.williamdlm.msclient.service;

import com.github.williamdlm.msclient.dto.ClientDTO;
import com.github.williamdlm.msclient.model.Client;
import com.github.williamdlm.msclient.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public Client findClientById(Long id) throws Exception {
        return this.clientRepository.findById(id).orElseThrow(() -> new Exception("Client not found"));
    }

    public Client findClientByDocument(String idDocument) throws Exception {
        return this.clientRepository.findByIdDocument(idDocument).orElseThrow(()->new Exception("Client not found"));
    }

    public void saveClient(ClientDTO clientDTO){
        Client newClient = clientDTO.toModel();
        this.clientRepository.save(newClient);
    }

    public List<Client> getAllClients(){
        return this.clientRepository.findAll();
    }
}
