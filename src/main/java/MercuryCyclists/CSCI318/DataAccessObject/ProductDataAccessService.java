package MercuryCyclists.CSCI318.DataAccessObject;

import MercuryCyclists.CSCI318.Model.Part;
import MercuryCyclists.CSCI318.Model.Product;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

public class ProductDataAccessService extends PartDataAccessService implements ProductDao {
    private static final ArrayList<Product> DB = new ArrayList<Product>();

    @Override
    public int createProduct(Product product){
        DB.add(new Product(product.getName(), product.getProductID(), product.getPrice(), product.getComment()));
        return 1;
    }

    @Override
    public ArrayList<Product> getAllProducts(){
        return DB;
    }

    @Override
    public Optional<Product> getProductByID(long id){
        return DB.stream().filter(product -> product.getProductID() == id).findFirst();
    }

    @Override
    public int deleteProductByID(long id){
        Optional<Product> productToDelete = getProductByID(id);
        if(productToDelete.isPresent()){
            DB.remove(productToDelete.get());
            return 1;
        }
        return 0;
    }

    //This stuff may be a tad wacky
    @Override
    public int updateProductByID(long id, Product update){
        return getProductByID(id)
                .map(product ->{
                    int productIndex = DB.indexOf(product);
                    List<Optional<Part>> parts = product.getParts() ;
                    if(productIndex >= 0){
                        DB.set(productIndex, new Product(update.getName(),id, update.getPrice(), update.getComment()));
                        for(Optional<Part> part : parts) {
                            product.addPart(part);
                            //Might need to change
                            part.get().setProduct(DB.get(productIndex));
                        }
                        return 1;
                    }
                    return 0;
                }).orElse(0);
    }

    @Override
    public int addPartToProduct(long productID, long partID){
        Optional<Product> product = getProductByID(productID);
        Optional<Part> part = getPartById(partID);

        product.get().addPart(part);
        Product product2 = product.get();
        part.get().setProduct(product2);
        return 1;
    }

    @Override
    public int removePartFromProduct(long productID, long partID){
        Optional<Part> part = getPartById(partID);
        Optional<Product> product = getProductByID(productID);
        product.get().removePart(part);
        return 1;
    }
}
