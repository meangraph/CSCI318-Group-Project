package com.example.SalesMicroservice.Service;

import com.example.SalesMicroservice.Model.InStoreSale;
import com.example.SalesMicroservice.Model.Store;
import com.example.SalesMicroservice.Repository.StoreRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    private final StoreRepo storeRepo;
    private final ProductService productService;

    public StoreService(StoreRepo storeRepo, ProductService productService) {
        this.storeRepo = storeRepo;
        this.productService = productService;
    }

    public void addStore(Store store) { storeRepo.save(store); }

    public List<Store> getAllStores(){return storeRepo.findAll();}

    public Store getStoreById(Long id){return storeRepo.findById(id).orElseThrow(() -> new RuntimeException("Store: " + id + " Not found"));}


    public List<InStoreSale> getSaleFromStore(Long storeId) {return getStoreById(storeId).getSales();}


}
