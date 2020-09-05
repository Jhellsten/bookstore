package com.hellsten.bookstore.training.web;

import java.util.List;

import com.hellsten.bookstore.training.domain.Book;
import com.hellsten.bookstore.training.domain.BookRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
