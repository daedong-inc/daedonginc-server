package com.daedonginc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author domo
 * Created on 2023/04/17
 */
@Controller
public class IndexController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
}
