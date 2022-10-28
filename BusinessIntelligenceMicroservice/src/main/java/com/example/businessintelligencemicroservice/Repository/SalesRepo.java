package com.example.businessintelligencemicroservice.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import com.example.businessintelligencemicroservice.Model.Sale;

@NoRepositoryBean
public interface SalesRepo extends JpaRepository<Sale, Long> {


}
