package MercuryCyclists.CSCI318.Service;

import MercuryCyclists.CSCI318.DataAccessObject.ContactDao;
import MercuryCyclists.CSCI318.DataAccessObject.SupplierDao;

import MercuryCyclists.CSCI318.Model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    private final SupplierDao supplierDao;
    private final ContactDao contactDao;

    @Autowired
    public SupplierService(@Qualifier("fakeDao") SupplierDao supplierDao, @Qualifier("fakeDao1") ContactDao contactDao) {
        this.supplierDao = supplierDao;
        this.contactDao = contactDao;
    }

    public int addSupplier(Supplier supplier){
        return supplierDao.createSupplier(supplier);
    }

    public List<Supplier> getAllSuppliers(){
        return supplierDao.getAllSuppliers();
    }

    public Optional<Supplier> getSupplierById(long id) { return supplierDao.getSupplierById(id); }

    public int updateSupplierById(long id, Supplier newSupplier) { return supplierDao.updateSupplierById(id, newSupplier); }

    public int deleteSupplierById(long id) { return supplierDao.deleteSupplierById(id); }

    public int addContactToSupplier(long supplierID, long contactID){ return supplierDao.addContactToSupplier(supplierID, contactID);}

    public int removeContactFromSupplier(long supplierID, long contactID){ return supplierDao.removeContactFromSupplier(supplierID, contactID);}
}
