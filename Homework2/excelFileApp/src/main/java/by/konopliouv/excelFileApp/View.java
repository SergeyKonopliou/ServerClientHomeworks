package by.konopliouv.excelFileApp;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet.ColorAttribute;

/**
 * Create view class
 *
 */

public class View extends JFrame{

	private static final long serialVersionUID = 5699553196613899908L;

	public View() {
		super("Student list");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void startApp() {
		JPanel panel = createAddPanel();
		panel.setBackground(Color.GRAY);
		add(panel);
		setSize(800, 700);
		setLocationRelativeTo(null);
		ImageIcon icon = new ImageIcon("src/main/resources/excel.png");
		setIconImage(icon.getImage());
		setVisible(true);
	}

	private JPanel createAddPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(7, 3, 60, 60));
		JLabel addFirstName = new JLabel("   Имя:    ");
		JTextField textFirstName = new JTextField("");
		JLabel addSecondName = new JLabel("   Фамилия:    ");
		JTextField textSecondName = new JTextField("");
		JLabel addAge = new JLabel("   Возраст:    ");
		JTextField textAddAge = new JTextField("");
		JLabel addGroupNumber = new JLabel("   Номер группы:    ");
		JTextField textGroupNumber = new JTextField("");
		JLabel addStudentID = new JLabel("   Номер зачетки:    ");
		JTextField textStudentID = new JTextField("");

		JButton read = new JButton("Считать и вывести");
		JButton add = new JButton("Записать");

		panel.add(addFirstName);
		panel.add(textFirstName);
		panel.add(addSecondName);
		panel.add(textSecondName);
		panel.add(addAge);
		panel.add(textAddAge);
		panel.add(addGroupNumber);
		panel.add(textGroupNumber);
		panel.add(addStudentID);
		panel.add(textStudentID);
		panel.add(read);
		panel.add(add);

		read.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				if (helper.writeFile(textFirstName.getText(), textSecondName.getText(), textThirdName.getText(),
//						textNumber.getText(), textMail.getText(), textYearBorn.getText())) {
//					JOptionPane.showMessageDialog(null, "Добавлено", "Сообщение", 1);
//				} else {
//					JOptionPane.showMessageDialog(null, "Проверте введенную информацию", "Сообщение", 1);
//				}
			}
		});

		
		return panel;
	}
}
