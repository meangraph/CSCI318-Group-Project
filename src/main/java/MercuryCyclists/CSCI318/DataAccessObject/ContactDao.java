package MercuryCyclists.CSCI318.DataAccessObject;

import MercuryCyclists.CSCI318.Model.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactDao {

    int createContact(Contact contact);

    List<Contact> getAllContacts();

    Optional<Contact> getContactById(String id);
    int deleteContactById(String id);

    int updateContactById(String id, Contact newContact);

}
