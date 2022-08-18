package MercuryCyclists.CSCI318.DataAccessObject;

import MercuryCyclists.CSCI318.Model.Contact;
import MercuryCyclists.CSCI318.Model.Supplier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("fakeDao1")
public class ContactDataAccessService implements ContactDao {

    private static final List<Contact> DB = new ArrayList<>();
    @Override
    public int createContact(Contact contact) {
        DB.add(new Contact(contact.getID(),contact.getName(), contact.getPhone(), contact.getEmail(),
                contact.getPosition()));
        return 1;
    }

    @Override
    public List<Contact> getAllContacts() {
        return DB;
    }

    @Override
    public Optional<Contact> getContactById(long id) {
        return DB.stream()
                .filter(contact -> contact.getID() == id)
                .findFirst();
    }

    @Override
    public int deleteContactById(long id) {
        Optional<Contact> contactToDelete = getContactById(id);
        if (contactToDelete.isPresent()) {
            DB.remove(contactToDelete.get());
            return 1;
        }
        return 0;
    }

    @Override
    public int updateContactById(long id, Contact update) {
        return getContactById(id)
                .map(contact -> {
                    int contactIndex = DB.indexOf(contact);
                    Supplier supplier = contact.getSupplier();
                    if (contactIndex >= 0) {
                        DB.set(contactIndex, new Contact(id, update.getName(), update.getEmail(), update.getPhone(), update.getPosition()));
                        supplier.addContact(getContactById(id));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
