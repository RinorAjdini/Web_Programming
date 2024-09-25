package mk.ukim.finki.mk.lab.web.controller;

import mk.ukim.finki.mk.lab.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/author")
public class AuthorController
{
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService)
    {
        this.authorService = authorService;
    }
    @PostMapping
    public String getAuthorPage(@RequestParam(name = "isbn") String bookIsbn, Model model)
    {
        if (bookIsbn == null || bookIsbn.equals(""))
            return "redirect:/books";
        model.addAttribute("bookIsbn", bookIsbn);
        model.addAttribute("authors", this.authorService.listAuthors());
        return "authorList";
    }
}