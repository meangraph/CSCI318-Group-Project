package com.example.InventoryMicroservice.Repository;


import com.example.InventoryMicroservice.Model.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepo  extends JpaRepository<Part, Long> {
}
