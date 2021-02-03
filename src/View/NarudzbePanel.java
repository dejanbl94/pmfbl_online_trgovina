package View;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class NarudzbePanel extends JPanel {
	
	private JTable table;

	public NarudzbePanel() {
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Narudzbe "),
				BorderFactory.createEmptyBorder(15, 15, 15, 15)));
	}
	
	public void setupTable() {
		table = new JTable(data, columnNames); 
		for(int i = 0; i < data.length; i++) {
        	for(int j = 0; j < data[i].length; j++) {
    			table.getColumnModel().getColumn(i).setCellRenderer(new CustomTableRenderer());
    		}
        }
       // Drawing d = new Drawing(text, font, textColor);
		table.setBounds(20, 20, 200, 200); 
		// adding it to JScrollPane 
        JScrollPane sp = new JScrollPane(table); 
        add(sp); 
        
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                // do some actions here, for example
                // print first column value from selected row
                System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
            }
        });
	}
	
	public String[][] getData() {
		return this.data;
	}
	
	public void setData(String[][] data) {
		this.data = data;
	}
	
	private String[][] data;
	private static String[] columnNames = { "Datum narudzbe", "Datum isporuke", "Napomena", "Iznos"}; 
	
}
