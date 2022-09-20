package com.example.SalesMicroservice.Repository;

import com.example.SalesMicroservice.Model.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepo extends JpaRepository<Part, Long> {
}
