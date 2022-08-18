package MercuryCyclists.CSCI318.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {
    private int postCode;
    private String suburb;
    private String street;
    private int streetNumber;

    public Address(@JsonProperty int postCode,@JsonProperty String suburb,@JsonProperty String street,@JsonProperty int streetNumber){
        this.postCode = postCode;
        this.suburb = suburb;
        this.street = street;
        this.streetNumber = streetNumber;
    }

    public int getPostCode() {
        return postCode;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public String getStreet() {
        return street;
    }

    public String getSuburb() {
        return suburb;
    }
}
