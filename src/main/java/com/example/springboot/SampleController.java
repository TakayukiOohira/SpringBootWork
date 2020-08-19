package com.example.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SampleController {

	@RequestMapping(value = "/sample/test", method = RequestMethod.GET)
	public ModelAndView test(ModelAndView mav) {
		mav.setViewName("test");
		mav.addObject("title","Inquiry Form");
		return mav;
	}

}
