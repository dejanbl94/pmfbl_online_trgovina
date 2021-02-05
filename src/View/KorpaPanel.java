package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class KorpaPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	private JTable table;
	
	public KorpaPanel() {
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Korpa "),
				BorderFactory.createEmptyBorder(15, 15, 15, 15)));
	}
	
	public void setupTable() {
		model = new DefaultTableModel(data, columnNames);
		table = new JTable(model);
		
		// Hide ID col.
		table.getColumnModel().getColumn(3).setMinWidth(0);
		table.getColumnModel().getColumn(3).setMaxWidth(0);
		
		table.getTableHeader().setOpaque(true);
		table.getTableHeader().setBackground(new Color(142, 209, 195));
		table.getTableHeader().setForeground(Color.BLACK);
		table.getTableHeader().setFont(table.getFont().deriveFont(Font.BOLD, 12f));
		table.setBounds(20, 20, 200, 200);
		// adding it to JScrollPane
		JScrollPane sp = new JScrollPane(table);
		add(sp);
	}
	
	public void addKorpaMouseListener(MouseListener listener) {
		this.table.addMouseListener(listener);
	}

	public String[][] getData() {
		return this.data;
	}

	public void setData(String[][] data) {
		this.data = data;
	}
	
	public DefaultTableModel getTableModel() {
		return this.model;
	}
	
	private DefaultTableModel model;
	private String[][] data;
	private static String[] columnNames = { "Naziv", "Opis", "Cijena", "ID" };
}
