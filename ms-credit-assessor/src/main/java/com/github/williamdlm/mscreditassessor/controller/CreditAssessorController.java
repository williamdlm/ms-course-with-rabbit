package com.github.williamdlm.mscreditassessor.controller;

import com.github.williamdlm.mscreditassessor.model.ClientStatus;
import com.github.williamdlm.mscreditassessor.service.CreditAssessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("assessors")
@RequiredArgsConstructor
public class CreditAssessorController {
    private final CreditAssessorService creditAssessorService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @GetMapping(value = "/client-status", params = "document")
    public ResponseEntity<ClientStatus> clientStatusQuery(@RequestParam("document") String document) {
        ClientStatus clientStatus = CreditAssessorService.getClientStatus(document);
        return ResponseEntity.ok(clientStatus);
    }
}
