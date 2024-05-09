package com.github.williamdlm.mscreditassessor.feignclient;

import com.github.williamdlm.mscreditassessor.pojo.ClientCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ms-card",path = "/cards")
@Component
public interface CardFeignClient {

    @GetMapping(params = "document")
    public ResponseEntity<List<ClientCard>> findByDocument(@RequestParam("document") String document);
}
