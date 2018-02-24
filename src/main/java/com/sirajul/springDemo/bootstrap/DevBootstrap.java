package com.sirajul.springDemo.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.sirajul.springDemo.model.Author;
import com.sirajul.springDemo.model.Book;
import com.sirajul.springDemo.model.Publisher;
import com.sirajul.springDemo.repositories.AuthorRepository;
import com.sirajul.springDemo.repositories.BookRepository;
import com.sirajul.springDemo.repositories.PublisherRepository;

//this is bean
@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
	private AuthorRepository authorRepository;
	private BookRepository bookRepository;
	private PublisherRepository publisherRepository;

	public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository,
			PublisherRepository publisherRepository) {
		// super();
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent ContextRefreshedEvent) {
		// TODO Auto-generated method stub
		intitData();
	}

	private void intitData() {
		// TODO Auto-generated method stub
		Publisher publisher = new Publisher();
		Publisher publisher2 = new Publisher();
		Publisher publisher3 = new Publisher();
		publisher.setName("Madina Publisher");
		publisher2.setName("Madina Publisher2");
		publisher3.setName("Madina Publisher3");
		publisherRepository.save(publisher);
		publisherRepository.save(publisher2);
		publisherRepository.save(publisher3);
		Author a = new Author("sirajul", "mostafa");
		Book b = new Book("Spring with JPA ", "1234", "avator.png", publisher);
		a.getBooks().add(b);
		b.getAuthors().add(a);
		authorRepository.save(a);
		bookRepository.save(b);
		Author author2 = new Author("hasib", "hasan");
		Book book2 = new Book("Spring boot ", "12344", "avator.png", publisher2);
		author2.getBooks().add(book2);
		book2.getAuthors().add(author2);
		authorRepository.save(author2);
		bookRepository.save(book2);

	}
}
