package com.scaleSampark.controllerTest;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
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

import com.scaleSampark.collector.MessageCollector;
import com.scaleSampark.config.BootstrapApplication;
import com.scaleSampark.dto.MessageDetailsDto;
import com.scaleSampark.response.ScaleSamparkResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootstrapApplication.class)
@AutoConfigureMockMvc
public class MessageControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private MessageCollector messageCollector;

	@Test
	public void getMessageDetailsWithMessage() throws Exception {
		MessageDetailsDto messageDetailsDto = new MessageDetailsDto();
		messageDetailsDto.setMessage("Hello, new Message");
		ScaleSamparkResponse samparkResponse = new ScaleSamparkResponse();
		samparkResponse.setData(messageDetailsDto);
		// samparkResponse.setResponseStatus(responseStatus);
		given(messageCollector.getMessageDetails(anyInt(), anyInt())).willReturn(samparkResponse);

		mvc.perform(MockMvcRequestBuilders.get("/fetchMessage").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data.message").value(((MessageDetailsDto) samparkResponse.getData()).getMessage()));
	}

	@Test
	public void getMessageDetailsIfNoMessagePresent() throws Exception {
		MessageDetailsDto messageDetailsDto = new MessageDetailsDto();
		messageDetailsDto.setMessage("Hello, new Message");
		ScaleSamparkResponse samparkResponse = new ScaleSamparkResponse();
		//samparkResponse.setData(messageDetailsDto);
		// samparkResponse.setResponseStatus(responseStatus);
		given(messageCollector.getMessageDetails(anyInt(), anyInt())).willReturn(samparkResponse);

		mvc.perform(MockMvcRequestBuilders.get("/fetchMessage").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data").doesNotExist());
	}
}



