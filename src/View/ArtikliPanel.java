package View;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ArtikliPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public ArtikliPanel() {
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Proizvodi "),
				BorderFactory.createEmptyBorder(15, 15, 15, 15)));
	}
	
	public void setupTable() {
		table = new JTable(data, columnNames);
		table.setBounds(1, 1, 50, 50); 
		table.getTableHeader().setOpaque(true);
		table.getTableHeader().setBackground(new Color(142, 209, 195));
		table.getTableHeader().setForeground(Color.BLACK);
		table.getTableHeader().setFont(table.getFont().deriveFont(Font.BOLD, 12f));
        JScrollPane sp = new JScrollPane(table); 
        add(sp); 
	}
	
	public String[][] getData() {
		return this.data;
	}
	
	public void setData(String[][] data) {
		this.data = data;
	}
	
	private JTable table;
	private String[][] data;
	private static String[] columnNames = { "Naziv", "Kolicina", "Cijena-komad", "Opis"}; 
}
