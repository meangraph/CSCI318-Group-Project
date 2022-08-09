package MercuryCyclists.CSCI318.DataAccessObject;

import MercuryCyclists.CSCI318.Model.Supplier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository("fakeDao")
public class SupplierDataAccessService implements SupplierDao {

    private static final List<Supplier> DB = new ArrayList<>();


    @Override
    public int createSupplier(Supplier supplier) {
        DB.add(new Supplier(supplier.getCompanyName(), supplier.getBase()));
        return 1;
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        return DB;
    }

}
