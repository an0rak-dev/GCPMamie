package com.github.an0rakdev.talkgcpmamie.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/")
public class HealthController {

	// Needed for GKE Ingress HealthCheck
	@GetMapping("")
	public ResponseEntity<Void> getHealth() {
		return ResponseEntity.ok().build();
	}
}
