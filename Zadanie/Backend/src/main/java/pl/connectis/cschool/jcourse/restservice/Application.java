package pl.connectis.cschool.jcourse.restservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import pl.connectis.cschool.jcourse.restservice.domain.*;
import pl.connectis.cschool.jcourse.restservice.repository.InvoiceRepository;

@SpringBootApplication
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
    @Bean
    CommandLineRunner init(final InvoiceRepository ir){
    		return new CommandLineRunner() {
				
				@Override
				public void run(String... arg0) throws Exception {


					Product p1 = new Product( "mleko");
					Product p2 = new Product("słoik");
					List<Product> products = new ArrayList<Product>();
					products.add(p1);
					products.add(p2);

					Invoice invoice1 = new Invoice(1l, "1", products,"Ala", "Kot", "120");
					Invoice invoice2 = new Invoice(2l, "2",products ,"Zosia", "Kot", "120");

					ir.save(invoice1);
					ir.save(invoice2);
				}
			};
    }

}
