package View;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class ConfirmOrderFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public ConfirmOrderFrame() {
		

		setToolbarIcon();
		
		panel = new JPanel();
		shipOrderBtn = new JButton("Pošalji narudžbu");
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Pregled narudžbe "),
				BorderFactory.createEmptyBorder(15, 15, 15, 15)));
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new AbstractFormatter() {

			@Override
			public Object stringToValue(String text) throws ParseException {

				return "";
			}

			@Override
			public String valueToString(Object value) throws ParseException {
				if (value != null) {
					Calendar cal = (Calendar) value;
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String date = format.format(cal.getTime());
					shippingDate = date;
					return date;
				}
				return "";
			}

		});

		this.getContentPane().setLayout(new BorderLayout());
		adjust();
		add(BorderLayout.CENTER, panel);
		add(BorderLayout.SOUTH, shipOrderBtn);
	}
	
	public void addShipOrderActionListener(ActionListener listener) {
		shipOrderBtn.addActionListener(listener);
	}

	public String getShippingDate() {
		return shippingDate;
	}
	
	public String getShippingNote() {
		return noteArea.getText();
	}

	private void adjust() {
		setSize(350, 250);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		// Set layout.
		panel.setLayout(new GridBagLayout());

		// Create components.
		deliveryDateLbl = new JLabel("Datum isporuke: ");
		noteLbl = new JLabel("Napomena: ");
		
		noteArea = new JTextField(20);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(deliveryDateLbl, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(datePicker, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(noteLbl, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(noteArea, gbc);
	}

	private void setToolbarIcon() {
		try {
			setIconImage(ImageIO.read(new File("src/resources/laptop.png")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	

	private JLabel deliveryDateLbl;
	private JLabel noteLbl;
	private JTextField deliveryDateTxt;
	private JTextField noteArea;
	private JPanel panel;
	private JButton shipOrderBtn;
	private JDatePickerImpl datePicker;
	private String shippingDate;
}
