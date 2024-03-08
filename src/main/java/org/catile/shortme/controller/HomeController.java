package org.catile.shortme.controller;

import org.catile.shortme.service.ShortlinkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public record HomeController(ShortlinkService service) {
    @GetMapping("create")
    public String create(@RequestParam String url) {
        return service.create(url);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> redirect(@PathVariable("id") String id) {
        return service
                .findById(id)
                .map(url -> ResponseEntity
                        .status(302)
                        .location(URI.create(url))
                        .build())
                .orElse(ResponseEntity
                        .status(404)
                        .body("Not found"));
    }
}
