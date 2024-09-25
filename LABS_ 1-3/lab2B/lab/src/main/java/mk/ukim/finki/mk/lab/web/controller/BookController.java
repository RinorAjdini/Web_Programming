package mk.ukim.finki.mk.lab.web.controller;


import mk.ukim.finki.mk.lab.model.Book;
import mk.ukim.finki.mk.lab.service.BookService;
import mk.ukim.finki.mk.lab.service.BookStoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;


@Controller
@RequestMapping("/books")
public class BookController
{
    private final BookService bookService;
    private final BookStoreService bookStoreService;

    public BookController(BookService bookService, BookStoreService bookStoreService)
    {
        this.bookService = bookService;
        this.bookStoreService = bookStoreService;
    }

    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String error, Model model)
    {
        if (error != null && !error.isEmpty())
        {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("books", this.bookService.listBooks());
        return "listBooks";
    }



    @PostMapping("/editBook")
    public String handleEditBookForm(@RequestParam(name="id") Long id,
                                     @RequestParam(name = "editTitle") String title,
                                     @RequestParam(name = "editIsbn") String isbn,
                                     @RequestParam(name = "editGenre") String genre,
                                     @RequestParam(name = "editYear") int year,
                                     @RequestParam(name = "editStore") Long storeID)
    {
        Optional<Book> b = this.bookService.findById(id);
        if(b.isPresent())
        {
            Book book = b.get();
            book.title=title;
            book.isbn=isbn;
            book.genre=genre;
            book.year=year;
            book.setBookStore(this.bookStoreService.findAll().stream().filter(bs->bs.getId().equals(storeID)).findFirst().get());
        }
        else {
            throw new IllegalArgumentException("Book not found");
        }
//        this.bookService.save(isbn,title,genre,year,storeID);
        return "redirect:/books";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditBookForm(@PathVariable Long id, Model model)
    {
        Book bookToEdit = null;
        if(this.bookService.findById(id).isPresent()) {
            bookToEdit = this.bookService.findById(id).get();
        }
        else return "redirect:/books?error=book Not Found";
        model.addAttribute("book", bookToEdit);
        model.addAttribute("id",id);
        model.addAttribute("stores", this.bookStoreService.findAll());
        return "edit-book";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id)
    {
        this.bookService.deleteBookByID(id);
        return "redirect:/books";
    }

    @GetMapping("/add-form")
    public String getAddBookPage(Model model)
    {
        model.addAttribute("bookStores", this.bookStoreService.findAll());
        return "add-book";
    }

    @PostMapping("/addNewBook")
    public String saveBook(@RequestParam(name = "isbn") String isbn,
                           @RequestParam(name = "Title") String title,
                           @RequestParam(name = "genre") String genre,
                           @RequestParam(name = "year") int year,
                           @RequestParam(name = "store") Long idStore)
    {
        this.bookService.addNewBook(isbn,title,genre,year,idStore);
        return "redirect:/books";
    }
}