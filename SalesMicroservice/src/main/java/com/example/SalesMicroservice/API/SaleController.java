package com.example.SalesMicroservice.API;

import com.example.SalesMicroservice.Model.*;
import com.example.SalesMicroservice.Service.SaleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/sale")//localhost:8082/api/v1/sale
@RestController
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }
    @PostMapping(path = "/{productID}/online")
    public void createSale(@PathVariable("productID") Long productId,@RequestBody OnlineSale onlineSale){saleService.createSale(productId,onlineSale);}

    @PostMapping(path = "/{productID}/{storeID}")
    public void createSale(@PathVariable("productID") Long productID,@PathVariable("storeID") Long storeID,@RequestBody InStoreSale inStoreSale){saleService.createSale(productID,storeID,inStoreSale);}

    @GetMapping
    public List<Sale> getAllStoreSales(){return saleService.getAllSales();}

    @GetMapping(path = "/{orderID}/store")
    public Store getStoreFromSale(@PathVariable("orderID") Long orderId){return saleService.getStoreFromSale(orderId);}

    @GetMapping(path = "/{orderID}/product")
    public Product getProduct(@PathVariable("orderID") Long orderId){return saleService.getProduct(orderId);}

}
