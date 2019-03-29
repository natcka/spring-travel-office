package com.example.newtraveloffice;

import com.example.newtraveloffice.models.*;
import com.example.newtraveloffice.services.TravelOfficeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelOfficeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TravelOfficeService travelOfficeService;

    private final SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy");


    //shouldReturnStatusOkAndCustomer
    @Test
    public void addCustomer() throws Exception {
        //given
        Address address = new Address("Mariacka", "12-123", "Kato");
        Customer customer = new Customer("Adam", "Małysz", address);

        //when&then
        mockMvc.perform(post("/customer/add")
                .content(asJsonString(customer))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(customer)));
    }


    //shouldReturnStatusOkAndCustomer
    @Test
    public void getCustomer() throws Exception {
        //given
        Address address = new Address("Mariacka", "12-123", "Kato");
        Customer customer = new Customer("Adam", "Nowak", address);
        travelOfficeService.addCustomer(customer);

        //when&then
        mockMvc.perform(get("/customer/{surname}", "Nowak"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(customer)));
    }


    //shouldReturnStatusNotFound
    @Test
    public void getCustomerWhenCustomerDoesNotExist() throws Exception {
        //when&then
        mockMvc.perform(get("/customer/{surname}", "Nowak"))
                .andExpect(status().isNotFound());
    }


    //shouldReturnStatusOk
    @Test
    public void removeCustomer() throws Exception {
        //given
        Address address = new Address("Mariacka", "12-123", "Kato");
        Customer customer = new Customer("Adam", "Nowak", address);
        travelOfficeService.addCustomer(customer);

        //when&then
        mockMvc.perform(delete("/customer/remove")
                .param("name", "Adam")
                .param("surname", "Nowak"))
                .andExpect(status().isOk());
    }


    //shouldReturnStatusOk
    @Test
    public void getListOfCustomers() throws Exception {
        //given
        Address address = new Address("Mariacka", "12-123", "Kato");
        Customer customer = new Customer("Adam", "Nowak", address);
        travelOfficeService.addCustomer(customer);

        //when&then
        mockMvc.perform(get("/customer/list")).andExpect(status().isOk());
    }


    //shouldReturnStatusOk
    @Test
    public void assignTripToCustomer() throws Exception {
        //given
        Customer customer = new Customer("Adam", "Małysz", new Address("Name", "name", "12-123"));
        Trip abroadTrip = new AbroadTrip(SDF.parse("20-12-2019"), SDF.parse("21-12-2019"), "Malibu", 1234L, 123L, false);
        travelOfficeService.addCustomer(customer);
        travelOfficeService.addTrip(abroadTrip);

        //when&then
        mockMvc.perform(put("/assign")
                .contentType(MediaType.APPLICATION_JSON)
                .param("surname", customer.getSurname())
                .param("destination", abroadTrip.getDestination())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    //shouldReturnStatusOkAndTrip
    @Test
    public void addTrip() throws Exception {
        //given
        Trip trip = new AbroadTrip(SDF.parse("21-12-2019"), SDF.parse("24-12-2019"), "Malibu", 1234L, 123L, false);

        //when&then
        mockMvc.perform(post("/trip/add")
                .content(asJsonString(trip))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(trip)));
    }


    //shouldReturnStatusOkAndTrip
    @Test
    public void getTrip() throws Exception {
        //given
        Trip trip = new AbroadTrip(SDF.parse("21-12-2019"), SDF.parse("24-12-2019"), "Malibu", 1234L, 123L, false);
        travelOfficeService.addTrip(trip);

        //when&then
        mockMvc.perform(get("/trip/{destination}", "Malibu"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(trip)));
    }


    //shouldReturnStatusOk
    @Test
    public void removeTrip() throws Exception {
        //given
        Trip trip = new AbroadTrip(SDF.parse("21-12-2019"), SDF.parse("24-12-2019"), "Malibu", 1234L, 123L, false);
        travelOfficeService.addTrip(trip);

        //when&then
        mockMvc.perform(delete("/trip/remove")
                .param("destination", "Malibu"))
                .andExpect(status().isOk());
    }

    //shouldReturnStatusOk
    @Test
    public void getListOfTrips() throws Exception {
        //given
        Trip trip = new AbroadTrip(SDF.parse("21-12-2019"), SDF.parse("24-12-2019"), "Malibu", 1234L, 123L, false);
        travelOfficeService.addTrip(trip);

        //when&then
        mockMvc.perform(get("/trip/list")).andExpect(status().isOk());
    }


    //shouldReturnStatusBadRequest
    @Test
    public void getListOfTripsWhenListIsEmpty() throws Exception {
        //given
        if (travelOfficeService.getAllTrips() != null) {
            travelOfficeService.getAllTrips().clear();
        }

        //when&then
        mockMvc.perform(get("/trip/list")).andExpect(status().isBadRequest());
    }


    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writer().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}