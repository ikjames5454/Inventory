package org.inventory.controllers;

import org.inventory.dto.ProductResponse;
import org.inventory.dto.RequestProduct;
import org.inventory.model.Products;
import org.inventory.model.RetailInventory;
import org.inventory.services.RetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/inventory")
public class RetailInventoryController {
    @Autowired
    private RetailServiceImpl retailService;
    @PostMapping("/create")
    public RetailInventory create(){
        return retailService.create();
    }
    @PostMapping("/listOfProduct")
    public String listOfProducts(@RequestBody RequestProduct requestProduct){
        try {
            return retailService.addProducts(requestProduct).toString();
        }catch (Exception e){
            return e.getMessage();
        }
    }
    @GetMapping("/findById")
    public String findById(@RequestBody String id){
        try{
        return retailService.findById(id).toString();
        }catch(Exception e){
            return e.getMessage();
        }
    }
    @GetMapping("/findListOfProduct")
    public String findListOfProduct(@RequestBody String id){
        try{
            return retailService.listOfProduct(id).toString();
        }catch (Exception e){
            return "no list of product found";
        }
    }
    @GetMapping("/findProductByName")
    public List<Products> findProductByName(@RequestBody ProductResponse productResponse){
        return retailService.findByProductName(productResponse);
    }
    @DeleteMapping("/deleteProduct")
    public String deleteProduct(@RequestBody ProductResponse productResponse){
        try{
            retailService.deleteItem(productResponse);
            return "successfully deleted";
        }catch (Exception e){
            return "Unsuccessful";
        }
    }
    @PostMapping("/addQuantity")
    public List<Products> addQuantityOfProduct(@RequestBody RequestProduct requestProduct){
        return retailService.addQuantity(requestProduct);
    }

    @PostMapping("/RemoveQuantity")
    public List<Products> removeQuantityOfProduct(@RequestBody RequestProduct requestProduct){
        return retailService.deleteQuantity(requestProduct);
    }

}
