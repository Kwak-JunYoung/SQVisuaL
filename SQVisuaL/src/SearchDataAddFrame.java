import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SearchDataAddFrame extends JFrame {

   public JButton apply, cancel;
   private String a, b;
   private JPanel contentPane;
   public JTextField column, constraint;
   
   SearchDataFrame sdf = new SearchDataFrame();

   public SearchDataAddFrame() {

      
      setSize(400, 150);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(new BorderLayout(0, 0));
      
      JPanel panel = new JPanel();
      contentPane.add(panel, BorderLayout.SOUTH);
      panel.setLayout(new GridLayout(1,2));
      
      apply = new JButton("Apply");
      panel.add(apply);
      apply.addActionListener(new applyButtonClickListener());
      
      cancel = new JButton("Cancel");
      panel.add(cancel);
      
      JPanel panel_1 = new JPanel();
      contentPane.add(panel_1, BorderLayout.CENTER);
      panel_1.setLayout(new GridLayout(2,2));
      
      JLabel lblNewLabel = new JLabel("Column");
      panel_1.add(lblNewLabel);
      
      column = new JTextField();
      panel_1.add(column);
      column.setColumns(10);
      
      JLabel lblNewLabel_1 = new JLabel("Constraint");
      panel_1.add(lblNewLabel_1);
      
      constraint = new JTextField();
      panel_1.add(constraint);
      constraint.setColumns(10);
      
      
   }
   
    class applyButtonClickListener implements ActionListener {
         public void actionPerformed (ActionEvent e) {
            a = column.getText();
            b = constraint.getText();
            
            column.setText("");
            constraint.setText("");
           
            Object[] row = {a, b};
            sdf.model.addRow(row);
            
            sdf.setVisible(true);
            
         }
         
      }
    
    

}
