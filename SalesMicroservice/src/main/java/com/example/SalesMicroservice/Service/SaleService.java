package com.example.SalesMicroservice.Service;

import com.example.SalesMicroservice.Model.*;
import com.example.SalesMicroservice.Repository.SalesRepo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Scanner;


@Service
public class SaleService {

    private final SalesRepo inStoreRepo;
    private final SalesRepo onLineRepo;
    private final StoreService storeService;
    private final ProductService productService;

    public SaleService(@Qualifier("inStoreSaleRepo") SalesRepo inStoreRepo, @Qualifier("onlineSaleRepo") SalesRepo onLineRepo, StoreService storeService, ProductService productService) {
        this.inStoreRepo = inStoreRepo;
        this.onLineRepo = onLineRepo;
        this.storeService = storeService;
        this.productService = productService;
    }


    public void createSale(Long productId,OnlineSale sale){
        Product product = productService.getProductById(productId);

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
                    }

                }
                else {
                    sale.setStatus(OrderStatus.CONFIRMED);
                }
            }
        }

        sale.setProduct(product);
        onLineRepo.save(sale);
    }

    @Transactional
    public void createSale(Long productId,Long storeId,InStoreSale sale){
        Store store = storeService.getStoreById(storeId);
        Product product = productService.getProductById(productId);

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
