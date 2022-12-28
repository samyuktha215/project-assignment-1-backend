package se.jensenyh.javacourse.saltmerch.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import se.jensenyh.javacourse.saltmerch.backend.model.CartItem;
import se.jensenyh.javacourse.saltmerch.backend.repository.CartRepository;

import java.util.List;


public class CartService
{
    @Autowired
    CartRepository db;
    public List<CartItem> getALLItems(){
        return db.selectAllItems();
    }
    public int addItem(CartItem item){
        return db.insertOrIncrementItem(item);
    }
    public int deleteItem(CartItem item){
        return db.deleteOrDecrementItem(item);
    }

}
