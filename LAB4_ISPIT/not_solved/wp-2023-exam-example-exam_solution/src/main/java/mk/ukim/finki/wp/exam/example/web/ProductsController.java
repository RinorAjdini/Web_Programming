package mk.ukim.finki.wp.exam.example.web;

import mk.ukim.finki.wp.exam.example.model.Product;
import mk.ukim.finki.wp.exam.example.service.CategoryService;
import mk.ukim.finki.wp.exam.example.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;


public class ProductsController {


    public String showProducts(String nameSearch, Long categoryId) {
       return "";
    }


    public String showAdd() {
        return "";
    }


    public String showEdit(Long id) {
        return "";
    }


    public String create(String name,
                         Double price,
                         Integer quantity,
                         List<Long> categories) {
        return "";
    }


    public String update(Long id,
                         String name,
                         Double price,
                         Integer quantity,
                         List<Long> categories ){
        return "";
    }


    public String delete(Long id) {
        return "";
    }
}
