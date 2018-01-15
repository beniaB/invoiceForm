package pl.connectis.cschool.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.Viewport;


public class GWTApp implements EntryPoint {

    public void onModuleLoad() {

        VBoxLayoutContainer vLT = new VBoxLayoutContainer(VBoxLayoutContainer.VBoxLayoutAlign.LEFT);
       vLT.add(new InvoicePanel());

        Viewport viewPort = new Viewport();
        viewPort.add(vLT);

        RootPanel.get().add(viewPort);
    }
}

