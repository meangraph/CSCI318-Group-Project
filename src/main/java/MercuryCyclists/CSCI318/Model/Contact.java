package MercuryCyclists.CSCI318.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
/** This is the class responsible for creating objects of type Contact. Contact objects are employees that are a part of Supplier objects*/

public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    @NotBlank
    private final String name;
    @NotBlank
    private final String phone;
    @NotBlank
    private final String email;
    @NotBlank
    private final String position;

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
