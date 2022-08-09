package MercuryCyclists.CSCI318.API;

import MercuryCyclists.CSCI318.Model.Supplier;
import MercuryCyclists.CSCI318.Service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
