package mk.ukim.finki.wp.exam.example.web;

import mk.ukim.finki.wp.exam.example.model.Product;
import mk.ukim.finki.wp.exam.example.service.CategoryService;
import mk.ukim.finki.wp.exam.example.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ProductsController {
    private final ProductService service;
    private final CategoryService categoryService;

    public ProductsController(ProductService service, CategoryService categoryService) {
        this.service = service;
        this.categoryService = categoryService;
    }


    public String showProducts(String nameSearch, Long categoryId) {
        List<Product> products;
        if (nameSearch == null && categoryId == null) {
            products = this.service.listAllProducts();
        } else {
            products = this.service.listProductsByNameAndCategory(nameSearch, categoryId);
        }
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
        this.service.create(name, price, quantity, categories);
        return "";
    }


    public String update(Long id,
                         String name,
                         Double price,
                         Integer quantity,
                         List<Long> categories) {
        this.service.update(id, name, price, quantity, categories);
        return "";
    }


    public String delete(Long id) {
        this.service.delete(id);
        return "";
    }
}
