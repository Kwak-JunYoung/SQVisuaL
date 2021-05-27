import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSplitPane;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

public class SearchDataAddFrame extends JFrame {

	public JButton apply, cancel;
	private String a, b;
	private JPanel contentPane;
	public JTextField column, constraint;
	private JPanel panel_1;
	private JPanel panel_2;
	private JComboBox<String> attrList;
	private JLabel lblNewLabel;
	private JPanel panel_3;
	private JPanel panel_4;
	private JRadioButton gt;
	private JRadioButton lt;
	private JRadioButton gte;
	private JRadioButton lte;
	private JRadioButton neq;
	private JRadioButton eq;
	private JTextField numComp;
	private JPanel panel_5;
	private JPanel panel_6;
	private JRadioButton like;
	private JRadioButton notLike;
	private JTextField textComp;
	public SearchDataFrame sdf;
	private ArrayList<Pair<String, Boolean>> attrs;
	private JRadioButton eqtxt;
	private JRadioButton neqtxt;

	public SearchDataAddFrame(SearchDataFrame sdf, ArrayList<Pair<String, Boolean>> attrs) {

		this.sdf = sdf;
		this.attrs = attrs;
		setSize(487, 301);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		ButtonGroup g1 = new ButtonGroup(), g2 = new ButtonGroup();

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(1, 2));

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

		panel_6 = new JPanel();
		panel_3.add(panel_6);
		panel_6.setLayout(new GridLayout(4, 1));

		eqtxt = new JRadioButton("Exact match");
		panel_6.add(eqtxt);

		neqtxt = new JRadioButton("Doesn't match");
		panel_6.add(neqtxt);

		like = new JRadioButton("Like");
		panel_6.add(like);

		notLike = new JRadioButton("Not like");
		panel_6.add(notLike);

		textComp = new JTextField();
		panel_3.add(textComp);
		textComp.setColumns(10);

		panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new GridLayout(1, 3));

		panel_5 = new JPanel();
		panel_4.add(panel_5);
		panel_5.setLayout(new GridLayout(6, 1));

		eq = new JRadioButton("=");
		panel_5.add(eq);

		neq = new JRadioButton("\u2260");
		panel_5.add(neq);

		gt = new JRadioButton(">");
		panel_5.add(gt);
		g1.add(gt);

		gte = new JRadioButton("\u2265");
		panel_5.add(gte);
		g1.add(gte);

		lt = new JRadioButton("<");
		panel_5.add(lt);

		lte = new JRadioButton("\u2264");
		panel_5.add(lte);
		g1.add(lte);

		numComp = new JTextField();
		panel_4.add(numComp);
		numComp.setColumns(10);

		panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.NORTH);

		lblNewLabel = new JLabel("Apply constraint on:");
		panel_2.add(lblNewLabel);
		attrList = new JComboBox<String>();
		attrList.addItem("Select an attribute...");
		panel_2.add(attrList);
		this.setBoxText();
		g1.add(eq);
		g1.add(neq);
		g1.add(lt);

		g2.add(like);
		g2.add(notLike);
		g2.add(eqtxt);
		g2.add(neqtxt);
		toggleTxt(false);
		toggleNum(false);
		apply.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}

		});
		attrList.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED && attrList.getSelectedIndex() != 0) {
					if (attrs.get(attrList.getSelectedIndex() - 1).getSecond()) {
						toggleNum(true);
						toggleTxt(false);
					} else {
						toggleNum(false);
						toggleTxt(true);
					}
				}
			}
		});
	}

	public void toggleTxt(boolean v) {
		like.setEnabled(v);
		notLike.setEnabled(v);
		eqtxt.setEnabled(v);
		neqtxt.setEnabled(v);
		textComp.setEnabled(v);
	}

	public void toggleNum(boolean v) {
		eq.setEnabled(v);
		neq.setEnabled(v);
		gt.setEnabled(v);
		gte.setEnabled(v);
		lt.setEnabled(v);
		lte.setEnabled(v);
		numComp.setEnabled(v);
	}

	public void setBoxText() {
		for (Pair<String, Boolean> p : this.attrs) {
			this.attrList.addItem(p.getFirst());
		}
	}
}
