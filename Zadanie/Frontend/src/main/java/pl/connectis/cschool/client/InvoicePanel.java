package pl.connectis.cschool.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.*;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import pl.connectis.cschool.client.domain.InvoiceDTOProperties;
import pl.connectis.cschool.client.domain.ProductProperties;
import pl.connectis.cschool.shared.Invoice;
import pl.connectis.cschool.shared.Product;
import pl.connectis.cschool.shared.dto.InvoiceDTO;

import java.awt.*;
import java.util.*;
import java.util.List;


/**
 * Created by Benia on 2017-06-24.
 */
public class InvoicePanel implements IsWidget {


    final TextField invoiceNumberTF = new TextField();
    final TextField productsTF = new TextField();
    final TextField reciverFirstNameTF = new TextField();
    final TextField reciverNameTF = new TextField();
    final TextField invoiceAmountTF = new TextField();

    final TextButton addInvoiceTB = new TextButton("Add invoice");
    final TextButton getAllInvoiceTB = new TextButton("Pobierz faktury");

    private CheckBoxSelectionModel<InvoiceDTO> checkBoxSelectionModel;
    private CheckBoxSelectionModel<Product> productCheckBoxSelectionModel;


    private InvoiceDTOProperties invoiceDTOProperties = GWT.create(InvoiceDTOProperties.class);
    ListStore<InvoiceDTO> invoiceDTOListStore = new ListStore<InvoiceDTO>(invoiceDTOProperties.invoiceId());
    private InvoiceServiceAsync invoiceServiceAsync = GWT.create(InvoiceService.class);

    private ProductProperties productProperties = GWT.create(ProductProperties.class);
    ListStore<Product> productListStore = new ListStore<Product>(productProperties.id());



