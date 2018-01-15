package pl.connectis.cschool.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import pl.connectis.cschool.client.InvoiceService;
import pl.connectis.cschool.shared.Invoice;
import pl.connectis.cschool.shared.Product;
import pl.connectis.cschool.shared.dto.InvoiceDTO;

import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benia on 2017-06-24.
 */
@SuppressWarnings("serial")
public class InvoiceServiceImpl extends RemoteServiceServlet implements InvoiceService {

    private final static String INVOICE_SERVICE_URL = "http://localhost:8080";
    private final static String INVOICE_SERVICE_PATH = "invoices";

    public List<InvoiceDTO> allInvoiceDTO() {
        List<InvoiceDTO> result = new ArrayList<InvoiceDTO>();

        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.
                target(INVOICE_SERVICE_URL).
                path(INVOICE_SERVICE_PATH).path("DTO");

        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();

        result = response.readEntity(new GenericType<List<InvoiceDTO>>(){});
        return result;
    }


    @Override
    public void addInvoice(Invoice invoice) {

        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client
                .target(INVOICE_SERVICE_URL)
                .path(INVOICE_SERVICE_PATH);

        Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(invoice, MediaType.APPLICATION_JSON));

    }

    @Override
    public List<Product> findProductById(Long id) {

            List<Product> result = new ArrayList<Product>();

            Client client = ClientBuilder.newClient();
            WebTarget webTarget = client.
                    target(INVOICE_SERVICE_URL).
                    path(INVOICE_SERVICE_PATH).path(id.toString());

            Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.get();

            result = response.readEntity(new GenericType<List<Product>>(){});
            return result;
        }
    }



