package pl.connectis.cschool.jcourse.restservice.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Benia on 2017-06-24.
 */
@Entity
public class Invoice {

    private Long id;
    private String number;
    private List<Product> products;
    private String reciverFirstName;
    private String reciverName;
    private String invoiceAmount;

    public Invoice(Long id, String number, List<Product> products, String reciverFirstName, String reciverName, String invoiceAmount) {
        this.id = id;
        this.number = number;
        this.products = products;
        this.reciverFirstName = reciverFirstName;
        this.reciverName = reciverName;
        this.invoiceAmount = invoiceAmount;
    }

    public Invoice() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getReciverFirstName() {
        return reciverFirstName;
    }

    public void setReciverFirstName(String reciverFirstName) {
        this.reciverFirstName = reciverFirstName;
    }

    public String getReciverName() {
        return reciverName;
    }

    public void setReciverName(String reciverName) {
        this.reciverName = reciverName;
    }

    public String getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(String invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }
}
