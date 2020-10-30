package io.artemptushkin.github.gustavo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GustavoFringController {

	@GetMapping("/fring/say-my-name")
	public FringResponse say() {
		return FringResponse
				.builder()
				.name("heisenberg")
				.build();
	}
}
