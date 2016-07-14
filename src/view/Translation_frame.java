package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import module.Dictionary;
import dao.Db_dao_dictionary;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Translation_frame extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public Translation_frame(final Dictionary dictionary) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 781, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u8981\u7FFB\u8BD1\u7684\u53E5\u5B50\uFF1A");
		label.setFont(new Font("张海山锐线体简", Font.PLAIN, 18));
		
		final JTextArea eng = new JTextArea();
		eng.setFont(new Font("张海山锐线体简", Font.PLAIN, 18));
		
		JLabel label_1 = new JLabel("\u7FFB\u8BD1\u7ED3\u679C\uFF1A");
		label_1.setFont(new Font("张海山锐线体简", Font.PLAIN, 18));
		
		final JTextArea chi = new JTextArea();
		chi.setFont(new Font("张海山锐线体简", Font.PLAIN, 18));
		
		JButton button = new JButton("\u7FFB\u8BD1");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Db_dao_dictionary dao_dictionary= new Db_dao_dictionary();
					 String Eng = eng.getText();
					 String temp = dao_dictionary.translate(dictionary, Eng);
					 if(temp.isEmpty()){
						 JOptionPane.showMessageDialog(null, "要翻译的句子中有没有收录的单词！");
					 }
					 chi.setText(temp);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		button.setIcon(new ImageIcon("F:\\Workspace\\Translation_Program\\src\\image\\Arrow_big_right_22.514285714286px_1197963_easyicon.net.png"));
		button.setFont(new Font("张海山锐线体简", Font.PLAIN, 18));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(65)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(chi, GroupLayout.PREFERRED_SIZE, 557, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addComponent(label)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(button))
							.addComponent(eng, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 557, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(131, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(57)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(button))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(eng, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chi, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(53, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
