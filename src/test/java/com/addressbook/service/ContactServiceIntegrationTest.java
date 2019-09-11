package com.addressbook.service;

import com.addressbook.controller.contact.ContactController;
import com.addressbook.entity.contact.Contact;
import com.addressbook.entity.customer.Customer;
import com.addressbook.security.JwtAuthenticationEntryPoint;
import com.addressbook.security.JwtAuthenticationFilter;
import com.addressbook.service.impl.CustomUserDetailsService;
import com.addressbook.utils.mapper.contact.ContactMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Test
    public void whenValidContactId_thenContactShouldBeFound() throws Exception {

        Customer customer = new Customer(1L, "Sergey", "Plekhanov", "plekhanov@yandex.ru");
        Contact contact = new Contact(1L, "Timur", "Manko", "+7(978)6544334", "manko@yahoo.com", customer);

        given(service.getContact(1L)).willReturn(contactMapper.toDto(contact));

        mvc.perform( MockMvcRequestBuilders
                .get("/contacts/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

}
