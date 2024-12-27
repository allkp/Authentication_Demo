package com.springdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerCls {

	@GetMapping("/allkp")
	public String getMethod() {
		System.out.println("ALLKP.........!!!");
		return "ALLKP.........!!!";
	}
	
}
