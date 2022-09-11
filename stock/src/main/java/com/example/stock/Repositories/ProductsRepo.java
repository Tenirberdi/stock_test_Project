package com.example.stock.Repositories;


import com.example.stock.Models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductsRepo extends CrudRepository<Product, Long> {
    @Query(value = "SELECT SUM(quantity) FROM `products`", nativeQuery = true)
    int getTotal();

    @Query(value = "SELECT sum(quantity) FROM `products` WHERE `name` = ?;", nativeQuery = true)
    int getAmountOfOneProduct(String name);

    @Query(value = "SELECT name FROM `products` ", nativeQuery = true)
    List<String> getListOfNames();
}
