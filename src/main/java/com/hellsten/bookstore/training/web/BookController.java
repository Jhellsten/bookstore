package com.hellsten.bookstore.training.web;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hellsten.bookstore.training.domain.Book;
import com.hellsten.bookstore.training.domain.BookRepo;
import com.hellsten.bookstore.training.domain.CategoryRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookController {

    @Autowired
    private BookRepo books;
    @Autowired
    private CategoryRepo categories;

    // REST API for all books
    @RequestMapping(value="/books", method = RequestMethod.GET)
        public @ResponseBody List<Book> studentListRest() {
        return (List<Book>) books.findAll();
    }
    
    // Get all books
    @GetMapping("/booklist")
    public String getFriends(Model model) {
        List<Book> allBooks = books.findAll();
        model.addAttribute("books", allBooks);
        for (Book book : allBooks) {
            System.out.println(book);
        }
        return "book";
    }

    // REST API for delete book
    @RequestMapping(value="/books/{id}", method = RequestMethod.DELETE)
        public @ResponseBody String deleteBookById() {
        return "deleted";
    }

    // Delete book by id
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    books.deleteById(bookId);
    return "redirect:../booklist";
    }   

    // Get values for new book
    @RequestMapping(value = "/newbook", method = RequestMethod.GET)
    public String newBook(Model model) {
    model.addAttribute("book", new Book());
    model.addAttribute("categories", categories.findAll());
    return "addbook";
    }   

    // REST API for get book by id
    @RequestMapping(value="/books/{id}", method = RequestMethod.GET)
    public @ResponseBody Book getBookById(@PathVariable("id") Long bookId) {
        return books.findById(bookId).orElse(null);
    }

    // REST API for Save new book
    @JsonBackReference(value = "category")
    @RequestMapping(value="/books",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    Book saveApiBook(@RequestBody Book book) {
    return books.save(book);
    }   

    // REST API for edit existing book
    @JsonBackReference(value = "category")
    @RequestMapping(value="/books/{id}",method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    Book saveApiBook(@RequestBody Book book, @PathVariable("id") Long bookId) {
        return books.findById(bookId)
        .map(existingBook -> {
        existingBook.setAuthor(book.getAuthor());;
        existingBook.setTitle(book.getTitle());;
        existingBook.setYear(book.getYear());;
        existingBook.setIsbn(book.getIsbn());;
        existingBook.setCategory(book.getCategory());;
        return books.save(existingBook);
      })
      .orElseGet(() -> {
        book.setId(bookId);
        return books.save(book);
      });
    }   

    // Edit existing book
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editBook(@PathVariable("id") Long bookId, Model model) {
    model.addAttribute("book", books.findById(bookId));
    model.addAttribute("categories", categories.findAll());
    return "editbook";
    }   

    // Save new book
    @RequestMapping(value = "/newbook", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ADMIN')")
    public String saveBook(@ModelAttribute Book book, Model model) {
    System.out.println(book);
        books.save(book);
    return "redirect:booklist";
    }   
 
}
