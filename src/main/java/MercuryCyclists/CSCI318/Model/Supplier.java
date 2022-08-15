package MercuryCyclists.CSCI318.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    @NotBlank
    private final String companyName;
    @NotBlank
    private final String base;
    private final List<Optional<Contact>> contacts;

    public Supplier(@JsonProperty("id") long ID,
                    @JsonProperty("companyName") String companyName,
                    @JsonProperty("base")String base) {
        this.ID = ID;
        this.companyName = companyName;
        this.base = base;
        this.contacts = new ArrayList<Optional<Contact>>();
    }

    public long getID() {return ID;}

    public String getCompanyName() {
        return companyName;
    }

    public String getBase() {
        return base;
    }

    public List<Optional<Contact>> getContacts() { return contacts; }

    public void addContact(Optional<Contact> contact) {
        contacts.add(contact);
    }
}
