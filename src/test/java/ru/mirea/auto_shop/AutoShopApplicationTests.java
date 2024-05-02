package ru.mirea.auto_shop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.mirea.auto_shop.services.CarService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class AutoShopApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CarService carService;

	@Test
	public void testGetHome() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/home"))
				.andExpect(status().isOk())
				.andExpect(view().name("home"));
	}

	@Test
	public void testGetAllCars() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/cars"))
				.andExpect(status().isOk())
				.andExpect(view().name("catalog"));
	}

	@Test
	public void testGetRegistration() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/login"))
				.andExpect(status().isOk())
				.andExpect(view().name("login"));
	}
}
