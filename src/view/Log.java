package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import dao.Db_dao_dictionary;
import dao.Db_dao_user;
import module.Dictionary;
import module.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Log extends JFrame {

	private JPanel contentPane;
	private JTextField UsernameTxt;
	private JTextField PasswordTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Log frame = new Log();
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
	public Log() {
		setBackground(Color.DARK_GRAY);
		setResizable(false);
		setTitle("\u4E2A\u4EBA\u8BCD\u5178\u5C0F\u52A9\u624B");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JLabel lblNewLabel = new JLabel("\u4E2A\u4EBA\u8BCD\u5178\u5C0F\u52A9\u624B");
		lblNewLabel.setIcon(new ImageIcon("F:\\Workspace\\Translation_Program\\src\\image\\Font_Book_32px_504077_easyicon.net.png"));
		lblNewLabel.setFont(new Font("张海山锐线体简", Font.PLAIN, 18));
		
		JLabel label = new JLabel("\u8D26\u53F7");
		label.setFont(new Font("张海山锐线体简", Font.PLAIN, 18));
		
		UsernameTxt = new JTextField();
		UsernameTxt.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801");
		label_1.setFont(new Font("张海山锐线体简", Font.PLAIN, 18));
		
		PasswordTxt = new JTextField();
		PasswordTxt.setColumns(10);
		
		JButton button = new JButton("\u767B\u5F55");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String	a = UsernameTxt.getText();
				String	b = PasswordTxt.getText();
				User user = new User();
				user.setId(a);
				user.setPassword(b);
				try {
					Db_dao_user dao_user = new Db_dao_user();
					if(dao_user.login(user)!=null){
						Db_dao_dictionary dao_dictionary = new Db_dao_dictionary();
						Dictionary dictionary = new Dictionary();
						dictionary.setId(a);
						dispose();
						Mainframe mainframe = new Mainframe();
						mainframe.InitDictionary(a, dao_dictionary.find_all(dictionary));
						mainframe.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "用户名或密码错误！");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		button.setFont(new Font("张海山锐线体简", Font.PLAIN, 14));
		
		JButton button_1 = new JButton("\u6CE8\u518C");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Register register = new Register();
				register.setVisible(true);
			}
		});
		button_1.setFont(new Font("张海山锐线体简", Font.PLAIN, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(113)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label)
							.addGap(18)
							.addComponent(UsernameTxt, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
							.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addComponent(label_1)
								.addGap(18)
								.addComponent(PasswordTxt, 141, 141, 141))))
					.addContainerGap(126, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(UsernameTxt, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(PasswordTxt, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(49, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		this.setLocationRelativeTo(null);
	}
}
