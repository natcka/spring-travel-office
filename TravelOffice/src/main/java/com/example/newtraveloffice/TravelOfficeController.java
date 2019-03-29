package com.example.newtraveloffice;

import com.example.newtraveloffice.exceptions.NoSuchCustomerException;
import com.example.newtraveloffice.exceptions.NoSuchTripException;
import com.example.newtraveloffice.models.*;
import com.example.newtraveloffice.services.TravelOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Configuration
@EnableSwagger2
public class TravelOfficeController {

    // http://localhost:8080/swagger-ui.html

    @Autowired
    private TravelOfficeService travelOfficeService;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    @PostMapping(value = "/customer/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        ResponseEntity<Customer> result = null;
        Customer addedCustomer = travelOfficeService.addCustomer(customer);
        if (addedCustomer != null) {
            result = new ResponseEntity<>(addedCustomer, HttpStatus.OK);
        } else {
            result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @DeleteMapping("/customer/remove")
    public ResponseEntity removeCustomer(@RequestParam String name, @RequestParam String surname) {
        ResponseEntity result = null;
        try {
            travelOfficeService.removeCustomer(name, surname);
            result = new ResponseEntity(HttpStatus.OK);
        } catch (NoSuchCustomerException ex) {
            result = new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @GetMapping(value = "/customer/{surname}", produces = "application/json")
    public ResponseEntity<Customer> getCustomer(@PathVariable String surname) {
        ResponseEntity<Customer> result = null;
        try {
            Customer customer = travelOfficeService.findCustomerBySurname(surname);
            result = new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (NoSuchCustomerException ex) {
            result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return result;
    }

    @GetMapping("/customer/list")
    public ResponseEntity<List<Customer>> getListOfCustomers() {
        ResponseEntity<List<Customer>> result = null;
        List<Customer> allCustomers = travelOfficeService.getAllCustomers();
        if (allCustomers != null && !allCustomers.isEmpty()) {
            result = new ResponseEntity<>(allCustomers, HttpStatus.OK);
        } else {
            result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @PutMapping("/assign")
    public ResponseEntity assignTripToCustomer(@RequestParam String surname, @RequestParam String destination) {
        ResponseEntity result = null;
        try {
            travelOfficeService.assign(surname, destination);
            result = new ResponseEntity(HttpStatus.OK);
        } catch (NoSuchTripException e) {
            result = new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (NoSuchCustomerException e) {
            result = new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return result;
    }


    @PostMapping(value = "/trip/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Trip> addTrip(@RequestBody Trip trip) {
        ResponseEntity<Trip> result = null;
        Trip addedTrip = travelOfficeService.addTrip(trip);

        if (addedTrip != null) {
            result = new ResponseEntity<>(addedTrip, HttpStatus.OK);
        } else {
            result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @DeleteMapping("/trip/remove")
    public ResponseEntity removeTrip(@RequestParam String destination) {
        ResponseEntity result = null;
        try {
            travelOfficeService.removeTrip(destination);
            result = new ResponseEntity(HttpStatus.OK);
        } catch (NoSuchTripException ex) {
            result = new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @GetMapping("/trip/{destination}")
    public ResponseEntity<Trip> getTrip(@PathVariable String destination) {
        ResponseEntity<Trip> result = null;
        try {
            Trip trip = travelOfficeService.findTripByDestination(destination);
            result = new ResponseEntity<>(trip, HttpStatus.OK);
        } catch (NoSuchTripException ex) {
            result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return result;
    }

    @GetMapping("/trip/list")
    public ResponseEntity<List<Trip>> getListOfTrips() {
        ResponseEntity<List<Trip>> result = null;
        List<Trip> allTrips = travelOfficeService.getAllTrips();
        if (allTrips != null && !allTrips.isEmpty()) {
            result = new ResponseEntity<>(allTrips, HttpStatus.OK);
        } else {
            result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"), true, 10));
    }
}
