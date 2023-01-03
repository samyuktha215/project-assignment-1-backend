package se.jensenyh.javacourse.saltmerch.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.jensenyh.javacourse.saltmerch.backend.model.CartItem;
import se.jensenyh.javacourse.saltmerch.backend.service.CartService;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3010")
public class CartController
{
    @Autowired
    CartService cartService;
    @GetMapping ("/carts/{id}")
    public ResponseEntity<List<CartItem>> getAllCartItems(@PathVariable("id") Integer id){
        List<CartItem>cartItems=cartService.getALLItems();
        return  ResponseEntity.ok(cartItems);
    }
   //* @PatchMapping("/carts/{id}")
   // public Object addItem(@RequestParam("action") CartItem item){
      //  boolean action="add".equals(item);
      //  if(action){
       // return cartService.addItem(item);
       // }
        //return null;//
   // }







    
}
