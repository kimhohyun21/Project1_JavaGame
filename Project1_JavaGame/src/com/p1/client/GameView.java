package com.p1.client;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameView extends JPanel implements Runnable {
	// 필요한 요소, 배경, 플레이어, 포켓몬, 몬스터볼, 폭탄, 폭발
	Image back, player, pokemon, mball, bomb, explo;

	// 플레이어 좌표, 포켓몬 좌표, 몬스터볼 좌표
	int x = 640, y = 480, px = 0, py = 0;

	// 플레이어, 포켓몬 이미지 배열
	String[] player_arr = { "player01_Dft.gif", "player01_Lt.gif", "player01_Rt.gif", "player01_Dft.gif",
			"player01_Lt.gif", "player01_Rt.gif" };
	// ArrayList<Image> pokemon_arr=new ArrayList<>();
	String[] pokemon_arr = { "pkm_00.gif", "pkm_01.gif", "pkm_02.gif", "pkm_03.gif", "pkm_04.gif", "pkm_05.gif",
			"pkm_06.gif", "pkm_07.gif", "pkm_08.gif", "pkm_09.gif", "pkm_10.gif", "pkm_11.gif", "pkm_12.gif",
			"pkm_13.gif", "pkm_14.gif", "pkm_15.gif", "pkm_16.gif", "pkm_17.gif", "pkm_18.gif", "pkm_19.gif",
			"pkm_20.gif", "pkm_21.gif", "pkm_22.gif", "pkm_23.gif", "pkm_24.gif", "pkm_25.gif", "pkm_27.gif",
			"pkm_28.gif", "pkm_29.gif", "pkm_30.gif" };

	// 점수 및 기타 정보 표시 패널 및 버튼
	JPanel p;
	JTextField tf1, tf2, tf3, tf4;
	JButton b1;

	// 점수 및 기타 정보
	String pname; // 플레이어 이름
	int life=5; // 플레이어 목숨
	int score=0; // 플레이어 점수
	int time=1; // 플레이어 시간

	// 이미지를 얻는 메소드 생성
	Toolkit tk = Toolkit.getDefaultToolkit();

	// 쓰레드 생성
	Thread th;

	// 게임 패널 생성
	GameView() {
		// 패널 구성
		setLayout(null);

		// 점수 및 기타 정보 패널 구성
		tf1 = new JTextField(10);
		tf1.setBounds(20, 630, 100, 35);
		tf1.setBackground(Color.WHITE);
		tf1.setEditable(false);

		tf2 = new JTextField(10);
		tf2.setBounds(130, 630, 100, 35);
		tf2.setBackground(Color.WHITE);
		tf2.setEditable(false);

		tf3 = new JTextField(10);
		tf3.setBounds(240, 630, 100, 35);
		tf3.setBackground(Color.WHITE);
		tf3.setEditable(false);

		tf4 = new JTextField(10);
		tf4.setBounds(350, 630, 100, 35);
		tf4.setBackground(Color.WHITE);
		tf4.setEditable(false);

		b1 = new JButton("Exit");

		p = new JPanel();
		p.setBounds(getX(), 620, 1280, 60);
		p.setBackground(new Color(0, 100, 0, 200));
		p.add(b1);

		add(tf1);
		add(tf2);
		add(tf3);
		add(tf4);
		add(p);

		// 배경 이미지 설정
		back = tk.getImage("img\\Bg_6.jpg");

		// 디폴트 이미지 설정
		player = tk.getImage("img\\player01_dft.gif");
		mball=tk.getImage("img\\mball.gif");
		bomb=tk.getImage("img\\pkm_00.gif");
		explo=tk.getImage("img\\explosion.gif");
		
		// 포켓몬 이미지 생성
		pokemonSetImage();

		// 쓰레드 생성
		th = new Thread();
		th.start();

	}

	// 쓰레드 구동
	public void run() {
		try {
			Thread.sleep(10);
			repaint();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 플레이어 그리기 설정
	public void playerSetImage(int no) {
		player = tk.getImage("img\\" + player_arr[no]);
	}

	// 포켓몬 그리기 설정
	public void pokemonSetImage() {
		int i = (int) (Math.random() * 30);
		int j = (int) (Math.random() * 77);

		// 포켓몬 이미지 배정
		pokemon = tk.getImage("img\\" + pokemon_arr[i]);
		// 포켓몬 x좌표 배정
		px = 10 + j * 15;
	}

	// 그리기 메소드
	public void paintComponent(Graphics g) {
		// 배경 이미지 그리기
		g.drawImage(back, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(player, x, y, player.getWidth(this), player.getHeight(this), this);

		for (int i = 0; i < 1; i++) {
			if (py < 700) {
				py += 1 + i;
			} else {
				py = 0;
			}
			if (py >= 690) {
				i = (int) (Math.random() * 30);
				int j = (int) (Math.random() * 77);

				// 포켓몬 이미지 재배정
				pokemon = tk.getImage("img\\" + pokemon_arr[i]);
				// 포켓몬 x좌표 재배정
				px = 10 + j * 15;
			}			
			g.drawImage(pokemon, px, py, pokemon.getWidth(this), pokemon.getHeight(this), this);
			
			//플레이어와 포켓몬의 이미지 크기 값
			int w=player.getWidth(this);
			int h=player.getHeight(this);
			int pw=pokemon.getWidth(this);
			int ph=pokemon.getHeight(this);
			
			//플레이어의 이미지 중앙 좌표와 포켓몬 이미지의 범위를 매칭
			if ((x+(w/2) > px && x+(w/2) < px+pw) && (y+(h/2) > py+(ph/5) && y+(h/2) < py+ph)) {
				//냐옹이를 만나면 폭발 이미지로 변경
				if (pokemon==bomb) {						
					pokemon = tk.getImage("img\\explosion.gif");
				}else if((pokemon!=bomb)&&(pokemon!=explo)){
					//다른 포켓몬이 잡히면 몬스터 볼로 이미지 변경
					pokemon = tk.getImage("img\\mball.gif");
				}
			}
			
			//몬스터 볼이 500이상 내려가면 사라지면서 스코어 카운팅
			if (pokemon==mball && py>y+10) {				
				score++;
				py += 1 + i;
				pokemon = tk.getImage(" ");
				
			}
			
			//폭발이 500이상 내려가면 사라지면서 목숨 삭감
			if (pokemon==explo && py>y+10) {				
				life--;
				py += 1 + i;
				pokemon = tk.getImage(" ");
				
			}
			//텍스트 출력 및 게임 종료 프로세스 필요
		}	

		check();
	}


	public void check() {
		
		System.out.println("플레이어x좌표 : " + x + " 플레이어y좌표 : " + y);
		System.out.println("포켓몬 x좌표 : " + px + " 포켓몬 y좌표 : " + py);
		System.out.println("score : " + score);
		System.out.println("life : " + life);
	}

}