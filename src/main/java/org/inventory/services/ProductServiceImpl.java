package org.inventory.services;

import org.inventory.MyException.ProductAlreadyExistException;
import org.inventory.MyException.QuantityException;
import org.inventory.dto.RequestProduct;
import org.inventory.model.Products;
import org.inventory.repositories.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductsRepo productsRepo;


    @Override
    public Products addProducts(RequestProduct requestProducts) {
        Products products = new Products();
        validate(requestProducts.getProductName());
        quantityValidation(requestProducts.getQuantityOfProduct());
        quantityValidation(requestProducts.getQuantityOfProductAdded());
        quantityValidation(requestProducts.getQuantityOfProductRemoved());
        products.setProductName(requestProducts.getProductName());
        products.setQuantityOfProduct(requestProducts.getQuantityOfProduct());
        products.setQuantityOfProductAdded(requestProducts.getQuantityOfProductAdded());
        products.setQuantityOfProductRemoved(requestProducts.getQuantityOfProductRemoved());
        productsRepo.save(products);
        return products;
    }

    @Override
    public long count() {
        return productsRepo.count();
    }

//    public void validate(String productName){
//        for(Products products: productsRepo.findAll()){
//            if (products.getProductName().equalsIgnoreCase(productName)){
//                throw new ProductAlreadyExistException("product already exist");
//            }
//        }
//    }
        public void validate(String productName){
            for(Products products: productsRepo.findAll()){
                String existingProductName = products.getProductName();
                if (existingProductName != null && existingProductName.equalsIgnoreCase(productName)){
                    throw new ProductAlreadyExistException("Product already exists: " + productName);
                }
            }
        }

    public void quantityValidation(int quantityOfProduct){
            if (quantityOfProduct < 0){
                throw new QuantityException("Number of Product must not be a Negative Number");
            }
    }


}
