package io.artemptushkin.github.gustavo.controller;

import java.util.List;

import io.artemptushkin.github.gustavo.service.Chicken;
import io.artemptushkin.github.gustavo.service.Crystal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FringResponse {
	private List<Crystal> crystals;
	private List<Chicken> chicken;
}
