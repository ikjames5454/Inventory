package org.inventory.repositories;

import org.inventory.model.RetailInventory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RetailInventoryRepo extends MongoRepository<RetailInventory, String> {
}
