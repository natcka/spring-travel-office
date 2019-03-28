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
import java.util.HashMap;
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
//        Address newAddress = new Address(street, zipCode, city);
//        Customer newCustomer = new Customer(name, surname);
//        newCustomer.setAddress(newAddress);
        Customer addedCustomer = travelOfficeService.addCustomer(customer);

        if (addedCustomer != null) {
            result = new ResponseEntity<Customer>(addedCustomer, HttpStatus.OK);
        } else {
            result = new ResponseEntity<Customer>(addedCustomer, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @DeleteMapping("/customer/remove")
    public ResponseEntity removeCustomer(@RequestParam String name, @RequestParam String surname) throws NoSuchCustomerException {
        ResponseEntity result = null;
        try {
            travelOfficeService.removeCustomer(name, surname);
            result = ResponseEntity.ok("all fine");
        } catch (Exception ex) {

        }
        return result;
    }

    @GetMapping(value = "/customer/{surname}", produces = "application/json")
    public ResponseEntity<Customer> getCustomer(@PathVariable String surname) throws NoSuchCustomerException {
        Customer customer = travelOfficeService.findCustomerBySurname(surname);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("/customer/list")
    public List<Customer> getListOfCustomers() {
        return travelOfficeService.getAllCustomers();
    }

    @PutMapping("/assign")
    public ResponseEntity assignTripToCustomer(@RequestParam String surname, @RequestParam String destination) {
        ResponseEntity result = null;
        try {
            travelOfficeService.assign(surname, destination);
            return new ResponseEntity(HttpStatus.OK);
        } catch (NoSuchTripException e) {
            e.printStackTrace();
        } catch (NoSuchCustomerException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


    @PostMapping(value = "/trip/add", produces = "application/json")
    public ResponseEntity<Trip> addTrip(@RequestParam String destination, @RequestParam Long price, @RequestParam Date start,
                                        @RequestParam Date end, @RequestParam boolean domestic, @RequestParam Long insOrDisc) {
        ResponseEntity<Trip> result = null;
        Trip newTrip = null;
        if (domestic) {
            newTrip = new DomesticTrip(start, end, destination, price, insOrDisc, domestic);
        } else {
            newTrip = new AbroadTrip(start, end, destination, price, insOrDisc, domestic);
        }
        Trip addedTrip = travelOfficeService.addTrip(newTrip);

        if (addedTrip != null) {
            result = new ResponseEntity<>(addedTrip, HttpStatus.OK);
        } else {
            result = new ResponseEntity<>(addedTrip, HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @DeleteMapping("/trip/remove")
    public ResponseEntity removeTrip(@RequestParam String destination) throws NoSuchTripException {
        ResponseEntity result = null;
        if (travelOfficeService.removeTrip(destination)) {
            result = ResponseEntity.ok("all fine");
        }
        return result;
    }

    @GetMapping("/trip/{destination}")
    public Trip getTrip(@PathVariable String destination) throws NoSuchTripException {
        return travelOfficeService.findTripByDestination(destination);
    }

    @GetMapping("/trip/list")
    public HashMap<String, Trip> getListOfTrips() {
        return travelOfficeService.getMapOfTrips();
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"), true, 10));
    }
}
