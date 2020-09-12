package com.hellsten.bookstore.training.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String title;
    private String author;
    private int year;
    private String isbn;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "categoryid")
    private Category category;

    public Book(String title, String author, int year, String isbn, Category category) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
        this.category = category;
    }

    public Book() {
        
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

    @Override
	public String toString() {
		return "Book [title=" + title + " author=" + author +  " year=" + year + " isbn=" + isbn + " category=" + category.getName() + "]";
	}
}
