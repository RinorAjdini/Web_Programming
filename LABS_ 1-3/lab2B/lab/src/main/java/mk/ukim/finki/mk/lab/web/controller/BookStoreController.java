package mk.ukim.finki.mk.lab.web.controller;

import mk.ukim.finki.mk.lab.model.Book;
import mk.ukim.finki.mk.lab.model.BookStore;
import mk.ukim.finki.mk.lab.service.BookService;
import mk.ukim.finki.mk.lab.service.BookStoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/bookstore")
public class BookStoreController {
    private final BookStoreService bookStoreService;
    private final BookService bookService;

    public BookStoreController(BookStoreService bookStoreService, BookService bookService) {
        this.bookStoreService = bookStoreService;
        this.bookService = bookService;
    }

    @GetMapping
    public String getBookstorePage(Model model){
        model.addAttribute("bookstores",this.bookStoreService.findAll());
        return "bookstorelist";
    }
    @GetMapping("/details/{id}")
    public String getEditBookForm(@PathVariable Long id, Model model)
    {
        List<Book> books = this.bookService.listBooks().stream().filter(i->i.getBookStore().getId().equals(id)).toList();
        model.addAttribute("books", books);
        BookStore bookStore = this.bookStoreService.findAll().stream().filter(i->i.getId().equals(id)).findFirst().get();
        model.addAttribute("bookStore",bookStore);
        return "bookStoreDetails";
    }

}
