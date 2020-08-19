package com.example.springboot;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.springboot.repositories.InquiryDataRepository;
import com.example.springboot.repositories.SampleDataRepository;

@Controller
public class SampleController {

	@Autowired
	SampleDataRepository sampleRepository;

	@Autowired
	InquiryDataRepository repository;

	@PersistenceContext
	EntityManager entityManager;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView test(ModelAndView mav) {
		mav.setViewName("test");
		mav.addObject("title","Inquiry Form");

		Iterable<SampleData> list = sampleRepository.findAll();
		mav.addObject("data", list);
		return mav;
	}

//	@RequestMapping(value = "/form", method = RequestMethod.GET)
	@RequestMapping(value = "/form")
	public ModelAndView form(@ModelAttribute("formModel") InquiryData inquiryData, ModelAndView mav) {
		mav.setViewName("form");
		mav.addObject("formModel", inquiryData);
		mav.addObject("title", "Inquiry Form");
		return mav;
	}

//	@RequestMapping(value = "/form", method = RequestMethod.POST)
//	public ModelAndView form_back(@ModelAttribute("formModel") InquiryData inquiryData, ModelAndView mav) {
//		mav.setViewName("form");
//		mav.addObject("formModel", inquiryData);
//		mav.addObject("title", "Inquiry Form");
//		return mav;
//	}

	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public ModelAndView confirm(@ModelAttribute("formModel") @Validated InquiryData inquiryData,
			BindingResult result, ModelAndView mav) {
		ModelAndView res = null;
		if (!result.hasErrors()){
			mav.addObject("title", "Confirm");
			mav.addObject("formModel", inquiryData);
			mav.setViewName("confirm");
			res = mav;
		} else {
			mav.setViewName("form");
			mav.addObject("title", "Inquiry Form");
			mav.addObject("formModel", inquiryData);
			res = mav;
		}
		return res;
	}

	@RequestMapping(value = "/complete", method = RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView update(@ModelAttribute("formModel") @Validated InquiryData inquiryData,
			ModelAndView mav) {
		repository.saveAndFlush(inquiryData);
		mav.setViewName("form");
		mav.addObject("title", "Inquiry Form");
		return new ModelAndView("redirect:/form");
	}

	@PostConstruct
	public void init(){
		SampleData s1 = new SampleData();
		s1.setName("ichiro");
		s1.setMail("ichiro@sample.com");
		sampleRepository.saveAndFlush(s1);
		SampleData s2 = new SampleData();
		s2.setName("hanako");
		s2.setMail("hanako@sample.com");
		sampleRepository.saveAndFlush(s2);
		SampleData s3 = new SampleData();
		s3.setName("joro");
		s3.setMail("joro@sample.com");
		sampleRepository.saveAndFlush(s3);
	}
}
