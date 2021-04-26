package io.artemptushkin.github.gustavo;

import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import io.artemptushkin.github.gustavo.service.HeisenbergResponse;
import io.artemptushkin.github.gustavo.service.HeisenbergService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@PactTestFor(port = "8091", providerName = "heisenberg")
@ExtendWith(value = {SpringExtension.class, PactConsumerTestExt.class})
class HeisenbergServiceContractTest {

	HeisenbergService heisenbergService;

	@BeforeEach
	void setup() {
		heisenbergService = new HeisenbergService(new RestTemplate());
	}

	@Pact(consumer = "gustavo-fring", provider = "heisenberg")
	public RequestResponsePact createPact(PactDslWithProvider builder) {
		return builder
				.given("get crystals")
				.uponReceiving("GET REQUEST")
				.method("GET")
				.path("/heisenberg/v1/cook")
				.query("amount=2")
				.willRespondWith()
				.body(
						new PactDslJsonBody()
						.array("crystals")
								.object()
									.numberValue("id", 1)
									.stringValue("color", "red")
								.closeObject()
								.object()
									.numberValue("id", 2)
									.stringValue("color", "blue")
								.closeObject()
						.closeArray())
				.status(200)
				.toPact();
	}

	@Test
	void itReturnsData() {
		HeisenbergResponse heisenbergResponse = heisenbergService.cookCrystals(2);

		assertThat(heisenbergResponse.getCrystals()).hasSize(2);
	}

}
