package mk.ukim.finki.mk.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import mk.ukim.finki.mk.lab.service.AuthorService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import java.io.IOException;


@WebServlet(name="author-servlet" , urlPatterns = "/servlet/author")
public class AuthorServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final AuthorService authorService;

    public AuthorServlet(SpringTemplateEngine springTemplateEngine, AuthorService authorService) {
        this.springTemplateEngine = springTemplateEngine;
        this.authorService = authorService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req,resp);
        WebContext context = new WebContext(webExchange);
        HttpSession session = req.getSession();
        String bookIsbn = (String) session.getAttribute("bookIsbn");
        String bookIsbn1 = req.getParameter("isbn");
        /* SEARCH DOPOLNITELNA FUNKCIJA
        List<Author> listAuthors = authorService.listAuthors();
        String searchAuthor = req.getParameter("searchAuthor");
        if(searchAuthor!=null){
            listAuthors = listAuthors.stream().filter(au->au.getName().equals(searchAuthor)
                    || au.getSurname().equals(searchAuthor)  || au.getBiography().contains(searchAuthor))
                    .collect(Collectors.toList());
        }
        Don't forget in the next line in context in variable authors to set listAuthors
         */
        context.setVariable("authors",authorService.listAuthors());
        context.setVariable("isbn",bookIsbn);
        springTemplateEngine.process("authorList.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("author",req.getParameter("authorId"));
        session.setAttribute("bookIsbn",req.getParameter("isbn"));
        resp.sendRedirect("/bookDetails");
    }
}
