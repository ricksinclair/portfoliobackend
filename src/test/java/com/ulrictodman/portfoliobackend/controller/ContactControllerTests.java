package com.ulrictodman.portfoliobackend.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ulrictodman.portfoliobackend.enumeration.ContactRole;
import com.ulrictodman.portfoliobackend.exception.NotFoundException;
import com.ulrictodman.portfoliobackend.model.ContactInformation;
import com.ulrictodman.portfoliobackend.service.ContactInformationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ContactInformationController.class)
public class ContactControllerTests {


    private static final ContactInformation TEST_CONTACT_1_NOT_ADDED = new ContactInformation(0,
            "firstName", "lastName",
            "addressLine1", "addressLine2",
            "suite", "00000",
            "city", "NY",
            "test@domain.com", ContactRole.CLIENT);

    private static final ContactInformation TEST_CONTACT_1_ADDED = new ContactInformation(1,
            "firstName", "lastName",
            "addressLine1", "addressLine2",
            "suite", "00000",
            "city", "NY",
            "test@domain.com", ContactRole.CLIENT);


    private static final ContactInformation TEST_CONTACT_1_UPDATED = new ContactInformation(1,
            "firstName", "lastName",
            "addressLine1", "addressLine2",
            "suite", "00000",
            "city", "NY",
            "test@domain.com", ContactRole.CLIENT);


    private List<ContactInformation> contactInformationList;


    @Autowired
    private MockMvc mvc;
    @MockBean
    private ContactInformationService service;

    @BeforeEach
    public void setUp() throws Exception {

        contactInformationList = new ArrayList<>();
        contactInformationList.add(TEST_CONTACT_1_ADDED);


        when(service.save(TEST_CONTACT_1_NOT_ADDED)).thenReturn(TEST_CONTACT_1_ADDED);
        when(service.get(1)).thenReturn(TEST_CONTACT_1_ADDED);
        when(service.getAll()).thenReturn(contactInformationList);
        doNothing().when(service).update(TEST_CONTACT_1_UPDATED);
        doNothing().when(service).delete(1);
        when(service.get(5)).thenThrow(new NotFoundException("The resource with id 15 was not found."));

    }

    @Test
    public void createContactTest() throws Exception {
        ObjectMapper mapper = Jackson2ObjectMapperBuilder.json().build();

        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();

        String mappedRequestObject = writer.writeValueAsString(TEST_CONTACT_1_NOT_ADDED);
        System.out.println(mappedRequestObject);
        RequestBuilder request = MockMvcRequestBuilders.post("/ContactInformation")
                .contentType(APPLICATION_JSON)
                .content(mappedRequestObject)
                .characterEncoding("utf-8");

        MvcResult result = mvc.perform(request).andExpect(status().isCreated()).andReturn();

        String mappedResult = result.getResponse().getContentAsString();

        ContactInformation resultObject = mapper.readValue(mappedResult, ContactInformation.class);

        assertEquals(TEST_CONTACT_1_ADDED.getFirstName(), resultObject.getFirstName());

    }

    @Test
    public void getContactTest() throws Exception {
        ObjectMapper mapper = Jackson2ObjectMapperBuilder.json().build();

        RequestBuilder request = MockMvcRequestBuilders.get("/ContactInformation/1");

        MvcResult result = mvc.perform(request).andExpect(status().isOk()).andReturn();

        String mappedResult = result.getResponse().getContentAsString();

        ContactInformation resultObject = mapper.readValue(mappedResult, ContactInformation.class);

        assertEquals(TEST_CONTACT_1_ADDED.getFirstName(), resultObject.getFirstName());

    }

    @Test
    public void getAllContactsTest() throws Exception {
        ObjectMapper mapper = Jackson2ObjectMapperBuilder.json().build();

        RequestBuilder request = MockMvcRequestBuilders.get("/ContactInformation");

        MvcResult result = mvc.perform(request).andExpect(status().isOk()).andReturn();

        String mappedResult = result.getResponse().getContentAsString();

        ContactInformation[] resultArray = mapper.readValue(mappedResult, ContactInformation[].class);

        List<ContactInformation> responseObject = Arrays.asList(resultArray);

        assertEquals(contactInformationList, responseObject);


    }


    @Test
    public void updateContactTest() throws Exception {

        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();

        String mappedRequestObject = writer.writeValueAsString(TEST_CONTACT_1_UPDATED);

        RequestBuilder request = MockMvcRequestBuilders.put("/ContactInformation")
                .contentType(APPLICATION_JSON)
                .content(mappedRequestObject)
                .characterEncoding("utf-8");

        mvc.perform(request).andExpect(status().isAccepted()).andReturn();

    }

    @Test
    public void deleteContactTest() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.delete("/ContactInformation/1");
        mvc.perform(request).andExpect(status().isAccepted()).andReturn();

    }


    @Test
    public void notfoundContactTest() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/ContactInformation/5");
        mvc.perform(request).andExpect(status().isNotFound()).andReturn();

    }

    @Test
    public void badInputGetContactTest() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders.get("/ContactInformation/g");

            mvc.perform(request).andExpect(status().isBadRequest()).andReturn();

    }


}
