package com.sirajul.springDemo.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sirajul.springDemo.model.Author;
import com.sirajul.springDemo.model.Book;
import com.sirajul.springDemo.repositories.AuthorRepository;
import com.sirajul.springDemo.repositories.BookRepository;
import com.sirajul.springDemo.repositories.PublisherRepository;

@Controller
public class BookController {
	// we can use autowired instant of contructor

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private PublisherRepository publisherRepository;
	// private static final Logger logger =
	// LoggerFactory.getLogger(BookController.class);

	/*
	 * public BookController(BookRepository bookRepository) { // super();
	 * this.bookRepository = bookRepository; }
	 */

	@RequestMapping("/books")
	public String getBooks(Model model) {
		// log.debug("Getting ingredient list for recipe id: " + recipeId);
		model.addAttribute("books", bookRepository.findAll());
		return "book/index";
	}

	@RequestMapping("/book/show/{id}")
	// @GetMapping("/book/show/{id}")
	public String showBook(@PathVariable("id") Book book, Model model) {
		/*
		 * if (!bookRepository.existsById(new Long(id))) { return "redirect:/";
		 * }
		 */
		// Optional<Book> book = bookRepository.findById(id);
		// model.addAttribute("book",
		// bookRepository.findById(Long.valueOf(id)));
		model.addAttribute("book", book);
		// System.out.print("this is id" + id);
		return "book/show";
	}

	@RequestMapping("/book/new")
	public String newProduct(Model model) {

		// model.addAttribute("authors", authorRepository.findAll());
		model.addAttribute("publishers", publisherRepository.findAll());
		model.addAttribute("authors", authorRepository.findAll());
		model.addAttribute("book", new Book());
		// fetch all publishers
		/*
		 * for (Publisher publisher : publisherRepository.findAll()) {
		 * logger.info(publisher.toString()); }
		 */

		return "book/bookForm";
	}

	@RequestMapping("book/edit/{id}")
	public String edit(@PathVariable("id") Book book, Model model) {
		Iterable<Author> authors = authorRepository.findAll();
		System.out.println(authors.iterator().hasNext());
		model.addAttribute("publishers", publisherRepository.findAll());
		model.addAttribute("authors", authors);
		model.addAttribute("book", book);


		// System.out.print("this is id" + id);
		return "book/editBookForm";

		/*
		 * System.out.println(studentLst.size());
		 * System.out.println(subLst.size());
		 * 
		 * 
		 * System.out.
		 * println("===================Students info:==================");
		 * studentLst.forEach(student->System.out.println(student.toString()));
		 * 
		 * System.out.
		 * println("===================Students info:==================");
		 * subLst.forEach(subject->System.out.println(subject.toString()));
		 */
	}

	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public String saveOrUpdateProduct(@Valid Book book, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			// return "redirect:/book/new";
			/*
			 * model.addAttribute("publishers", publisherRepository.findAll());
			 * model.addAttribute("bookForm", new Book());
			 */

			model.addAttribute("publishers", publisherRepository.findAll());
			model.addAttribute("authors", authorRepository.findAll());
			/*
			 * * * model.addAttribute("book", book);
			 */
			return "book/bookForm";
			// redirectAttributes.addFlashAttribute("message", "Please select a
			// file to upload");
			// return "redirect:book/new";
			// return "redirect:uploadStatus";

		}
		Book book1 = bookRepository.save(book);
		redirectAttributes.addFlashAttribute("message", "Book Added successfully");
		return "redirect:/book/show/" + book1.getId();
	}
}
