package com.example.ProcurementMicroservice.API;

import com.example.ProcurementMicroservice.Model.Contact;
import com.example.ProcurementMicroservice.Service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("api/v1/contact")//localhost:8080/api/v1/contact
@RestController
public class ContactController {

    private final ContactService contactService; //allows us to use the ContactService in this class

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping//Post Requests
    public void createContact(@Valid @NonNull @RequestBody Contact contact) {
        contactService.addContact(contact);
    }

    @GetMapping//Get Requests
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }
    @GetMapping(path = "{ID}")//Get Requests
    public Contact getContactById(@PathVariable("ID") Long id){
        return contactService.getContactById(id);
    }
    @DeleteMapping(path = "{ID}")//Delete Requests
    public void deleteContactById(@PathVariable("ID") Long id){
        contactService.deleteContactById(id);
    }

    @PutMapping(path = "{ID}")//Update Requests
    public void updateContactById(@PathVariable ("ID") Long id,@Valid @NonNull @RequestBody Contact contact){
        contactService.updateContactById(id,contact);
    }
}
