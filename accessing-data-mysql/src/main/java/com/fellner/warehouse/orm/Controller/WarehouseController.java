package com.fellner.warehouse.orm.Controller;

import com.fellner.warehouse.orm.entities.Product;
import com.fellner.warehouse.orm.entities.Warehouse;
import com.fellner.warehouse.orm.repositories.ProductRepository;
import com.fellner.warehouse.orm.repositories.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * WarehouseController - Manages all HTTP requests
 *
 * @author Manuel Fellner
 * @version 2024-04-09
 */

@Controller
@RequestMapping(path = "/warehouse")
public class WarehouseController {

    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewWarehouse(@RequestParam String name, @RequestParam String address, @RequestParam int postalCode, @RequestParam String city, @RequestParam String country) {
        Warehouse warehouse = new Warehouse();
        warehouse.setName(name);
        warehouse.setAddress(address);
        warehouse.setPostalCode(postalCode);
        warehouse.setCity(city);
        warehouse.setCountry(country);
        warehouse.setTimestamp(LocalDateTime.now());

        warehouseRepository.save(warehouse);
        return "Warehouse with the name " + name + " saved!";
    }

    @PostMapping (path = "{id}/addProduct")
    public @ResponseBody String addProductToWarehouse (@PathVariable int id, @RequestParam String name, @RequestParam String productCategory, @RequestParam int productQuantity, @RequestParam String productUnit) {
        Product product = new Product();
        product.setName(name);
        product.setProductCategory(productCategory);
        product.setProductQuantity(productQuantity);
        product.setProductUnit(productUnit);

        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(id);

        if (warehouseOptional.isPresent()) {
            Warehouse warehouse = warehouseOptional.get();
            warehouse.addProduct(product);
            product.setWarehouse(warehouse);
            warehouseRepository.save(warehouse);
            return "Product " + name + " added to warehouse " + warehouse.getName();
        } else {
            return "Warehouse with ID " + id + " not found!";
        }
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody Optional<Warehouse> getSpecificWarehouse(@PathVariable int id) {
        return warehouseRepository.findById(id);
    }
}
