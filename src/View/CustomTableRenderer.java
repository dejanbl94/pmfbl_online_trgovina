package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CustomTableRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        Component c = super.getTableCellRendererComponent(table, value, isSelected,
                hasFocus, row, column);

        if (table.getColumnName(column).compareToIgnoreCase("Datum isporuke") == 0) {
            String versionVal = (String) value;

            if (versionVal.contains("na čekanju...")) {
                c.setForeground(new Color(249, 99, 50));
                c.setFont(new Font("Dialog", Font.BOLD, 13));
            } else {
                c.setForeground(Color.BLACK);
                c.setFont(new Font("Dialog", Font.PLAIN, 12));
            }
        } else {
            c.setForeground(Color.BLACK);
            c.setFont(new Font("Dialog", Font.PLAIN, 12));
        }
        return c;
    }
}
