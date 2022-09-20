package com.example.InventoryMicroservice.API;

import com.example.InventoryMicroservice.Model.Part;
import com.example.InventoryMicroservice.Model.Product;
import com.example.InventoryMicroservice.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/product")//localhost:8080/api/v1/supplier
@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public void createProduct(@RequestBody Product product){ productService.addProduct(product); }

    @GetMapping
    public List<Product> getAllProducts(){ return productService.getAllProducts(); }

    @GetMapping(path = "{ID}")
    public Product getProductById(@PathVariable("ID") Long id){ return productService.getProductById(id); }

    @DeleteMapping(path = "{ID}")
    public void deleteProductById(@PathVariable("ID") Long id){ productService.deleteProductById(id);}

    @PutMapping(path = "{ID}")
    public void updateProductById(@PathVariable("ID") Long id, @RequestBody Product product){ productService.updateProductByID(id, product);}

    @GetMapping(path = "{ID}/parts")
    public List <Part> getPartsInProductById(@PathVariable("ID") Long id){return productService.getAllProductParts(id);}

    @PutMapping(path = "{productID}/{partID}")
    public void addPartToProduct(@PathVariable ("productID") Long productId, @PathVariable ("partID") Long partId){ productService.addPartToProduct(productId,partId);}
}
