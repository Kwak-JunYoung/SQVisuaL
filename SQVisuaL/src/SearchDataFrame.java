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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.table.TableModel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;

public class SearchDataFrame extends JFrame {
   public JTable table;
   private JScrollPane scrollPane;
   private JComboBox<String> tables;
   private JButton update;
   private JTextField textField;
   public SearchDataFrame() {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 450, 300);
      
      JPanel panel_1 = new JPanel();
      getContentPane().add(panel_1, BorderLayout.SOUTH);
      panel_1.setLayout(new GridLayout(2, 2));
      
      JButton add = new JButton("Add Constraint");
      panel_1.add(add);
      add.addActionListener(new addButtonClickListener());
      
      JButton delete = new JButton("Delete Constraint");
      panel_1.add(delete);
      add.addActionListener(new deleteButtonClickListener());
      
      JButton cancel = new JButton("Cancel");
      panel_1.add(cancel);
      
      JButton create = new JButton("Create");
      panel_1.add(create);
      
      
      
      String[] columns = {"Column Name", "Constraint"};
      table = new JTable(new DefaultTableModel(columns, 0));
      DefaultTableModel model = (DefaultTableModel) table.getModel();
      
      scrollPane = new JScrollPane();
      getContentPane().add(scrollPane, BorderLayout.WEST);
      scrollPane.setViewportView(table);
      
      JPanel panel_2 = new JPanel();
      getContentPane().add(panel_2, BorderLayout.NORTH);
      
      tables = new JComboBox<String>();
      //for(String name : t) tables.addItem(name);
      panel_2.add(tables);
      
      update = new JButton("Refresh tables");
      panel_2.add(update);
      
      Object[] row = {"Attr1", "Val1"};
      
      model.addRow(row);
      

   }
   
   
   class addButtonClickListener implements ActionListener {
      public void actionPerformed (ActionEvent e) {
         JPanel panel_3 = new JPanel();
         panel_3.setLayout(new GridLayout(2, 1));
         
         JTextField text1 = new JTextField();
         JTextField text2 = new JTextField();
         
         panel_3.add(text1);
         panel_3.add(text2);
         
         scrollPane.add(panel_3);
      }
      
   }
   
   class deleteButtonClickListener implements ActionListener {
      public void actionPerformed (ActionEvent e) {
   
      }
      
   }
   
   
   
   

}