    private Widget prepareInvoiceForm() {

        FramedPanel framedPanel = new FramedPanel();
        framedPanel.setHeadingText("Invoice form");



        VBoxLayoutContainer vBLC = new VBoxLayoutContainer(VBoxLayoutContainer.VBoxLayoutAlign.STRETCH);

        vBLC.add(new FieldLabel(invoiceNumberTF, "Numer faktury"));
        vBLC.add(new FieldLabel(productsTF, "Produkty"));
        vBLC.add(new FieldLabel(reciverFirstNameTF, "Imie odbiorcy"));
        vBLC.add(new FieldLabel(reciverNameTF, "Nazwisko odbiorcy"));
        vBLC.add(new FieldLabel(invoiceAmountTF, "Kwota faktury"));

        getAllInvoiceTB.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent selectEvent) {
                fillInvoiceTable();
            }
        });

        addInvoiceTB.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent selectEvent) {

                int s = invoiceDTOListStore.size();
                Invoice invoice = new Invoice();
                Long id = Long.valueOf(s+1);

                List<Product> pList = new ArrayList<Product>();
                String splited = productsTF.getText();
                String[] splitedArray = splited.split(",");

                for(String productName: splitedArray){
                    Product p = new Product();
                    p.setProductName(productName);
                    pList.add(p);

                }

                invoice.setId(id);
                invoice.setNumber(invoiceNumberTF.getText());
                invoice.setProducts(pList);
                invoice.setReciverFirstName(reciverFirstNameTF.getText());
                invoice.setReciverName(reciverNameTF.getText());
                invoice.setInvoiceAmount(invoiceAmountTF.getText());


                invoiceServiceAsync.addInvoice(invoice, new AsyncCallback<Void>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        GWT.log( throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(Void aVoid) {


                    }
                });

            }
        });


        HBoxLayoutContainer hBLC = new HBoxLayoutContainer(HBoxLayoutContainer.HBoxLayoutAlign.STRETCHMAX);
        hBLC.add(addInvoiceTB);
        hBLC.add(getAllInvoiceTB);

        VerticalLayoutContainer mainVLC = new VerticalLayoutContainer();
        mainVLC.add(vBLC);
        mainVLC.add(hBLC);
        framedPanel.add(mainVLC);


        return framedPanel;
    }

    private Widget prepareInvoiceGrid() {
        FramedPanel framedPanel = new FramedPanel();
        framedPanel.setHeadingText("All invoices");

        IdentityValueProvider<InvoiceDTO> identity = new IdentityValueProvider<InvoiceDTO>();
        checkBoxSelectionModel = new CheckBoxSelectionModel<InvoiceDTO>(identity);


        ColumnConfig<InvoiceDTO, String> invoiceNumberCol = new ColumnConfig<InvoiceDTO, String>(invoiceDTOProperties.invoiceNumber(), 70, "Numer faktury");
        ColumnConfig<InvoiceDTO, String> invoiceReciverFNameCol = new ColumnConfig<InvoiceDTO, String>(invoiceDTOProperties.reciverFirstName(), 70, "Imie odbiorcy");
        ColumnConfig<InvoiceDTO, String> invoiceReciverNameCol = new ColumnConfig<InvoiceDTO, String>(invoiceDTOProperties.reciverName(), 70, "Nazwisko odbiorcy");
        ColumnConfig<InvoiceDTO, String> invoiceAmountCol = new ColumnConfig<InvoiceDTO, String>(invoiceDTOProperties.invoiceAmount(), 70, "Kwota faktury");


        List<ColumnConfig<InvoiceDTO, ?>> columns = new ArrayList<ColumnConfig<InvoiceDTO, ?>>();
        columns.add(checkBoxSelectionModel.getColumn());
        columns.add(invoiceNumberCol);
        columns.add(invoiceReciverFNameCol);
        columns.add(invoiceReciverNameCol);
        columns.add(invoiceAmountCol);

        ColumnModel<InvoiceDTO> columnModel = new ColumnModel<InvoiceDTO>(columns);
        Grid<InvoiceDTO> grid = new Grid<InvoiceDTO>(invoiceDTOListStore, columnModel);
        grid.setSelectionModel(checkBoxSelectionModel);


        framedPanel.add(grid);

        return framedPanel;
    }

    private Widget prepareProductGrid() {

        FramedPanel framedPanel = new FramedPanel();
        framedPanel.setHeadingText("All products");

        IdentityValueProvider<Product> identity = new IdentityValueProvider<Product>();
        productCheckBoxSelectionModel = new CheckBoxSelectionModel<Product>(identity);

        ColumnConfig<Product, String> productNameCol = new ColumnConfig<Product, String>(productProperties.productName(), 70, "Nazwa produktu");

        java.util.List<ColumnConfig<Product, ?>> columns = new ArrayList<ColumnConfig<Product, ?>>();
        columns.add(productCheckBoxSelectionModel.getColumn());
        columns.add(productNameCol);

        ColumnModel<Product> columnModel = new ColumnModel<Product>(columns);
        Grid<Product> grid = new Grid<Product>(productListStore, columnModel);
        grid.setSelectionModel(productCheckBoxSelectionModel);
        framedPanel.add(grid);

        return framedPanel;

    }

    private void fillInvoiceTable() {

        invoiceServiceAsync.allInvoiceDTO(new AsyncCallback<List<InvoiceDTO>>() {
            @Override
            public void onFailure(Throwable throwable) {
                GWT.log("Nie udało sie pobrać faktur");
            }

            @Override
            public void onSuccess(List<InvoiceDTO> invoiceDTOS) {
                invoiceDTOListStore.clear();
                for (InvoiceDTO invoiceDTO : invoiceDTOS) {

                    invoiceDTOListStore.add(invoiceDTO);
                }
            }
        });
        fill();

    }

    private void fill(){

        checkBoxSelectionModel.addSelectionHandler(new SelectionHandler<InvoiceDTO>() {
            @Override
            public void onSelection(SelectionEvent<InvoiceDTO> selectionEvent) {


                Long dtoId = selectionEvent.getSelectedItem().getInvoiceId();
                GWT.log(dtoId.toString());

                invoiceServiceAsync.findProductById(dtoId, new AsyncCallback<List<Product>>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        GWT.log("Nie udalo się pobrać produktów");
                    }

                    @Override
                    public void onSuccess(List<Product> products) {
                        productListStore.clear();
                        for (Product product : products) {
                            productListStore.add(product);
                        }

                    }
                });

            }
        });

    }

    @Override
    public Widget asWidget (){
            int width = 1; // 100%
            int height = 1; // 50px
            Margins margin = new Margins(10);
            VerticalLayoutContainer.VerticalLayoutData verticalLayoutData = new VerticalLayoutContainer.VerticalLayoutData(width, height, margin);

            VerticalLayoutContainer vLT = new VerticalLayoutContainer();
            vLT.add(prepareInvoiceForm());
            vLT.add(prepareInvoiceGrid());
            vLT.add(prepareProductGrid());



            return vLT;
        }
    }
