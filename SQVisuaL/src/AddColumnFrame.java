import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;

public class AddColumnFrame extends JFrame {
	static Object[] tuple = new Object[5];
	private JPanel contentPane;
	public JButton apply, cancel;

	private JLabel lblNewLabel, lblNewLabel_1;
	private JTextField ColumnName, DataType;
	private JCheckBox isPK;
	private JCheckBox unique;
	private JCheckBox canBeNull;
	private JLabel lblNewLabel_2;

	public AddColumnFrame(AddTable at) {
		setSize(488, 300);
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		lblNewLabel_2 = new JLabel("");
		panel_1.add(lblNewLabel_2);

		isPK = new JCheckBox("Primary key");
		panel_1.add(isPK);

		canBeNull = new JCheckBox("Can be set to NULL");
		panel_1.add(canBeNull);

		unique = new JCheckBox("Must be unique");
		panel_1.add(unique);
		
		isPK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				optionsVisibility(!isPK.isSelected());
			}
			
		});

		apply.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tuple[0] = ColumnName.getText();
				tuple[1] = DataType.getText();

				if (isPK.isSelected()) {
					tuple[2] = "Yes";
					tuple[3] = "No";
					tuple[4] = "Yes";
				} else {
					tuple[2] = "No";
					if (canBeNull.isSelected())
						tuple[3] = "Yes";
					else
						tuple[3] = "No";

					if (unique.isSelected())
						tuple[4] = "Yes";
					else
						tuple[4] = "No";
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
	
	public void optionsVisibility(boolean v) {
		this.canBeNull.setVisible(v);
		this.unique.setVisible(v);
		
	}

	public Object[] getTuple() {
		return tuple;
	}
}