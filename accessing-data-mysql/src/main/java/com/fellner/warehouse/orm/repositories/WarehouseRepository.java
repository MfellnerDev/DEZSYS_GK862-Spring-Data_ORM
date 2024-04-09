package com.fellner.warehouse.orm.repositories;

import com.fellner.warehouse.orm.entities.Warehouse;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

/**
 * Warehouse repository
 *
 * @author Manuel Fellner
 * @version 2024-04-09
 */
public interface WarehouseRepository extends CrudRepository<Warehouse, Integer> {
}
