package org.inventory.repositories;

import org.inventory.database.model.Products;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductsRepo extends MongoRepository<Products,String> {
}
