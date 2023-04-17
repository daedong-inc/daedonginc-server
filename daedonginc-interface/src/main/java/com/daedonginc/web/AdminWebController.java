package com.daedonginc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author domo
 * Created on 2023/04/17
 */
@Controller
@RequestMapping("/admin")
public class AdminWebController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}
}
