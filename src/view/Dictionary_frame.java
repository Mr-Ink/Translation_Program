package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import module.Dictionary;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.Db_dao_dictionary;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Dictionary_frame extends JFrame {

	private JPanel contentPane;
	private JTextField add_eng;
	private JTextField add_chi;
	private JTextField change_eng;
	private JTextField change_chi;
	private Dictionary dictionary;
	private JTable dictionary_table;
	/*
	 * 初始化表格
	 */
	public void fill_table() {
		DefaultTableModel dtp =  (DefaultTableModel) dictionary_table.getModel();
		dtp.setRowCount(0);
		Map<String, String> map = dictionary.getWordMap();
		for (Entry<String, String> entry : map.entrySet()) {
			Vector<String> vector = new Vector<>();
			vector.add(entry.getKey());
			vector.add(entry.getValue());
			dtp.addRow(vector);
		}
	}
	/**
	 * Create the frame.
	 */
	public Dictionary_frame(final Dictionary dictionary) {
		this.dictionary = dictionary;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 581);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("\u6DFB\u52A0\u5355\u8BCD");
		label.setFont(new Font("张海山锐线体简", Font.PLAIN, 15));
		
		add_eng = new JTextField();
		add_eng.setText("english...");
		add_eng.setFont(new Font("张海山锐线体简", Font.PLAIN, 15));
		add_eng.setColumns(10);
		
		add_chi = new JTextField();
		add_chi.setText("\u4E2D\u6587...");
		add_chi.setFont(new Font("张海山锐线体简", Font.PLAIN, 15));
		add_chi.setColumns(10);
		
		JButton button = new JButton("\u6DFB\u52A0");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String eng = add_eng.getText();
					String chi = add_chi.getText();
					Db_dao_dictionary dao_dictionary = new Db_dao_dictionary();
					boolean flag = dao_dictionary.add_word(dictionary, eng, chi);
					if(eng.isEmpty()||chi.isEmpty()){
						JOptionPane.showMessageDialog(null, "添加的值不能为空！");
					}else {
						if(!flag){
							JOptionPane.showMessageDialog(null, "单词已存在！");
						}else{dictionary.setWordMap(dao_dictionary.find_all(dictionary));
						fill_table();
						}
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
		button.setFont(new Font("张海山锐线体简", Font.PLAIN, 15));
		
		JButton button_1 = new JButton("\u4FEE\u6539");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String eng = change_eng.getText();
					String chi = change_chi.getText();
					Db_dao_dictionary dao_dictionary = new Db_dao_dictionary();
					boolean flag = dao_dictionary.update(dictionary, eng, chi);
					if(eng.isEmpty()||chi.isEmpty()){
						JOptionPane.showMessageDialog(null, "修改的值不能为空！");
					}else {
						if(!flag){
							JOptionPane.showMessageDialog(null, "修改的单词不存在！");
						}else{
							dictionary.setWordMap(dao_dictionary.find_all(dictionary));
							fill_table();
						}
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
		button_1.setFont(new Font("张海山锐线体简", Font.PLAIN, 15));
		
		change_eng = new JTextField();
		change_eng.setText("english...");
		change_eng.setFont(new Font("张海山锐线体简", Font.PLAIN, 15));
		change_eng.setColumns(10);
		
		change_chi = new JTextField();
		change_chi.setText("\u4E2D\u6587");
		change_chi.setFont(new Font("张海山锐线体简", Font.PLAIN, 15));
		change_chi.setColumns(10);
		
		JLabel label_1 = new JLabel("\u4FEE\u6539\u8BCD\u610F");
		label_1.setFont(new Font("张海山锐线体简", Font.PLAIN, 15));
		
		JLabel label_2 = new JLabel("\u8BCD\u5E93\u603B\u89C8");
		label_2.setFont(new Font("张海山锐线体简", Font.PLAIN, 15));
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(100, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 586, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
							.addGap(310)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(add_eng, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
							.addGap(28)
							.addComponent(add_chi, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
							.addGap(87)
							.addComponent(change_eng, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(change_chi, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
							.addGap(312)
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)))
					.addGap(65))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(46)
					.addComponent(label_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 290, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(add_eng, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(add_chi, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(change_eng, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(change_chi, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(54, Short.MAX_VALUE))
		);
		
		dictionary_table = new JTable();
		dictionary_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Word", "Chinese", "Example_Sentence"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		dictionary_table.getColumnModel().getColumn(2).setPreferredWidth(400);
		scrollPane.setViewportView(dictionary_table);
		contentPane.setLayout(gl_contentPane);
		this.fill_table();
	}
}
