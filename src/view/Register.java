package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;

import javax.swing.JButton;

import dao.Db_dao_user;
import module.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField UserID;
	private JTextField Password;
	private JTextField NickName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u7528\u6237ID\uFF1A");
		lblNewLabel.setFont(new Font("张海山锐线体简", Font.PLAIN, 15));
		
		UserID = new JTextField();
		UserID.setColumns(10);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u5BC6\u7801\uFF1A");
		label.setFont(new Font("张海山锐线体简", Font.PLAIN, 15));
		
		Password = new JTextField();
		Password.setColumns(10);
		
		JLabel label_1 = new JLabel("\u8BF7\u8F93\u5165\u6635\u79F0\uFF1A");
		label_1.setFont(new Font("张海山锐线体简", Font.PLAIN, 15));
		
		NickName = new JTextField();
		NickName.setColumns(10);
		
		JButton button = new JButton("\u6CE8\u518C");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String	a = UserID.getText();
				String	b = Password.getText();
				String	c = NickName.getText();
				/*
				 * a.trim()消除空格的影响
				 */
				if(a.trim().isEmpty()||b.trim().isEmpty()||c.trim().isEmpty()){
					JOptionPane.showMessageDialog(null, "不允许有空值！");
				}else{
					try {
						Db_dao_user dao_user = new Db_dao_user();
						User user = dao_user.register(a, b, c);
						JOptionPane.showMessageDialog(null, "注册成功！\n"+user);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "用户名已存在！");
					}
				}
			}
		});
		button.setFont(new Font("张海山锐线体简", Font.PLAIN, 15));
		
		JButton button_1 = new JButton("\u8FD4\u56DE\u767B\u5F55");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Log log = new Log();
				log.setVisible(true);
			}
		});
		button_1.setFont(new Font("张海山锐线体简", Font.PLAIN, 15));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(117, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1)
						.addComponent(label)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(button)
								.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
								.addComponent(button_1))
							.addComponent(UserID, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
							.addComponent(Password, 206, 206, 206)
							.addComponent(NickName, 206, 206, 206)))
					.addGap(99))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(UserID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(NickName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(button_1))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		this.setLocationRelativeTo(null);
	}
}
