//package com.addressbook.controller.customer;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import static org.springframework.http.ResponseEntity.noContent;
//import static org.springframework.http.ResponseEntity.ok;
//
//@RestController
//public class CustomerController {
//
//    private final CustomerService customerService;
//
//    @Autowired
//    public CustomerController(CustomerService customerService) {
//        this.customerService = customerService;
//    }
//
//    @GetMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public ResponseEntity<Page<CustomerDTO>> getAllCustomers(Pageable pageable) {
//        return ok(customerService.getAllCustomers(pageable));
//    }
//
//    @GetMapping(value = "/customer/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable("id") Long id) {
//        return ok(customerService.getCustomer(id));
//    }
//
//    @PostMapping(value = "/customer", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerDTO customer) {
//        return ok(customerService.saveCustomer(customer));
//    }
//
//    @PutMapping(value = "/customer/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public ResponseEntity<CustomerDTO> updateCustomer(
//            @PathVariable("id") Long id, @RequestBody CustomerDTO customer) {
//        return ok(customerService.updateCustomer(id, customer));
//    }
//
//    @DeleteMapping(value = "/customer/{id}")
//    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id) {
//        customerService.deleteCustomer(id);
//        return noContent().build();
//    }
//
//}
