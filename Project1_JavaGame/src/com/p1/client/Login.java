package com.p1.client;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.sql.*;

public class Login extends JPanel {
	// 라벨 : ID, PW 입력창 : ID, PW, 버튼 : 로그인, 회원가입
	JLabel la1, la2;
	JTextField tf;
	JPasswordField pf;
	JButton b1, b2;
	Image img;

	Login() {
		// 전체 창 레이아웃
		img = Toolkit.getDefaultToolkit().getImage("img\\Bg_1.png"); // 추후 이미지
																		// 추가
		setLayout(null); // 빈공간, 입력 부분은 별도의 패널로 자유롭게 레이아웃 설정

		// 객체 생성
		la1 = new JLabel("ID"); // 스트링 값으로 텍스트를 출력해줄 수 있음
		la2 = new JLabel("PW");

		tf = new JTextField(); // 빈텍스트 입력창 생성
		pf = new JPasswordField();

		b1 = new JButton("로그인"); // 입력된 스트링 값의 버튼 생성
		b2 = new JButton("회원가입");

		// 배치
		la1.setBounds(820, 300, 50, 30);
		la1.setForeground(new Color(200, 0, 50, 255));
		la1.setFont(new Font("null", Font.BOLD, 15));
		tf.setBounds(850, 300, 230, 30);

		la2.setBounds(820, 340, 50, 30);
		la2.setForeground(new Color(200, 0, 50, 255));
		la2.setFont(new Font("null", Font.BOLD, 15));
		pf.setBounds(850, 340, 230, 30);
		// 비밀번호 입력을 *로 표시되게 처리
		pf.setEchoChar('*');

		b1.setBounds(840, 390, 105, 30);
		b2.setBounds(960, 390, 105, 30);

		// 패널로 부분 배경 사용
		JPanel p = new JPanel();
		p.setBounds(800, 280, 300, 160);
		p.setBorder(new BevelBorder(BevelBorder.RAISED));
		p.setBackground(Color.ORANGE);

		// 각 컴포넌트 및 패널을 클래스 안에 추가
		add(la1);
		add(la2);
		add(tf);
		add(pf);
		add(b1);
		add(b2);
		add(p);
	}

	// 로그인 체크
	public boolean loginCheck(){
		Login login=new Login();
		String idcheck=null;
		String pwcheck = null;
		Connection conn=null;
		String sql1, sql2;
		PreparedStatement pstm1=null, pstm2=null;
		ResultSet rs1=null, rs2=null;
		String pwdb=null;
		
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url="jdbc:oracle:thin:@localhost:1521:ORCL";
			
			//JDBC 연결
			conn=DriverManager.getConnection(url,"scott","tiger");
			
			//테이블 내용을 쿼리문으로 확인 
			sql1="SELECT * FROM pkdb";
							
			//쿼리문 보내기 위한 객체 생성
			pstm1=conn.prepareStatement(sql1);
			
			//쿼리문 실행
			rs1=pstm1.executeQuery();
			
			idcheck=tf.getText();
			// 아이디 체크
			if(idcheck.equals("")){
				JOptionPane.showMessageDialog(login, "아이디가 존재하지 않습니다.\n 다시 입력하세요.");
				tf.setText("");
				tf.requestFocus();	
				return false;
			}
			
			while(rs1.next()){
				if(idcheck.equals(rs1.getString(2))){
					//비밀 번호 입력
					pwcheck=new String(pf.getPassword());
					
					//db에서 비밀 번호값 호출
					sql2="SELECT userpwd FROM pkdb where userid=?";
					pstm2=conn.prepareStatement(sql2);
					pstm2.setString(1, idcheck);
					rs2=pstm2.executeQuery();
					if(rs2.next()){
					pwdb=rs2.getString("userpwd"); 
					}// ResultSet.next() 한번 호출해줘야 값이 생성 if, while 모두 상관 없음. 차이는?	
							
					//비밀번호 일치 체크
					if(pwcheck.equals(pwdb)==false){
						JOptionPane.showMessageDialog(login, "비밀번호가 일치하지 않습니다.");
						pf.setText("");
						pf.requestFocus();				
						return false;
					}else{						
						return true;
					}
				}				
			}

			JOptionPane.showMessageDialog(login, "아이디가 존재하지 않습니다.\n 다시 입력하세요.");
			tf.setText("");
			tf.requestFocus();
			return false;
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{			
			try{
				if(rs2!=null){
					rs2.close();
				}
				if(pstm2!=null){
					pstm2.close();
				}
				if(conn!=null){
					conn.close();
				}	
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	return false;
	}
	
	
	// 이미지 호출하기
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
}
