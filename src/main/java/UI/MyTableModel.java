package UI;

import burp.api.montoya.http.message.HttpRequestResponse;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class MyTableModel extends AbstractTableModel
{
    private final List<HttpRequestResponse> log;

    public MyTableModel()
    {
        this.log = new ArrayList<>();
    }

    @Override
    public synchronized int getRowCount()
    {
        return log.size();
    }

    @Override
    public int getColumnCount()
    {
        return 3;
    }

    @Override
    public String getColumnName(int column)
    {
        return switch (column)
        {
            case 0 -> "URL";
            case 1 -> "Method";
            case 2 -> "Status";
            default -> "";
        };
    }

    @Override
    public synchronized Object getValueAt(int rowIndex, int columnIndex)
    {
        HttpRequestResponse requestResponse = log.get(rowIndex);

        return switch (columnIndex)
        {
            case 0 -> requestResponse.request().url();
            case 1 -> requestResponse.request().method();
            case 2 -> requestResponse.response().statusCode();
            default -> "";
        };
    }

    public synchronized void add(HttpRequestResponse responseReceived)
    {
        int index = log.size();
        log.add(responseReceived);
        fireTableRowsInserted(index, index);
    }

    public synchronized HttpRequestResponse get(int rowIndex)
    {
        return log.get(rowIndex);
    }
}
