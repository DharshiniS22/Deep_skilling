package com.cognizant.springlearn;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SpringLearnApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	void contextLoads() {
	}

	@Test
	public void testHello() throws Exception {
		mvc.perform(get("/hello"))
				.andExpect(status().isOk())
				.andExpect(content().string("Hello World!!"));
	}

	@Test
	public void testGetCountryIndia() throws Exception {
		mvc.perform(get("/country"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.code").value("IN"))
				.andExpect(jsonPath("$.name").value("India"));
	}

	@Test
	public void testGetCountryIndiaByCode() throws Exception {
		mvc.perform(get("/countries/in"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.code").value("IN"))
				.andExpect(jsonPath("$.name").value("India"));
	}

	@Test
	public void testGetCountryNotFound() throws Exception {
		mvc.perform(get("/countries/xx"))
				.andExpect(status().isNotFound())
				.andExpect(status().reason("Country not found"));
	}
}
