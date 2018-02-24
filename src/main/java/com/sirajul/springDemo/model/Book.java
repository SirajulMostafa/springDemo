package com.sirajul.springDemo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	// @Column(name = "title", nullable = false)
	@Column(nullable = false) // for DDL hints creat databse
	@Size(min = 3, max = 30)
	// @NotNull(message = "Title Name cannot be empty")
	private String title;

	@Column(nullable = false) // for DDL hints creat databse
	@Size(min = 3, max = 10)
	@NotNull(message = "Title Name cannot be empty")
	private String isbn;

	private String image;

	@OneToOne
	private Publisher publisher;
	@ManyToMany
	@JoinTable(name = "author_book", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "auhtor_id"))
	private Set<Author> authors = new HashSet<>();

	public Book() {
		// TODO Auto-generated constructor stub
	}

	public Book(long id, String title, String isbn, String image, Publisher publisher, Set<Author> authors) {
		// super();
		this.id = id;
		this.title = title;
		this.isbn = isbn;
		this.image = image;
		this.publisher = publisher;
		this.authors = authors;
	}

	public Book(long id, String title, String isbn, String image, Publisher publisher) {
		// super();
		this.id = id;
		this.title = title;
		this.isbn = isbn;
		this.publisher = publisher;
		this.image = image;
	}

	public Book(String title, String isbn, String image, Publisher publisher) {
		// super();
		this.title = title;
		this.isbn = isbn;
		this.image = image;
		this.publisher = publisher;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/*
	 * You can use toString() on an class by overriding it to provide some
	 * meaningful text representation of your object.
	 * 
	 * For example you may override toString() on a Person class to return the
	 * first and last name.
	 * 
	 * @Override public String toString() { return "Book [id=" + id + ", title="
	 * + title + ", isbn=" + isbn + ", image=" + image + ", publisher=" +
	 * publisher + ", authors=" + authors + "]"; }
	 */


	/*
	 * @Override public String toString() { return "Book [id=" + id + ", title="
	 * + title + ", isbn=" + isbn + ", image=" + image + ", publisher=" +
	 * publisher + ", authors=" + authors + "]"; }
	 */
	/*
	 * @Override public String toString() { String result = String.format(
	 * "Book [id=%d, title='%s,isbn=%s,image=%s,publisher=']%n", id, title); if
	 * (publishers != null) { for(Publisher publisher : publishers) { result +=
	 * String.format( "Publisher[id=%d, name='%s']%n", publisher.getId(),
	 * publisher.getName()); } }
	 */

	/*
	 * 4
	 * https://stackoverflow.com/questions/25891833/hibernate-stackoverflowerror
	 * /25892390
	 * https://stackoverflow.com/questions/19069098/springhibernate-java-lang-
	 * stackoverflowerror down vote accepted The toString() method for most List
	 * implementations iterates through the List elements and calls toString()
	 * on them. So calling
	 * 
	 * @Override public String toString() { return "User [userId=" + userId +
	 * ", userName=" + userName + ", userPassword=" + userPassword +
	 * ", userPriveleges=" + userPriveleges + ", userEmale=" + userEmale +
	 * ", userGender=" + userGender + ", userMessageList=" + userMessageList +
	 * "]"; } the + userMessageList is actually calling the toString() of each
	 * UserMessage which is calling toString() on each User, ad nauseam.
	 * 
	 * Change your toString() to not print them, or print just some value of
	 * them (like an ID).
	 */

	/*
	 * @Override public String toString() { String result = String.format(
	 * "Book [id=%d, name='%s']%n", id, name); if (publishers != null) {
	 * for(Publisher publisher : publishers) { result += String.format(
	 * "Publisher[id=%d, name='%s']%n", publisher.getId(), publisher.getName());
	 * } }
	 * 
	 * return result; }
	 */


}
