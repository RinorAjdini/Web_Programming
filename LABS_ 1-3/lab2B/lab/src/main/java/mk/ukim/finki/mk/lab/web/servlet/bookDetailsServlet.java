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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name="bookdetails-servlet",urlPatterns = "/servlet/bookDetails")
public class bookDetailsServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final BookService bookService;
    private final AuthorService authorService;
    public bookDetailsServlet(SpringTemplateEngine springTemplateEngine,BookService bookService,AuthorService authorService) {
        this.springTemplateEngine = springTemplateEngine;
        this.bookService = bookService;
        this.authorService =authorService;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req,resp);
        WebContext context = new WebContext(webExchange);
        HttpSession session =req.getSession();
        String bookIsbn = (String) session.getAttribute("bookIsbn");
        Book b = bookService.findBookByIsbn(bookIsbn);
        context.setVariable("book",b);
        long authorId = -1;
        if(session.getAttribute("author")==null){
            springTemplateEngine.process("bookDetails.html",context,resp.getWriter());
        }
        else {
            authorId = Long.parseLong((String) session.getAttribute("author"));
            // String[] lista = req.getParameterValues("more_authors");
            // Object lista = context.getVariable("listaauthori");
            Author auth = authorService.findById(authorId);
            bookService.addAuthorToBook(authorId, bookIsbn);
            context.setVariable("auth", auth);
            springTemplateEngine.process("bookDetails.html", context, resp.getWriter());
        }
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req,resp);
//        WebContext context = new WebContext(webExchange);
//        HttpSession session = req.getSession();
//        String bookIsbn = (String) session.getAttribute("bookIsbn");
//        Book b = bookService.findBookByIsbn(bookIsbn);
//        String[] lista = req.getParameterValues("more_authors");
//        List<Author> authorLista1 = new ArrayList<>();
//        for(int i=0;i<lista.length;i++)
//        {
//            authorLista1.add(authorService.findById(Long.valueOf(lista[i])));
//        }
//        String bookIsbn2 = (String) session.getAttribute("bookIsbn");
//        springTemplateEngine.process("bookDetails.html",context,resp.getWriter());
//    }

}
