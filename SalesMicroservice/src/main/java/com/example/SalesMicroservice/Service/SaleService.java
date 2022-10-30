package com.example.SalesMicroservice.Service;

import com.example.SalesMicroservice.Model.*;
import com.example.SalesMicroservice.Repository.ProductRepo;
import com.example.SalesMicroservice.Repository.SalesRepo;
import org.apache.kafka.streams.StreamsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.function.StreamBridge;
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
    private final ProductRepo productRepo;
    private final RestTemplate restTemplate;

    @Autowired
    private StreamBridge streamBridge;

    @Autowired
    public SaleService(@Qualifier("inStoreSaleRepo") SalesRepo inStoreRepo, @Qualifier("onlineSaleRepo") SalesRepo onLineRepo, StoreService storeService, ProductRepo productRepo, RestTemplate restTemplate) {
        this.inStoreRepo = inStoreRepo;
        this.onLineRepo = onLineRepo;
        this.storeService = storeService;
        this.productRepo = productRepo;
        this.restTemplate = restTemplate;
    }


    public KafkaEvent createSale(Long productId,OnlineSale sale){

        RestTemplate restTemplate = new RestTemplate();
        Product product = restTemplate.getForObject("http://localhost:8080/api/v1/product/" + productId, Product.class);
        Product newProduct = new Product();

        newProduct.setName(product.getName());
        newProduct.setComment(product.getComment());
        newProduct.setPrice(product.getPrice());
        newProduct.setStock(product.getStock());
        newProduct.setPartsList(product.getPartsList());
        newProduct.setProductID(product.getProductID());

        productRepo.save(newProduct);

        KafkaEvent event = new KafkaEvent();


        if(newProduct.getStock() == 0){
            System.out.println(product.getName() + " has no stock, checking to see if the parts are available");
            for (int i = 0; i < newProduct.getParts().size(); i++) {
                int stockCheck = newProduct.getParts().get(i).getStock();
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
        sale.setStatus(OrderStatus.PROCESSING);
        onLineRepo.save(sale);
        return null;
    }

    @Transactional
    public KafkaEvent createSale(Long productId,Long storeId,InStoreSale sale){
        Store store = storeService.getStoreById(storeId);
        Product product = restTemplate.getForObject("http://localhost:8080/api/v1/product/" + productId, Product.class);
        Product newProduct = new Product();

        newProduct.setName(product.getName());
        newProduct.setComment(product.getComment());
        newProduct.setPrice(product.getPrice());
        newProduct.setStock(0);
        newProduct.setPartsList(product.getPartsList());
        newProduct.setProductID(product.getProductID());

        productRepo.save(newProduct);
        KafkaEvent event = new KafkaEvent();



        if(newProduct.getStock() == 0){
            System.out.println(product.getName() + " has no stock, checking to see if the parts are available");
                System.out.println(product.getName() + " parts has no stock. We can put this item on back order to get the parts from out suppliers. Would you like to" +
                        " do this? Yes/No");
                Scanner scanner = new Scanner(System.in);
                String response = scanner.nextLine();

                if (response.equalsIgnoreCase("Yes")){
                    System.out.println("Your product has been successfully placed on back order");
                    sale.setStatus(OrderStatus.BACKORDER);
                    sale.setProduct(product);
                    sale.setStore(store);
                    store.addSales(sale);
                    event.setSale(sale);
                    event.setProduct(product);
                    event.setComment("Sale sent to procurement");
                    inStoreRepo.save(sale);

                }
                else{
                    sale.setStatus(OrderStatus.CANCELED);

                }



        }else {
            sale.setStore(store);
            sale.setProduct(product);
            store.addSales(sale);
            sale.setStatus(OrderStatus.COMPLETED);
            inStoreRepo.save(sale);

            streamBridge.send("sales-outbound", sale);

        }return null;
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
