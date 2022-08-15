package MercuryCyclists.CSCI318.Service;

import MercuryCyclists.CSCI318.DataAccessObject.ContactDao;
import MercuryCyclists.CSCI318.Model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private final ContactDao contactDao;

    @Autowired
    public ContactService(@Qualifier("fakeDao1") ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    public int addContact(Contact contact){return contactDao.createContact(contact);}

    public List<Contact> getAllContacts(){return contactDao.getAllContacts();}

    public Optional<Contact> getContactById(String id){return contactDao.getContactById(id);}

    public int deleteContactById(String id){
        return contactDao.deleteContactById(id);
    }

    public int updateContactById(String id, Contact newContact){
        return contactDao.updateContactById(id,newContact);
    }
}
