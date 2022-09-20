package com.example.InventoryMicroservice.API;

import com.example.InventoryMicroservice.Model.Part;
import com.example.InventoryMicroservice.Model.Supplier;
import com.example.InventoryMicroservice.Service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/part")//localhost:8080/api/v1/part
@RestController
public class PartController {

    private final PartService partService;

    @Autowired
    public PartController(PartService partService) {
        this.partService = partService;
    }

    @PostMapping
    public void createPart( @NonNull @RequestBody Part part){partService.addPart(part);}

    @GetMapping
    public List<Part> getAllParts(){return partService.getAllParts();}

    @GetMapping(path = "{ID}")
    public Part getPartById(@PathVariable("ID") Long id){return partService.getPartById(id);}

    @DeleteMapping(path = "{ID}")
    public void deletePartById(@PathVariable("ID") Long id){partService.deletePartById(id);}

    @PutMapping(path = "{ID}")
    public void updatePartById(@PathVariable("ID") Long id, @NonNull @RequestBody Part part){
        partService.updatePartById(id,part);
    }
    @PutMapping(path = "{partID}/{supplierID}")
    public void addPartToSupplier(@PathVariable ("partID") Long partId,@PathVariable("supplierID") Long supplierId){
        partService.addPartToSupplier(partId,supplierId);
    }

    @GetMapping(path = "{partID}/supplier")
    public Supplier getPartSupplier(@PathVariable ("partID") Long partId){
        return partService.getSupplierByPart(partId);
    }

    @DeleteMapping(path = "{partID}/{supplierID}")
    public void removePartToSupplier(@PathVariable ("partID") Long partId,@PathVariable("supplierID") Long supplierId){
        partService.removePartFromSupplier(partId,supplierId);
    }

}
