package com.example.InventoryMicroservice.Service;

import com.example.InventoryMicroservice.Model.Contact;
import com.example.InventoryMicroservice.Model.Part;
import com.example.InventoryMicroservice.Model.Supplier;
import com.example.InventoryMicroservice.Repository.ContactRepo;
import com.example.InventoryMicroservice.Repository.PartRepo;
import com.example.InventoryMicroservice.Repository.SupplierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PartService {

    private final PartRepo partRepo;
    private final RestTemplate restTemplate;
    private final SupplierRepo supplierRepo;
    private final ContactService contactService;
    private final SupplierService supplierService;
    private final ContactRepo contactRepo;


    @Autowired
    public PartService(PartRepo partRepo, RestTemplate restTemplate, SupplierRepo supplierRepo, ContactService contactService, SupplierService supplierService, ContactRepo contactRepo) {
        this.partRepo = partRepo;
        this.restTemplate = restTemplate;
        this.supplierRepo = supplierRepo;
        this.contactService = contactService;
        this.supplierService = supplierService;
        this.contactRepo = contactRepo;
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
            return partRepo.save(newPart);
        });
    }

    public void deletePartById(Long id) {
        Part part = getPartById(id);
        partRepo.delete(part);
    }

    public void addPartToSupplier(Long partID,Long supplierID ) {
        Part part = getPartById(partID);
        Supplier supplier = restTemplate.getForObject("http://localhost:8081/api/v1/supplier/" + supplierID, Supplier.class);
        Contact contact = restTemplate.getForObject("http://localhost:8081/api/v1/contact/" + "2", Contact.class);

        Contact newContact = new Contact(contact.getID(), contact.getName(), contact.getPhone(), contact.getEmail(), contact.getPosition());
        contactRepo.save(newContact);
        supplier.addPart(part);

        restTemplate.put("http://localhost:8081/api/v1/supplier/" + supplierID,supplier, Supplier.class);


            Supplier newSupplier = new Supplier();
            newSupplier.setBase(supplier.getBase());
            newSupplier.setCompanyName(supplier.getCompanyName());
            newSupplier.setContactList(supplier.getContactList());
            newSupplier.setPartList(supplier.getPartList());
            newSupplier.setID(supplierID);
            supplierRepo.save(newSupplier);
            part.setSupplier(newSupplier);
            updatePartById(partID, part);

    }

    public void removePartFromSupplier(Long partID, Long supplierID) {
        Supplier supplier = restTemplate.getForObject("http://localhost:8081/api/v1/supplier/" + supplierID, Supplier.class);
        Part part = getPartById(partID);

        part.setSupplier(null);
        supplier.removePart(part);
    }

    public Supplier getSupplierByPart(Long partID) {
        Supplier supplier = getPartById(partID).getSupplier();

        return supplier;
    }
}
