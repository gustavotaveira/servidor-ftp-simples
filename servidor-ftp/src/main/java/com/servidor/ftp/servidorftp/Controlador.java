package com.servidor.ftp.servidorftp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controlador {
	@RequestMapping("/hello")
	public String hello() {
		return "Ola Mundo";
	}
}
