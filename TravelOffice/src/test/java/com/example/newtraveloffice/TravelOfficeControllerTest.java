package com.example.newtraveloffice;

import com.example.newtraveloffice.models.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TravelOfficeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addCustomer() throws Exception {
        mockMvc.perform(post("/customer/add")
                .content(asJsonString(new Customer("Adam", "Małysz")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
  //              .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    public void getCustomer() {

    }

    @Test
    public void removeCustomer() {

    }

    @Test
    public void getListOfCustomers() {

    }

    @Test
    public void addTrip() {

    }

    @Test
    public void getTrip() {

    }

    @Test
    public void removeTrip() {

    }

    @Test
    public void getListOfTrip() {

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
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