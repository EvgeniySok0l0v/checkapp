package com.sakalou.checkapp.web.controller;

import com.sakalou.checkapp.facade.CheckFacade;
import com.sakalou.checkapp.web.request.ProductRequest;
import com.sakalou.checkapp.web.response.CheckResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/check")
@RestController
public class CheckController {

    private final CheckFacade checkFacade;

    public CheckController(CheckFacade checkFacade) {
        this.checkFacade = checkFacade;
    }

    @PostMapping
    public CheckResponse getCheck(@RequestBody ProductRequest[] request, @RequestParam Long cardId){
        log.info(checkFacade.createCheckResponse(request, cardId).toString());
        return checkFacade.createCheckResponse(request, cardId);
    }
}
