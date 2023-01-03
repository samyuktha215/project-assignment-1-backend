package se.jensenyh.javacourse.saltmerch.backend.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.web.bind.annotation.*;
import se.jensenyh.javacourse.saltmerch.backend.model.ColorVariant;
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
@GetMapping("products/hats")
    public ResponseEntity getProductsOfCategory(String hats) {
    List<Product>productList=productService.getProductsOfCategory("hats");
    return  ResponseEntity.ok(productList);
}

@GetMapping("/products/{id}")
    public Object getProductById(@PathVariable("id") Integer id){
    return productService.getEntireProduct(id);
}
@PostMapping("/products/jackets")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
    return new ResponseEntity<>( product,HttpStatus.CREATED);
}
@DeleteMapping("/products/{id}")
    public ResponseEntity<Integer> deleteProduct(@PathVariable("id") Integer id)
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
            return ResponseEntity.internalServerError().body("Server error, probably");
        default:
            return ResponseEntity.status(200).body("The product is updated");
    }
}
@PutMapping("/products/{id}/variants/stock")
public ResponseEntity<Integer> restockSize(HttpServletRequest request){
   String size=request.getParameter("size");
   String color=request.getParameter("color");
   Integer quantity=request.getContentLength();
   return new ResponseEntity<>(HttpStatus.OK);
}
@PostMapping("/products/{id}/variants")
    public ResponseEntity<ColorVariant> addVariant(HttpServletRequest req,@RequestBody ColorVariant reqBody) {
    return new ResponseEntity<>(reqBody,HttpStatus.valueOf(201));
}
}





