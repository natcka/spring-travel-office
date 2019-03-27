package com.example.newtraveloffice;

import com.example.newtraveloffice.exceptions.NoSuchCustomerException;
import com.example.newtraveloffice.models.Customer;
import com.example.newtraveloffice.services.TravelOfficeService;
import com.sun.net.httpserver.HttpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;

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
    public ResponseEntity addCustomer(@Valid @RequestBody Customer customer) {
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
}
