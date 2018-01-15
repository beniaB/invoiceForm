package pl.connectis.cschool.jcourse.restservice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Benia on 2017-06-24.
 */
@Entity
public class Product {

    private Long id;
    private String productName;

    public Product(String productName) {

        this.productName = productName;
    }

    public Product() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
