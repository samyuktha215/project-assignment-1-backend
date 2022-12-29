package se.jensenyh.javacourse.saltmerch.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.jensenyh.javacourse.saltmerch.backend.model.Product;
import se.jensenyh.javacourse.saltmerch.backend.service.ProductService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3010")
public class ProductController
{
@Autowired
    ProductService productService;
@GetMapping("/products")
    public  ResponseEntity<List<Product>> getProducts(){
    List<Product>productList =productService.getProducts();
   return ResponseEntity.ok(productList);
}
@GetMapping("/products/hats")
    public ResponseEntity<List<Product>> getProducts(String hats) {
    List<Product> productList = productService.getProducts(hats);
    return ResponseEntity.ok(productList);
}
@GetMapping("/products/{id}")
    public ResponseEntity<List<Product>> getProductById(@PathVariable("id") String id){
    List<Product>productList=productService.getProductsOffCategory(id);
    return ResponseEntity.ok(productList);
}
@PostMapping("/products/jackets")
    public ResponseEntity<Product> postProductAndProps(@RequestBody Product product){
    return new ResponseEntity<>( product,HttpStatus.CREATED);
}
@DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") Integer id)
    {
        if (productService.deleteProduct(id) >= 0)
            return ResponseEntity.ok().build();
        return ResponseEntity.internalServerError().build();
    }
@PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable("id") Integer id, @RequestBody Product product) {
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

