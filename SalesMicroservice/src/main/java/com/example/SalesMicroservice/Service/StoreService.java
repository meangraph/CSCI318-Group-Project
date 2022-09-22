package com.example.SalesMicroservice.Service;

import com.example.SalesMicroservice.Model.InStoreSale;
import com.example.SalesMicroservice.Model.Store;
import com.example.SalesMicroservice.Repository.StoreRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StoreService {

    private final StoreRepo storeRepo;


    public StoreService(StoreRepo storeRepo) {
        this.storeRepo = storeRepo;
    }

    public void addStore(Store store) { storeRepo.save(store); }

    public List<Store> getAllStores(){return storeRepo.findAll();}

    public Store getStoreById(Long id){return storeRepo.findById(id).orElseThrow(() -> new RuntimeException("Store: " + id + " Not found"));}


    public List<InStoreSale> getSaleFromStore(Long storeId) {return getStoreById(storeId).getSales();}


}
