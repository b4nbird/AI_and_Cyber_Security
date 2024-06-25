package UI;

import burp.api.montoya.ui.contextmenu.ContextMenuEvent;
import burp.api.montoya.ui.contextmenu.ContextMenuItemsProvider;
import burp.api.montoya.http.message.HttpRequestResponse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MyContextMenuItemsProvider implements ContextMenuItemsProvider
{

    private final MyTableModel tableModel;
    public MyContextMenuItemsProvider(MyTableModel tableModel)
    {
        this.tableModel = tableModel;
    }
    @Override
    public List<Component> provideMenuItems(ContextMenuEvent event)
    {
        List<Component> menuItemList = new ArrayList<>();
        JMenuItem retrieveRequestItem = new JMenuItem("Send to BurpGPT");
        menuItemList.add(retrieveRequestItem);
        HttpRequestResponse requestResponse = event.messageEditorRequestResponse().isPresent() ? event.messageEditorRequestResponse().get().requestResponse() : event.selectedRequestResponses().get(0);
        retrieveRequestItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableModel.add(requestResponse);
            }
        });
        return menuItemList;
    }

}
