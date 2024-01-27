package org.inventory.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RetailInventory extends MongoRepository<RetailInventory, String> {
}
