package com.scaleSampark.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scaleSampark")
public class ScaleSamparkController {

	@RequestMapping("ping")
	public String getServerStatus() {
		return "Success";
	}

}
