package com.fellner.warehouse.orm.Controller;
import com.fellner.warehouse.orm.entities.Product;
import com.fellner.warehouse.orm.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

/**
 * Product - Manages all HTTP requests
 *
 * @author Manuel Fellner
 * @version 2024-04-09
 */

@Controller
@RequestMapping(path = "/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewProduct (@RequestParam String name, @RequestParam String productCategory, @RequestParam int productQuantity, @RequestParam String productUnit)    {
        Product product = new Product();
        product.setName(name);
        product.setProductCategory(productCategory);
        product.setProductQuantity(productQuantity);
        product.setProductUnit(productUnit);

        productRepository.save(product);
        return "Product with the name " + name + " saved!";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Product> getAllProducts ()  {
        return productRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<Product> getSpecificProduct(@PathVariable int id) {
        return productRepository.findById(id);
    }


}
