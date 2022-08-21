package MercuryCyclists.CSCI318.DataAccessObject;

import MercuryCyclists.CSCI318.Model.Product;
import org.springframework.stereotype.Repository;
import MercuryCyclists.CSCI318.Model.Part;
import java.util.ArrayList;
import java.util.Optional;

import java.util.List;

@Repository("fakeDoa2")
public class PartDataAccessService implements PartDao{
    private static final ArrayList<Part> DB = new ArrayList<Part>();

    @Override
    public int createPart(Part part){
            DB.add(new Part(part.getName(), part.getPartID(), part.getDescription(), part.getSupplier()));
        return 1;
    }

    @Override
    public ArrayList<Part> getAllParts(){
        return DB;
    }

    @Override
    public Optional<Part> getPartById(long id){
        return DB.stream().filter(part -> part.getPartID() == id).findFirst();
    }

    @Override
    public int deletePartByID(long id){
        Optional<Part> partToDelete = getPartById(id);
        if(partToDelete.isPresent()){
            DB.remove(partToDelete.get());
            return 1;
        }
        return 0;
    }

    //Part of the DB lookup and update stuff feels redundant, but if it works it works
    @Override
    public int updatePartByID(long id, Part update){
        return getPartById(id)
                .map(part ->{
                    int partIndex = DB.indexOf(part);
                    Product product = part.getProduct();
                    if(partIndex >= 0){
                        DB.set(partIndex, new Part(update.getName(),id, update.getDescription(), update.getSupplier()));
                        //product.addPart(getPartById(id)); FAM, REMEMBER TO IMPLEMENT THIS IN PRODUCT
                        return 1;
                    }
                    return 0;
        }).orElse(0);
    }


}

