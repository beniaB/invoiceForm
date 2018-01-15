package pl.connectis.cschool.shared.dto;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Created by Benia on 2017-06-24.
 */
public class InvoiceDTO  implements IsSerializable {

    private Long invoiceId;
    private String invoiceNumber;
    private String reciverFirstName;
    private String reciverName;
    private String invoiceAmount;

    public InvoiceDTO(Long invoiceId, String invoiceNumber, String reciverFirstName, String reciverName, String invoiceAmount) {
        this.invoiceId = invoiceId;
        this.invoiceNumber = invoiceNumber;
        this.reciverFirstName = reciverFirstName;
        this.reciverName = reciverName;
        this.invoiceAmount = invoiceAmount;
    }

    public InvoiceDTO() {
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
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
