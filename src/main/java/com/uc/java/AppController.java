package com.uc.java;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AppController {

	@Autowired
	private ProductService service;
	
	Logger logger=LoggerFactory.getLogger(SpringBootDemoApplication.class);

	@RequestMapping("/")
	public String viewHomePage(Model model) {
		System.out.println("controller method");
		logger.debug("Request {}");
		List<Product> listProducts = service.listAll();
		model.addAttribute("listProducts", listProducts);
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewPage(Model model) {
		logger.debug("Request {}");
		logger.info("info logging in new ");
		Product product=new Product();
		model.addAttribute("newProduct",product);
		return "new_Product";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String savePage(@ModelAttribute("saveproduct") Product product) {
		logger.debug("Request{}",product);
		service.save(product);
		return "redirect:/";
	}
	
	@RequestMapping(value="/edit/{id}")
	public ModelAndView editPage(@PathVariable(name="id") int id) {
			ModelAndView mv=new ModelAndView("edit_product");
			Product product=service.get(id);
			mv.addObject("product",product);
			return mv;
	}
	
	@RequestMapping("/delete/{id}")
	public String deletePage(@PathVariable(name="id") int id) {
		service.delete(id);
		return "redirect:/";
	}
	
}
