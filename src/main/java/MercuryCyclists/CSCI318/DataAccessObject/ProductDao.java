package MercuryCyclists.CSCI318.DataAccessObject;

import MercuryCyclists.CSCI318.Model.Part;
import MercuryCyclists.CSCI318.Model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductDao {
    int createProduct(Product product);

    List<Product> getAllProducts();

    Optional<Product> getProductByID(long id);

    int deleteProductByID(long id);

    int updateProductByID(long id, Product product);

    int addPartToProduct(long productID, long partID);

    int removePartFromProduct(long productID, long partID);
}
