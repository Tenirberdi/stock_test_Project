package com.example.stock.Controllers;

import com.example.stock.Models.Product;
import com.example.stock.Services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    MainService mainService;

    int total = -1;

    @GetMapping("/")
    public String getStarted(Model model){
        model.addAttribute("products", mainService.getContent());

        if(total==-1){
            model.addAttribute("total", mainService.getTotal());
        }
        else {

            model.addAttribute("total", total);
            total = -1;
        }
        return "index";
    }

    @GetMapping("/newProduct")
    public String addNewProduct(){
        return "newProduct";
    }

    @PostMapping("/amountOfOneProduct")
    public String getAmount(@RequestParam String name, RedirectAttributes redirectAttributes){
        total = mainService.getAmountOfOneProduct(name);
        return "redirect:/";
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestParam String name, @RequestParam int price , @RequestParam int quantity, @RequestParam Date date){

        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setPrice(price);
        newProduct.setQuantity(quantity);
        newProduct.setDate(date);

        mainService.addProduct(newProduct);

        return "redirect:/";
    }
}
