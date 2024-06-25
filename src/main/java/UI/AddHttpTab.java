package UI;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.ui.UserInterface;
import burp.api.montoya.ui.editor.Editor;
import burp.api.montoya.ui.editor.HttpRequestEditor;
import burp.api.montoya.ui.editor.HttpResponseEditor;

import javax.swing.*;
import java.awt.*;

import static burp.api.montoya.ui.editor.EditorOptions.READ_ONLY;

public class AddHttpTab extends JPanel{
    public Component requestreponsetab(MontoyaApi api,MyTableModel tableModel)
    {
        // main split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

        // tabs with request/response viewers
        JTabbedPane tabs = new JTabbedPane();

        UserInterface userInterface = api.userInterface();

        HttpRequestEditor requestViewer = userInterface.createHttpRequestEditor(READ_ONLY);
        HttpResponseEditor responseViewer = userInterface.createHttpResponseEditor(READ_ONLY);
        Editor reponseGPT = userInterface.createHttpRequestEditor(READ_ONLY);

        tabs.addTab("Request", requestViewer.uiComponent());
        tabs.addTab("Response", responseViewer.uiComponent());
        tabs.addTab("AI assistant", reponseGPT.uiComponent());

        splitPane.setRightComponent(tabs);

        // table of log entries
        JTable table = new JTable(tableModel)
        {
            @Override
            public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend)
            {
                // show the log entry for the selected row
                HttpRequestResponse responseReceived = tableModel.get(rowIndex);
                requestViewer.setRequest(responseReceived.request());
                responseViewer.setResponse(responseReceived.response());
                super.changeSelection(rowIndex, columnIndex, toggle, extend);
            }
        };

        JScrollPane scrollPane = new JScrollPane(table);

        splitPane.setLeftComponent(scrollPane);

        return splitPane;
    }
}