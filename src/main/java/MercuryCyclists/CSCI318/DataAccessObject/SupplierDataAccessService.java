package MercuryCyclists.CSCI318.DataAccessObject;

import MercuryCyclists.CSCI318.Model.Supplier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository("fakeDao")
public class SupplierDataAccessService implements SupplierDao {

    private static final List<Supplier> DB = new ArrayList<>();


    @Override
    public int createSupplier(Supplier supplier) {
        DB.add(new Supplier(supplier.getID(),supplier.getCompanyName(), supplier.getBase()));
        return 1;
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return DB;
    }

    @Override
    public Optional<Supplier> getSupplierById(long id) {
        return DB.stream()
                .filter(supplier -> supplier.getID() == id)
                .findFirst();
    }

    @Override
    public int deleteSupplierById(long id) {
        Optional<Supplier> supplierToDelete = getSupplierById(id);
        if(supplierToDelete.isPresent()){
            DB.remove(supplierToDelete.get());
            return 1;
        }
        return 0;
    }

    @Override
    public int updateSupplierById(long id,Supplier update) {
        return getSupplierById(id)
                .map(supplier -> {
                    int supplierIndex = DB.indexOf(supplier);
                    if (supplierIndex >= 0){
                        DB.set(supplierIndex, new Supplier(id, update.getCompanyName(),update.getBase()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

}