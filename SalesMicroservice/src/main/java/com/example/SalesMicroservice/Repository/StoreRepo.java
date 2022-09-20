package com.example.SalesMicroservice.Repository;

import com.example.SalesMicroservice.Model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepo extends JpaRepository<Store,Long> {
}
