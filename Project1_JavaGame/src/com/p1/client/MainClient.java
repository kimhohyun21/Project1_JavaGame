package com.p1.client;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class MainClient extends JFrame implements ActionListener, KeyListener{
	//사용할 클래스를 멤버변수로 선언
	Login login=new Login();
	SignUp signUp=new SignUp();
	GameView gv=new GameView();
	WaitingRoom wr=new WaitingRoom();
	CardLayout card=new CardLayout();
	ScoreBoard score=new ScoreBoard();
	
	//현재 클래스의 기능을 정의
	MainClient(){		
		setTitle("Pokemon Get! 신나게 포켓몬을 잡아보아요~!");
		setLayout(card);
		add("LOG", login);
		add("SU", signUp);
		add("WR", wr);
		add("GV", gv);
		//add("SB", score);
		setSize(1280, 720);
		setVisible(true);
		
		// 윈도우창의 x표시를 눌렀을 때 자동 터미널 가능
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		// 윈도우 창을 가운데 뜨게 하는 설정
		setLocationRelativeTo(null);
		
		//버튼에 액션 추가
		login.b1.addActionListener(this);
		login.b2.addActionListener(this);
		signUp.sumit.addActionListener(this);
		signUp.exit.addActionListener(this);
		wr.waitingStart.addActionListener(this);
		gv.b1.addActionListener(this);		
		
		//키리스너 추가
		gv.addKeyListener(this);
		
		
	
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==login.b1){
			//if(login.loginCheck()==true){
				card.show(getContentPane(),"WR");
				//System.out.println(login.tf.getText());
			//}else{
				//return;
			//}
		}else if(e.getSource()==login.b2){
			card.show(getContentPane(),"SU");
		}else if(e.getSource()==signUp.exit){
			card.show(getContentPane(), "LOG");
		}else if(e.getSource()==signUp.sumit){
			signUp.infoComfirm();
		}else if(e.getSource()==wr.waitingStart){
			card.show(getContentPane(), "GV");
			//키리스너 포커스 점령, 키리스너 작동 조건
			gv.requestFocus();
		}else if(e.getSource()==gv.b1){
			score.setVisible(true);
		}			
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT:
			gv.playerSetImage(1);
			gv.x-=15;
			if(gv.x<0){
				gv.x=0;
			}break;
		case KeyEvent.VK_RIGHT:
			gv.playerSetImage(2);
			gv.x+=15;
			if(gv.x>=1180){
				gv.x=1180;
			}break;		
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		gv.playerSetImage(0);	
		
	}
	
	public static void main(String[] args){
		new MainClient();
	}

}