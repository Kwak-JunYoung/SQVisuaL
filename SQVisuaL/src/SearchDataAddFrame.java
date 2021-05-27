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
import javax.swing.JComboBox;
import javax.swing.JSplitPane;
import javax.swing.JRadioButton;

public class SearchDataAddFrame extends JFrame {

   public JButton apply, cancel;
   private String a, b;
   private JPanel contentPane;
   public JTextField column, constraint;
   private JPanel panel_1;
   private JPanel panel_2;
   private JComboBox attrList;
   private JLabel lblNewLabel;
   private JPanel panel_3;
   private JPanel panel_4;
   private JRadioButton gt;
   private JRadioButton lt;
   private JRadioButton gte;
   private JRadioButton lte;
   private JRadioButton neq;
   private JRadioButton eq;
   private JLabel currentAttrNum;
   private JTextField textField;
   private JPanel panel_5;
   private JLabel currentAttrTxt;
   private JPanel panel_6;
   private JRadioButton like;
   private JRadioButton notLike;
   private JTextField textField_1;
   public SearchDataFrame sdf;
   public SearchDataAddFrame(SearchDataFrame sdf) {

      this.sdf = sdf;
      setSize(530, 301);
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
      
      cancel = new JButton("Cancel");
      panel.add(cancel);
      
      panel_1 = new JPanel();
      contentPane.add(panel_1, BorderLayout.CENTER);
      panel_1.setLayout(new GridLayout(1, 2));
      
      panel_3 = new JPanel();
      panel_1.add(panel_3);
      panel_3.setLayout(new GridLayout(1, 3));
      
      currentAttrTxt = new JLabel("New label");
      panel_3.add(currentAttrTxt);
      
      panel_6 = new JPanel();
      panel_3.add(panel_6);
      panel_6.setLayout(new GridLayout(2, 1));
      
      like = new JRadioButton("LIKE");
      panel_6.add(like);
      
      notLike = new JRadioButton("NOT LIKE");
      panel_6.add(notLike);
      
      textField_1 = new JTextField();
      panel_3.add(textField_1);
      textField_1.setColumns(10);
      
      panel_4 = new JPanel();
      panel_1.add(panel_4);
      panel_4.setLayout(new GridLayout(1, 3));
      
      currentAttrNum = new JLabel("New label");
      panel_4.add(currentAttrNum);
      
      panel_5 = new JPanel();
      panel_4.add(panel_5);
      panel_5.setLayout(new GridLayout(6, 1));
      
      eq = new JRadioButton("=");
      panel_5.add(eq);
      
      neq = new JRadioButton("\u2260");
      panel_5.add(neq);
      
      lt = new JRadioButton("<");
      panel_5.add(lt);
      
      gte = new JRadioButton("\u2265");
      panel_5.add(gte);
      
      gt = new JRadioButton(">");
      panel_5.add(gt);
      
      lte = new JRadioButton("\u2264");
      panel_5.add(lte);
      
      textField = new JTextField();
      panel_4.add(textField);
      textField.setColumns(10);
      
      panel_2 = new JPanel();
      contentPane.add(panel_2, BorderLayout.NORTH);
      
      lblNewLabel = new JLabel("Apply constraint on:");
      panel_2.add(lblNewLabel);
      
      attrList = new JComboBox();
      panel_2.add(attrList);
   }
}
