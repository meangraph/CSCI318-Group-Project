package com.example.SalesMicroservice.Service;

import com.example.SalesMicroservice.Model.*;
import com.example.SalesMicroservice.Repository.SalesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Scanner;


@Service
public class SaleService {

    private final SalesRepo inStoreRepo;
    private final SalesRepo onLineRepo;
    private final StoreService storeService;
    private final RestTemplate restTemplate;

    @Autowired
    public SaleService(@Qualifier("inStoreSaleRepo") SalesRepo inStoreRepo, @Qualifier("onlineSaleRepo") SalesRepo onLineRepo, StoreService storeService, RestTemplate restTemplate) {
        this.inStoreRepo = inStoreRepo;
        this.onLineRepo = onLineRepo;
        this.storeService = storeService;
        this.restTemplate = restTemplate;
    }


    public KafkaEvent createSale(Long productId,OnlineSale sale){

        RestTemplate restTemplate = new RestTemplate();
        Product product = restTemplate.getForObject("http://localhost8080/api/v1/products/" + productId, Product.class);
        KafkaEvent event = new KafkaEvent();


        if(product.getStock() == 0){
            System.out.println(product.getName() + " has no stock, checking to see if the parts are available");
            for (int i = 0; i < product.getParts().size(); i++) {
                int stockCheck = product.getParts().get(i).getStock();
                if (stockCheck == 0){
                    System.out.println(product.getName() + " parts has no stock. We can put this item on back order to get the parts from out suppliers. Would you like to" +
                            " do this? Yes/No");
                    Scanner scanner = new Scanner(System.in);
                    String response = scanner.nextLine();

                    if (response.equalsIgnoreCase("Yes")){
                        System.out.println("Your product has been successfully placed on back order");
                        sale.setStatus(OrderStatus.BACKORDER);
                        event.setSale(sale);
                        event.setProduct(product);
                        event.setComment("Sale sent to procurement");
                    }
                    else{
                        sale.setStatus(OrderStatus.CANCELED);
                    }

                }
                else {
                    sale.setStatus(OrderStatus.CONFIRMED);
                }
            }
        }

        sale.setProduct(product);
        onLineRepo.save(sale);
        return null;
    }

    @Transactional
    public KafkaEvent createSale(Long productId,Long storeId,InStoreSale sale){
        Store store = storeService.getStoreById(storeId);
        Product product = restTemplate.getForObject("http://localhost8080/api/v1/products/" + productId, Product.class);
        KafkaEvent event = new KafkaEvent();

        if(product.getStock() == 0){
            System.out.println(product.getName() + " has no stock, checking to see if the parts are available");
            for (int i = 0; i < product.getParts().size(); i++) {
                int stockCheck = product.getParts().get(i).getStock();
                if (stockCheck == 0){
                    System.out.println(product.getName() + " parts has no stock. We can put this item on back order to get the parts from out suppliers. Would you like to" +
                            " do this? Yes/No");
                    Scanner scanner = new Scanner(System.in);
                    String response = scanner.nextLine();

                    if (response.equalsIgnoreCase("Yes")){
                        System.out.println("Your product has been successfully placed on back order");
                        sale.setStatus(OrderStatus.BACKORDER);
                    }
                    else{
                        sale.setStatus(OrderStatus.CANCELED);
                        event.setSale(sale);
                        event.setProduct(product);
                        event.setComment("Sale sent to procurement");
                    }

                }
                else {
                    sale.setStatus(OrderStatus.CONFIRMED);
                }
            }

        }
        sale.setStore(store);
        sale.setProduct(product);
        store.addSales(sale);
        inStoreRepo.save(sale);
        return null;
    }

    public List<Sale> getAllSales() {return inStoreRepo.findAll();}

    public Store getStoreFromSale(Long orderId) {
        InStoreSale inStoreSale = (InStoreSale) inStoreRepo.findById(orderId).orElseThrow(() -> new RuntimeException("Sale: " + orderId + " Not found"));
        return inStoreSale.getStore();
    }

    public Product getProduct(Long orderId) {
        Sale sale = inStoreRepo.findById(orderId).orElse(onLineRepo.findById(orderId).orElseThrow(() -> new RuntimeException("Sale: " + orderId + " Not found")));

        return sale.getProduct();
    }
}
