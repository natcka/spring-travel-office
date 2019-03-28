package com.example.newtraveloffice;

import com.example.newtraveloffice.exceptions.NoSuchCustomerException;
import com.example.newtraveloffice.exceptions.NoSuchTripException;
import com.example.newtraveloffice.models.Address;
import com.example.newtraveloffice.models.Customer;
import com.example.newtraveloffice.models.Trip;
import com.example.newtraveloffice.services.TravelOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.HashSet;

@RestController
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
//    @Autowired
//    private UserService userService;

    @PostMapping(value = "/customer/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity addCustomer(@RequestBody Customer customer) {
        ResponseEntity result = null;
        Customer addedCustomer = travelOfficeService.addCustomer(customer);
        if (addedCustomer != null) {
            result = new ResponseEntity(HttpStatus.OK);
        } else {
            result = new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return result;
    }

    @DeleteMapping("/customer/remove")
    public ResponseEntity removeCustomer(@RequestParam String name, @RequestParam String surname) throws NoSuchCustomerException {
        ResponseEntity result = null;
        if (travelOfficeService.removeCustomer(name, surname)) {
            result = ResponseEntity.ok("all fine");
        }
        return result;
    }

    @GetMapping("/customer/{name}")
    public Customer getCustomer(@PathVariable String name) throws NoSuchCustomerException {
        return travelOfficeService.findCustomerByName(name);
    }

    @GetMapping("/customer/list")
    public HashSet<Customer> getListOfCustomers() {
        return travelOfficeService.getSetOfCustomers();
    }

    @PostMapping(value = "/trip/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity addTrip(@Valid @RequestBody Trip trip) {
        ResponseEntity result = null;
        Trip addedTrip = travelOfficeService.addTrip(trip);
        if (addedTrip != null) {
            result = new ResponseEntity(HttpStatus.OK);
        } else {
            result = new ResponseEntity(HttpStatus.BAD_REQUEST);
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
}
