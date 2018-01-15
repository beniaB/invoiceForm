package pl.connectis.cschool.shared;

import com.google.gwt.user.client.rpc.IsSerializable;


/**
 * Created by Benia on 2017-06-24.
 */

public class Product implements IsSerializable {

    private Long id;
    private String productName;

    public Product(String productName) {

        this.productName = productName;
    }

    public Product() {
    }


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (!id.equals(product.id)) return false;
        return productName.equals(product.productName);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + productName.hashCode();
        return result;
    }
}
