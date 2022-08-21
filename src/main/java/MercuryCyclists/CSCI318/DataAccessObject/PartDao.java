package MercuryCyclists.CSCI318.DataAccessObject;
import MercuryCyclists.CSCI318.Model.Part;

import java.util.List;
import java.util.Optional;

public interface PartDao {

    int createPart(Part part);

    List<Part> getAllParts();

    Optional<Part> getPartById(long id);

    int deletePartByID(long id);

    int updatePartByID(long id, Part part);

}
