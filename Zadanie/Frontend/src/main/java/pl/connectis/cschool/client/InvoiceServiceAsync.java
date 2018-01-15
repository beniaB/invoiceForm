package pl.connectis.cschool.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import pl.connectis.cschool.shared.Invoice;
import pl.connectis.cschool.shared.Product;
import pl.connectis.cschool.shared.dto.InvoiceDTO;

import java.util.List;

public interface InvoiceServiceAsync {
    void allInvoiceDTO(AsyncCallback<List<InvoiceDTO>> async);

    void findProductById(Long invoiceId, AsyncCallback<List<Product>> async);

    void addInvoice(Invoice invoice, AsyncCallback<Void> async);
}
