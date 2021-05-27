import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.xdevapi.Table;

//import SearchDataAddFrame.applyButtonClickListener;
//import SearchDataAddFrame.cancelButtonClickListener;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class AddColumnFrame extends JFrame {
	static Object[] tuple = new Object[5];
	private JPanel contentPane;
	public JButton apply, cancel;

	private JLabel lblNewLabel, lblNewLabel_1, lblNewLabel_2, lblNewLabel_3, lblNewLabel_4;
	private JTextField ColumnName, DataType;
	private JPanel panel_2, panel_3, panel_4;
	private JRadioButton PK_Yes, PK_No;
	private JRadioButton Null_Yes, Null_No, Null_Unknown;
	private JRadioButton Unique_Yes, Unique_No, Unique_Unknown;
	private AddTable at;

	public AddColumnFrame(AddTable at) {
		setSize(488, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(1, 2));

		apply = new JButton("Apply");
		panel.add(apply);

		cancel = new JButton("Cancel");
		panel.add(cancel);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(5, 2));

		lblNewLabel = new JLabel("Column Name");
		panel_1.add(lblNewLabel);

		ColumnName = new JTextField();
		panel_1.add(ColumnName);
		ColumnName.setColumns(10);

		lblNewLabel_1 = new JLabel("Data Type");
		panel_1.add(lblNewLabel_1);

		DataType = new JTextField();
		panel_1.add(DataType);
		DataType.setColumns(10);

		lblNewLabel_2 = new JLabel("Primary Key?");
		panel_1.add(lblNewLabel_2);

		panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(1, 0, 0, 0));

		PK_Yes = new JRadioButton("Yes");
		panel_2.add(PK_Yes);

		PK_No = new JRadioButton("No");
		panel_2.add(PK_No);

		lblNewLabel_3 = new JLabel("Can Be NULL?");
		panel_1.add(lblNewLabel_3);

		panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));

		Null_Yes = new JRadioButton("Yes");
		panel_3.add(Null_Yes);

		Null_No = new JRadioButton("No");
		panel_3.add(Null_No);

		Null_Unknown = new JRadioButton("Unknown");
		panel_3.add(Null_Unknown);

		lblNewLabel_4 = new JLabel("Must be Unique?");
		panel_1.add(lblNewLabel_4);

		panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new GridLayout(1, 0, 0, 0));

		Unique_Yes = new JRadioButton("Yes");
		panel_4.add(Unique_Yes);

		Unique_No = new JRadioButton("No");
		panel_4.add(Unique_No);

		Unique_Unknown = new JRadioButton("Unknown");
		panel_4.add(Unique_Unknown);

		PK_Yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PK_No.setSelected(false);
				Null_Yes.setVisible(false);
				Null_No.setVisible(false);
				Null_Unknown.setVisible(false);
				Unique_Yes.setVisible(false);
				Unique_No.setVisible(false);
				Unique_Unknown.setVisible(false);
				lblNewLabel_3.setVisible(false);
				lblNewLabel_4.setVisible(false);
			}
		});

		PK_No.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PK_Yes.setSelected(false);
				Null_Yes.setVisible(true);
				Null_No.setVisible(true);
				Null_Unknown.setVisible(true);
				Unique_Yes.setVisible(true);
				Unique_No.setVisible(true);
				Unique_Unknown.setVisible(true);
				lblNewLabel_3.setVisible(true);
				lblNewLabel_4.setVisible(true);
			}
		});

		Null_Yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Null_No.setSelected(false);
				Null_Unknown.setSelected(false);
			}
		});

		Null_No.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Null_Yes.setSelected(false);
				Null_Unknown.setSelected(false);
			}
		});

		Null_Unknown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Null_Yes.setSelected(false);
				Null_No.setSelected(false);
			}
		});

		Unique_Yes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Unique_No.setSelected(false);
				Unique_Unknown.setSelected(false);
			}
		});

		Unique_No.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Unique_Yes.setSelected(false);
				Unique_Unknown.setSelected(false);
			}
		});

		Unique_Unknown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Unique_Yes.setSelected(false);
				Unique_No.setSelected(false);
			}
		});

		apply.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tuple[0] = ColumnName.getText();
				tuple[1] = DataType.getText();

				if (PK_Yes.isSelected()) {
					tuple[2] = "Yes";
					tuple[3] = "No";
					tuple[4] = "Yes";
				} else {
					if (PK_No.isSelected())
						tuple[2] = "No";
					else
						tuple[2] = "Not Selected";

					if (Null_Yes.isSelected())
						tuple[3] = "Yes";
					else if (Null_No.isSelected())
						tuple[3] = "No";
					else
						tuple[3] = "UnKnown";

					if (Unique_Yes.isSelected())
						tuple[4] = "Yes";
					else if (Unique_No.isSelected())
						tuple[4] = "No";
					else
						tuple[4] = "Unknown";
				}
				at.addRow(getTuple());
				setVisible(false);
			}
		});

		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});

	}

	public Object[] getTuple() {
		return tuple;
	}
}