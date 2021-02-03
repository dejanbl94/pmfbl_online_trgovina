package View;


import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class NarudzbePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable table;

	public NarudzbePanel() {
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Narudzbe "),
				BorderFactory.createEmptyBorder(15, 15, 15, 15)));
	}

	public void setupTable() {
		table = new JTable(data, columnNames);

		// Hide ID col.
		table.getColumnModel().getColumn(4).setMinWidth(0);
		table.getColumnModel().getColumn(4).setMaxWidth(0);
		
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				table.getColumnModel().getColumn(i).setCellRenderer(new CustomTableRenderer());
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

	private String[][] data;
	private static String[] columnNames = { "Datum narudzbe", "Datum isporuke", "Napomena", "Iznos", "ID" };

}
