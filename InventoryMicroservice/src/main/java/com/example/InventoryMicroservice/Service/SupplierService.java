package com.example.InventoryMicroservice.Service;

import com.example.InventoryMicroservice.Model.Contact;
import com.example.InventoryMicroservice.Model.Supplier;
import com.example.InventoryMicroservice.Repository.SupplierRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepo supplierRepo;

    private final RestTemplate restTemplate;

    public SupplierService(SupplierRepo supplierRepo, RestTemplate restTemplate) {
        this.supplierRepo = supplierRepo;
        this.restTemplate = restTemplate;
    }


    public void addSupplier(Supplier supplier){
        supplierRepo.save(supplier);
    }

    public List<Supplier> getAllSuppliers(){
        return supplierRepo.findAll();
    }

    public Supplier getSupplierById(Long id) { return supplierRepo.findById(id).orElseThrow(() -> new RuntimeException("Supplier: " + id + " Not found")); }

    public Supplier updateSupplierById(Long id, Supplier newSupplier) {
        return supplierRepo.findById(id).map(supplier -> {
            supplier.setBase(newSupplier.getBase());
            supplier.setCompanyName(newSupplier.getCompanyName());
            supplier.setPartList(newSupplier.getPartList());
            return supplierRepo.save(supplier);
        }).orElseGet(() -> {
            newSupplier.setID(id);
            return supplierRepo.save(newSupplier);
        });
    }

    public void deleteSupplierById(Long id) {
        Supplier supplier = getSupplierById(id);
        supplierRepo.delete(supplier);

    }


}
