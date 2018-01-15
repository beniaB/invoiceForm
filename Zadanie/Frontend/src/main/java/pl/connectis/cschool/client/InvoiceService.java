package pl.connectis.cschool.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import pl.connectis.cschool.shared.Invoice;
import pl.connectis.cschool.shared.Product;
import pl.connectis.cschool.shared.dto.InvoiceDTO;

import java.util.List;

/**
 * Created by Benia on 2017-06-24.
 */
@RemoteServiceRelativePath("InvoiceService")
public interface InvoiceService extends RemoteService {

    List<InvoiceDTO> allInvoiceDTO();
    List<Product> findProductById(Long id);
    void addInvoice(Invoice invoice);

}
