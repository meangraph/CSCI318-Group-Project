package com.example.ProcurementMicroservice.Repository;

import com.example.ProcurementMicroservice.Model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier, Long> {
}
