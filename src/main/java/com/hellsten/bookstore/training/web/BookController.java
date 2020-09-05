package com.hellsten.bookstore.training.web;

import java.util.List;

import com.hellsten.bookstore.training.domain.Book;
import com.hellsten.bookstore.training.domain.BookRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BookController {

    @Autowired
    private BookRepo repository;

    @GetMapping("/booklist")
    public String getFriends(Model model) {
        List<Book> books = repository.findAll();
        model.addAttribute("books", books);
        for (Book book : books) {
            System.out.println(book);
        }
        return "book";
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    repository.deleteById(bookId);
    return "redirect:../booklist";
    }   

    @RequestMapping(value = "/newbook", method = RequestMethod.GET)
    public String newBook(Model model) {
    model.addAttribute("book", new Book());
    return "addbook";
    }   

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editBook(@PathVariable("id") Long bookId, Model model) {
    model.addAttribute("book", repository.findById(bookId));
    return "editbook";
    }   

    @RequestMapping(value = "/newbook", method = RequestMethod.POST)
    public String saveBook(@ModelAttribute Book book, Model model) {
    System.out.println(book);
        repository.save(book);
    return "redirect:booklist";
    }   
 
}
