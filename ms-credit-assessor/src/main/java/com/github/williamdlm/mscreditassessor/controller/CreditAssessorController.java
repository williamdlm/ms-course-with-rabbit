package com.github.williamdlm.mscreditassessor.controller;

import com.github.williamdlm.mscreditassessor.exception.ClientDataNotFoundException;
import com.github.williamdlm.mscreditassessor.exception.ErrorComunicationWebserviceException;
import com.github.williamdlm.mscreditassessor.pojo.EvaluateData;
import com.github.williamdlm.mscreditassessor.pojo.ClientStatus;
import com.github.williamdlm.mscreditassessor.pojo.ResponseEvaluateCredit;
import com.github.williamdlm.mscreditassessor.service.CreditAssessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity clientStatusQuery(@RequestParam("document") String document) throws Exception, ErrorComunicationWebserviceException {
        try {
        ClientStatus clientStatus = creditAssessorService.getClientStatus(document);
        return ResponseEntity.ok(clientStatus);
        }catch (ClientDataNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (ErrorComunicationWebserviceException e){
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity evaluateCredit(@RequestBody EvaluateData data){
        try {
            ResponseEvaluateCredit resultEvaluate = creditAssessorService.evaluateCredit(data.getDocument(), data.getIncome());
            return ResponseEntity.ok(resultEvaluate);
        }catch (ClientDataNotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (ErrorComunicationWebserviceException e){
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }

    }
}
