package MercuryCyclists.CSCI318.DataAccessObject;

import MercuryCyclists.CSCI318.Model.Contact;
import MercuryCyclists.CSCI318.Model.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierDao {

    int createSupplier(Supplier supplier);

    List<Supplier> getAllSuppliers();

    Optional<Supplier> getSupplierById(long id);
    int deleteSupplierById(long id);

    int updateSupplierById(long id,Supplier supplier);

    int addContactToSupplier(long supplierID, long contactID);

    int removeContactFromSupplier(long supplierID, long contactID);

}
