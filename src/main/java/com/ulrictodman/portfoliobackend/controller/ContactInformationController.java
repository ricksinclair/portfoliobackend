package com.ulrictodman.portfoliobackend.controller;

import com.ulrictodman.portfoliobackend.model.ContactInformation;
import com.ulrictodman.portfoliobackend.service.ContactInformationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactInformationController {


    final ContactInformationService contactInformationService;


    public ContactInformationController(ContactInformationService contactInformationService) {
        this.contactInformationService = contactInformationService;
    }

    @PostMapping(path = "/ContactInformation", produces = "application/json")
    @ResponseStatus(value = HttpStatus.CREATED)
    public  @ResponseBody ContactInformation postContact(@RequestBody ContactInformation contactInformation) {
        return contactInformationService.save(contactInformation);
    }


    @GetMapping(path = "/ContactInformation/{id}", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody ContactInformation getContact(@PathVariable int id) throws Exception {
        return contactInformationService.get(id);
    }

    @GetMapping(path = "/ContactInformation", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<ContactInformation> getAllContacts(){
        return contactInformationService.getAll();
    }

    @PutMapping(path = "/ContactInformation", produces = "application/json")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void updateContact(@RequestBody ContactInformation contactInformation) throws Exception {
        contactInformationService.update(contactInformation);
    }

    @DeleteMapping(path ="/ContactInformation/{id}", produces = "application/json")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void deleteContact(@PathVariable int id) throws Exception {
        contactInformationService.delete(id);
        System.out.println("The item with id ");
    }
}
