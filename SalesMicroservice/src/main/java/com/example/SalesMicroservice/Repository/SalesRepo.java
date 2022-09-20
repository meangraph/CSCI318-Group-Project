package com.example.SalesMicroservice.Repository;

import com.example.SalesMicroservice.Model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface SalesRepo extends JpaRepository<Sale, Long> {
}
