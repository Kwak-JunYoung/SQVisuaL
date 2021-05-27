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
      //apply.addActionListener(new applyButtonClickListener());
      
      cancel = new JButton("Cancel");
      panel.add(cancel);
   }
}
