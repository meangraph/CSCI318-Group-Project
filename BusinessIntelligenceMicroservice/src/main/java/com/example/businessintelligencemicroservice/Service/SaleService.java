package com.example.businessintelligencemicroservice.Service;

import com.example.businessintelligencemicroservice.Repository.SalesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import com.example.businessintelligencemicroservice.Model.Sale;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.function.Consumer;

@Service
public class SaleService {
    @Autowired
    public SaleService(@Qualifier("salesRepo") SalesRepo salesRepo) {
        this.salesRepo = salesRepo;
    }

    @Bean
    public Consumer<Sale> consumer(){
        return sale -> salesRepo.save(sale);
    }

    private final SalesRepo salesRepo;



    public List<Sale> getAllSales() {return salesRepo.findAll();}

}
