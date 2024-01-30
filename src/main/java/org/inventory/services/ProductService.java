package org.inventory.services;

import org.inventory.dto.RequestProduct;
import org.inventory.model.Products;

public interface ProductService {
    Products addProducts(RequestProduct requestProducts);
    long count();
}
