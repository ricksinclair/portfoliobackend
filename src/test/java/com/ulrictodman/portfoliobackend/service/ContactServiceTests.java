package com.ulrictodman.portfoliobackend.service;

import com.ulrictodman.portfoliobackend.enumeration.ContactRole;
import com.ulrictodman.portfoliobackend.exception.NotFoundException;
import com.ulrictodman.portfoliobackend.model.ContactInformation;
import com.ulrictodman.portfoliobackend.repository.ContactInformationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest

public class ContactServiceTests {
    @Autowired
    private ContactInformationRepository contactInformationRepository;

    @Autowired
    private ContactInformationService contactInformationService;

    private ContactInformation testContact1;

    private ContactInformation testContact2;


    @BeforeEach
    public void setUp() {
        contactInformationRepository.deleteAll();

        testContact1 = new ContactInformation();
        testContact1.setFirstName("firstName");
        testContact1.setLastName("lastName");
        testContact1.setAddressLine1("addressLine1");
        testContact1.setAddressLine2("addressLine2");
        testContact1.setAptOrSuite("TestSuite");
        testContact1.setEmail("testEmailAddress@Email.com");
        testContact1.setRole(ContactRole.CLIENT);
        testContact1.setCity("New York");
        testContact1.setState("NY");
        testContact1.setZipCode("00000");


        testContact2 = new ContactInformation();
        testContact2.setFirstName("firstName");
        testContact2.setLastName("lastName");
        testContact2.setAddressLine1("addressLine1");
        testContact2.setAddressLine2("addressLine2");
        testContact2.setAptOrSuite("TestSuite");
        testContact2.setEmail("testEmailAddress@Email.com");
        testContact2.setRole(ContactRole.CLIENT);
        testContact2.setCity("New York");
        testContact2.setState("NY");
        testContact2.setZipCode("00000");



    }

    @Test
    public void addGetContactInfoTest() throws Exception {
        testContact1 = contactInformationService.save(testContact1);
        ContactInformation fromDb = contactInformationService.get(testContact1.getId());
        assertEquals(testContact1, fromDb);
    }

    @Test
    public void whenInvalidIdProvidedOnDelete_NotFoundExceptionIsThrown() {

        Exception exception = assertThrows(NotFoundException.class, () -> contactInformationService.delete(39));


        String expectedMessage = "The resource with id 39 was not found and therefore couldn't be deleted.";

        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);

    }


    @Test
    public void whenInvalidIdProvidedOnGet_NotFoundExceptionIsThrown() {
        Exception exception = assertThrows(NotFoundException.class, () -> contactInformationService.get(39));

        String expectedMessage = "The resource with id 39 was not found.";

        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }


    @Test
    public void afterInsertingThenDeletingThenTryingToRetrieveById_NotFoundExceptionIsThrown() throws Exception {
        testContact1 = contactInformationService.save(testContact1);
        contactInformationService.delete(testContact1.getId());
        Exception exception = assertThrows(NotFoundException.class, () -> contactInformationService.get(testContact1.getId()));

        String expectedMessage = String.format("The resource with id %d was not found.", testContact1.getId());

        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);


    }

    @Test
    public void afterAddingTwoInstancesToDb_TwoResultsRetrievedWhenFindingAll() {
        contactInformationService.save(testContact1);
        contactInformationService.save(testContact2);
        List<ContactInformation> allContacts = contactInformationService.getAll();
        assertEquals(2, allContacts.size());
    }


    @Test
    public void afterAddingAndUpdatingValue_UpdatedValueRetrievedFromDb() throws Exception {
        testContact1 = contactInformationService.save(testContact1);

        ContactInformation fromDbBeforeUpdate = contactInformationService.get(testContact1.getId());

        testContact1.setCity("updated city");
        contactInformationService.update(testContact1);

        ContactInformation fromDbAfterUpdate = contactInformationService.get(testContact1.getId());

        assertNotEquals(fromDbBeforeUpdate, fromDbAfterUpdate);


    }


    @Test
    public void whenTryingToUpdateResourceWithInvalidId_NotFoundExceptionIsThrown() {

        testContact1.setId(45);
        Exception exception = assertThrows(NotFoundException.class, () -> contactInformationService.update(testContact1));
        String expectedMessage = String.format("The resource with id %d was not found and therefore couldn't be updated.", testContact1.getId());
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

}
