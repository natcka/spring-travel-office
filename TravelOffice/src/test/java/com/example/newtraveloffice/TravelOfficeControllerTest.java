package com.example.newtraveloffice;

import com.example.newtraveloffice.models.*;
import com.example.newtraveloffice.services.TravelOfficeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelOfficeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TravelOfficeService travelOfficeService;

    @InjectMocks
    private TravelOfficeController travelOfficeController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(travelOfficeController)
      //          .addFilters(new CorsFilter())
                .build();
    }

    @Test
    public void addCustomer() throws Exception {
        Address address = new Address("Mariacka", "12-123", "Kato");
        Customer customer = new Customer("Adam", "Małysz", address);

        mockMvc.perform(post("/customer/add")
                .content(asJsonString(customer))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(customer)));
    }


    @Test
    public void getCustomer() throws Exception {
        Address address = new Address("Mariacka", "12-123", "Kato");
        Customer customer = new Customer("Adam", "Nowak", address);
        travelOfficeService.addCustomer(customer);

        mockMvc.perform(get("/customer/{surname}", "Nowak"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(customer)));
    }

    @Test
    public void removeCustomer() throws Exception {
        Address address = new Address("Mariacka", "12-123", "Kato");
        Customer customer = new Customer("Adam", "Nowak", address);

        doNothing().when(travelOfficeService).removeCustomer(customer.getSurname(), customer.getName());

        mockMvc.perform(delete("/customer/remove")
                .param("surname", "Nowak")
                .param("name", "Adam"))
                .andExpect(status().isOk());

        verify(travelOfficeService, times(1)).removeCustomer(customer.getName(), customer.getSurname());
        verifyNoMoreInteractions(travelOfficeService);
    }

    @Test
    public void getListOfCustomers() throws Exception {
        List<Customer> customers = Arrays.asList(new Customer("Adam", "Kowalski"),
                new Customer("Michał", "Bąk"));
        when(travelOfficeService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get("/customer/list")).andExpect(status().isOk());
        verify(travelOfficeService, times(1)).getAllCustomers();
        verifyNoMoreInteractions(travelOfficeService);
    }


    @Test
    public void assignTripToCustomer() throws Exception {
        Customer customer = new Customer("Adam", "Małysz", new Address("Name", "name", "12-123"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        AbroadTrip abroadTrip = new AbroadTrip(sdf.parse("20-12-2019"), sdf.parse("21-12-2019"), "Malibu", 1234L, 123L, false);

//        when(travelOfficeService.findCustomerBySurname(customer.getSurname())).thenReturn(customer);
//        when(travelOfficeService.findTripByDestination(abroadTrip.getDestination())).thenReturn(abroadTrip);
        doNothing().when(travelOfficeService).assign(customer.getSurname(), abroadTrip.getDestination());



        mockMvc.perform(put("/assign")

                .contentType(MediaType.APPLICATION_JSON)
                .param("surname", customer.getSurname())
                .param("destination", abroadTrip.getDestination())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

//        verify(travelOfficeService, times(1)).findCustomerBySurname(customer.getSurname());
//        verify(travelOfficeService, times(1)).findTripByDestination(abroadTrip.getDestination());
        verify(travelOfficeService, times(1)).assign(customer.getSurname(), abroadTrip.getDestination());
        verifyNoMoreInteractions(travelOfficeService);
    }

//    @Test
//    public void addTrip() throws Exception {
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//        Trip trip = new AbroadTrip(sdf.parse("21-12-2019"), sdf.parse("24-12-2019"), "Malibu", 1234L, 123L, false);
//        when(travelOfficeService.addTrip(trip)).thenReturn(trip);
//        //        if (trip.isDomestic()) {
////            DomesticTrip tripToAdd = new DomesticTrip(;
////        }
//
//        mockMvc.perform(post("/trip/add")
//                .param("destination", "Malibu")
//                .param("price", "1234")
//                .param("start", "21-12-2019")
//                .param("end", "27-12-2019")
//                .param("domestic", "false")
//                .param("insOrDisc", "123")
//                .content(asJsonString(trip))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().json(asJsonString(trip)));
//    }

//    @Test
//    public void getTrip() {
//
//    }
//
//    @Test
//    public void removeTrip() {
//
//    }
//
//    @Test
//    public void getListOfTrip() {
//
//    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writer().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//
//    @Test
//    public void getCustomer() {
//
//    }
}