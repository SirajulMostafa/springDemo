package com.sirajul.springDemo.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
/*import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sirajul.springDemo.model.Book;
import com.sirajul.springDemo.repositories.BookRepository;

@Controller
public class BookImageUploadController {
	// Save the uploaded file to this folder
	private static String UPLOADED_FOLDER = "C://Users//siraj//Documents//workspace-sts-3.8.4.RELEASE//springDemo//src//main//resources//static//image//";

	@Autowired
	BookRepository bookRepository;

	@RequestMapping("/book/{id}/imageupload")
	public String bookImageUploadForm(@PathVariable("id") Book book, Model model) {
		model.addAttribute("book", book);
		return "book/BookImageUploadForm";
	}

	@PostMapping("/book/{id}/imageupload") // //new annotation since 4.3
	// @Valid Book book, BindingResult bindingResult,
	// public String singleBookFileUpload(@RequestParam("image") MultipartFile
	// file,
	// @Transactional
	public String singleBookFileUpload(@Valid Book book, BindingResult bindingResult,
			@RequestParam("image") MultipartFile file,

			RedirectAttributes redirectAttributes) {

		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return "redirect:/message/checkerror";

		}

		try {

			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);

			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + file.getOriginalFilename() + "'");
			// book.setImage(file.getOriginalFilename());


		} catch (IOException e) {
			e.printStackTrace();
		}

		book.setImage(file.getOriginalFilename());
		bookRepository.save(book);

		return "redirect:/message/checksuccess";
	}

	@GetMapping("/message/checkerror")
	public String checkError() {
		return "message/checkerror";
	}

	@GetMapping("/message/checksuccess")
	public String checkSuccess() {
		return "message/checksuccess";
	}

}
