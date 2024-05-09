package com.github.williamdlm.mscreditassessor.feignclient;

import com.github.williamdlm.mscreditassessor.pojo.ClientData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ms-client", path = "/clients")
@Component
public interface ClientFeignClient {

    @GetMapping(params = "document")
    ResponseEntity<ClientData> findByDocument(@RequestParam String document);

}
