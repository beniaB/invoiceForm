package pl.connectis.cschool.jcourse.restservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.connectis.cschool.jcourse.restservice.domain.Invoice;
import pl.connectis.cschool.jcourse.restservice.domain.Product;
import pl.connectis.cschool.jcourse.restservice.dto.InvoiceDTO;
import pl.connectis.cschool.jcourse.restservice.repository.InvoiceRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benia on 2017-06-24.
 */
@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);

    @Autowired
    InvoiceRepository invoiceRepository;

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Invoice> addInvoice(@RequestBody Invoice invoice) {
        Invoice savedInvoice = invoiceRepository.save(invoice);
        logger.info("Add invoice :)");
        return new ResponseEntity<Invoice>(savedInvoice, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    List<Invoice> allInvoice(){
        return (List<Invoice>) invoiceRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value="/DTO")
    List<InvoiceDTO> allInvoiceDTO(){

        List<InvoiceDTO> invoiceDTOList = new ArrayList<InvoiceDTO>();
        List<Invoice> invoiceList = (List<Invoice>) invoiceRepository.findAll();
        for(Invoice invoice:invoiceList){

            InvoiceDTO invoiceDTO = new InvoiceDTO();

            invoiceDTO.setInvoiceId(invoice.getId());
            invoiceDTO.setInvoiceNumber(invoice.getNumber());
            invoiceDTO.setReciverFirstName(invoice.getReciverFirstName());
            invoiceDTO.setReciverName(invoice.getReciverName());
            invoiceDTO.setInvoiceAmount(invoice.getInvoiceAmount());

            invoiceDTOList.add(invoiceDTO);
        }

        return invoiceDTOList;
    }

    @RequestMapping(method = RequestMethod.GET, value="/{id}")
    List<Product> findProductById(@PathVariable Long id) {
        Invoice invoice = invoiceRepository.findOne(id);
        return invoice.getProducts();

    }

}