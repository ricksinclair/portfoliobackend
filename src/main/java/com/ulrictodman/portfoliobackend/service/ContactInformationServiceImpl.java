package com.ulrictodman.portfoliobackend.service;

import com.ulrictodman.portfoliobackend.model.ContactInformation;
import com.ulrictodman.portfoliobackend.repository.ContactInformationRepository;
import com.ulrictodman.portfoliobackend.service.util.GlobalHelperMethods;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactInformationServiceImpl implements ContactInformationService {

    private final ContactInformationRepository contactInformationRepository;


    public ContactInformationServiceImpl(ContactInformationRepository contactInformationRepository) {
        this.contactInformationRepository = contactInformationRepository;
    }

    @Override
    public ContactInformation save(ContactInformation contactInformation) {
        return contactInformationRepository.save(contactInformation);
    }

    @Override
    public void update(ContactInformation contactInformation)  {
        Optional<ContactInformation> fromDb =  contactInformationRepository.findById(contactInformation.getId());

        if (fromDb.isPresent())
            contactInformationRepository.save(contactInformation);
        else
            GlobalHelperMethods.notFoundExceptionHelper(contactInformation.getId(), "The resource with id %d was not found and therefore couldn't be updated.");
    }

    @Override
    public ContactInformation get(int id) {
        Optional<ContactInformation> fromDb = contactInformationRepository.findById(id);

        ContactInformation returnValue = new ContactInformation();

        if (fromDb.isPresent())
            returnValue = fromDb.get();

        else
            GlobalHelperMethods.notFoundExceptionHelper(id, "The resource with id %d was not found.");

        return returnValue;
    }


    @Override
    public List<ContactInformation> getAll() {
        return contactInformationRepository.findAll();
    }


    @Override
    public void delete(int id) {
        Optional<ContactInformation> fromDb;
        fromDb = contactInformationRepository.findById(id);

        if (fromDb.isPresent())
            contactInformationRepository.deleteById(id);
        else {
            GlobalHelperMethods.notFoundExceptionHelper(id, "The resource with id %d was not found and therefore couldn't be deleted.");
        }

    }

}
