package burp;

import UI.MyContextMenuItemsProvider;
import UI.MyTableModel;
import UI.mainGui;
import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;

public class BurpExtender implements BurpExtension
{
    private MontoyaApi api;
    public static String EXPAND_NAME = "BurpGPT";

    @Override
    public void initialize(MontoyaApi api)
    {
        this.api = api;
        api.extension().setName(EXPAND_NAME);
        MyTableModel tableModel = new MyTableModel();
        api.userInterface().registerContextMenuItemsProvider(new MyContextMenuItemsProvider(tableModel));
        api.userInterface().registerSuiteTab(EXPAND_NAME, new mainGui(api, tableModel));
    }
}
