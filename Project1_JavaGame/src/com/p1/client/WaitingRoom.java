package com.p1.client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.*;
import java.sql.*;

public class WaitingRoom extends JPanel implements ActionListener, KeyListener{
	JTable userList;
	DefaultTableModel userListInfo;
	TableColumn column;
	JTextArea chatting;
	JTextField chattingInput;
	JLabel characterSelector;
	ImageIcon userImage1;
	ImageIcon userImage2;
	/*JLabel userLabel1;
	JLabel userLabel2;
	*/JButton user1;
	JButton user2;
	JButton waitingStart;
	JButton waitingEnd;
	String naming;
	String test;
	String test2 = "스타크래쉬";
	JPanel jp;
	JButton testButton;
	Image[] img = new Image[2];
	EtchedBorder eb1, eb2, eb3, eb4;
	
	/*private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	*/
	//String driver = "oracle.jdbc.driver.OracleDriver";
	// DB에서 스윙 화면으로 테이블 값 가져오기(select), 저장하기(insert), 수정하기(update), 삭제하기(delete)
//	String url = "jdbc:oracle:thin@localhost:1521:ORCL";
	
	public WaitingRoom(){
		
		eb1 = new EtchedBorder(Color.BLACK, Color.WHITE);
		
		userImage1 = new ImageIcon("C:\\dev\\workspace2\\P1_GameProject\\image\\프로그래머_테크트리.jpg");
		userImage2 = new ImageIcon("C:\\dev\\workspace2\\P1_GameProject\\image\\Team1_P1_Flow_Chart_ver1.png");
		
		characterSelector = new JLabel("Character");
		/*userLabel1 = new JLabel(image1, JLabel.CENTER);
		userLabel2 = new JLabel(image2, JLabel.CENTER);
		*/
		String[] col = { "접속 멤버" };
		
		Object[][] row = new Object[0][0];
		// 0 - 첫번째 아이디 1 - 패스워드 2 - 점수 
		// 1 - 두번째 아이디 1 - 패스워드 2 - 점수
		// 2
		
		userListInfo = new DefaultTableModel(row, col){
			
			String sql = "select * from userDB where id = ?";
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public Class getColumnClass(int columnIndex) {
				return getValueAt(0, columnIndex).getClass();
			}
		
			
		};
		
		userList = new JTable(userListInfo);
		userList.setRowHeight(30);
		userList.setShowVerticalLines(false);
		userList.getTableHeader().setReorderingAllowed(false);
		userList.getTableHeader().setResizingAllowed(false);
		
		Object[] data = new Object[1];
		data[0] = idTest(test);
		userListInfo.addRow(data);
		
		Object[] data1 = new Object[1];
		data1[0] = idTest(test2);
		userListInfo.addRow(data1);
		
		
		
		for(int i=0;i<col.length;i++){
			column = userList.getColumnModel().getColumn(i);
			DefaultTableCellRenderer rnd = new DefaultTableCellRenderer();
			if(i==0){
				rnd.setHorizontalAlignment(JLabel.CENTER);
				column.setPreferredWidth(80);
			}
			column.setCellRenderer(rnd);
		}
		
		JScrollPane js = new JScrollPane(userList);
		
		user1 = new JButton(userImage1);
		user2 = new JButton(userImage2);
		
		
		
		setLayout(null);
		js.setBounds(100, 450, 600, 150);
		add(js);
		
		chatting = new JTextArea();
		chattingInput = new JTextField();
		
		waitingStart = new JButton("게임 시작");
		waitingEnd = new JButton("나가기");
		
		characterSelector.setBounds(300, 20, 100, 50);
		user1.setBounds(125, 80, 200, 150);
		user2.setBounds(350, 80, 200, 150);
		
		/*userLabel1.setBounds(125, 80, 200, 150);
		userLabel2.setBounds(350, 80, 200, 150);
		*/
		chatting.setBounds(750, 20, 356, 250);	// 가로, 세로, 넓이(가로), 높이(세로)
		chattingInput.setBounds(750, 270, 356, 50);
		//1280 * 720
		
		waitingStart.setBounds(750, 470, 356, 50);
		waitingEnd.setBounds(750, 550, 356, 50);
		
		/*testButton = new JButton();
		
		testButton.setBounds(125, 150, 200, 150);
		testButton.addActionListener(this);
		add(testButton);*/
		
		add(characterSelector);
		/*add(userLabel1);
		add(userLabel2);
		*/add(user1);
		add(user2);
		add(chatting);
		chatting.setBorder(eb1);
		add(chattingInput);
		add(waitingStart);
		add(waitingEnd);
//		set
		user1.addActionListener(this);
		user2.addActionListener(this);
		chattingInput.addKeyListener(this);
	}
	// 유저1 배열을 만들어서, 유저2 배열을 만들어서, 이미지를 배열로 넣는다.
	// 이미지 배열로 유저1와 유저2의 사진을 넣어준다.
	

	public String idTest(String name){
		naming = name;
		return naming;
		
	}
	
	/*public void select(){
		String sql = "select id from pokedb";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, "scott", "12345");
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				userListInfo.addRow(new Object[]{rs.getString("id")});
			}
		}catch(Exception e){
			
		}finally{
			try{
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
	}*/
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == user1){
			img[0] = userImage1.getImage();
			
		}else if(e.getSource() == user2){
			img[1] = userImage2.getImage();
		}else if(e.getSource() == chattingInput){
			
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyChar() == KeyEvent.VK_ENTER){
//			test = chattingInput.getText();
			chatting.append(test2 + " 님 : " + chattingInput.getText() + "\n");
			chattingInput.setText("");
		}
		
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


		
	
}
