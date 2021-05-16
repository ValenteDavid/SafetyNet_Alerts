package integration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.core.IsAnything;
import org.hamcrest.text.MatchesPattern;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.safetynetalerts.SafetyNetAlertsApplication;

@SpringBootTest(classes = SafetyNetAlertsApplication.class)
@AutoConfigureMockMvc
public class AlertControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testfindPersonInfoByFireStationNumber_ReturnContent_ValidArgument() throws Exception {
		String stationNumber = "2";
		String regex = "29 15th St|892 Downing Ct|951 LoneTree Rd";

		mockMvc.perform(get("/firestation")
				.param("stationNumber", stationNumber))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.numberOfAdults", is(4)))
				.andExpect(jsonPath("$.numberOfChildren", is(1)))

				.andExpect(jsonPath("$.listPerson[0].address", MatchesPattern.matchesPattern(regex)))
				.andExpect(jsonPath("$.listPerson[1].address", MatchesPattern.matchesPattern(regex)))
				.andExpect(jsonPath("$.listPerson[2].address", MatchesPattern.matchesPattern(regex)))
				.andExpect(jsonPath("$.listPerson[3].address", MatchesPattern.matchesPattern(regex)))
				.andExpect(jsonPath("$.listPerson[4].address", MatchesPattern.matchesPattern(regex)))

				.andExpect(jsonPath("$.listPerson[*].firstName", IsAnything.anything()))
				.andExpect(jsonPath("$.listPerson[*].lastName", IsAnything.anything()))
				.andExpect(jsonPath("$.listPerson[*].address", IsAnything.anything()))
				.andExpect(jsonPath("$.listPerson[*].phone", IsAnything.anything()));
	}
}
