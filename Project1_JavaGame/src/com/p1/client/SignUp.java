package com.p1.client;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.sql.*;

public class SignUp extends JPanel{
	JLabel title;
	JLabel la1;
	JLabel la2;
	JTextField id;
    JPasswordField pw;
	JButton sumit;
	JButton exit;
	Image img, img2;
			
	SignUp(){
		img=Toolkit.getDefaultToolkit().getImage("img\\Bg_2.jpg");
		setLayout(null); 
		
		//생성
		title=new JLabel("<회원가입을 환영합니다!>");
		la1=new JLabel("1. 아이디를 입력하세요.");
		la2=new JLabel("2. 패스워드를 입력하세요.");
		id=new JTextField();
		pw=new JPasswordField();		
		sumit=new JButton("등록");
		exit=new JButton("나가기");
		
		//설정
		title.setForeground(Color.WHITE);
		title.setFont(new Font("null",Font.BOLD,20));
		title.setHorizontalAlignment(0);
		la1.setForeground(Color.WHITE);
		la1.setFont(new Font("null",Font.BOLD,15));
		la2.setForeground(Color.WHITE);
		la2.setFont(new Font("null",Font.BOLD,15));
		pw.setEchoChar('*');
		
		
		//배치
		title.setBounds(850, 230, 250, 30);
		la1.setBounds(850, 270, 250, 30);
		id.setBounds(850, 300, 250, 30);
		la2.setBounds(850, 340, 250, 30);
		pw.setBounds(850, 370, 250, 30);
		sumit.setBounds(860, 420, 105, 30);
		exit.setBounds(990, 420, 105, 30);
				
		//패널로 부분 배경 사용
		JPanel p=new JPanel();
		p.setBounds(820,200,310,280);
		p.setBorder(new BevelBorder(BevelBorder.RAISED));
		p.setBackground(new Color(0, 130, 255, 255));
		
		//컴포넌트 합체
		add(title);
		add(la1);
		add(id);
		add(la2);
		add(pw);
		add(sumit);
		add(exit);
		
		add(p);
	}
		
	//회원 정보를 저장하는 DB생성
	public void infoComfirm(){
		SignUp signUp=new SignUp();
		String user;
		String pwd;
		Connection conn=null;
		String sql1, sql2, sql3;
		PreparedStatement pstm1=null, pstm2=null, pstm3=null;
		ResultSet rs1=null, rs2=null, rs3=null;
			
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
			
			user=id.getText();
			if(user.length()<4 || user.length()>10){  // length 왜 ()가 필요한가?
				JOptionPane.showMessageDialog(signUp, "4글자 이상 10글자 이하의 아이디를 입력해주세요.");
				id.requestFocus();
				return;
			}else{
				// 아이디 중복체크					
				while(rs1.next()){
					if(user.equals(rs1.getString(2))){
						System.out.println(rs1.getString(2));
						JOptionPane.showMessageDialog(signUp, "이미 존재하는 아이디입니다.\n 다시 입력하세요.");
						id.setText("");
						id.requestFocus();
						return;
					}
				}					
			}
			//비밀 번호 입력 및 체크
			pwd=new String(pw.getPassword());
			if(pwd.length()<4 || pwd.length()>10){
				JOptionPane.showMessageDialog(signUp, "4글자 이상 10글자 이하의 패스워드를 입력해주세요.");
				pw.setText("");
				pw.requestFocus();
				return;
			}					
				
			//입력 쿼리문 생성 
			sql2="INSERT INTO pkdb(no, userid, userpwd) values(autonum.NEXTVAL, ?, ?)"; // no 자동 증가 시퀀스
				
			//쿼리문 보내기 위한 객체 생성
			pstm2=conn.prepareStatement(sql2);
								
			//쿼리문에 입력값 세팅
			pstm2.setString(1, user);
			pstm2.setString(2, pwd);
								
			//쿼리문 실행
			pstm2.executeUpdate();
				
			//테이블 내용을 쿼리문으로 확인 
			sql3="SELECT * FROM pkdb";
								
			//쿼리문 보내기 위한 객체 생성
			pstm3=conn.prepareStatement(sql3);
				
			//쿼리문 실행
			rs3=pstm3.executeQuery();			
				
			//실행결과 출력
			while(rs3.next()){
				System.out.println(rs3.getInt(1)+" "+rs3.getString(2)+" "+rs3.getString(3)+" "+rs3.getString(4));
			}
			
		}catch(Exception ex){
				ex.printStackTrace();
		}finally{
			try{
				if(rs3!=null){
					rs3.close();
				}
				if(pstm3!=null){
					pstm3.close();
				}
				if(conn!=null){
					conn.close();
				}	
			}catch(Exception e){
				e.printStackTrace();
			}				
		}
		id.setText("");
		pw.setText("");	
		JOptionPane.showMessageDialog(signUp, "등록되었습니다.^^");			
	}


	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(img,0,0,getWidth(),getHeight(),this);
	}
	
	
	
}
