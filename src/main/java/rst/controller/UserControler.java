package rst.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserControler {
	Logger logger = LoggerFactory.getLogger(super.getClass());
	


	
	@RequestMapping("toLogin")
	public String toLogin() {
		return "login/login";
	}
	@RequestMapping("toIndex")
	public String toIndex() {
		return "jsp/index";
	}

	@RequestMapping("toTest")
	public String toTest() {
		return "jsp/baowenForTest";
	}
	@RequestMapping("toFDD")
	public String toFDD() {
		return "jsp/baowenForFDD";
	}
	@RequestMapping("toQueryResult")
	public String toQueryResult() {
		return "jsp/queryResult";
	}

	@RequestMapping("toUploadForm")
	public String toUploadForm() {
		return "jsp/uploadForm";
	}
	

	
}
