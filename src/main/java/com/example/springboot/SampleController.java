package com.example.springboot;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.springboot.repositories.SampleDataRepository;

@Controller
public class SampleController {

	@Autowired
	SampleDataRepository repository;

	@PersistenceContext
	EntityManager entityManager;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView test(ModelAndView mav) {
		mav.setViewName("test");
		mav.addObject("title","Inquiry Form");

		Iterable<SampleData> list = repository.findAll();
		mav.addObject("data", list);
		return mav;
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public ModelAndView form(@ModelAttribute("formModel") InquiryData inquiryData, ModelAndView mav) {
		mav.setViewName("form");
		mav.addObject("title", "Inquiry Form");
		return mav;
	}

	@PostConstruct
	public void init(){
		SampleData s1 = new SampleData();
		s1.setName("ichiro");
		s1.setMail("ichiro@sample.com");
		repository.saveAndFlush(s1);
		SampleData s2 = new SampleData();
		s2.setName("hanako");
		s2.setMail("hanako@sample.com");
		repository.saveAndFlush(s2);
		SampleData s3 = new SampleData();
		s3.setName("joro");
		s3.setMail("joro@sample.com");
		repository.saveAndFlush(s3);
	}
}
