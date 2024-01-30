package org.inventory.services;

import com.mongodb.internal.operation.ListDatabasesOperation;
import org.inventory.dto.ListOfProductResponse;
import org.inventory.dto.ProductResponse;
import org.inventory.dto.RequestProduct;
import org.inventory.model.Products;
import org.inventory.model.RetailInventory;

import java.util.List;

public interface RetailService {
    RetailInventory create();
    List<Products> addProducts(RequestProduct requestProduct);

    RetailInventory findById(String id);

    List<Products> findByProductName(ProductResponse productResponse);

    void deleteItem(ProductResponse productResponse);


    List<Products> addQuantity(RequestProduct requestProduct);

    List<Products> listOfProduct(String Id);


    List<Products> deleteQuantity(RequestProduct requestProduct);

    long count();





}


