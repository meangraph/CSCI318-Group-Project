package MercuryCyclists.CSCI318.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    @NotBlank
    private String companyName;
    @NotBlank
    private String base;

    @Transient
    private List<Optional<Contact>> contacts;

    @OneToMany(mappedBy = "supplier",
               cascade = CascadeType.ALL)
    private List<Contact> contactList;

    public Supplier(@JsonProperty("id") long ID,
                    @JsonProperty("companyName") String companyName,
                    @JsonProperty("base")String base) {
        this.ID = ID;
        this.companyName = companyName;
        this.base = base;
        this.contacts = new ArrayList<>();
        this.contactList = new ArrayList<>();
    }

    public Supplier() {}

    public long getID() {return ID;}

    public String getCompanyName() {
        return companyName;
    }

    public String getBase() {
        return base;
    }

    public List<Optional<Contact>> getContacts() { return contacts; }

    public void addContact(Optional<Contact> contact) {
        if (contacts.stream().anyMatch(c -> c.get().getID() == contact.get().getID())) {
            System.out.println("Found!");
            removeContact(contact);
        }
        contacts.add(contact);
        contactList.add(contact.get());
    }

    public void removeContact(Optional<Contact> contact) {
        contacts.remove(contact);
        contactList.remove(contact);

    }

}
