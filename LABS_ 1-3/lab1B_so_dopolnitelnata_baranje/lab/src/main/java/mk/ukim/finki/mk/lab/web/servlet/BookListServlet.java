package mk.ukim.finki.mk.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import mk.ukim.finki.mk.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import java.io.IOException;

@WebServlet (name="booklist-servlet",urlPatterns = "/listBooks")
public class BookListServlet extends HttpServlet {
    private final SpringTemplateEngine springTemplateEngine;
    private final BookService bookService;

    public BookListServlet(SpringTemplateEngine springTemplateEngine, BookService bookService) {
        this.springTemplateEngine = springTemplateEngine;
        this.bookService = bookService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req,resp);
        WebContext context = new WebContext(webExchange);
        /* Search dopolnitelna funkcija
        String search = req.getParameter("search");
        List<Book> lista = bookService.listBooks();
        if(search!=null)
        {

            lista = lista.stream().filter(b->b.getTitle().contains(search) || b.getGenre().contains(search)
                    || b.getIsbn().contains(search)).collect(Collectors.toList());
        }
         */
        context.setVariable("books",bookService.listBooks());
        springTemplateEngine.process("listBooks.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookIsbn = req.getParameter("isbn");
        HttpSession session = req.getSession();
        session.setAttribute("bookIsbn",bookIsbn);
        resp.sendRedirect("/author?isbn="+bookIsbn);
    }
}
