package com.scaleSampark.controllerTest;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaleSampark.collector.RegistrationCollector;
import com.scaleSampark.config.BootstrapApplication;
import com.scaleSampark.dto.ParticipantDetailsDto;
import com.scaleSampark.response.ScaleSamparkResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootstrapApplication.class)
@AutoConfigureMockMvc
public class RegistrationControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private RegistrationCollector registrationCollector;
	ObjectMapper mapper = new ObjectMapper();

	@Test
	public void saveParticipantDetails() throws Exception {
		ScaleSamparkResponse samparkResponse = new ScaleSamparkResponse();
		samparkResponse.setData(1);

		ParticipantDetailsDto detailsDto = new ParticipantDetailsDto();
		detailsDto.setEmail("Abc@gmail.com");
		detailsDto.setNickName("ABC");
		given(registrationCollector.saveParticipantDetails(any(ParticipantDetailsDto.class)))
				.willReturn(samparkResponse);

		mvc.perform(MockMvcRequestBuilders.post("/participant").content(mapper.writeValueAsString(detailsDto))
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data").value(samparkResponse.getData()));

	}

	@Test
	public void saveParticipantDetailsWithNoData() throws Exception {
		ScaleSamparkResponse samparkResponse = new ScaleSamparkResponse();
		samparkResponse.setData(1);

		ParticipantDetailsDto detailsDto = new ParticipantDetailsDto();
		given(registrationCollector.saveParticipantDetails(any(ParticipantDetailsDto.class)))
				.willReturn(samparkResponse);

		mvc.perform(MockMvcRequestBuilders.post("/participant").content(mapper.writeValueAsString(detailsDto))
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());

	}

	@Test
	public void saveParticipantDetailsWithNoNickName() throws Exception {
		ScaleSamparkResponse samparkResponse = new ScaleSamparkResponse();
		samparkResponse.setData(1);

		ParticipantDetailsDto detailsDto = new ParticipantDetailsDto();
		detailsDto.setEmail("Abc@gmail.com");
		given(registrationCollector.saveParticipantDetails(any(ParticipantDetailsDto.class)))
				.willReturn(samparkResponse);

		mvc.perform(MockMvcRequestBuilders.post("/participant").content(mapper.writeValueAsString(detailsDto))
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());

	}

	@Test
	public void saveParticipantDetailsWithNoEMail() throws Exception {
		ScaleSamparkResponse samparkResponse = new ScaleSamparkResponse();
		samparkResponse.setData(1);

		ParticipantDetailsDto detailsDto = new ParticipantDetailsDto();
		detailsDto.setNickName("ABC");
		given(registrationCollector.saveParticipantDetails(any(ParticipantDetailsDto.class)))
				.willReturn(samparkResponse);

		mvc.perform(MockMvcRequestBuilders.post("/participant").content(mapper.writeValueAsString(detailsDto))
				.contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());

	}

	@Test
	public void fetchParticipantDetails() throws Exception {
		ParticipantDetailsDto detailsDto = new ParticipantDetailsDto();
		detailsDto.setEmail("abc@gmail.com");
		detailsDto.setNickName("ABC");
		ScaleSamparkResponse samparkResponse = new ScaleSamparkResponse();
		samparkResponse.setData(detailsDto);
		given(registrationCollector.getParticipantList()).willReturn(samparkResponse);

		mvc.perform(MockMvcRequestBuilders.get("/participant").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.email")
						.value(((ParticipantDetailsDto) samparkResponse.getData()).getEmail()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.nickName")
						.value(((ParticipantDetailsDto) samparkResponse.getData()).getNickName()));
	}
	
	@Test
	public void fetchParticipantDetailsNoDataFound() throws Exception {
		ScaleSamparkResponse samparkResponse = new ScaleSamparkResponse();
		given(registrationCollector.getParticipantList()).willReturn(samparkResponse);

		mvc.perform(MockMvcRequestBuilders.get("/participant").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNoContent())
		.andExpect(MockMvcResultMatchers.jsonPath("$.data").doesNotExist());
	}
	
	@Test
	public void deleteParticipant() throws Exception {
		ScaleSamparkResponse samparkResponse = new ScaleSamparkResponse();
		samparkResponse.setData("Success");
		given(registrationCollector.disableParticipant((long) anyInt())).willReturn(samparkResponse);

		mvc.perform(MockMvcRequestBuilders.delete("/participant/1").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.data").value(samparkResponse.getData()));
	}
	
	@Test
	public void deleteParticipantWIthNoParticipant() throws Exception {
		ScaleSamparkResponse samparkResponse = new ScaleSamparkResponse();
		given(registrationCollector.disableParticipant((long) anyInt())).willReturn(samparkResponse);

		mvc.perform(MockMvcRequestBuilders.delete("/participant/1").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNoContent())
		.andExpect(MockMvcResultMatchers.jsonPath("$.data").doesNotExist());
	}
}
