package com.vivelibre.controller;

import com.vivelibre.model.VivelibreResponse;
import com.vivelibre.service.VivelibreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VivelibreController{

    @Autowired
    private VivelibreService vivelibreService;

    @GetMapping("/get-token")
    public VivelibreResponse getToken() {
        return vivelibreService.callPostEndpoint();
    }
}
