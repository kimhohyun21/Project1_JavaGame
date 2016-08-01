package com.p1.client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ScoreBoard extends JPanel {
	JLabel la1;
	
	ScoreBoard(){
		la1=new JLabel("작업 부탁합니다.");
		la1.setFont(new Font("맑은고딕", Font.BOLD, 50));
		add(la1);
	}
}
