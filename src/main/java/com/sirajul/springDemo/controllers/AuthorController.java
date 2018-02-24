package com.sirajul.springDemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sirajul.springDemo.repositories.AuthorRepository;

@Controller
public class AuthorController {
	// we can use autowired instant of contructor
	private AuthorRepository authorRepository;

	public AuthorController(AuthorRepository authorRepository) {
		// super();
		this.authorRepository = authorRepository;
	}

	@RequestMapping("/authors")
	// @RequestMapping() // if we use this error message show me there is alreay
	// authorRepositorybeen method
	public String getAuthors(Model model) {
		model.addAttribute("authors", authorRepository.findAll());
		return "author/index";// this is for html file path
	}

	@RequestMapping("/author/show/{id}")
	public String getAuthor(@PathVariable String id, Model model) {
		model.addAttribute("author", authorRepository.findById(Long.valueOf(id)));
		return "author/show";
	}
}
