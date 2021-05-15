package integration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AlertControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testfindPersonInfoByFireStationNumber_ReturnContent_ValidArgument() throws Exception {
		String param = "2";
		mockMvc.perform(get("/firestation")
				.param("stationNumber", param))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[].numberOfAdults", is("Eric")))
				.andExpect(jsonPath("$[].numberOfChildren", is("Eric")));
	}
}
