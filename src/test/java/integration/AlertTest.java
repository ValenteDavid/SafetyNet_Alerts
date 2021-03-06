package integration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
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
public class AlertTest {

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

	@Test
	public void testphoneAlert_ReturnContent_ValidArgument() throws Exception {
		String firestation = "2";

		mockMvc.perform(get("/phoneAlert")
				.param("firestation", firestation))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.numbers[*]", containsInAnyOrder(
						"841-874-7512",
						"841-874-7878",
						"841-874-7458",
						"841-874-6513")));
	}

	@Test
	public void testfireAlert_ReturnContent_ValidArgument() throws Exception {
		String address = "947 E. Rose Dr";

		mockMvc.perform(get("/fire")
				.param("address", address))
				.andExpect(status().isOk())

				.andExpect(jsonPath("$.persons[*].lastName", IsAnything.anything()))
				.andExpect(jsonPath("$.persons[*].phone", IsAnything.anything()))
				.andExpect(jsonPath("$.persons[*].age", IsAnything.anything()))
				.andExpect(jsonPath("$.persons[*].medications", IsAnything.anything()))
				.andExpect(jsonPath("$.persons[*].allergies", IsAnything.anything()))

				.andExpect(jsonPath("$.stationNumber", is(1)));
	}

	@Test
	public void testfloodAlert_ReturnContent_ValidArgument() throws Exception {
		String listOfStationNumbers = "1,2";

		mockMvc.perform(get("/flood/stations")
				.param("stations", listOfStationNumbers))
				.andExpect(status().isOk())

				.andExpect(jsonPath("$.addressesPersons[*].['951 LoneTree Rd'][0].lastName", IsAnything.anything()))
				.andExpect(jsonPath("$.addressesPersons[*].['951 LoneTree Rd'][1].phone", IsAnything.anything()))
				.andExpect(jsonPath("$.addressesPersons[*].['951 LoneTree Rd'][2].age", IsAnything.anything()))
				.andExpect(jsonPath("$.addressesPersons[*].['951 LoneTree Rd'][3].medications", IsAnything.anything()))
				.andExpect(jsonPath("$.addressesPersons[*].['951 LoneTree Rd'][4].allergies", IsAnything.anything()));
	}

	@Test
	public void testchildAlert_ReturnContent_ValidArgument() throws Exception {
		String address = "892 Downing Ct";

		mockMvc.perform(get("/childAlert")
				.param("address", address))
				.andExpect(status().isOk())

				.andExpect(jsonPath("$.children[*].firstName", IsAnything.anything()))
				.andExpect(jsonPath("$.children[*].lastName", IsAnything.anything()))
				.andExpect(jsonPath("$.children[*].age", IsAnything.anything()));
	}

	@Test
	public void testfindPersonInfoByFirstnameAndLastname_ReturnContent_ValidArgument() throws Exception {
		String firstName = "Kendrik";
		String lastName = "Stelzer";

		mockMvc.perform(get("/personInfo")
				.param("firstName", firstName)
				.param("lastName", lastName))
				.andExpect(status().isOk())

				.andExpect(jsonPath("$.persons[0].lastName", is("Stelzer")))
				.andExpect(jsonPath("$.persons[0].address", is("947 E. Rose Dr 97451 Culver")))
				.andExpect(jsonPath("$.persons[0].age", IsAnything.anything()))
				.andExpect(jsonPath("$.persons[0].email", is("bstel@email.com")))
				.andExpect(jsonPath("$.persons[0].medications[0]", is("noxidian:100mg")))
				.andExpect(jsonPath("$.persons[0].medications[1]", is("pharmacol:2500mg")))
				.andExpect(jsonPath("$.persons[0].allergies", IsAnything.anything()));
	}

	@Test
	public void testfindCommunityEmailByCity_ReturnContent_ValidArgument() throws Exception {
		String city = "Culver";

		mockMvc.perform(get("/communityEmail")
				.param("city", city))
				.andExpect(status().isOk())

				.andExpect(jsonPath("$.email", contains(
						"jaboyd@email.com",
						"drk@email.com",
						"tenz@email.com",
						"tcoop@ymail.com",
						"lily@email.com",
						"soph@email.com",
						"ward@email.com",
						"zarc@email.com",
						"reg@email.com",
						"jpeter@email.com",
						"aly@imail.com",
						"bstel@email.com",
						"ssanw@email.com",
						"clivfd@ymail.com",
						"gramps@email.com")));
	}
}
