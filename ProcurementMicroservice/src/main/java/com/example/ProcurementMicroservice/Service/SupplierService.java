package com.example.ProcurementMicroservice.Service;

import com.example.ProcurementMicroservice.Model.Contact;
import com.example.ProcurementMicroservice.Model.Part;
import com.example.ProcurementMicroservice.Model.Supplier;
import com.example.ProcurementMicroservice.Repository.SupplierRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepo supplierRepo;
    private final ContactService contactService;

    private final RestTemplate restTemplate;

    public SupplierService(SupplierRepo supplierRepo, ContactService contactService, RestTemplate restTemplate) {
        this.supplierRepo = supplierRepo;
        this.contactService = contactService;
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
            supplier.setContactList(newSupplier.getContactList());
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
