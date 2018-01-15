package pl.connectis.cschool.jcourse.restservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import pl.connectis.cschool.jcourse.restservice.domain.Invoice;
import pl.connectis.cschool.jcourse.restservice.domain.Product;
import pl.connectis.cschool.jcourse.restservice.dto.InvoiceDTO;
import pl.connectis.cschool.jcourse.restservice.repository.InvoiceRepository;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by Benia on 2017-06-24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class InvoiceTest {

    private MockMvc mockMvc1;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Before
    public void setup() {

        mockMvc1 = webAppContextSetup(webApplicationContext).build();

    }

    @Test
    public void getAllSenderTest() throws Exception{

        mockMvc1.perform(get("/invoices")).
                andExpect(status().isOk()).
                andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).
                andExpect(jsonPath("$..number", hasSize(2)));

    }

    @Test
    public void addInvoiceTest() throws Exception {

        Product p1 = new Product( "mleko");
        Product p2 = new Product("słoik");
        List<Product> products = new ArrayList<Product>();
        products.add(p1);
        products.add(p2);

        String json = convertToJSON(new Invoice(3l, "2",products ,"Zosia", "Ziom", "120"));

        mockMvc1.perform(post("/invoices").content(json).contentType(APPLICATION_JSON_UTF8)).
                andExpect(status().isOk());
        mockMvc1.perform(get("/invoices")).andExpect(jsonPath("$..reciverName",hasItem("Ziom")));
    }

    @Test
    public void findProductById() throws Exception{

        Product p1 = new Product( "mleko");
        Product p2 = new Product("słoik");
        List<Product> products = new ArrayList<Product>();
        products.add(p1);
        products.add(p2);

        String json = convertToJSON(new Invoice(3l, "2",products ,"Zosia", "Ziom", "120"));

        mockMvc1.perform(post("/invoices").content(json).contentType(APPLICATION_JSON_UTF8)).
                andExpect(status().isOk());
        mockMvc1.perform(get("/invoices/3")).andExpect(jsonPath("$..productName",hasItem("mleko")));

    }




    private String convertToJSON(Object o) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(o);
    }

}
