import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class DeleteColumnFrame extends JFrame {

	private JPanel contentPane;
	private JTextField ColumnName;
	private JButton Apply;
	private JButton Cancel;

	public DeleteColumnFrame(AddTable at) {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 2));

		JLabel lblNewLabel = new JLabel("Column Name: ");
		contentPane.add(lblNewLabel);

		ColumnName = new JTextField();
		contentPane.add(ColumnName);
		ColumnName.setColumns(10);

		Apply = new JButton("Apply");
		contentPane.add(Apply);

		Cancel = new JButton("Cancel");
		contentPane.add(Cancel);

		Apply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				at.deleteRow(ColumnName.getText());
				setVisible(false);
			}
		});

		Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
			}
		});
		setSize(250, 150);
	}

}
