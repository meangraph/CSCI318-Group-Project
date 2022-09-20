package com.example.InventoryMicroservice.Service;

import com.example.InventoryMicroservice.Model.Contact;
import com.example.InventoryMicroservice.Model.Supplier;
import com.example.InventoryMicroservice.Repository.SupplierRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepo supplierRepo;
    private final ContactService contactService;


    public SupplierService(SupplierRepo supplierRepo, ContactService contactService) {
        this.supplierRepo = supplierRepo;
        this.contactService = contactService;
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
            supplier.setContactList(newSupplier.getContactList());
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
    @Transactional
    public void addContactToSupplier(Long supplierID, Long contactID){
        Supplier supplier = getSupplierById(supplierID);
        Contact contact = contactService.getContactById(contactID);
        supplier.addContact(contact);


    }
    @Transactional
    public void removeContactFromSupplier(Long supplierID, Long contactID){
        Supplier supplier = getSupplierById(supplierID);
        Contact contact = contactService.getContactById(contactID);

        supplier.removeContact(contact);
    }
}
