package com.example.InventoryMicroservice.Service;

import com.example.InventoryMicroservice.Model.Part;
import com.example.InventoryMicroservice.Model.Supplier;
import com.example.InventoryMicroservice.Repository.PartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PartService {

    private final PartRepo partRepo;
    private final SupplierService supplierService;

    @Autowired
    public PartService(PartRepo partRepo,  SupplierService supplierService) {
        this.partRepo = partRepo;
        this.supplierService = supplierService;

    }

    public void addPart(Part part) { partRepo.save(part); }

    public List<Part> getAllParts() {return partRepo.findAll();}

    public Part getPartById(Long id){ return partRepo.findById(id).orElseThrow(() -> new RuntimeException("Part: " + id + " Not found")); }

    public Part updatePartById(Long id, Part newPart){
        return partRepo.findById(id).map(part  -> {
            part.setDescription(newPart.getDescription());
            part.setName(newPart.getName());
            part.setSupplier(newPart.getSupplier());
            part.setProduct(newPart.getProduct());
            part.setStock(newPart.getStock());
            return partRepo.save(part);
        }).orElseGet(() -> {
            newPart.setPartID(id);
            return partRepo.save(newPart);
        });
    }

    public void deletePartById(Long id) {
        Part part = getPartById(id);
        partRepo.delete(part);
    }

    @Transactional
    public void addPartToSupplier(Long partID,Long supplierID ) {
        Supplier supplier = supplierService.getSupplierById(supplierID);
        Part part = getPartById(partID);

        supplier.addPart(part);

    }

    public void removePartFromSupplier(Long partID, Long supplierID) {
        Supplier supplier = supplierService.getSupplierById(supplierID);
        Part part = getPartById(partID);

        part.setSupplier(null);
        supplier.removePart(part);
    }

    public Supplier getSupplierByPart(Long partID) {
        Supplier supplier = getPartById(partID).getSupplier();

        return supplier;
    }
}
