package com.ulrictodman.portfoliobackend.service;

import com.ulrictodman.portfoliobackend.model.ContactInformation;

import java.util.List;

public interface ContactInformationService {

    ContactInformation save(ContactInformation contactInformation);

    void update(ContactInformation contactInformation) throws Exception;

    ContactInformation get(int id) throws Exception;

    List<ContactInformation> getAll();

    void delete(int id) throws  Exception;
}
