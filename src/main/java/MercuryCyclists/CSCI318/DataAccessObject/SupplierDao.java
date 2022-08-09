package MercuryCyclists.CSCI318.DataAccessObject;

import MercuryCyclists.CSCI318.Model.Contact;
import MercuryCyclists.CSCI318.Model.Supplier;

import java.util.List;

public interface SupplierDao {

    int createSupplier(Supplier supplier);

    List<Supplier> getAllSuppliers();

}
