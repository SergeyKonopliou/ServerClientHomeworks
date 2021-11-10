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

/**
 * Create view class
 *
 */

public class View extends JFrame{

	private static final long serialVersionUID = 5699553196613899908L;
	private ExcelWorker worker;
	private JTextField textFirstName;
	private JTextField textSecondName;
	private JTextField textAddAge;
	private JTextField textGroupNumber;
	private JTextField textStudentID;

	public View(ExcelWorker worker) {
		super("Student list");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.worker = worker;
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
		textFirstName = new JTextField("");
		JLabel addSecondName = new JLabel("   Фамилия:    ");
		textSecondName = new JTextField("");
		JLabel addAge = new JLabel("   Возраст:    ");
		textAddAge = new JTextField("");
		JLabel addGroupNumber = new JLabel("   Номер группы:    ");
		textGroupNumber = new JTextField("");
		JLabel addStudentID = new JLabel("   Номер зачетки:    ");
		textStudentID = new JTextField("");

		JButton read = new JButton("Считать и вывести");
		JButton write = new JButton("Записать");

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
		panel.add(write);

		read.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				worker.readFile();
				JOptionPane.showMessageDialog(null, "Прочитано", "Сообщение", 1);
			}
		});

		write.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Student student = new Student(textFirstName.getText(),textSecondName.getText(),Integer.parseInt(textAddAge.getText()),
						textGroupNumber.getText(),Integer.parseInt(textStudentID.getText()));
				worker.writeFile(student);
				JOptionPane.showMessageDialog(null, "Записано", "Сообщение", 1);

				
			}
		});

		
		return panel;
	}
}
