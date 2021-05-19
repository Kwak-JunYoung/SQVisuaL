import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Insets;
import javax.swing.ListSelectionModel;
import java.awt.Dimension;
import javax.swing.JScrollPane;

public class AddTable extends JFrame {
	public JTable table;
	public JButton Add, Delete, Cancel, Create;
	private JButton btnNewButton;
	private JComboBox comboBox;


   /**
    * Launch the application.
    */


   /**
    * Create the frame.
    */
   public AddTable() {
	   String column[]={"Column Name","Data Type","Primary Key?", "Can be NULL?", "Must be unique?"};
	   String data[][]={ {"Name","VARCHAR", "No", "No", "No"},    
			   {"ID","VARCHAR","Yes", "-", "-"}    };

		table = new JTable(new DefaultTableModel(column, 0));
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
   	JScrollPane scrollPane = new JScrollPane();
   	getContentPane().add(scrollPane, BorderLayout.CENTER);
	scrollPane.setViewportView(table);

	
	JPanel panel_1 = new JPanel();
	getContentPane().add(panel_1, BorderLayout.SOUTH);
	panel_1.setLayout(new GridLayout(2, 2));
	
	Add = new JButton("Add Column");
	Add.setVisible(true);
	panel_1.add(Add);
	
	Delete = new JButton("Delete Column");
	Add.setVisible(true);
	panel_1.add(Delete);
	
	Cancel = new JButton("Cancel");
	Cancel.setVisible(true);
	panel_1.add(Cancel);
	
	Create = new JButton("Create");
	Create.setVisible(true);
	panel_1.add(Create);
	
	JPanel panel = new JPanel();
	getContentPane().add(panel, BorderLayout.NORTH);
	panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	
	comboBox = new JComboBox<String>();
	panel.add(comboBox);
	
	btnNewButton = new JButton("Refresh");
	panel.add(btnNewButton);

	setSize(500, 400);

   }

}