package MercuryCyclists.CSCI318.API;

import MercuryCyclists.CSCI318.Model.Supplier;
import MercuryCyclists.CSCI318.Service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("api/v1/supplier")//localhost:8080/api/v1/supplier
@RestController
public class SupplierController {

    private final SupplierService supplierService;

    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping //Post requests
    public void createSupplier(@RequestBody Supplier supplier){
        supplierService.addSupplier(supplier);
    }

    @GetMapping//Get Requests
    public List<Supplier> getAllSuppliers(){
        return supplierService.getAllSuppliers();
    }
    
    @GetMapping(path = "{ID}")//Get Requests
    public Supplier getSupplierById(@PathVariable("ID") long id){
        return supplierService.getSupplierById(id)
                .orElse(null);
    }
    
    @DeleteMapping(path = "{ID}")//Delete Requests
    public void deleteSupplierById(@PathVariable ("ID") long id) {supplierService.deleteSupplierById(id);}
    
    @PutMapping(path = "{ID}")//Update Requests
    public void updateSupplierById(@PathVariable ("ID") long id, @Valid @NonNull @RequestBody Supplier supplier) {
        supplierService.updateSupplierById(id, supplier);}

    @PutMapping(path = "{supplierID}/{contactID}")//Update Requests
    public void addContactToSupplier(@PathVariable ("supplierID") long supplierID, @PathVariable ("contactID") long contactID){
        supplierService.addContactToSupplier(supplierID, contactID);
    }
}
