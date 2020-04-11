package com.scaleSampark.collectorTest;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.scaleSampark.collector.MessageCollector;
import com.scaleSampark.service.MessageService;
import com.scaleSampark.util.ScaleSamparkHelper;

public class MessageCollectorTest {

	@InjectMocks MessageCollector collector;
	
	@Mock 
	MessageService messageService;
	
	@Mock
	ScaleSamparkHelper helper;
}
