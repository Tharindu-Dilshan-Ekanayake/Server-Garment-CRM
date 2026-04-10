package com.garmentsystem.crm.repository;

import com.garmentsystem.crm.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

     //get all inventory



}
