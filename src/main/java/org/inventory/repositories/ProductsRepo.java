package org.inventory.repositories;

import org.inventory.model.Products;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductsRepo extends MongoRepository<Products,String> {
}
