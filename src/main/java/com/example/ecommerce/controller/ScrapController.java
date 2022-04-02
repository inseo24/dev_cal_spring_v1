package com.example.ecommerce.controller;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.example.ecommerce.dto.scrap.ScrapDTO;
import com.example.ecommerce.service.ScrapService;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/scrap")
@RequiredArgsConstructor
public class ScrapController {

    private final ScrapService scrapService;

    @GetMapping
    public ResponseEntity<?> retrieveByUserId(@AuthenticationPrincipal String userId) {
        List<ScrapDTO> response = scrapService.retrieve(userId)
                .stream().map(ScrapDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/{eventId}")
    public ResponseEntity<?> scrap(@PathVariable Long eventId, @AuthenticationPrincipal String userId) {
        scrapService.scrap(eventId, userId);
        return ok(HttpStatus.OK);
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<?> unScrap(@PathVariable Long eventId, @AuthenticationPrincipal String userId) {
        scrapService.unScrap(eventId, userId);
        return ok(HttpStatus.OK);
    }
}
