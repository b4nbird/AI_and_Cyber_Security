package UI;


import burp.api.montoya.MontoyaApi;

import javax.swing.*;
import java.awt.*;

public class mainGui extends JTabbedPane {

    private MontoyaApi api;
    public mainGui(MontoyaApi api, MyTableModel tableModel) {

        this.api = api;
        // Panel 1
        Component panel1 = new AddHttpTab().requestreponsetab(api,tableModel);
        add("Data List", panel1);

        // Panel 2
        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("这是面板1 ,占位"));
        addTab("Ask ChatGPT", panel2);

        // Panel 2
        JPanel panel3 = new JPanel();
        panel3.add(new JLabel("这是面板 ,占位"));
        addTab("Config", panel3);

    }
}