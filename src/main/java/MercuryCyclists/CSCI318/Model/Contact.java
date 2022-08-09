package MercuryCyclists.CSCI318.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
/** This is the class responsible for creating objects of type Contact. Contact objects are employees that are a part of Supplier objects*/
public class Contact {
    @NotBlank
    private final String ID;
    @NotBlank
    private final String name;
    @NotBlank
    private final String phone;
    @NotBlank
    private final String email;
    @NotBlank
    private final String position;

    public Contact(@JsonProperty("ID") String ID,
                   @JsonProperty("name") String name,
                   @JsonProperty("phone") String phone,
                   @JsonProperty("email") String email,
                   @JsonProperty("position") String position) {
        this.ID = ID;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.position = position;
    }

    public String getID() {
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
