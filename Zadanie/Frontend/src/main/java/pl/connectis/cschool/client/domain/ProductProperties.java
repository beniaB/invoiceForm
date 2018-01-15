package pl.connectis.cschool.client.domain;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import pl.connectis.cschool.shared.Product;
import pl.connectis.cschool.shared.dto.InvoiceDTO;

/**
 * Created by Benia on 2017-06-24.
 */
public interface ProductProperties extends PropertyAccess<Product> {

    ModelKeyProvider<Product> id();

    ValueProvider<Product, String> productName();

}
