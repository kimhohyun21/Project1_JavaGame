package com.p1.client;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;


public class ScoreBoard extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScoreBoard frame = new ScoreBoard();
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
	public ScoreBoard() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
	    JPanel upperPanel = new JPanel(); 

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.white);

		JLabel label = new JLabel("이름");
		label.setForeground(Color.white);
		label.setBounds(50, 158, 57, 15);
		label.setOpaque(true);
		label.setBackground(Color.black);
		contentPane.add(label);
		label.setHorizontalAlignment(JLabel.CENTER);

		JLabel label1 = new JLabel("시간");
		label1.setForeground(Color.white);
		label1.setBounds(148, 158, 57, 15);
		label1.setOpaque(true);
		label1.setBackground(Color.black);
		contentPane.add(label1);
		label1.setHorizontalAlignment(JLabel.CENTER);

		JLabel label2 = new JLabel("점수");
		label2.setForeground(Color.white);
		label2.setBounds(246, 158, 57, 15);
		label2.setOpaque(true);
		label2.setBackground(Color.black);
		contentPane.add(label2);
		label2.setHorizontalAlignment(JLabel.CENTER);

		JLabel label3 = new JLabel("목숨");
		label3.setForeground(Color.white);
		label3.setBounds(347, 158, 57, 15);
		label3.setOpaque(true);
		label3.setBackground(Color.black);
		contentPane.add(label3);
		label3.setHorizontalAlignment(JLabel.CENTER);

		JLabel label4 = new JLabel("승패");
		label4.setForeground(Color.white);
		label4.setBounds(445, 158, 57, 15);
		label4.setOpaque(true);
		label4.setBackground(Color.black);
		contentPane.add(label4);
		label4.setHorizontalAlignment(JLabel.CENTER);

		JPanel panel = new JPanel();
		panel.setBounds(22, 189, 511, 76);
		contentPane.add(panel);
		panel.setLayout(null);

		textField = new JTextField();
		textField.setBounds(12, 10, 87, 21);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(111, 10, 87, 21);
		panel.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(210, 10, 87, 21);
		panel.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(309, 10, 87, 21);
		panel.add(textField_3);

		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(408, 10, 87, 21);
		panel.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(12, 41, 87, 21);
		panel.add(textField_5);

		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(111, 41, 87, 21);
		panel.add(textField_6);

		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(210, 41, 87, 21);
		panel.add(textField_7);

		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(309, 41, 87, 21);
		panel.add(textField_8);

		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(408, 41, 87, 21);
		panel.add(textField_9);

		int imgCount = (int) (Math.random() * 10);
		ImageIcon icon;
		if (imgCount >= 6) {
			icon = new ImageIcon("img\\plz_stop.jpg");
		} else {
			icon = new ImageIcon("img\\plz_stop1.jpg");
		}
		JPanel panel_1 = new JPanel() {
			// ImageIcon icon= new ImageIcon("img\\plz_stop.jpg");
			// Image
			// icon=Toolkit.getDefaultToolkit().getImage("img\\plz_stop.jpg");
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, this.getWidth(), 332, null);
			}
		};
		panel_1.setBounds(22, 288, 511, 390);
		contentPane.add(panel_1);
		
		JTextArea textArea = new JTextArea();
	
		textArea.setBounds(635, 158, 337, 406);
		contentPane.add(textArea);
		textArea.setBackground(new Color(255, 255, 245));
		//행 넘기기 기능 켜기
		textArea.setLineWrap(true);
	    //행 넘길 때 행의 마지막 단어가 두행에 걸쳐 나뉘지 않도록 하기
		textArea.setWrapStyleWord(true);
	    // 편집이 불가능하도록 설정
		textArea.setEditable(false);
	    // TextArea의 테두리선의 색을 검정 두깨를 1px로 설정합니다.
	    Border lineBorder = BorderFactory.createLineBorder(Color.black, 1);
	    // 텍스트와 TextArea 경계 사이에 여백을 두기 위해서 emptyBorder를 생성합니다. 
	    Border emptyBorder = BorderFactory.createEmptyBorder(7, 7, 7, 7);
	    //TextArea에 lineBorder(검정테두리), emptyBorder(여백)로 구성된 복합 경계선을 설정합니다.
	    textArea.setBorder(BorderFactory.createCompoundBorder(lineBorder, emptyBorder));
	    // TextArea에 스크롤 기능을 추가한 후 Panel안에 집어 넣습니다.
		contentPane.add(textArea);


	    JTextField tf = new JTextField("텍스트 입력", 40);
	    tf.setBounds(635, 574, 337, 32);
	    contentPane.add(tf);
	    //TextField의 높이 조정하고 싶다면 Font Size를 변경하면 된다.
	    // Font명, 굵기, 크기를 설정하고 Font 객체를 생성합니다.
	    Font font = new Font("arian", Font.BOLD, 14);
	    // TextField의 font를 설정합니다.
	    tf.setFont(font);
	    // TextField에 Bevel 경계선을 설정합니다. 하이라이트된 부분을 회색으로 음영부분을 초록색으로 설정합니다.
	    tf.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.gray, Color.gray));
	    
	    JButton btnNewButton = new JButton("다시 시작");
	    btnNewButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    	}
	    });
	    btnNewButton.setBounds(635, 628, 150, 50);
	    contentPane.add(btnNewButton);
	    
	    JButton button = new JButton("게임 종료");
	    button.setBounds(822, 628, 150, 50);
	    contentPane.add(button);
	    
	    textField_11 = new JTextField();
	    textField_11.setText("\uC2B9\uD328\uAD00\uB828 \uBA54\uC2DC\uC9C0 \uCD9C\uB825");
	    textField_11.setBounds(288, 37, 497, 67);
	    contentPane.add(textField_11);
	    textField_11.setColumns(10);
	
	}
}
