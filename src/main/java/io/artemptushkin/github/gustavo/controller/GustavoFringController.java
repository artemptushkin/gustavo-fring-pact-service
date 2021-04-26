package io.artemptushkin.github.gustavo.controller;

import java.util.List;

import io.artemptushkin.github.gustavo.service.Chicken;
import io.artemptushkin.github.gustavo.service.HeisenbergResponse;
import io.artemptushkin.github.gustavo.service.HeisenbergService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GustavoFringController {
	private final HeisenbergService heisenbergService;

	@GetMapping("/chicken")
	public FringResponse getChicken(@RequestParam Integer amount) {
		HeisenbergResponse heisenbergResponse = heisenbergService.cookCrystals(amount);
		return FringResponse
				.builder()
				.crystals(heisenbergResponse.getCrystals())
				.chicken(List.of(Chicken
						.builder()
						.id(1L)
						.name("The best chicken!")
						.build())
				)
				.build();
	}
}
