package com.addressbook.service;

import com.addressbook.utils.mapper.contact.ContactMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ContactServiceIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ContactMapper contactMapper;

    @MockBean
    private ContactService service;

//    @Test
//    @WithMockUser
//    public void whenValidContactId_thenContactShouldBeFound() throws Exception {
//        List<Contact> contacts = new
//        User user = new User(1L, "Sergey", "Plekhanov", "plekhanov@yandex.ru");
//        Contact contact = new Contact(26L, "Oleg", "Polovinkin", "+7(978)6544334", "polovina@yahoo.com");
//        given(service.getContact(26L)).willReturn(contactMapper.toDto(contact));
//        System.out.println(service.getContact(26L));
//    }

//    @Test
//    @WithMockUser
//    public void whenValidContactId_thenContactShouldBeFound_mock() throws Exception {
//
//        Customer customer = new Customer(1L, "Sergey", "Plekhanov", "plekhanov@yandex.ru");
//        Contact contact = new Contact(1L, "Oleg", "Polovinkin", "+7(978)6544334", "polovina@yahoo.com", customer);
//        repository.save(contact);
//        System.out.println(repository.save(contact));
//        System.out.println(repository.findById(1L));
////        Optional<Contact> optContact = Optional.of(contact);
////        when(repository.findById(1L)).thenReturn(optContact);
////        given(service.getContact(1L)).willReturn(contactMapper.toDto(contact));
//
//
//
////        mvc.perform( MockMvcRequestBuilders
////                .get("/contacts/{id}", 1)
////                .accept(MediaType.APPLICATION_JSON))
////                .andDo(print())
////                .andExpect(status().isOk())
////                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
//    }

}
