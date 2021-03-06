package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class NarudzbePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable table;

	public NarudzbePanel() {
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Narudžbe "),
				BorderFactory.createEmptyBorder(15, 15, 15, 15)));
		
	}

	public void setupTable() {
		TableModel model = new DefaultTableModel(data, columnNames);
		table = new JTable(model);

		// Hide ID col.
		table.getColumnModel().getColumn(4).setMinWidth(0);
		table.getColumnModel().getColumn(4).setMaxWidth(0);
		
		table.getTableHeader().setOpaque(true);
		table.getTableHeader().setBackground(new Color(142, 209, 195));
		table.getTableHeader().setForeground(Color.BLACK);
		table.getTableHeader().setFont(table.getFont().deriveFont(Font.BOLD, 12f));
		
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				//System.out.println(table.getColumnModel().getColumn(j));
				table.getColumnModel().getColumn(j).setCellRenderer(new CustomTableRenderer());
			}
		}
		table.setBounds(20, 20, 200, 200);
		// adding it to JScrollPane
		JScrollPane sp = new JScrollPane(table);
		add(sp);
	}
	
	public void addMouseListener(MouseListener mouseListener) {
		this.table.addMouseListener(mouseListener);
	}

	public String[][] getData() {
		return this.data;
	}

	public void setData(String[][] data) {
		this.data = data;
	}
	
	public void refresh(int row) {
		DefaultTableModel model = (DefaultTableModel)table.getModel();
		model.removeRow(row);
	}

	private String[][] data;
	private static String[] columnNames = { "Datum narudzbe", "Datum isporuke", "Napomena", "Iznos", "ID" };

}
