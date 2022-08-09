package MercuryCyclists.CSCI318.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

public class Supplier {
    @NotBlank
    private final String companyName;
    @NotBlank
    private final String base;
    private final List<Contact> contacts;

    public Supplier(@JsonProperty("companyName") String companyName,
                    @JsonProperty("base")String base) {
        this.companyName = companyName;
        this.base = base;
        this.contacts = new ArrayList<>();
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getBase() {
        return base;
    }

    public List<Contact> getContacts() { return contacts; }
}
