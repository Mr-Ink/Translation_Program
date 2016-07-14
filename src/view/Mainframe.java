package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import module.Dictionary;
import dao.Db_dao_dictionary;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.Map.Entry;

public class Mainframe extends JFrame {

	private JPanel contentPane;
	private JTextField SearchWord;
	private Dictionary dictionary;
	
	/*
	 * 初始化Dictionary(因为每个不同的ID对应的Dictionary都不相同)
	 */
	public void InitDictionary(String id,Map<String, String> wordMap) {
		dictionary = new Dictionary();
		dictionary.setId(id);
		dictionary.setWordMap(wordMap);
	}
	/**
	 * Create the frame.
	 */
	public Mainframe() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		final JTextArea textArea = new JTextArea();
		final JTextArea chinese = new JTextArea();
		chinese.setEditable(false);
		setContentPane(contentPane);
		
		JButton button = new JButton("\u8BCD\u5E93\u603B\u89C8");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dictionary_frame dictionary_frame = new Dictionary_frame(dictionary);
				dictionary_frame.setVisible(true);
			}
		});
		button.setFont(new Font("张海山锐线体简", Font.PLAIN, 18));
		
		JButton button_1 = new JButton("\u5355\u8BCD\u672C");
		button_1.setFont(new Font("张海山锐线体简", Font.PLAIN, 18));
		
		JButton button_2 = new JButton("\u53E5\u5B50\u7FFB\u8BD1");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Translation_frame tra_view = new Translation_frame(dictionary);
				tra_view.setVisible(true);
			}
		});
		button_2.setFont(new Font("张海山锐线体简", Font.PLAIN, 18));
		
		SearchWord = new JTextField();
		SearchWord.setFont(new Font("张海山锐线体简", Font.PLAIN, 18));
		SearchWord.setText("\u8BF7\u8F93\u5165\u8981\u67E5\u8BE2\u7684\u5355\u8BCD......");
		SearchWord.setColumns(10);
		
		JButton button_3 = new JButton("\u67E5\u8BE2");
		button_3.setFont(new Font("张海山锐线体简", Font.PLAIN, 18));
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a = SearchWord.getText();
				try {
					Db_dao_dictionary dao_dictionary = new Db_dao_dictionary();
					Map<String, String> map = dao_dictionary.find(dictionary, a);
					if(map.isEmpty()){
						JOptionPane.showMessageDialog(null, "查询的单词不存在");
					}else{
						String str = new String();
						for (Entry<String, String> s : map.entrySet()) {
							str+=s.getKey()+":"+s.getValue()+"\n";
						}
						chinese.setText(str);
						chinese.setFont(new Font("张海山锐线体简", Font.PLAIN, 18));
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}finally{
					try {
						Db_dao_dictionary.close_con();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		button_3.setIcon(new ImageIcon("F:\\Workspace\\Translation_Program\\src\\image\\search.png"));
		
		JLabel lblNewLabel = new JLabel("\u91CA\u4E49:");
		lblNewLabel.setIcon(new ImageIcon("F:\\Workspace\\Translation_Program\\src\\image\\modify.png"));
		lblNewLabel.setFont(new Font("张海山锐线体简", Font.PLAIN, 18));
		
		JLabel label = new JLabel("\u76F8\u5173\u4F8B\u53E5:");
		label.setFont(new Font("张海山锐线体简", Font.PLAIN, 15));
		label.setIcon(new ImageIcon("F:\\Workspace\\Translation_Program\\src\\image\\me.png"));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(140)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 352, GroupLayout.PREFERRED_SIZE)
						.addComponent(chinese, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(button)
								.addGap(30)
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
								.addGap(38)
								.addComponent(button_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(SearchWord, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
								.addGap(30)
								.addComponent(button_3))))
					.addContainerGap(161, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(45)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(button_1)
						.addComponent(button_2))
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(SearchWord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_3))
					.addGap(18)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(chinese, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(label)
					.addGap(17)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(21, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		this.setLocationRelativeTo(null);
	}
}
