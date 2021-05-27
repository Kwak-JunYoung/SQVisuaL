import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.table.TableModel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

public class SearchDataFrame extends JFrame {
	public ArrayList<String> t;
   public JTable table;
   public JButton add, delete, cancel, create;
   public DefaultTableModel model;
   private JScrollPane scrollPane;
   private JComboBox<String> tables;
   private JTextField textField;
   private SQVisuaL sql;

   
   public SearchDataFrame(SQVisuaL sql) {
	   
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 450, 300);
      this.sql = sql;
      this.updateTables();
      
      JPanel panel_1 = new JPanel();
      getContentPane().add(panel_1, BorderLayout.SOUTH);
      panel_1.setLayout(new GridLayout(2, 2));
      
      add = new JButton("Add Constraint");
      panel_1.add(add);
      add.addActionListener(new addButtonClickListener(this));
      
      delete = new JButton("Delete Constraint");
      panel_1.add(delete);
      delete.addActionListener(new deleteButtonClickListener());
      
      cancel = new JButton("Cancel");
      panel_1.add(cancel);
      cancel.addActionListener(new cancelButtonClickListener());
      
      create = new JButton("Create");
      panel_1.add(create);
      create.addActionListener(new createButtonClickListener());
      
      
      
      String[] columns = {"Column Name", "Constraint"};
      table = new JTable(new DefaultTableModel(columns, 0));
      model = (DefaultTableModel) table.getModel();
      
      scrollPane = new JScrollPane();
      getContentPane().add(scrollPane, BorderLayout.WEST);
      scrollPane.setViewportView(table);
      
      JPanel panel_2 = new JPanel();
      getContentPane().add(panel_2, BorderLayout.NORTH);
      
      tables = new JComboBox<String>();
      panel_2.add(tables);
      for(String name : this.t) tables.addItem(name);
      

   }
   
   class createButtonClickListener implements ActionListener {
       public void actionPerformed (ActionEvent e) {
    	   setVisible(false);
       }
       
    }
   
   class cancelButtonClickListener implements ActionListener {
       public void actionPerformed (ActionEvent e) {
    	   DefaultTableModel tm = (DefaultTableModel)table.getModel();
    	   tm.setNumRows(0);
    	   setVisible(false);
       }
       
    }
   
   class deleteButtonClickListener implements ActionListener {
       public void actionPerformed (ActionEvent e) {
    	   int row = table.getSelectedRow();
    	   DefaultTableModel tm = (DefaultTableModel)table.getModel();
    	   tm.removeRow(row);
       }
       
    }
   class addButtonClickListener implements ActionListener {
	   private SearchDataFrame sdf;
       public addButtonClickListener(SearchDataFrame searchDataFrame) {
    	   	super();
			this.sdf = searchDataFrame;
       }

       public void actionPerformed (ActionEvent e) {
    	   SearchDataAddFrame sdaf = new SearchDataAddFrame(this.sdf);
    	   sdaf.setVisible(true);
       }
       
    }
   public void updateTables() {
		if(this.sql.connect()) {
			ArrayList<String> tables = sql.getProvider().getTables();
			tables.add(0, "Please select a table...");
			if(tables.size() != 0) this.t = tables;
		}
	}

}

