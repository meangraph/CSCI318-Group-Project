package com.example.InventoryMicroservice.Model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Supplier")
@JsonPropertyOrder({"id"})//This makes it so the auto generated ID appears first
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    private String companyName;
    private String base;

    @OneToMany(mappedBy = "supplier",
               cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Contact> contactList = new ArrayList<Contact>();

    @JsonManagedReference
    @OneToMany(mappedBy = "supplier",
            cascade = CascadeType.PERSIST)
    private List<Part> partList = new ArrayList<>();


    public Supplier(@JsonProperty("companyName") String companyName,
                    @JsonProperty("base")String base) {
        this.companyName = companyName;
        this.base = base;
    }

    public Supplier() {}

    public Long getID() {return ID;}

    public String getCompanyName() {
        return companyName;
    }

    public String getBase() {
        return base;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public List<Part> getPartList() {
        return partList;
    }

    public void setPartList(List<Part> partList) {
        this.partList = partList;
    }

    public void addContact(Contact contact) {

        contact.setSupplier(this);
        this.contactList.add(contact);

    }

    public void removeContact(Contact contact) {
        contactList.remove(contact);

    }

    public void addPart(Part part){
        partList.add(part);
    }

    public void removePart(Part part){
        partList.remove(part);
    }
    @Override
    public String toString() {
        return "Supplier{" +
                "ID=" + ID +
                ", companyName='" + companyName + '\'' +
                ", base='" + base + '\'' +
                ", contactList=" + contactList +
                '}';
    }
}
