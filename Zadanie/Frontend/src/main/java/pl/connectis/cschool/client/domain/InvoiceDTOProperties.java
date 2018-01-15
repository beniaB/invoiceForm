package pl.connectis.cschool.client.domain;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import pl.connectis.cschool.shared.Invoice;
import pl.connectis.cschool.shared.dto.InvoiceDTO;

/**
 * Created by Benia on 2017-06-24.
 */
public interface InvoiceDTOProperties extends PropertyAccess<InvoiceDTO> {

    ModelKeyProvider<InvoiceDTO> invoiceId();

    ValueProvider<InvoiceDTO, String> invoiceNumber();

    ValueProvider<InvoiceDTO, String> reciverFirstName();

    ValueProvider<InvoiceDTO, String > reciverName();

    ValueProvider<InvoiceDTO,String> invoiceAmount();

}
