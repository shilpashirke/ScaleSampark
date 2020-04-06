package com.scaleSampark.controllerTest;

import static org.mockito.BDDMockito.given;
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

import com.scaleSampark.collector.RegistrationCollector;
import com.scaleSampark.config.BootstrapApplication;
import com.scaleSampark.dto.MessageDetailsDto;
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
	
	@Test
	public void saveParticipantDetails() throws Exception {/*
		ParticipantDetailsDto participantDetailsDto = new ParticipantDetailsDto();
		participantDetailsDto.setMessage("Hello, new Message");
		ScaleSamparkResponse samparkResponse = new ScaleSamparkResponse();
		samparkResponse.setData(messageDetailsDto);
		// samparkResponse.setResponseStatus(responseStatus);
		given(messageCollector.getMessageDetails(anyInt(), anyInt())).willReturn(samparkResponse);

		mvc.perform(MockMvcRequestBuilders.get("/fetchMessage").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.message").value(((MessageDetailsDto) samparkResponse.getData()).getMessage()));
	*/}

}
