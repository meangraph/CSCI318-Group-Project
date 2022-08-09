package MercuryCyclists.CSCI318.Service;

import MercuryCyclists.CSCI318.DataAccessObject.SupplierDao;
import MercuryCyclists.CSCI318.Model.Contact;
import MercuryCyclists.CSCI318.Model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {
    private final SupplierDao supplierDao;

    @Autowired
    public SupplierService(@Qualifier("fakeDao") SupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }

    public int addSupplier(Supplier supplier){
        return supplierDao.createSupplier(supplier);
    }

    public List<Supplier> getAllSuppliers(){
        return supplierDao.getAllSuppliers();
    }

}
