package com.github.williamdlm.msclient.model;

import com.github.williamdlm.msclient.dto.ClientDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String idDocument;

    public Client(String name, String idDocument){
        this.name = name;
        this.idDocument = idDocument;
    }


}
