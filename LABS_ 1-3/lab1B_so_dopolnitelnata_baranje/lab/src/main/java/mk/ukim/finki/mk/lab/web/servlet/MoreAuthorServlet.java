package mk.ukim.finki.mk.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.mk.lab.model.Author;
import mk.ukim.finki.mk.lab.model.Book;
import mk.ukim.finki.mk.lab.service.AuthorService;
import mk.ukim.finki.mk.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="moreauthors-servlet",urlPatterns = "/moreauthor")
public class MoreAuthorServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final AuthorService authorService;
    private final BookService bookService;
    public MoreAuthorServlet(SpringTemplateEngine springTemplateEngine, AuthorService authorService, BookService bookService) {
        this.springTemplateEngine = springTemplateEngine;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req,resp);
        WebContext context = new WebContext(webExchange);
        HttpSession session = req.getSession();
        String bookIsbn = (String) session.getAttribute("bookIsbn");
        context.setVariable("authors",authorService.listAuthors());
        springTemplateEngine.process("moreauthorList.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req,resp);
        WebContext context = new WebContext(webExchange);
        String isbn = (String) session.getAttribute("bookIsbn");
        String[] lista = req.getParameterValues("more_authors");
        bookService.addAuthorsToBook(List.of(lista),isbn);
//        List<Author> authorLista1 = new ArrayList<>();
//        for(int i=0;i<lista.length;i++)
//        {
//            authorLista1.add(authorService.findById(Long.valueOf(lista[i])));
//        }
//        String bookIsbn = (String) session.getAttribute("bookIsbn");
        context.setVariable("listaauthori",lista);
        resp.sendRedirect("/bookDetails");
    }
}
