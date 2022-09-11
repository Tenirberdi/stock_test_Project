package com.example.stock.Services;

import com.example.stock.Models.Product;
import com.example.stock.Repositories.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MainService {
    @Autowired
    ProductsRepo productsRepo;

    public List<Product> getContent(){
        return  Streamable.of(productsRepo.findAll()).toList();
    }

    public int getTotal(){
        return productsRepo.getTotal();
    }

    public int getAmountOfOneProduct(String name){

        List<String> names = productsRepo.getListOfNames();

        if(names.contains(name)){
            return productsRepo.getAmountOfOneProduct(name);
        }else {
            return -1;
        }

    }

    public void addProduct(Product newProduct){
        productsRepo.save(newProduct);
    }
}
