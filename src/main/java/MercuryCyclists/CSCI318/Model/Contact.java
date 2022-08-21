package MercuryCyclists.CSCI318.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

/** This is the class responsible for creating objects of type Contact. Contact objects are employees that are a part of Supplier objects*/

@Entity(name = "Contact")
public class Contact {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;

    private String name;

    private String phone;

    private String email;

    private String position;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    @JsonIgnore
    private Supplier supplier;

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Contact(@JsonProperty("id") long id,
                   @JsonProperty("name") String name,
                   @JsonProperty("phone") String phone,
                   @JsonProperty("email") String email,
                   @JsonProperty("position") String position) {
        this.ID = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.position = position;
    }

    public Contact(){}

    public long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPosition() {
        return position;
    }

}
