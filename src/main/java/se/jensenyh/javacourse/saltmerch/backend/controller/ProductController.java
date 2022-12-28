package se.jensenyh.javacourse.saltmerch.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.jensenyh.javacourse.saltmerch.backend.model.Product;
import se.jensenyh.javacourse.saltmerch.backend.service.ProductService;

import java.util.List;

@RestController

public class ProductController
{
@Autowired
    ProductService productService;
@GetMapping("/products")
    public  ResponseEntity<List<Product>> getProducts(){
    List<Product>productList =productService.getProducts();
   return ResponseEntity.ok(productList);
}
@GetMapping("/products/category")
    public ResponseEntity<List<Product>> getProducts(String category){
    List<Product>productList=productService.getProducts(category);
    return  ResponseEntity.ok(productList);
}
@GetMapping("/products/{id}")
    public ResponseEntity<List<Product>> getProductById(@PathVariable("id") int id){
    List<Product>productList=productService.getProducts();
    return ResponseEntity.ok(productList);
}
@PostMapping("/products/hats")
    public ResponseEntity<Product> postProductAndProps(@RequestBody Product product){
    return new ResponseEntity<>( product,HttpStatus.CREATED);
}
@PutMapping("/products/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
    int res = productService.updateProduct(id, product);
    switch (res) {
        case 0:
            return ResponseEntity.badRequest().body("No product with that product id is present");
        case 1:
            return ResponseEntity.status(200).body("The product is updated");
        default:
            return ResponseEntity.internalServerError().body("Server error, probably");
    }


}



}

