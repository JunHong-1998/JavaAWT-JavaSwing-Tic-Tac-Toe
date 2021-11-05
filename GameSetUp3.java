import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class GameSetUp3 implements ActionListener,KeyListener,Runnable{		//LEE WENG HONG & LIEW MING KAI
	JPanel GamePanel3 = new JPanel();
	JLabel BackgroundContinue, Victory, Defeat, Draw;
	JLabel  ply_turn1, ply_turn2, ply_turn, winner_turn1, winner_turn2, winner_turn, winner_draw;
	JButton N7, N8, N9, N4, N5, N6, N1, N2, N3, Continue;
	JLabel Ply1_name, Ply2_name;
	JLabel Ply1_score1A, Ply1_score2A, Ply1_score3A, Ply1_score1B, Ply1_score2B, Ply1_score3B;
	JLabel Ply2_score1A, Ply2_score2A, Ply2_score3A, Ply2_score1B, Ply2_score2B, Ply2_score3B;
	JLabel Time1, Time2, Time3;
	JLabel X_symbol1, X_symbol2, X_symbol3, X_symbol4, X_symbol5, X_symbol6, X_symbol7, X_symbol8, X_symbol9;
	JLabel WinX_symbol1, WinX_symbol2, WinX_symbol3, WinX_symbol4, WinX_symbol5, WinX_symbol6, WinX_symbol7, WinX_symbol8, WinX_symbol9;
	JLabel O_symbol1, O_symbol2, O_symbol3, O_symbol4, O_symbol5, O_symbol6, O_symbol7, O_symbol8, O_symbol9;
	JLabel WinO_symbol1, WinO_symbol2, WinO_symbol3, WinO_symbol4, WinO_symbol5, WinO_symbol6, WinO_symbol7, WinO_symbol8, WinO_symbol9;
	JLabel SP_Background;JTextField NickP1;

	private int WinCounter=1, match=0, move=9, Winner=0, ANS;
	int ans_defense, ans_attack;
	int k,l;
	Random rand = new Random();
	boolean turn=true, stop=false;
	Font f = new Font("Algerian", Font.ITALIC, 24);
	String[] ans = new String[10];
	int[] PC_ans = new int[5];
	int[] PLY_ans = new int[5];
	int[] error = new int[10];


	public GameSetUp3(){
		GamePanel3();
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			Ply1_name.setText(NickP1.getText());
			ply_turn1.setText(NickP1.getText());
			winner_turn1.setText(NickP1.getText());
			NickP1.setVisible(false);
			SP_Background.setVisible(false);
			BackgroundContinue.setVisible(true);
			Continue.setEnabled(true);
			}
		}
	public void keyReleased(KeyEvent e) { }
    public void keyTyped(KeyEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == N1){
			forN1();
		}
		if(e.getSource() == N2){
			forN2();
		}
		if(e.getSource() == N3){
			forN3();
		}
		if(e.getSource() == N4){
			forN4();
		}
		if(e.getSource() == N5){
			forN5();
		}
		if(e.getSource() == N6){
			forN6();
		}
		if(e.getSource() == N7){
			forN7();
		}
		if(e.getSource() == N8){
			forN8();
		}
		if(e.getSource() == N9){
			forN9();
		}
		Thread t=new Thread(this);
		if(e.getSource() == Continue){
System.out.println("match : " +match);
			if(match ==0){
				Clean();
				t.start();
			}
			else if(match==1){
				Clean();
				t.start();
			while(this.ANS != 1 && this.ANS != 3 && this.ANS != 7 && this.ANS != 9 ){		//1st step of PC move must be at CORNER
				this.ANS = rand.nextInt(9)+1;
				System.out.println("Trying ... : "+this.ANS);
			}
			PC_ans[0] = this.ANS;		//PC_ans1
			error[this.ANS]=this.ANS;
			PC_inner_move(this.ANS);
			Progress();
			}
			else if (match == 2){
				Clean();
				t.start();
			}
			else if (match == 3 && Winner >= 1){
				Victory.setVisible(true);
				Continue.setEnabled(false);
			}
			else if (match == 3 && Winner <= -1){
				Defeat.setVisible(true);
				Continue.setEnabled(false);
			}
			else if (match == 3 && Winner == 0){
				Draw.setVisible(true);
				Continue.setEnabled(false);
			}
			else if (match == 4){
				//dispose
			}
			match++;
		}
	}


	public void GamePanel3(){
		GamePanel3.setLayout(null);
		SP_Background = new JLabel(new ImageIcon("SP_Background.jpg"));
		SP_Background.setBounds(0, 0, 500, 800);
		NickP1 = new JTextField("Player 1");
		NickP1.setFont(f);
		NickP1.setBounds(150, 275, 200, 35);
		NickP1.addKeyListener(this);

		BackgroundContinue = new JLabel(new ImageIcon("BackgroundContinue.png"));
		BackgroundContinue.setBounds(0, 0, 500, 800);
		BackgroundContinue.setVisible(true);
		Continue = new JButton();
		Continue.setBounds(0, 0, 500, 800);
		Continue.setContentAreaFilled(false);
		Continue.setBorder(BorderFactory.createEmptyBorder());
		Continue.addActionListener(this);
		Continue.setEnabled(false);

		Victory = new JLabel(new ImageIcon("Victory.jpg"));
		Victory.setBounds(0, 0, 500, 800);
		Victory.setVisible(false);
		Defeat = new JLabel(new ImageIcon("Defeat.jpg"));
		Defeat.setBounds(0, 0, 500, 800);
		Defeat.setVisible(false);
		Draw = new JLabel(new ImageIcon("Draw.jpg"));
		Draw.setBounds(0, 0, 500, 800);
		Draw.setVisible(false);

		JLabel Background5 = new JLabel(new ImageIcon("Background5.jpg"));
		Background5.setBounds(0, 0, 500, 800);

/////////////////////////////////// T u r n ///////////////////////////////
		winner_turn = new JLabel("WINNER : ");
		winner_turn.setFont(new Font("Algerian", Font.BOLD, 32));
		winner_turn.setForeground(Color.RED);
		winner_turn.setBounds(100,25,400,50);
		winner_turn.setVisible(false);

		winner_turn1 = new JLabel();
		winner_turn1.setFont(new Font("Algerian", Font.BOLD, 32));
		winner_turn1.setForeground(Color.RED);
		winner_turn1.setBounds(250,25,400,50);
		winner_turn1.setVisible(false);
		winner_turn2 = new JLabel("PC");
		winner_turn2.setFont(new Font("Algerian", Font.BOLD, 32));
		winner_turn2.setForeground(Color.RED);
		winner_turn2.setBounds(300,25,400,50);
		winner_turn2.setVisible(false);
		winner_draw = new JLabel("DRAW");
		winner_draw.setFont(new Font("Algerian", Font.BOLD, 32));
		winner_draw.setForeground(Color.RED);
		winner_draw.setBounds(200,25,200,50);
		winner_draw.setVisible(false);

		ply_turn = new JLabel(" turn...");
		ply_turn.setFont(new Font("Algerian", Font.BOLD, 32));
		ply_turn.setForeground(Color.RED);
		ply_turn.setBounds(280,25,300,50);			//here
		ply_turn.setVisible(false);
		ply_turn1 = new JLabel();
		ply_turn1.setFont(new Font("Algerian", Font.BOLD, 32));
		ply_turn1.setForeground(Color.RED);
		ply_turn1.setBounds(100,25,300,50);
		ply_turn1.setVisible(false);
		ply_turn2 = new JLabel("PC turn...");
		ply_turn2.setFont(new Font("Algerian", Font.BOLD, 32));
		ply_turn2.setForeground(Color.RED);
		ply_turn2.setBounds(100,25,300,50);
		ply_turn2.setVisible(false);


		N7 = new JButton();
		N7.setBounds(25, 100, 150, 150);
		N7.setContentAreaFilled(false);
		//N7.setBorder(BorderFactory.createEmptyBorder());
		N7.addActionListener(this);
		X_symbol7 = new JLabel(new ImageIcon("X symbol.png"));
		X_symbol7.setBounds(25, 100, 150, 150);
		X_symbol7.setVisible(false);
		WinX_symbol7 = new JLabel(new ImageIcon("WinX symbol.png"));
		WinX_symbol7.setBounds(25, 100, 150, 150);
		WinX_symbol7.setVisible(false);
		O_symbol7 = new JLabel(new ImageIcon("O symbol.png"));
		O_symbol7.setBounds(25, 100, 150, 150);
		O_symbol7.setVisible(false);
		WinO_symbol7 = new JLabel(new ImageIcon("WinO symbol.png"));
		WinO_symbol7.setBounds(25, 100, 150, 150);
		WinO_symbol7.setVisible(false);

		N8 = new JButton();
		N8.setBounds(175, 100, 150, 150);
		N8.setContentAreaFilled(false);
		//N8.setBorder(BorderFactory.createEmptyBorder());
		N8.addActionListener(this);
		X_symbol8 = new JLabel(new ImageIcon("X symbol.png"));
		X_symbol8.setBounds(175, 100, 150, 150);
		X_symbol8.setVisible(false);
		WinX_symbol8 = new JLabel(new ImageIcon("WinX symbol.png"));
		WinX_symbol8.setBounds(175, 100, 150, 150);
		WinX_symbol8.setVisible(false);
		O_symbol8 = new JLabel(new ImageIcon("O symbol.png"));
		O_symbol8.setBounds(175, 100, 150, 150);
		O_symbol8.setVisible(false);
		WinO_symbol8 = new JLabel(new ImageIcon("WinO symbol.png"));
		WinO_symbol8.setBounds(175, 100, 150, 150);
		WinO_symbol8.setVisible(false);

		N9 = new JButton();
		N9.setBounds(325, 100, 150, 150);
		N9.setContentAreaFilled(false);
		//N9.setBorder(BorderFactory.createEmptyBorder());
		N9.addActionListener(this);
		X_symbol9 = new JLabel(new ImageIcon("X symbol.png"));
		X_symbol9.setBounds(325, 100, 150, 150);
		X_symbol9.setVisible(false);
		WinX_symbol9 = new JLabel(new ImageIcon("WinX symbol.png"));
		WinX_symbol9.setBounds(325, 100, 150, 150);
		WinX_symbol9.setVisible(false);
		O_symbol9 = new JLabel(new ImageIcon("O symbol.png"));
		O_symbol9.setBounds(325, 100, 150, 150);
		O_symbol9.setVisible(false);
		WinO_symbol9 = new JLabel(new ImageIcon("WinO symbol.png"));
		WinO_symbol9.setBounds(325, 100, 150, 150);
		WinO_symbol9.setVisible(false);


		N4 = new JButton();
		N4.setBounds(25, 250, 150, 150);
		N4.setContentAreaFilled(false);
		//N4.setBorder(BorderFactory.createEmptyBorder());
		N4.addActionListener(this);
		X_symbol4 = new JLabel(new ImageIcon("X symbol.png"));
		X_symbol4.setBounds(25, 250, 150, 150);
		X_symbol4.setVisible(false);
		WinX_symbol4 = new JLabel(new ImageIcon("WinX symbol.png"));
		WinX_symbol4.setBounds(25, 250, 150, 150);
		WinX_symbol4.setVisible(false);
		O_symbol4 = new JLabel(new ImageIcon("O symbol.png"));
		O_symbol4.setBounds(25, 250, 150, 150);
		O_symbol4.setVisible(false);
		WinO_symbol4 = new JLabel(new ImageIcon("WinO symbol.png"));
		WinO_symbol4.setBounds(25, 250, 150, 150);
		WinO_symbol4.setVisible(false);

		N5 = new JButton();
		N5.setBounds(175, 250, 150, 150);
		N5.setContentAreaFilled(false);
		//N5.setBorder(BorderFactory.createEmptyBorder());
		N5.addActionListener(this);
		X_symbol5 = new JLabel(new ImageIcon("X symbol.png"));
		X_symbol5.setBounds(175, 250, 150, 150);
		X_symbol5.setVisible(false);
		WinX_symbol5 = new JLabel(new ImageIcon("WinX symbol.png"));
		WinX_symbol5.setBounds(175, 250, 150, 150);
		WinX_symbol5.setVisible(false);
		O_symbol5 = new JLabel(new ImageIcon("O symbol.png"));
		O_symbol5.setBounds(175, 250, 150, 150);
		O_symbol5.setVisible(false);
		WinO_symbol5 = new JLabel(new ImageIcon("WinO symbol.png"));
		WinO_symbol5.setBounds(175, 250, 150, 150);
		WinO_symbol5.setVisible(false);

		N6 = new JButton();
		N6.setBounds(325, 250, 150, 150);
		N6.setContentAreaFilled(false);
		//N6.setBorder(BorderFactory.createEmptyBorder());
		N6.addActionListener(this);
		X_symbol6 = new JLabel(new ImageIcon("X symbol.png"));
		X_symbol6.setBounds(325, 250, 150, 150);
		X_symbol6.setVisible(false);
		WinX_symbol6 = new JLabel(new ImageIcon("WinX symbol.png"));
		WinX_symbol6.setBounds(325, 250, 150, 150);
		WinX_symbol6.setVisible(false);
		O_symbol6 = new JLabel(new ImageIcon("O symbol.png"));
		O_symbol6.setBounds(325, 250, 150, 150);
		O_symbol6.setVisible(false);
		WinO_symbol6 = new JLabel(new ImageIcon("WinO symbol.png"));
		WinO_symbol6.setBounds(325, 250, 150, 150);
		WinO_symbol6.setVisible(false);

		N1 = new JButton();
		N1.setBounds(25, 400, 150, 150);
		N1.setContentAreaFilled(false);
		//N1.setBorder(BorderFactory.createEmptyBorder());
		N1.addActionListener(this);
		X_symbol1 = new JLabel(new ImageIcon("X symbol.png"));
		X_symbol1.setBounds(25, 400, 150, 150);
		X_symbol1.setVisible(false);
		WinX_symbol1 = new JLabel(new ImageIcon("WinX symbol.png"));
		WinX_symbol1.setBounds(25, 400, 150, 150);
		WinX_symbol1.setVisible(false);
		O_symbol1 = new JLabel(new ImageIcon("O symbol.png"));
		O_symbol1.setBounds(25, 400, 150, 150);
		O_symbol1.setVisible(false);
		WinO_symbol1 = new JLabel(new ImageIcon("WinO symbol.png"));
		WinO_symbol1.setBounds(25, 400, 150, 150);
		WinO_symbol1.setVisible(false);

		N2 = new JButton();
		N2.setBounds(175, 400, 150, 150);
		N2.setContentAreaFilled(false);
		//N2.setBorder(BorderFactory.createEmptyBorder());
		N2.addActionListener(this);
		X_symbol2 = new JLabel(new ImageIcon("X symbol.png"));
		X_symbol2.setBounds(175, 400, 150, 150);
		X_symbol2.setVisible(false);
		WinX_symbol2 = new JLabel(new ImageIcon("WinX symbol.png"));
		WinX_symbol2.setBounds(175, 400, 150, 150);
		WinX_symbol2.setVisible(false);
		O_symbol2 = new JLabel(new ImageIcon("O symbol.png"));
		O_symbol2.setBounds(175, 400, 150, 150);
		O_symbol2.setVisible(false);
		WinO_symbol2 = new JLabel(new ImageIcon("WinO symbol.png"));
		WinO_symbol2.setBounds(175, 400, 150, 150);
		WinO_symbol2.setVisible(false);

		N3 = new JButton();
		N3.setBounds(325, 400, 150, 150);
		N3.setContentAreaFilled(false);
		//N3.setBorder(BorderFactory.createEmptyBorder());
		N3.addActionListener(this);
		X_symbol3 = new JLabel(new ImageIcon("X symbol.png"));
		X_symbol3.setBounds(325, 400, 150, 150);
		X_symbol3.setVisible(false);
		WinX_symbol3 = new JLabel(new ImageIcon("WinX symbol.png"));
		WinX_symbol3.setBounds(325, 400, 150, 150);
		WinX_symbol3.setVisible(false);
		O_symbol3 = new JLabel(new ImageIcon("O symbol.png"));
		O_symbol3.setBounds(325, 400, 150, 150);
		O_symbol3.setVisible(false);
		WinO_symbol3 = new JLabel(new ImageIcon("WinO symbol.png"));
		WinO_symbol3.setBounds(325, 400, 150, 150);
		WinO_symbol3.setVisible(false);

		N1.setEnabled(false);
		N2.setEnabled(false);
		N3.setEnabled(false);
		N4.setEnabled(false);
		N5.setEnabled(false);
		N6.setEnabled(false);
		N7.setEnabled(false);
		N8.setEnabled(false);
		N9.setEnabled(false);

/////////////////////////////////////////   N A M E & S C O R E   //////////////////////////////////////////////
		Ply1_name = new JLabel ();
		Ply1_name.setFont(new Font("Algerian", Font.BOLD, 24));
		Ply1_name.setForeground(Color.WHITE);
		Ply1_name.setBounds(50, 600, 125, 25);
		Ply2_name = new JLabel ("PC");
		Ply2_name.setFont(new Font("Algerian", Font.BOLD, 24));
		Ply2_name.setForeground(Color.WHITE);
		Ply2_name.setBounds(380, 600, 125, 25);

		Ply1_score1A = new JLabel ("0");
		Ply1_score1A.setFont(new Font("Algerian", Font.BOLD, 24));
		Ply1_score1A.setForeground(Color.WHITE);
		Ply1_score1A.setBounds(125, 658, 25, 33);
		Ply1_score1A.setVisible(false);
		Ply1_score1B = new JLabel ("1");
		Ply1_score1B.setFont(new Font("Algerian", Font.BOLD, 24));
		Ply1_score1B.setForeground(Color.WHITE);
		Ply1_score1B.setBounds(125, 658, 25, 33);
		Ply1_score1B.setVisible(false);

		Ply2_score1A = new JLabel ("0");
		Ply2_score1A.setFont(new Font("Algerian", Font.BOLD, 24));
		Ply2_score1A.setForeground(Color.WHITE);
		Ply2_score1A.setBounds(355, 658, 25, 33);
		Ply2_score1A.setVisible(false);
		Ply2_score1B = new JLabel ("1");
		Ply2_score1B.setFont(new Font("Algerian", Font.BOLD, 24));
		Ply2_score1B.setForeground(Color.WHITE);
		Ply2_score1B.setBounds(355, 658, 25, 33);
		Ply2_score1B.setVisible(false);

		Ply1_score2A = new JLabel ("0");
		Ply1_score2A.setFont(new Font("Algerian", Font.BOLD, 24));
		Ply1_score2A.setForeground(Color.WHITE);
		Ply1_score2A.setBounds(125, 705, 25, 33);
		Ply1_score2A.setVisible(false);
		Ply1_score2B = new JLabel ("1");
		Ply1_score2B.setFont(new Font("Algerian", Font.BOLD, 24));
		Ply1_score2B.setForeground(Color.WHITE);
		Ply1_score2B.setBounds(125, 705, 25, 33);
		Ply1_score2B.setVisible(false);

		Ply2_score2A = new JLabel ("0");
		Ply2_score2A.setFont(new Font("Algerian", Font.BOLD, 24));
		Ply2_score2A.setForeground(Color.WHITE);
		Ply2_score2A.setBounds(355, 705, 25, 33);
		Ply2_score2A.setVisible(false);
		Ply2_score2B = new JLabel ("1");
		Ply2_score2B.setFont(new Font("Algerian", Font.BOLD, 24));
		Ply2_score2B.setForeground(Color.WHITE);
		Ply2_score2B.setBounds(355, 705, 25, 33);
		Ply2_score2B.setVisible(false);

		Ply1_score3A =new JLabel ("0");
		Ply1_score3A.setFont(new Font("Algerian", Font.BOLD, 24));
		Ply1_score3A.setForeground(Color.WHITE);
		Ply1_score3A.setBounds(125, 750, 25, 33);
		Ply1_score3A.setVisible(false);
		Ply1_score3B =new JLabel ("1");
		Ply1_score3B.setFont(new Font("Algerian", Font.BOLD, 24));
		Ply1_score3B.setForeground(Color.WHITE);
		Ply1_score3B.setBounds(125, 750, 25, 33);
		Ply1_score3B.setVisible(false);

		Ply2_score3A = new JLabel ("0");
		Ply2_score3A.setFont(new Font("Algerian", Font.BOLD, 24));
		Ply2_score3A.setForeground(Color.WHITE);
		Ply2_score3A.setBounds(355, 750, 25, 33);
		Ply2_score3A.setVisible(false);
		Ply2_score3B = new JLabel ("1");
		Ply2_score3B.setFont(new Font("Algerian", Font.BOLD, 24));
		Ply2_score3B.setForeground(Color.WHITE);
		Ply2_score3B.setBounds(355, 750, 25, 33);
		Ply2_score3B.setVisible(false);

		Time1 = new JLabel ();
		Time1.setFont(new Font("Algerian", Font.BOLD, 20));
		Time1.setForeground(Color.RED);
		Time1.setBounds(215, 658, 200, 33);
		Time2 = new JLabel ();
		Time2.setFont(new Font("Algerian", Font.BOLD, 20));
		Time2.setForeground(Color.RED);
		Time2.setBounds(215, 705, 75, 33);
		Time3 = new JLabel ();
		Time3.setFont(new Font("Algerian", Font.BOLD, 20));
		Time3.setForeground(Color.RED);
		Time3.setBounds(215, 750, 75, 33);

		GamePanel3.add(SP_Background);
		GamePanel3.add(NickP1);
		GamePanel3.add(Victory);
		GamePanel3.add(Defeat);
		GamePanel3.add(Draw);
		GamePanel3.add(BackgroundContinue);

		GamePanel3.add(winner_turn);
		GamePanel3.add(winner_turn1);
		GamePanel3.add(winner_turn2);
		GamePanel3.add(winner_draw);
		GamePanel3.add(ply_turn);
		GamePanel3.add(ply_turn1);
		GamePanel3.add(ply_turn2);

		GamePanel3.add(N7);
		GamePanel3.add(N8);
		GamePanel3.add(N9);
		GamePanel3.add(N4);
		GamePanel3.add(N5);
		GamePanel3.add(N6);
		GamePanel3.add(N1);
		GamePanel3.add(N2);
		GamePanel3.add(N3);

		GamePanel3.add(WinX_symbol1);
		GamePanel3.add(WinO_symbol1);
		GamePanel3.add(WinX_symbol2);
		GamePanel3.add(WinO_symbol2);
		GamePanel3.add(WinX_symbol3);
		GamePanel3.add(WinO_symbol3);
		GamePanel3.add(WinX_symbol4);
		GamePanel3.add(WinO_symbol4);
		GamePanel3.add(WinX_symbol5);
		GamePanel3.add(WinO_symbol5);
		GamePanel3.add(WinX_symbol6);
		GamePanel3.add(WinO_symbol6);
		GamePanel3.add(WinX_symbol7);
		GamePanel3.add(WinO_symbol7);
		GamePanel3.add(WinX_symbol8);
		GamePanel3.add(WinO_symbol8);
		GamePanel3.add(WinX_symbol9);
		GamePanel3.add(WinO_symbol9);
		GamePanel3.add(X_symbol1);
		GamePanel3.add(O_symbol1);
		GamePanel3.add(X_symbol2);
		GamePanel3.add(O_symbol2);
		GamePanel3.add(X_symbol3);
		GamePanel3.add(O_symbol3);
		GamePanel3.add(X_symbol4);
		GamePanel3.add(O_symbol4);
		GamePanel3.add(X_symbol5);
		GamePanel3.add(O_symbol5);
		GamePanel3.add(X_symbol6);
		GamePanel3.add(O_symbol6);
		GamePanel3.add(X_symbol7);
		GamePanel3.add(O_symbol7);
		GamePanel3.add(X_symbol8);
		GamePanel3.add(O_symbol8);
		GamePanel3.add(X_symbol9);
		GamePanel3.add(O_symbol9);

		GamePanel3.add(Ply1_name);
		GamePanel3.add(Ply2_name);
		GamePanel3.add(Ply1_score1A);
		GamePanel3.add(Ply2_score1A);
		GamePanel3.add(Ply1_score2A);
		GamePanel3.add(Ply2_score2A);
		GamePanel3.add(Ply1_score3A);
		GamePanel3.add(Ply2_score3A);
		GamePanel3.add(Ply1_score1B);
		GamePanel3.add(Ply2_score1B);
		GamePanel3.add(Ply1_score2B);
		GamePanel3.add(Ply2_score2B);
		GamePanel3.add(Ply1_score3B);
		GamePanel3.add(Ply2_score3B);
		GamePanel3.add(Time1);
		GamePanel3.add(Time2);
		GamePanel3.add(Time3);

		GamePanel3.add(Continue);

		GamePanel3.add(Background5);

		GamePanel3.setSize(500,800);

	}

	public Boolean setTurn(){
System.out.println("BOOLEAN Turn : " +turn);
		if(turn){
			ply_turn2.setVisible(true);
			ply_turn1.setVisible(false);
			turn = false;
			WinCounter = 1;
System.out.println("WinCounter : " +WinCounter);
System.out.println("Turn : P1");
		}
		else{
			ply_turn1.setVisible(true);
			ply_turn2.setVisible(false);
			turn = true;
			WinCounter = -1;
System.out.println("WinCounter : " +WinCounter);
System.out.println("Turn : PC / P2");
		}
		return turn;
	}

	public void Progress(){
		if(move>=0){
			setTurn();}
		WIN_condition(ans);
	}

	public void Ply_ans(int ans){			//used to record player answer
System.out.println("Recording PLY move");
		if( match == 1 || match == 3){
			if(move == 9){
				PLY_ans[0]=ans;
				error[ans]=ans;
			}
			else if(move == 7){
				PLY_ans[1]=ans;
				error[ans]=ans;
			}
			else if(move == 5){
				PLY_ans[2]=ans;
				error[ans]=ans;
			}
			else if(move == 3){
				PLY_ans[3]=ans;
				error[ans]=ans;
			}
			else if(move == 1){
				PLY_ans[4]=ans;
				error[ans]=ans;
			}
		}
		else if(match == 2){
System.out.println("Match 2");
			if(move == 8){
				PLY_ans[0]=ans;
				error[ans]=ans;
			}
			else if(move == 6){
				PLY_ans[1]=ans;
				error[ans]=ans;
			}
			else if(move == 4){
				PLY_ans[2]=ans;
				error[ans]=ans;
			}
			else if(move == 2){
				PLY_ans[3]=ans;
				error[ans]=ans;
			}
		}
		this.move--;
System.out.println("Player's move :" +ans);
	}

	public void PC_move_byMatch(){
		if( match==1 && move>=2 || match ==3 && move>=2 ){
			PC_move1();
		}
		else
		if(match == 2 && move>=1){
			PC_move2();
		}
	}

	public void forN1(){
		O_symbol1.setVisible(true);
		ans[1]="O";
		this.ANS=1;
		Ply_ans(this.ANS);
		N1.setEnabled(false);
		Progress();
		PC_move_byMatch();
		Progress();
	}

	public void forN2(){
		O_symbol2.setVisible(true);
		ans[2]="O";
		this.ANS=2;
		Ply_ans(this.ANS);
		N2.setEnabled(false);
		Progress();
		PC_move_byMatch();
		Progress();
	}

	public void forN3(){
		O_symbol3.setVisible(true);
		ans[3]="O";
		this.ANS=3;
		Ply_ans(this.ANS);
		N3.setEnabled(false);
		Progress();
		PC_move_byMatch();
		Progress();
	}

	public void forN4(){
		O_symbol4.setVisible(true);
		ans[4]="O";
		this.ANS=4;
		Ply_ans(this.ANS);
		N4.setEnabled(false);
		Progress();
		PC_move_byMatch();
		Progress();
	}

	public void forN5(){
		O_symbol5.setVisible(true);
		ans[5]="O";
		this.ANS=5;
		Ply_ans(this.ANS);
		N5.setEnabled(false);
		Progress();
		PC_move_byMatch();
		Progress();
	}

	public void forN6(){
		O_symbol6.setVisible(true);
		ans[6]="O";
		this.ANS=6;
		Ply_ans(this.ANS);
		N6.setEnabled(false);
		Progress();
		PC_move_byMatch();
		Progress();
	}

	public void forN7(){
		O_symbol7.setVisible(true);
		ans[7]="O";
		this.ANS=7;
		Ply_ans(this.ANS);
		N7.setEnabled(false);
		Progress();
		PC_move_byMatch();
		Progress();
	}

	public void forN8(){
		O_symbol8.setVisible(true);
		ans[8]="O";
		this.ANS=8;
		Ply_ans(this.ANS);
		N8.setEnabled(false);
		Progress();
		PC_move_byMatch();
		Progress();
	}

	public void forN9(){
		O_symbol9.setVisible(true);
		ans[9]="O";
		this.ANS=9;
		Ply_ans(this.ANS);
		N9.setEnabled(false);
		Progress();
		PC_move_byMatch();
		Progress();
	}

	public void WIN_condition(String[] ans){
		if(this.move>=0){
System.out.println("PassBy Win_condition");
System.out.println(Arrays.toString(ans));
		if(ans[1]=="X" && ans[2]=="X" && ans[3]=="X" || ans[1]=="O" && ans[2]=="O" && ans[3]=="O" || ans[4]=="X" && ans[5]=="X" && ans[6]=="X" || ans[4]=="O" && ans[5]=="O" && ans[6]=="O"
		|| ans[7]=="X" && ans[8]=="X" && ans[9]=="X" || ans[7]=="O" && ans[8]=="O" && ans[9]=="O" || ans[1]=="X" && ans[4]=="X" && ans[7]=="X" || ans[1]=="O" && ans[4]=="O" && ans[7]=="O"
		|| ans[2]=="X" && ans[5]=="X" && ans[8]=="X" || ans[2]=="O" && ans[5]=="O" && ans[8]=="O" || ans[3]=="X" && ans[6]=="X" && ans[9]=="X" || ans[3]=="O" && ans[6]=="O" && ans[9]=="O"
		|| ans[1]=="X" && ans[5]=="X" && ans[9]=="X" || ans[1]=="O" && ans[5]=="O" && ans[9]=="O" || ans[3]=="X" && ans[5]=="X" && ans[7]=="X" || ans[3]=="O" && ans[5]=="O" && ans[7]=="O"
		){
System.out.println("Running Win_condition");
System.out.println(Arrays.toString(ans));
		this.move=-1;
				if(ans[1]=="X" && ans[2]=="X" && ans[3]=="X"){
					WinX_symbol1.setVisible(true);
					WinX_symbol2.setVisible(true);
					WinX_symbol3.setVisible(true);
				}
				else if(ans[1]=="O" && ans[2]=="O" && ans[3]=="O"){
					WinO_symbol1.setVisible(true);
					WinO_symbol2.setVisible(true);
					WinO_symbol3.setVisible(true);
				}
				else if(ans[4]=="X" && ans[5]=="X" && ans[6]=="X"){
					WinX_symbol4.setVisible(true);
					WinX_symbol5.setVisible(true);
					WinX_symbol6.setVisible(true);
				}
				else if(ans[4]=="O" && ans[5]=="O" && ans[6]=="O"){
					WinO_symbol4.setVisible(true);
					WinO_symbol5.setVisible(true);
					WinO_symbol6.setVisible(true);
				}
				else if(ans[7]=="X" && ans[8]=="X" && ans[9]=="X"){
					WinX_symbol7.setVisible(true);
					WinX_symbol8.setVisible(true);
					WinX_symbol9.setVisible(true);
				}
				else if(ans[7]=="O" && ans[8]=="O" && ans[9]=="O"){
					WinO_symbol7.setVisible(true);
					WinO_symbol8.setVisible(true);
					WinO_symbol9.setVisible(true);
				}
				else if(ans[1]=="X" && ans[4]=="X" && ans[7]=="X"){
					WinX_symbol1.setVisible(true);
					WinX_symbol4.setVisible(true);
					WinX_symbol7.setVisible(true);
				}
				else if(ans[1]=="O" && ans[4]=="O" && ans[7]=="O"){
					WinO_symbol1.setVisible(true);
					WinO_symbol4.setVisible(true);
					WinO_symbol7.setVisible(true);
				}
				else if(ans[2]=="X" && ans[5]=="X" && ans[8]=="X"){
					WinX_symbol2.setVisible(true);
					WinX_symbol5.setVisible(true);
					WinX_symbol8.setVisible(true);
				}
				else if(ans[2]=="O" && ans[5]=="O" && ans[8]=="O"){
					WinO_symbol2.setVisible(true);
					WinO_symbol5.setVisible(true);
					WinO_symbol8.setVisible(true);
				}
				else if(ans[3]=="X" && ans[6]=="X" && ans[9]=="X"){
					WinX_symbol3.setVisible(true);
					WinX_symbol6.setVisible(true);
					WinX_symbol9.setVisible(true);
				}
				else if(ans[3]=="O" && ans[6]=="O" && ans[9]=="O"){
					WinO_symbol3.setVisible(true);
					WinO_symbol6.setVisible(true);
					WinO_symbol9.setVisible(true);
				}
				else if(ans[1]=="X" && ans[5]=="X" && ans[9]=="X"){
					WinX_symbol1.setVisible(true);
					WinX_symbol5.setVisible(true);
					WinX_symbol9.setVisible(true);
				}
				else if(ans[1]=="O" && ans[5]=="O" && ans[9]=="O"){
					WinO_symbol1.setVisible(true);
					WinO_symbol5.setVisible(true);
					WinO_symbol9.setVisible(true);
				}
				else if(ans[3]=="X" && ans[5]=="X" && ans[7]=="X"){
					WinX_symbol3.setVisible(true);
					WinX_symbol5.setVisible(true);
					WinX_symbol7.setVisible(true);
				}
				else if(ans[3]=="O" && ans[5]=="O" && ans[7]=="O"){
					WinO_symbol3.setVisible(true);
					WinO_symbol5.setVisible(true);
					WinO_symbol7.setVisible(true);
				}

				setButton_OFF();
				winner();
				Continue.setEnabled(true);
			}
			else if (this.match == 1 && this.move == 0){

				ply_turn1.setVisible(false);
				ply_turn2.setVisible(false);
				winner_draw.setVisible(true);
				Ply1_score1A.setVisible(true);
				Ply2_score1A.setVisible(true);
				Continue.setEnabled(true);
				this.move=-1;
			}
			else if (this.match == 2 && this.move == 0){
System.out.println("Running Draw Progress");
				ply_turn1.setVisible(false);
				ply_turn2.setVisible(false);
				winner_draw.setVisible(true);
				Ply1_score2A.setVisible(true);
				Ply2_score2A.setVisible(true);
				Continue.setEnabled(true);
				this.move=-1;
			}
			else if (this.match == 3 && this.move == 0){

				ply_turn1.setVisible(false);
				ply_turn2.setVisible(false);
				winner_draw.setVisible(true);
				Ply1_score3A.setVisible(true);
				Ply2_score3A.setVisible(true);
				Continue.setEnabled(true);
				this.move=-1;
			}
		}
		if(this.move==-1){
			stop=true;
			ply_turn.setVisible(false);
			BackgroundContinue.setVisible(true);
		}
	}

	public void setButton_OFF(){
		N1.setEnabled(false);
		N2.setEnabled(false);
		N3.setEnabled(false);
		N4.setEnabled(false);
		N5.setEnabled(false);
		N6.setEnabled(false);
		N7.setEnabled(false);
		N8.setEnabled(false);
		N9.setEnabled(false);
	}

	public void winner(){
System.out.println("WinCounter in WIN : " +WinCounter);
		Winner += WinCounter;
System.out.println("\nWinner : " +Winner+"\n");
		if(WinCounter == 1){
			ply_turn1.setVisible(false);
			ply_turn2.setVisible(false);
			winner_turn1.setVisible(true);
			if(match == 1){
				Ply1_score1B.setVisible(true);
				Ply2_score1A.setVisible(true);
			}
			else if(match == 2){
				Ply1_score2B.setVisible(true);
				Ply2_score2A.setVisible(true);
			}
			else if(match == 3){
				Ply1_score3B.setVisible(true);
				Ply2_score3A.setVisible(true);
			}
		}
		else if(WinCounter == -1){
			ply_turn1.setVisible(false);
			ply_turn2.setVisible(false);
			winner_turn2.setVisible(true);
			if(match == 1){
				Ply1_score1A.setVisible(true);
				Ply2_score1B.setVisible(true);
			}
			else if(match == 2){
				Ply1_score2A.setVisible(true);
				Ply2_score2B.setVisible(true);
			}
			else if(match == 3){
				Ply1_score3A.setVisible(true);
				Ply2_score3B.setVisible(true);
			}
		}


	}

public void Clean(){
System.out.println("Cleaning... match: " +match);
		if(match == 1){
			ply_turn2.setVisible(true);
			ply_turn1.setVisible(false);
			turn = false;
		}
		else if (match==0 || match == 2){
			ply_turn1.setVisible(true);
			ply_turn2.setVisible(false);
			turn = true;
		}
		ply_turn.setVisible(true);
		winner_turn.setVisible(false);
		stop=false;
		Arrays.fill(ans, " ");
		Arrays.fill(PC_ans, 0);
		Arrays.fill(PLY_ans, 0);
		Arrays.fill(error, 0);
System.out.println(Arrays.toString(ans));
		move=9;
		BackgroundContinue.setVisible(false);
		Continue.setEnabled(false);
		winner_turn1.setVisible(false);
		winner_turn2.setVisible(false);
		winner_draw.setVisible(false);
		X_symbol1.setVisible(false);
		X_symbol2.setVisible(false);
		X_symbol3.setVisible(false);
		X_symbol4.setVisible(false);
		X_symbol5.setVisible(false);
		X_symbol6.setVisible(false);
		X_symbol7.setVisible(false);
		X_symbol8.setVisible(false);
		X_symbol9.setVisible(false);
		O_symbol1.setVisible(false);
		O_symbol2.setVisible(false);
		O_symbol3.setVisible(false);
		O_symbol4.setVisible(false);
		O_symbol5.setVisible(false);
		O_symbol6.setVisible(false);
		O_symbol7.setVisible(false);
		O_symbol8.setVisible(false);
		O_symbol9.setVisible(false);

		WinX_symbol1.setVisible(false);
		WinX_symbol2.setVisible(false);
		WinX_symbol3.setVisible(false);
		WinX_symbol4.setVisible(false);
		WinX_symbol5.setVisible(false);
		WinX_symbol6.setVisible(false);
		WinX_symbol7.setVisible(false);
		WinX_symbol8.setVisible(false);
		WinX_symbol9.setVisible(false);
		WinO_symbol1.setVisible(false);
		WinO_symbol2.setVisible(false);
		WinO_symbol3.setVisible(false);
		WinO_symbol4.setVisible(false);
		WinO_symbol5.setVisible(false);
		WinO_symbol6.setVisible(false);
		WinO_symbol7.setVisible(false);
		WinO_symbol8.setVisible(false);
		WinO_symbol9.setVisible(false);

		N1.setEnabled(true);
		N2.setEnabled(true);
		N3.setEnabled(true);
		N4.setEnabled(true);
		N5.setEnabled(true);
		N6.setEnabled(true);
		N7.setEnabled(true);
		N8.setEnabled(true);
		N9.setEnabled(true);
	}

public void PC_move2(){
System.out.println("Running PC move2");
System.out.println("move : " +move);
		if(move == 7){
			PC_rand_move();
			PC_ans[1] = this.ANS;		//PC_ans2
		}
//###########################################################################################################################################################
		else if(move == 5){
		ans_defense = defense_playerLEVEL_2();
		ans_attack = attack_playerLEVEL_2();
		if(ans_attack!=0 && ans_attack !=PLY_ans[0] && ans_attack !=PLY_ans[1] && ans_attack !=PLY_ans[2]
		&& ans_attack !=PC_ans[0] && ans_attack !=PC_ans[1]){
			this.ANS = ans_attack;
		}
		else if(ans_defense !=0 && ans_defense !=PLY_ans[0] && ans_defense !=PLY_ans[1] && ans_defense !=PLY_ans[2]
		&& ans_defense !=PC_ans[0] && ans_defense !=PC_ans[1]){
			this.ANS = ans_defense;
		}
		else
			PC_rand_move();
			PC_ans[2] = this.ANS;		//PC_ans3
		}
//########################################################################################################################################################
 		else if (move == 3){				//PC 4th move
		ans_defense = defense_playerLEVEL_3();
		ans_attack = attack_playerLEVEL_3();
		if(ans_attack!=0 && ans_attack !=PLY_ans[0] && ans_attack !=PLY_ans[1] && ans_attack !=PLY_ans[2]
		&& ans_attack !=PC_ans[0] && ans_attack !=PC_ans[1] &&  ans_attack !=PC_ans[2]){
			this.ANS = ans_attack;
		}
		else if(ans_defense !=0 && ans_defense !=PLY_ans[0] && ans_defense !=PLY_ans[1] && ans_defense !=PLY_ans[2]
		&& ans_defense !=PC_ans[0] &&ans_defense !=PC_ans[1] &&  ans_defense !=PC_ans[2]){
			this.ANS = ans_defense;
		}
		else
			PC_rand_move();
			PC_ans[3] = this.ANS;		//PC_ans4
		}
//##############################################################################################################################################################
		else if (move == 1){
			PC_rand_move();
			PC_ans[4] = this.ANS;		//PC_ans5
			error[this.ANS] = this.ANS;
		}
		PC_inner_move(this.ANS);
	}


//===================================================================================================================================
	public void PC_move1(){
System.out.println("Running PC move1");
System.out.println("move : " +move);
	if(move == 8){
		PC_rand_move();
		PC_ans[0] = this.ANS;		//PC_ans1
		}
//#######################################################################################################################################
	else if(move == 6){
		ans_defense = defense_playerLEVEL_1();
		if(ans_defense !=0 && ans_defense !=PLY_ans[0] && ans_defense !=PLY_ans[1] && ans_defense !=PC_ans[0] ){
			this.ANS = ans_defense;
		}
		else
			PC_rand_move();
			PC_ans[1] = this.ANS;
	}		//PC 2nd move
//###################################################################################################################################
	else if(move == 4){
		ans_defense = defense_playerLEVEL_2();
		ans_attack = attack_playerLEVEL_2();
		if(ans_attack!=0 && ans_attack !=PLY_ans[0] && ans_attack !=PLY_ans[1] && ans_attack !=PLY_ans[2]
		&& ans_attack !=PC_ans[0] && ans_attack !=PC_ans[1]){
			this.ANS = ans_attack;
		}
		else if(ans_defense !=0 && ans_defense !=PLY_ans[0] && ans_defense !=PLY_ans[1] && ans_defense !=PLY_ans[2]
		&& ans_defense !=PC_ans[0] && ans_defense !=PC_ans[1]){
			this.ANS = ans_defense;
		}
		else
			PC_rand_move();
			PC_ans[2] = this.ANS;
	}								//PC 3rd move
//#########################################################################################################################################
	else if(move == 2){
		ans_defense = defense_playerLEVEL_3();
		ans_attack = attack_playerLEVEL_3();
		if(ans_attack!=0 && ans_attack !=PLY_ans[0] && ans_attack !=PLY_ans[1] && ans_attack !=PLY_ans[2] && ans_attack !=PLY_ans[3]
		&& ans_attack !=PC_ans[0] && ans_attack !=PC_ans[1] &&  ans_attack !=PC_ans[2]){
			this.ANS = ans_attack;
		}
		else if(ans_defense !=0 && ans_defense !=PLY_ans[0] && ans_defense !=PLY_ans[1] && ans_defense !=PLY_ans[2] && ans_defense !=PLY_ans[3]
		&& ans_defense !=PC_ans[0] && ans_defense !=PC_ans[1] &&  ans_defense !=PC_ans[2]){
			this.ANS = ans_defense;
		}
		else
			PC_rand_move();
			PC_ans[3] = this.ANS;
	}
		PC_inner_move(this.ANS);
	}
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
	public void PC_rand_move(){
System.out.println("Generating random move");
		do{
System.out.println("error[?] : " +Arrays.toString(error));
			this.ANS = rand.nextInt(9)+1;
		}
		while(this.ANS==error[1] || this.ANS==error[2] || this.ANS==error[3] || this.ANS==error[4] || this.ANS==error[5]
			|| this.ANS==error[6] || this.ANS==error[7] || this.ANS==error[8] || this.ANS==error[9]);
System.out.println("\n this.ANS : " +this.ANS);
	}
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

	public void PC_inner_move(int PC_ANS){
System.out.println("Inner : " +PC_ANS);
		if(PC_ANS == 1){
			X_symbol1.setVisible(true);
			ans[1]="X";
			N1.setEnabled(false);
			error[PC_ANS]=PC_ANS;
		}
		else if(PC_ANS == 2){
			X_symbol2.setVisible(true);
			ans[2]="X";
			N2.setEnabled(false);
			error[PC_ANS]=PC_ANS;
		}
		else if(PC_ANS == 3){
			X_symbol3.setVisible(true);
			ans[3]="X";
			N3.setEnabled(false);
			error[PC_ANS]=PC_ANS;
		}
		else if(PC_ANS == 4){
			X_symbol4.setVisible(true);
			ans[4]="X";
			N4.setEnabled(false);
			error[PC_ANS]=PC_ANS;
		}
		else if(PC_ANS == 5){
			X_symbol5.setVisible(true);
			ans[5]="X";
			N5.setEnabled(false);
			error[PC_ANS]=PC_ANS;
		}
		else if(PC_ANS == 6){
			X_symbol6.setVisible(true);
			ans[6]="X";
			N6.setEnabled(false);
			error[PC_ANS]=PC_ANS;
		}
		else if(PC_ANS == 7){
			X_symbol7.setVisible(true);
			ans[7]="X";
			N7.setEnabled(false);
			error[PC_ANS]=PC_ANS;
		}
		else if(PC_ANS == 8){
			X_symbol8.setVisible(true);
			ans[8]="X";
			N8.setEnabled(false);
			error[PC_ANS]=PC_ANS;
		}
		else if(PC_ANS == 9){
			X_symbol9.setVisible(true);
			ans[9]="X";
			N9.setEnabled(false);
			error[PC_ANS]=PC_ANS;
		}
		this.move--;
	}

//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	public int attack_playerLEVEL_1(){
		int ans=0;
System.out.println("Runing Attack system...");
System.out.println("PC_ans[0]" +PC_ans[0]);
System.out.println("PC_ans[1]" +PC_ans[1]);
			if (PC_ans[0]==2 && PC_ans[1]==3 || PC_ans[0]==3 && PC_ans[1]==2 || PC_ans[0]==4 && PC_ans[1]==7
			|| PC_ans[0]==7 && PC_ans[1]==4 || PC_ans[0]==5 && PC_ans[1]==9 || PC_ans[0]==9 && PC_ans[1]==5 ){
System.out.println("AIM 1");
				ans=1;
			}
			else if (PC_ans[0]==1 && PC_ans[1]==3 || PC_ans[0]==3 && PC_ans[1]==1 || PC_ans[0]==5 && PC_ans[1]==8
			|| PC_ans[0]==8 && PC_ans[1]==5){
System.out.println("AIM 2");
					ans=2;
			}
			else if (PC_ans[0]==1 && PC_ans[1]==2 || PC_ans[0]==2 && PC_ans[1]==1 || PC_ans[0]==5 && PC_ans[1]==7
			|| PC_ans[0]==7 && PC_ans[1]==5 || PC_ans[0]==6 && PC_ans[1]==9 || PC_ans[0]==9 && PC_ans[1]==6 ){
System.out.println("AIM 3");
					ans=3;
			}
			else if (PC_ans[0]==5 && PC_ans[1]==6 || PC_ans[0]==6 && PC_ans[1]==5 || PC_ans[0]==1 && PC_ans[1]==7
			|| PC_ans[0]==7 && PC_ans[1]==1 ){
System.out.println("AIM 4");
					ans=4;
			}
			else if (PC_ans[0]==2 && PC_ans[1]==8 || PC_ans[0]==8 && PC_ans[1]==2 || PC_ans[0]==4 && PC_ans[1]==6
			|| PC_ans[0]==6 && PC_ans[1]==4 || PC_ans[0]==1 && PC_ans[1]==9 || PC_ans[0]==9 && PC_ans[1]==1
			|| PC_ans[0]==3 && PC_ans[1]==7 || PC_ans[0]==7 && PC_ans[1]==3){
System.out.println("AIM 5");
					ans=5;
			}
			else if (PC_ans[0]==4 && PC_ans[1]==5 || PC_ans[0]==5 && PC_ans[1]==4 || PC_ans[0]==3 && PC_ans[1]==9
			|| PC_ans[0]==9 && PC_ans[1]==3){
System.out.println("AIM 6");
					ans=6;
			}
			else if (PC_ans[0]==1 && PC_ans[1]==4 || PC_ans[0]==4 && PC_ans[1]==1 || PC_ans[0]==3 && PC_ans[1]==5
			|| PC_ans[0]==5 && PC_ans[1]==3 || PC_ans[0]==8 && PC_ans[1]==9 || PC_ans[0]==9 && PC_ans[1]==8 ){
System.out.println("AIM 7");
					ans=7;
			}
			else if (PC_ans[0]==2 && PC_ans[1]==5 || PC_ans[0]==5 && PC_ans[1]==2 || PC_ans[0]==7 && PC_ans[1]==9
			|| PC_ans[0]==9 && PC_ans[1]==7){
System.out.println("AIM 8");
					ans=8;
			}
			else if (PC_ans[0]==3 && PC_ans[1]==6 || PC_ans[0]==6 && PC_ans[1]==3 || PC_ans[0]==1 && PC_ans[1]==5
			|| PC_ans[0]==5 && PC_ans[1]==1 || PC_ans[0]==7 && PC_ans[1]==8 || PC_ans[0]==8 && PC_ans[1]==7 ){
System.out.println("AIM 9");
					ans=9;
			}
if (ans != 0){
System.out.println("I got you !!!");
}
			return ans;
		}
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	public int defense_playerLEVEL_1(){
			int ans=0;
System.out.println("Runing Defence system...");
System.out.println("PLY_ans[0]" +PLY_ans[0]);
System.out.println("PLY_ans[1]" +PLY_ans[1]);
			if (PLY_ans[0]==2 && PLY_ans[1]==3 || PLY_ans[0]==3 && PLY_ans[1]==2 || PLY_ans[0]==4 && PLY_ans[1]==7
			|| PLY_ans[0]==7 && PLY_ans[1]==4 || PLY_ans[0]==5 && PLY_ans[1]==9 || PLY_ans[0]==9 && PLY_ans[1]==5 ){
System.out.println("AIM 1");
				ans=1;
			}
			else if (PLY_ans[0]==1 && PLY_ans[1]==3 || PLY_ans[0]==3 && PLY_ans[1]==1 || PLY_ans[0]==5 && PLY_ans[1]==8
			|| PLY_ans[0]==8 && PLY_ans[1]==5){
System.out.println("AIM 2");
					ans=2;
			}
			else if (PLY_ans[0]==1 && PLY_ans[1]==2 || PLY_ans[0]==2 && PLY_ans[1]==1 || PLY_ans[0]==5 && PLY_ans[1]==7
			|| PLY_ans[0]==7 && PLY_ans[1]==5 || PLY_ans[0]==6 && PLY_ans[1]==9 || PLY_ans[0]==9 && PLY_ans[1]==6 ){
System.out.println("AIM 3");
					ans=3;
			}
			else if (PLY_ans[0]==5 && PLY_ans[1]==6 || PLY_ans[0]==6 && PLY_ans[1]==5 || PLY_ans[0]==1 && PLY_ans[1]==7
			|| PLY_ans[0]==7 && PLY_ans[1]==1 ){
System.out.println("AIM 4");
					ans=4;
			}
			else if (PLY_ans[0]==2 && PLY_ans[1]==8 || PLY_ans[0]==8 && PLY_ans[1]==2 || PLY_ans[0]==4 && PLY_ans[1]==6
			|| PLY_ans[0]==6 && PLY_ans[1]==4 || PLY_ans[0]==1 && PLY_ans[1]==9 || PLY_ans[0]==9 && PLY_ans[1]==1
			|| PLY_ans[0]==3 && PLY_ans[1]==7 || PLY_ans[0]==7 && PLY_ans[1]==3){
System.out.println("AIM 5");
					ans=5;
			}
			else if (PLY_ans[0]==4 && PLY_ans[1]==5 || PLY_ans[0]==5 && PLY_ans[1]==4 || PLY_ans[0]==3 && PLY_ans[1]==9
			|| PLY_ans[0]==9 && PLY_ans[1]==3){
System.out.println("AIM 6");
					ans=6;
			}
			else if (PLY_ans[0]==1 && PLY_ans[1]==4 || PLY_ans[0]==4 && PLY_ans[1]==1 || PLY_ans[0]==3 && PLY_ans[1]==5
			|| PLY_ans[0]==5 && PLY_ans[1]==3 || PLY_ans[0]==8 && PLY_ans[1]==9 || PLY_ans[0]==9 && PLY_ans[1]==8 ){
System.out.println("AIM 7");
					ans=7;
			}
			else if (PLY_ans[0]==2 && PLY_ans[1]==5 || PLY_ans[0]==5 && PLY_ans[1]==2 || PLY_ans[0]==7 && PLY_ans[1]==9
			|| PLY_ans[0]==9 && PLY_ans[1]==7){
System.out.println("AIM 8");
					ans=8;
			}
			else if (PLY_ans[0]==3 && PLY_ans[1]==6 || PLY_ans[0]==6 && PLY_ans[1]==3 || PLY_ans[0]==1 && PLY_ans[1]==5
			|| PLY_ans[0]==5 && PLY_ans[1]==1 || PLY_ans[0]==7 && PLY_ans[1]==8 || PLY_ans[0]==8 && PLY_ans[1]==7 ){
System.out.println("AIM 9");
					ans=9;
			}
if (ans != 0){
System.out.println("No way !!!");
}
			return ans;
		}
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	public int attack_playerLEVEL_2(){
	int ans=0;
System.out.println("Runing Attack system 2...");
System.out.println("PC_ans[0]" +PC_ans[0]);
System.out.println("PC_ans[1]" +PC_ans[1]);
System.out.println("PC_ans[2]" +PC_ans[2]);
System.out.println("Error : " +Arrays.toString(error));
		if (error[1]==0){
		if(PC_ans[0]==2 && PC_ans[1]==3 || PC_ans[0]==3 && PC_ans[1]==2
		|| PC_ans[0]==4 && PC_ans[1]==7 || PC_ans[0]==7 && PC_ans[1]==4
		|| PC_ans[0]==5 && PC_ans[1]==9 || PC_ans[0]==9 && PC_ans[1]==5

		|| PC_ans[2]==2 && PC_ans[1]==3 || PC_ans[2]==3 && PC_ans[1]==2
		|| PC_ans[2]==4 && PC_ans[1]==7 || PC_ans[2]==7 && PC_ans[1]==4
		|| PC_ans[2]==5 && PC_ans[1]==9 || PC_ans[2]==9 && PC_ans[1]==5

		|| PC_ans[0]==2 && PC_ans[2]==3 || PC_ans[0]==3 && PC_ans[2]==2
		|| PC_ans[0]==4 && PC_ans[2]==7 || PC_ans[0]==7 && PC_ans[2]==4
		|| PC_ans[0]==5 && PC_ans[2]==9 || PC_ans[0]==9 && PC_ans[2]==5){
System.out.println("AIM 1");
			ans=1;
		}
		}
		if (error[2]==0){
		if(PC_ans[0]==1 && PC_ans[1]==3 || PC_ans[0]==3 && PC_ans[1]==1
		|| PC_ans[0]==5 && PC_ans[1]==8 || PC_ans[0]==8 && PC_ans[1]==5

		|| PC_ans[2]==1 && PC_ans[1]==3 || PC_ans[2]==3 && PC_ans[1]==1
		|| PC_ans[2]==5 && PC_ans[1]==8 || PC_ans[2]==8 && PC_ans[1]==5

		|| PC_ans[0]==1 && PC_ans[2]==3 || PC_ans[0]==3 && PC_ans[2]==1
		|| PC_ans[0]==5 && PC_ans[2]==8 || PC_ans[0]==8 && PC_ans[2]==5){
System.out.println("AIM 2");
			ans=2;
		}
		}
		if (error[3]==0){
		if(PC_ans[0]==1 && PC_ans[1]==2 || PC_ans[0]==2 && PC_ans[1]==1
		|| PC_ans[0]==5 && PC_ans[1]==7 || PC_ans[0]==7 && PC_ans[1]==5
		|| PC_ans[0]==6 && PC_ans[1]==9 || PC_ans[0]==9 && PC_ans[1]==6

		|| PC_ans[2]==1 && PC_ans[1]==2 || PC_ans[2]==2 && PC_ans[1]==1
		|| PC_ans[2]==5 && PC_ans[1]==7 || PC_ans[2]==7 && PC_ans[1]==5
		|| PC_ans[2]==6 && PC_ans[1]==9 || PC_ans[2]==9 && PC_ans[1]==6

		|| PC_ans[0]==1 && PC_ans[2]==2 || PC_ans[0]==2 && PC_ans[2]==1
		|| PC_ans[0]==5 && PC_ans[2]==7 || PC_ans[0]==7 && PC_ans[2]==5
		|| PC_ans[0]==6 && PC_ans[2]==9 || PC_ans[0]==9 && PC_ans[2]==6){
System.out.println("AIM 3");
			ans=3;
		}
		}
		if (error[4]==0){
		if(PC_ans[0]==5 && PC_ans[1]==6 || PC_ans[0]==6 && PC_ans[1]==5
		|| PC_ans[0]==1 && PC_ans[1]==7 || PC_ans[0]==7 && PC_ans[1]==1

		|| PC_ans[2]==5 && PC_ans[1]==6 || PC_ans[2]==6 && PC_ans[1]==5
		|| PC_ans[2]==1 && PC_ans[1]==7 || PC_ans[2]==7 && PC_ans[1]==1

		|| PC_ans[0]==5 && PC_ans[2]==6 || PC_ans[0]==6 && PC_ans[2]==5
		|| PC_ans[0]==1 && PC_ans[2]==7|| PC_ans[0]==7 && PC_ans[2]==1){
System.out.println("AIM 4");
			ans=4;
		}
		}
		if (error[5]==0){
		if(PC_ans[0]==2 && PC_ans[1]==8 || PC_ans[0]==8 && PC_ans[1]==2
		|| PC_ans[0]==4 && PC_ans[1]==6 || PC_ans[0]==6 && PC_ans[1]==4
		|| PC_ans[0]==1 && PC_ans[1]==9 || PC_ans[0]==9 && PC_ans[1]==1
		|| PC_ans[0]==3 && PC_ans[1]==7 || PC_ans[0]==7 && PC_ans[1]==3

		|| PC_ans[2]==2 && PC_ans[1]==8 || PC_ans[2]==8 && PC_ans[1]==2
		|| PC_ans[2]==4 && PC_ans[1]==6 || PC_ans[2]==6 && PC_ans[1]==4
		|| PC_ans[2]==1 && PC_ans[1]==9 || PC_ans[2]==9 && PC_ans[1]==1
		|| PC_ans[2]==3 && PC_ans[1]==7 || PC_ans[2]==7 && PC_ans[1]==3

		|| PC_ans[0]==2 && PC_ans[2]==8 || PC_ans[0]==8 && PC_ans[2]==2
		|| PC_ans[0]==4 && PC_ans[2]==6 || PC_ans[0]==6 && PC_ans[2]==4
		|| PC_ans[0]==1 && PC_ans[2]==9 || PC_ans[0]==9 && PC_ans[2]==1
		|| PC_ans[0]==3 && PC_ans[2]==7 || PC_ans[0]==7 && PC_ans[2]==3 ){
System.out.println("AIM 5");
			ans=5;
		}
		}
		if (error[6]==0){
		if(PC_ans[0]==4 && PC_ans[1]==5 || PC_ans[0]==5 && PC_ans[1]==4
		|| PC_ans[0]==3 && PC_ans[1]==9	|| PC_ans[0]==9 && PC_ans[1]==3

		|| PC_ans[2]==4 && PC_ans[1]==5 || PC_ans[2]==5 && PC_ans[1]==4
		|| PC_ans[2]==3 && PC_ans[1]==9 || PC_ans[2]==9 && PC_ans[1]==3

		|| PC_ans[0]==4 && PC_ans[2]==5 || PC_ans[0]==5 && PC_ans[2]==4
		|| PC_ans[0]==3 && PC_ans[2]==9 || PC_ans[0]==9 && PC_ans[2]==3){
System.out.println("AIM 6");
			ans=6;
		}
		}
		if (error[7]==0){
		if(PC_ans[0]==1 && PC_ans[1]==4 || PC_ans[0]==4 && PC_ans[1]==1
		|| PC_ans[0]==3 && PC_ans[1]==5 || PC_ans[0]==5 && PC_ans[1]==3
		|| PC_ans[0]==8 && PC_ans[1]==9 || PC_ans[0]==9 && PC_ans[1]==8

		|| PC_ans[2]==1 && PC_ans[1]==4 || PC_ans[2]==4 && PC_ans[1]==1
		|| PC_ans[2]==3 && PC_ans[1]==5 || PC_ans[2]==5 && PC_ans[1]==3
		|| PC_ans[2]==8 && PC_ans[1]==9 || PC_ans[2]==9 && PC_ans[1]==8

		|| PC_ans[0]==1 && PC_ans[2]==4 || PC_ans[0]==4 && PC_ans[2]==1
		|| PC_ans[0]==3 && PC_ans[2]==5 || PC_ans[0]==5 && PC_ans[2]==3
		|| PC_ans[0]==8 && PC_ans[2]==9 || PC_ans[0]==9 && PC_ans[2]==8){
System.out.println("AIM 7");
			ans=7;
		}
		}
		if (error[8]==0){
		if(PC_ans[0]==2 && PC_ans[1]==5 || PC_ans[0]==5 && PC_ans[1]==2
		|| PC_ans[0]==7 && PC_ans[1]==9 || PC_ans[0]==9 && PC_ans[1]==7

		|| PC_ans[2]==2 && PC_ans[1]==5 || PC_ans[2]==5 && PC_ans[1]==2
		|| PC_ans[2]==7 && PC_ans[1]==9 || PC_ans[2]==9 && PC_ans[1]==7

		|| PC_ans[0]==2 && PC_ans[2]==5 || PC_ans[0]==5 && PC_ans[2]==2
		|| PC_ans[0]==7 && PC_ans[2]==9 || PC_ans[0]==9 && PC_ans[2]==7){
System.out.println("AIM 8");
			ans=8;
		}
		}
		if (error[9]==0){
		if(PC_ans[0]==3 && PC_ans[1]==6 || PC_ans[0]==6 && PC_ans[1]==3
		|| PC_ans[0]==1 && PC_ans[1]==5 || PC_ans[0]==5 && PC_ans[1]==1
		|| PC_ans[0]==7 && PC_ans[1]==8 || PC_ans[0]==8 && PC_ans[1]==7

		|| PC_ans[2]==3 && PC_ans[1]==6 || PC_ans[2]==6 && PC_ans[1]==3
		|| PC_ans[2]==1 && PC_ans[1]==5 || PC_ans[2]==5 && PC_ans[1]==1
		|| PC_ans[2]==7 && PC_ans[1]==8 || PC_ans[2]==8 && PC_ans[1]==7

		|| PC_ans[0]==3 && PC_ans[2]==6 || PC_ans[0]==6 && PC_ans[2]==3
		|| PC_ans[0]==1 && PC_ans[2]==5 || PC_ans[0]==5 && PC_ans[2]==1
		|| PC_ans[0]==7 && PC_ans[2]==8 || PC_ans[0]==8 && PC_ans[2]==7){
System.out.println("AIM 9");
			ans=9;
		}
		}
if (ans != 0){
System.out.println("I got you !!!");
}
		return ans;
		}
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	public int defense_playerLEVEL_2(){
		int ans=0;
System.out.println("Runing Defence system 2...");
System.out.println("PLY_ans[0]" +PLY_ans[0]);
System.out.println("PLY_ans[1]" +PLY_ans[1]);
System.out.println("PLY_ans[2]" +PLY_ans[2]);
		if (error[1]!=1){
		if(PLY_ans[0]==2 && PLY_ans[1]==3 || PLY_ans[0]==3 && PLY_ans[1]==2
		|| PLY_ans[0]==4 && PLY_ans[1]==7 || PLY_ans[0]==7 && PLY_ans[1]==4
		|| PLY_ans[0]==5 && PLY_ans[1]==9 || PLY_ans[0]==9 && PLY_ans[1]==5

		|| PLY_ans[2]==2 && PLY_ans[1]==3 || PLY_ans[2]==3 && PLY_ans[1]==2
		|| PLY_ans[2]==4 && PLY_ans[1]==7 || PLY_ans[2]==7 && PLY_ans[1]==4
		|| PLY_ans[2]==5 && PLY_ans[1]==9 || PLY_ans[2]==9 && PLY_ans[1]==5

		|| PLY_ans[0]==2 && PLY_ans[2]==3 || PLY_ans[0]==3 && PLY_ans[2]==2
		|| PLY_ans[0]==4 && PLY_ans[2]==7 || PLY_ans[0]==7 && PLY_ans[2]==4
		|| PLY_ans[0]==5 && PLY_ans[2]==9 || PLY_ans[0]==9 && PLY_ans[2]==5){
System.out.println("AIM 1");
			ans=1;
		}
		}
		if (error[2]!=2){
		if(PLY_ans[0]==1 && PLY_ans[1]==3 || PLY_ans[0]==3 && PLY_ans[1]==1
		|| PLY_ans[0]==5 && PLY_ans[1]==8 || PLY_ans[0]==8 && PLY_ans[1]==5

		|| PLY_ans[2]==1 && PLY_ans[1]==3 || PLY_ans[2]==3 && PLY_ans[1]==1
		|| PLY_ans[2]==5 && PLY_ans[1]==8 || PLY_ans[2]==8 && PLY_ans[1]==5

		|| PLY_ans[0]==1 && PLY_ans[2]==3 || PLY_ans[0]==3 && PLY_ans[2]==1
		|| PLY_ans[0]==5 && PLY_ans[2]==8 || PLY_ans[0]==8 && PLY_ans[2]==5){
System.out.println("AIM 2");
			ans=2;
		}
		}
		if(error[2]!=3){
		if(PLY_ans[0]==1 && PLY_ans[1]==2 || PLY_ans[0]==2 && PLY_ans[1]==1
		|| PLY_ans[0]==5 && PLY_ans[1]==7 || PLY_ans[0]==7 && PLY_ans[1]==5
		|| PLY_ans[0]==6 && PLY_ans[1]==9 || PLY_ans[0]==9 && PLY_ans[1]==6

		|| PLY_ans[2]==1 && PLY_ans[1]==2 || PLY_ans[2]==2 && PLY_ans[1]==1
		|| PLY_ans[2]==5 && PLY_ans[1]==7 || PLY_ans[2]==7 && PLY_ans[1]==5
		|| PLY_ans[2]==6 && PLY_ans[1]==9 || PLY_ans[2]==9 && PLY_ans[1]==6

		|| PLY_ans[0]==1 && PLY_ans[2]==2 || PLY_ans[0]==2 && PLY_ans[2]==1
		|| PLY_ans[0]==5 && PLY_ans[2]==7 || PLY_ans[0]==7 && PLY_ans[2]==5
		|| PLY_ans[0]==6 && PLY_ans[2]==9 || PLY_ans[0]==9 && PLY_ans[2]==6){
System.out.println("AIM 3");
			ans=3;
		}
		}
		if (error[4]!=4){
		if(PLY_ans[0]==5 && PLY_ans[1]==6 || PLY_ans[0]==6 && PLY_ans[1]==5
		|| PLY_ans[0]==1 && PLY_ans[1]==7 || PLY_ans[0]==7 && PLY_ans[1]==1

		|| PLY_ans[2]==5 && PLY_ans[1]==6 || PLY_ans[2]==6 && PLY_ans[1]==5
		|| PLY_ans[2]==1 && PLY_ans[1]==7 || PLY_ans[2]==7 && PLY_ans[1]==1

		|| PLY_ans[0]==5 && PLY_ans[2]==6 || PLY_ans[0]==6 && PLY_ans[2]==5
		|| PLY_ans[0]==1 && PLY_ans[2]==7 || PLY_ans[0]==7 && PLY_ans[2]==1){
System.out.println("AIM 4");
			ans=4;
		}
		}
		if (error[5]!=5){
		if(PLY_ans[0]==2 && PLY_ans[1]==8 || PLY_ans[0]==8 && PLY_ans[1]==2
		|| PLY_ans[0]==4 && PLY_ans[1]==6 || PLY_ans[0]==6 && PLY_ans[1]==4
		|| PLY_ans[0]==1 && PLY_ans[1]==9 || PLY_ans[0]==9 && PLY_ans[1]==1
		|| PLY_ans[0]==3 && PLY_ans[1]==7 || PLY_ans[0]==7 && PLY_ans[1]==3

		|| PLY_ans[2]==2 && PLY_ans[1]==8 || PLY_ans[2]==8 && PLY_ans[1]==2
		|| PLY_ans[2]==4 && PLY_ans[1]==6 || PLY_ans[2]==6 && PLY_ans[1]==4
		|| PLY_ans[2]==1 && PLY_ans[1]==9 || PLY_ans[2]==9 && PLY_ans[1]==1
		|| PLY_ans[2]==3 && PLY_ans[1]==7 || PLY_ans[2]==7 && PLY_ans[1]==3

		|| PLY_ans[0]==2 && PLY_ans[2]==8 || PLY_ans[0]==8 && PLY_ans[2]==2
		|| PLY_ans[0]==4 && PLY_ans[2]==6 || PLY_ans[0]==6 && PLY_ans[2]==4
		|| PLY_ans[0]==1 && PLY_ans[2]==9 || PLY_ans[0]==9 && PLY_ans[2]==1
		|| PLY_ans[0]==3 && PLY_ans[2]==7 || PLY_ans[0]==7 && PLY_ans[2]==3){
System.out.println("AIM 5");
			ans=5;
		}
		}
		if (error[6]!=6){
		if(PLY_ans[0]==4 && PLY_ans[1]==5 || PLY_ans[0]==5 && PLY_ans[1]==4
		|| PLY_ans[0]==3 && PLY_ans[1]==9 || PLY_ans[0]==9 && PLY_ans[1]==3

		|| PLY_ans[2]==4 && PLY_ans[1]==5 || PLY_ans[2]==5 && PLY_ans[1]==4
		|| PLY_ans[2]==3 && PLY_ans[1]==9 || PLY_ans[2]==9 && PLY_ans[1]==3

		|| PLY_ans[0]==4 && PLY_ans[2]==5 || PLY_ans[0]==5 && PLY_ans[2]==4
		|| PLY_ans[0]==3 && PLY_ans[2]==9 || PLY_ans[0]==9 && PLY_ans[2]==3){
System.out.println("AIM 6");
			ans=6;
		}
		}
		if (error[7]!=7){
		if(PLY_ans[0]==1 && PLY_ans[1]==4 || PLY_ans[0]==4 && PLY_ans[1]==1
		|| PLY_ans[0]==3 && PLY_ans[1]==5 || PLY_ans[0]==5 && PLY_ans[1]==3
		|| PLY_ans[0]==8 && PLY_ans[1]==9 || PLY_ans[0]==9 && PLY_ans[1]==8

		|| PLY_ans[2]==1 && PLY_ans[1]==4 || PLY_ans[2]==4 && PLY_ans[1]==1
		|| PLY_ans[2]==3 && PLY_ans[1]==5 || PLY_ans[2]==5 && PLY_ans[1]==3
		|| PLY_ans[2]==8 && PLY_ans[1]==9 || PLY_ans[2]==9 && PLY_ans[1]==8

		|| PLY_ans[0]==1 && PLY_ans[2]==4 || PLY_ans[0]==4 && PLY_ans[2]==1
		|| PLY_ans[0]==3 && PLY_ans[2]==5 || PLY_ans[0]==5 && PLY_ans[2]==3
		|| PLY_ans[0]==8 && PLY_ans[2]==9 || PLY_ans[0]==9 && PLY_ans[2]==8){
System.out.println("AIM 7");
			ans=7;
		}
		}
		if (error[8]!=8){
		if(PLY_ans[0]==2 && PLY_ans[1]==5 || PLY_ans[0]==5 && PLY_ans[1]==2
		|| PLY_ans[0]==7 && PLY_ans[1]==9 || PLY_ans[0]==9 && PLY_ans[1]==7

		|| PLY_ans[2]==2 && PLY_ans[1]==5 || PLY_ans[2]==5 && PLY_ans[1]==2
		|| PLY_ans[2]==7 && PLY_ans[1]==9 || PLY_ans[2]==9 && PLY_ans[1]==7

		|| PLY_ans[0]==2 && PLY_ans[2]==5 || PLY_ans[0]==5 && PLY_ans[2]==2
		|| PLY_ans[0]==7 && PLY_ans[2]==9 || PLY_ans[0]==9 && PLY_ans[2]==7){
System.out.println("AIM 8");
			ans=8;
		}
		}
		if (error[9]!=9){
		if(PLY_ans[0]==3 && PLY_ans[1]==6 || PLY_ans[0]==6 && PLY_ans[1]==3
		|| PLY_ans[0]==1 && PLY_ans[1]==5 || PLY_ans[0]==5 && PLY_ans[1]==1
		|| PLY_ans[0]==7 && PLY_ans[1]==8 || PLY_ans[0]==8 && PLY_ans[1]==7

		|| PLY_ans[2]==3 && PLY_ans[1]==6 || PLY_ans[2]==6 && PLY_ans[1]==3
		|| PLY_ans[2]==1 && PLY_ans[1]==5 || PLY_ans[2]==5 && PLY_ans[1]==1
		|| PLY_ans[2]==7 && PLY_ans[1]==8 || PLY_ans[2]==8 && PLY_ans[1]==7

		|| PLY_ans[0]==3 && PLY_ans[2]==6 || PLY_ans[0]==6 && PLY_ans[2]==3
		|| PLY_ans[0]==1 && PLY_ans[2]==5 || PLY_ans[0]==5 && PLY_ans[2]==1
		|| PLY_ans[0]==7 && PLY_ans[2]==8 || PLY_ans[0]==8 && PLY_ans[2]==7){
System.out.println("AIM 9");
			ans=9;
		}
		}
if (ans != 0){
System.out.println("No way !!!");
}
		return ans;
		}
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
public int defense_playerLEVEL_3(){
			int ans=0;
	System.out.println("Runing Defence system 3...");
	System.out.println("PLY_ans[0]" +PLY_ans[0]);
	System.out.println("PLY_ans[1]" +PLY_ans[1]);
	System.out.println("PLY_ans[2]" +PLY_ans[2]);
	System.out.println("PLY_ans[3]" +PLY_ans[3]);
			if (error[1]!=1){
			if(PLY_ans[0]==2 && PLY_ans[1]==3 || PLY_ans[0]==3 && PLY_ans[1]==2
			|| PLY_ans[0]==2 && PLY_ans[2]==3 || PLY_ans[0]==3 && PLY_ans[2]==2
			|| PLY_ans[0]==2 && PLY_ans[3]==3 || PLY_ans[0]==3 && PLY_ans[3]==2
			|| PLY_ans[1]==2 && PLY_ans[2]==3 || PLY_ans[1]==3 && PLY_ans[2]==2
			|| PLY_ans[1]==2 && PLY_ans[3]==3 || PLY_ans[1]==3 && PLY_ans[3]==2
			|| PLY_ans[2]==2 && PLY_ans[3]==3 || PLY_ans[2]==3 && PLY_ans[3]==2

			|| PLY_ans[0]==4 && PLY_ans[1]==7 || PLY_ans[0]==7 && PLY_ans[1]==4
			|| PLY_ans[0]==4 && PLY_ans[2]==7 || PLY_ans[0]==7 && PLY_ans[2]==4
			|| PLY_ans[0]==4 && PLY_ans[3]==7 || PLY_ans[0]==7 && PLY_ans[3]==4
			|| PLY_ans[1]==4 && PLY_ans[2]==7 || PLY_ans[1]==7 && PLY_ans[2]==4
			|| PLY_ans[1]==4 && PLY_ans[3]==7 || PLY_ans[1]==7 && PLY_ans[3]==4
			|| PLY_ans[2]==4 && PLY_ans[3]==7 || PLY_ans[2]==7 && PLY_ans[3]==4

			|| PLY_ans[0]==5 && PLY_ans[1]==9 || PLY_ans[0]==9 && PLY_ans[1]==5
			|| PLY_ans[0]==5 && PLY_ans[2]==9 || PLY_ans[0]==9 && PLY_ans[2]==5
			|| PLY_ans[0]==5 && PLY_ans[3]==9 || PLY_ans[0]==9 && PLY_ans[3]==5
			|| PLY_ans[1]==5 && PLY_ans[2]==9 || PLY_ans[1]==9 && PLY_ans[2]==5
			|| PLY_ans[1]==5 && PLY_ans[3]==9 || PLY_ans[1]==9 && PLY_ans[3]==5
			|| PLY_ans[2]==5 && PLY_ans[3]==9 || PLY_ans[2]==9 && PLY_ans[3]==5){
	System.out.println("AIM 1");
				ans=1;
			}
			}
			if (error[2]!=2){
			if(PLY_ans[0]==1 && PLY_ans[1]==3 || PLY_ans[0]==3 && PLY_ans[1]==1
			|| PLY_ans[0]==1 && PLY_ans[2]==3 || PLY_ans[0]==3 && PLY_ans[2]==1
			|| PLY_ans[0]==1 && PLY_ans[3]==3 || PLY_ans[0]==3 && PLY_ans[3]==1
			|| PLY_ans[1]==1 && PLY_ans[2]==3 || PLY_ans[1]==3 && PLY_ans[2]==1
			|| PLY_ans[1]==1 && PLY_ans[3]==3 || PLY_ans[1]==3 && PLY_ans[3]==1
			|| PLY_ans[2]==1 && PLY_ans[3]==3 || PLY_ans[2]==3 && PLY_ans[3]==1

			|| PLY_ans[0]==5 && PLY_ans[1]==8 || PLY_ans[0]==8 && PLY_ans[1]==5
			|| PLY_ans[0]==5 && PLY_ans[2]==8 || PLY_ans[0]==8 && PLY_ans[2]==5
			|| PLY_ans[0]==5 && PLY_ans[3]==8 || PLY_ans[0]==8 && PLY_ans[3]==5
			|| PLY_ans[1]==5 && PLY_ans[2]==8 || PLY_ans[1]==8 && PLY_ans[2]==5
			|| PLY_ans[1]==5 && PLY_ans[3]==8 || PLY_ans[1]==8 && PLY_ans[3]==5
			|| PLY_ans[2]==5 && PLY_ans[3]==8 || PLY_ans[2]==8 && PLY_ans[3]==5){
	System.out.println("AIM 2");
				ans=2;
			}
			}
			if(error[3]!=3){
			if(PLY_ans[0]==1 && PLY_ans[1]==2 || PLY_ans[0]==2 && PLY_ans[1]==1
			|| PLY_ans[0]==1 && PLY_ans[2]==2 || PLY_ans[0]==2 && PLY_ans[2]==1
			|| PLY_ans[0]==1 && PLY_ans[3]==2 || PLY_ans[0]==2 && PLY_ans[3]==1
			|| PLY_ans[1]==1 && PLY_ans[2]==2 || PLY_ans[1]==2 && PLY_ans[2]==1
			|| PLY_ans[1]==1 && PLY_ans[3]==2 || PLY_ans[1]==2 && PLY_ans[3]==1
			|| PLY_ans[2]==1 && PLY_ans[3]==2 || PLY_ans[2]==2 && PLY_ans[3]==1

			|| PLY_ans[0]==5 && PLY_ans[1]==7 || PLY_ans[0]==7 && PLY_ans[1]==5
			|| PLY_ans[0]==5 && PLY_ans[2]==7 || PLY_ans[0]==7 && PLY_ans[2]==5
			|| PLY_ans[0]==5 && PLY_ans[3]==7 || PLY_ans[0]==7 && PLY_ans[3]==5
			|| PLY_ans[1]==5 && PLY_ans[2]==7 || PLY_ans[1]==7 && PLY_ans[2]==5
			|| PLY_ans[1]==5 && PLY_ans[3]==7 || PLY_ans[1]==7 && PLY_ans[3]==5
			|| PLY_ans[2]==5 && PLY_ans[3]==7 || PLY_ans[2]==7 && PLY_ans[3]==5

			|| PLY_ans[0]==6 && PLY_ans[1]==9 || PLY_ans[0]==9 && PLY_ans[1]==6
			|| PLY_ans[0]==6 && PLY_ans[2]==9 || PLY_ans[0]==9 && PLY_ans[2]==6
			|| PLY_ans[0]==6 && PLY_ans[3]==9 || PLY_ans[0]==9 && PLY_ans[3]==6
			|| PLY_ans[1]==6 && PLY_ans[2]==9 || PLY_ans[1]==9 && PLY_ans[2]==6
			|| PLY_ans[1]==6 && PLY_ans[3]==9 || PLY_ans[1]==9 && PLY_ans[3]==6
			|| PLY_ans[2]==6 && PLY_ans[3]==9 || PLY_ans[2]==9 && PLY_ans[3]==6){
	System.out.println("AIM 3");
				ans=3;
			}
			}
			if (error[4]!=4){
			if(PLY_ans[0]==1 && PLY_ans[1]==7 || PLY_ans[0]==7 && PLY_ans[1]==1
			|| PLY_ans[0]==1 && PLY_ans[2]==7 || PLY_ans[0]==7 && PLY_ans[2]==1
			|| PLY_ans[0]==1 && PLY_ans[3]==7 || PLY_ans[0]==7 && PLY_ans[3]==1
			|| PLY_ans[1]==1 && PLY_ans[2]==7 || PLY_ans[1]==7 && PLY_ans[2]==1
			|| PLY_ans[1]==1 && PLY_ans[3]==7 || PLY_ans[1]==7 && PLY_ans[3]==1
			|| PLY_ans[2]==1 && PLY_ans[3]==7 || PLY_ans[2]==7 && PLY_ans[3]==1

			|| PLY_ans[0]==5 && PLY_ans[1]==6 || PLY_ans[0]==6 && PLY_ans[1]==5
			|| PLY_ans[0]==5 && PLY_ans[2]==6 || PLY_ans[0]==6 && PLY_ans[2]==5
			|| PLY_ans[0]==5 && PLY_ans[3]==6 || PLY_ans[0]==6 && PLY_ans[3]==5
			|| PLY_ans[1]==5 && PLY_ans[2]==6 || PLY_ans[1]==6 && PLY_ans[2]==5
			|| PLY_ans[1]==5 && PLY_ans[3]==6 || PLY_ans[1]==6 && PLY_ans[3]==5
			|| PLY_ans[2]==5 && PLY_ans[3]==6 || PLY_ans[2]==6 && PLY_ans[3]==5){
	System.out.println("AIM 4");
				ans=4;
			}
			}
			if (error[5]!=5){
			if(PLY_ans[0]==1 && PLY_ans[1]==9 || PLY_ans[0]==9 && PLY_ans[1]==1
			|| PLY_ans[0]==1 && PLY_ans[2]==9 || PLY_ans[0]==9 && PLY_ans[2]==1
			|| PLY_ans[0]==1 && PLY_ans[3]==9 || PLY_ans[0]==9 && PLY_ans[3]==1
			|| PLY_ans[1]==1 && PLY_ans[2]==9 || PLY_ans[1]==9 && PLY_ans[2]==1
			|| PLY_ans[1]==1 && PLY_ans[3]==9 || PLY_ans[1]==9 && PLY_ans[3]==1
			|| PLY_ans[2]==1 && PLY_ans[3]==9 || PLY_ans[2]==9 && PLY_ans[3]==1

			|| PLY_ans[0]==2 && PLY_ans[1]==8 || PLY_ans[0]==8 && PLY_ans[1]==2
			|| PLY_ans[0]==2 && PLY_ans[2]==8 || PLY_ans[0]==8 && PLY_ans[2]==2
			|| PLY_ans[0]==2 && PLY_ans[3]==8 || PLY_ans[0]==8 && PLY_ans[3]==2
			|| PLY_ans[1]==2 && PLY_ans[2]==8 || PLY_ans[1]==8 && PLY_ans[2]==2
			|| PLY_ans[1]==2 && PLY_ans[3]==8 || PLY_ans[1]==8 && PLY_ans[3]==2
			|| PLY_ans[2]==2 && PLY_ans[3]==8 || PLY_ans[2]==8 && PLY_ans[3]==2

			|| PLY_ans[0]==3 && PLY_ans[1]==7 || PLY_ans[0]==7 && PLY_ans[1]==3
			|| PLY_ans[0]==3 && PLY_ans[2]==7 || PLY_ans[0]==7 && PLY_ans[2]==3
			|| PLY_ans[0]==3 && PLY_ans[3]==7 || PLY_ans[0]==7 && PLY_ans[3]==3
			|| PLY_ans[1]==3 && PLY_ans[2]==7 || PLY_ans[1]==7 && PLY_ans[2]==3
			|| PLY_ans[1]==3 && PLY_ans[3]==7 || PLY_ans[1]==7 && PLY_ans[3]==3
			|| PLY_ans[2]==3 && PLY_ans[3]==7 || PLY_ans[2]==7 && PLY_ans[3]==3

			|| PLY_ans[0]==4 && PLY_ans[1]==6 || PLY_ans[0]==6 && PLY_ans[1]==4
			|| PLY_ans[0]==4 && PLY_ans[2]==6 || PLY_ans[0]==6 && PLY_ans[2]==4
			|| PLY_ans[0]==4 && PLY_ans[3]==6 || PLY_ans[0]==6 && PLY_ans[3]==4
			|| PLY_ans[1]==4 && PLY_ans[2]==6 || PLY_ans[1]==6 && PLY_ans[2]==4
			|| PLY_ans[1]==4 && PLY_ans[3]==6 || PLY_ans[1]==6 && PLY_ans[3]==4
			|| PLY_ans[2]==4 && PLY_ans[3]==6 || PLY_ans[2]==6 && PLY_ans[3]==4){
	System.out.println("AIM 5");
				ans=5;
			}
			}
			if (error[6]!=6){
			if(PLY_ans[0]==9 && PLY_ans[1]==3 || PLY_ans[0]==3 && PLY_ans[1]==9
			|| PLY_ans[0]==9 && PLY_ans[2]==3 || PLY_ans[0]==3 && PLY_ans[2]==9
			|| PLY_ans[0]==9 && PLY_ans[3]==3 || PLY_ans[0]==3 && PLY_ans[3]==9
			|| PLY_ans[1]==9 && PLY_ans[2]==3 || PLY_ans[1]==3 && PLY_ans[2]==9
			|| PLY_ans[1]==9 && PLY_ans[3]==3 || PLY_ans[1]==3 && PLY_ans[3]==9
			|| PLY_ans[2]==9 && PLY_ans[3]==3 || PLY_ans[2]==3 && PLY_ans[3]==9

			|| PLY_ans[0]==4 && PLY_ans[1]==5 || PLY_ans[0]==5 && PLY_ans[1]==4
			|| PLY_ans[0]==4 && PLY_ans[2]==5 || PLY_ans[0]==5 && PLY_ans[2]==4
			|| PLY_ans[0]==4 && PLY_ans[3]==5 || PLY_ans[0]==5 && PLY_ans[3]==4
			|| PLY_ans[1]==4 && PLY_ans[2]==5 || PLY_ans[1]==5 && PLY_ans[2]==4
			|| PLY_ans[1]==4 && PLY_ans[3]==5 || PLY_ans[1]==5 && PLY_ans[3]==4
			|| PLY_ans[2]==4 && PLY_ans[3]==5 || PLY_ans[2]==5 && PLY_ans[3]==4){
	System.out.println("AIM 6");
				ans=6;
			}
			}
			if (error[7]!=7){
			if(PLY_ans[0]==1 && PLY_ans[1]==4 || PLY_ans[0]==4 && PLY_ans[1]==1
			|| PLY_ans[0]==1 && PLY_ans[2]==4 || PLY_ans[0]==4 && PLY_ans[2]==1
			|| PLY_ans[0]==1 && PLY_ans[3]==4 || PLY_ans[0]==4 && PLY_ans[3]==1
			|| PLY_ans[1]==1 && PLY_ans[2]==4 || PLY_ans[1]==4 && PLY_ans[2]==1
			|| PLY_ans[1]==1 && PLY_ans[3]==4 || PLY_ans[1]==4 && PLY_ans[3]==1
			|| PLY_ans[2]==1 && PLY_ans[3]==4 || PLY_ans[2]==4 && PLY_ans[3]==1

			|| PLY_ans[0]==5 && PLY_ans[1]==3 || PLY_ans[0]==3 && PLY_ans[1]==5
			|| PLY_ans[0]==5 && PLY_ans[2]==3 || PLY_ans[0]==3 && PLY_ans[2]==5
			|| PLY_ans[0]==5 && PLY_ans[3]==3 || PLY_ans[0]==3 && PLY_ans[3]==5
			|| PLY_ans[1]==5 && PLY_ans[2]==3 || PLY_ans[1]==3 && PLY_ans[2]==5
			|| PLY_ans[1]==5 && PLY_ans[3]==3 || PLY_ans[1]==3 && PLY_ans[3]==5
			|| PLY_ans[2]==5 && PLY_ans[3]==3 || PLY_ans[2]==3 && PLY_ans[3]==5

			|| PLY_ans[0]==8 && PLY_ans[1]==9 || PLY_ans[0]==9 && PLY_ans[1]==8
			|| PLY_ans[0]==8 && PLY_ans[2]==9 || PLY_ans[0]==9 && PLY_ans[2]==8
			|| PLY_ans[0]==8 && PLY_ans[3]==9 || PLY_ans[0]==9 && PLY_ans[3]==8
			|| PLY_ans[1]==8 && PLY_ans[2]==9 || PLY_ans[1]==9 && PLY_ans[2]==8
			|| PLY_ans[1]==8 && PLY_ans[3]==9 || PLY_ans[1]==9 && PLY_ans[3]==8
			|| PLY_ans[2]==8 && PLY_ans[3]==9 || PLY_ans[2]==9 && PLY_ans[3]==8){
	System.out.println("AIM 7");
				ans=7;
			}
			}
			if (error[8]!=8){
			if(PLY_ans[0]==2 && PLY_ans[1]==5 || PLY_ans[0]==5 && PLY_ans[1]==2
			|| PLY_ans[0]==2 && PLY_ans[2]==5 || PLY_ans[0]==5 && PLY_ans[2]==2
			|| PLY_ans[0]==2 && PLY_ans[3]==5 || PLY_ans[0]==5 && PLY_ans[3]==2
			|| PLY_ans[1]==2 && PLY_ans[2]==5 || PLY_ans[1]==5 && PLY_ans[2]==2
			|| PLY_ans[1]==2 && PLY_ans[3]==5 || PLY_ans[1]==5 && PLY_ans[3]==2
			|| PLY_ans[2]==2 && PLY_ans[3]==5 || PLY_ans[2]==5 && PLY_ans[3]==2

			|| PLY_ans[0]==7 && PLY_ans[1]==9 || PLY_ans[0]==9 && PLY_ans[1]==7
			|| PLY_ans[0]==7 && PLY_ans[2]==9 || PLY_ans[0]==9 && PLY_ans[2]==7
			|| PLY_ans[0]==7 && PLY_ans[3]==9 || PLY_ans[0]==9 && PLY_ans[3]==7
			|| PLY_ans[1]==7 && PLY_ans[2]==9 || PLY_ans[1]==9 && PLY_ans[2]==7
			|| PLY_ans[1]==7 && PLY_ans[3]==9 || PLY_ans[1]==9 && PLY_ans[3]==7
			|| PLY_ans[2]==7 && PLY_ans[3]==9 || PLY_ans[2]==9 && PLY_ans[3]==7){
	System.out.println("AIM 8");
				ans=8;
			}
			}
			if (error[9]!=9){
			if(PLY_ans[0]==7 && PLY_ans[1]==8 || PLY_ans[0]==8 && PLY_ans[1]==7
			|| PLY_ans[0]==7 && PLY_ans[2]==8 || PLY_ans[0]==8 && PLY_ans[2]==7
			|| PLY_ans[0]==7 && PLY_ans[3]==8 || PLY_ans[0]==8 && PLY_ans[3]==7
			|| PLY_ans[1]==7 && PLY_ans[2]==8 || PLY_ans[1]==8 && PLY_ans[2]==7
			|| PLY_ans[1]==7 && PLY_ans[3]==8 || PLY_ans[1]==8 && PLY_ans[3]==7
			|| PLY_ans[2]==7 && PLY_ans[3]==8 || PLY_ans[2]==8 && PLY_ans[3]==7

			|| PLY_ans[0]==1 && PLY_ans[1]==5 || PLY_ans[0]==5 && PLY_ans[1]==1
			|| PLY_ans[0]==1 && PLY_ans[2]==5 || PLY_ans[0]==5 && PLY_ans[2]==1
			|| PLY_ans[0]==1 && PLY_ans[3]==5 || PLY_ans[0]==5 && PLY_ans[3]==1
			|| PLY_ans[1]==1 && PLY_ans[2]==5 || PLY_ans[1]==5 && PLY_ans[2]==1
			|| PLY_ans[1]==1 && PLY_ans[3]==5 || PLY_ans[1]==5 && PLY_ans[3]==1
			|| PLY_ans[2]==1 && PLY_ans[3]==5 || PLY_ans[2]==5 && PLY_ans[3]==1

			|| PLY_ans[0]==6 && PLY_ans[1]==3 || PLY_ans[0]==3 && PLY_ans[1]==6
			|| PLY_ans[0]==6 && PLY_ans[2]==3 || PLY_ans[0]==3 && PLY_ans[2]==6
			|| PLY_ans[0]==6 && PLY_ans[3]==3 || PLY_ans[0]==3 && PLY_ans[3]==6
			|| PLY_ans[1]==6 && PLY_ans[2]==3 || PLY_ans[1]==3 && PLY_ans[2]==6
			|| PLY_ans[1]==6 && PLY_ans[3]==3 || PLY_ans[1]==3 && PLY_ans[3]==6
			|| PLY_ans[2]==6 && PLY_ans[3]==3 || PLY_ans[2]==3 && PLY_ans[3]==6){
	System.out.println("AIM 9");
				ans=9;
			}
			}
	if (ans != 0){
	System.out.println("No way !!!");
	}
			return ans;
		}
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	public int attack_playerLEVEL_3(){
			int ans=0;
	System.out.println("Runing Attack system 3...");
	System.out.println("PC_ans[0]" +PC_ans[0]);
	System.out.println("PC_ans[1]" +PC_ans[1]);
	System.out.println("PC_ans[2]" +PC_ans[2]);
	System.out.println("PC_ans[3]" +PC_ans[3]);
			if (error[1]!=1){
			if(PC_ans[0]==2 && PC_ans[1]==3 || PC_ans[0]==3 && PC_ans[1]==2
			|| PC_ans[0]==2 && PC_ans[2]==3 || PC_ans[0]==3 && PC_ans[2]==2
			|| PC_ans[0]==2 && PC_ans[3]==3 || PC_ans[0]==3 && PC_ans[3]==2
			|| PC_ans[1]==2 && PC_ans[2]==3 || PC_ans[1]==3 && PC_ans[2]==2
			|| PC_ans[1]==2 && PC_ans[3]==3 || PC_ans[1]==3 && PC_ans[3]==2
			|| PC_ans[2]==2 && PC_ans[3]==3 || PC_ans[2]==3 && PC_ans[3]==2

			|| PC_ans[0]==4 && PC_ans[1]==7 || PC_ans[0]==7 && PC_ans[1]==4
			|| PC_ans[0]==4 && PC_ans[2]==7 || PC_ans[0]==7 && PC_ans[2]==4
			|| PC_ans[0]==4 && PC_ans[3]==7 || PC_ans[0]==7 && PC_ans[3]==4
			|| PC_ans[1]==4 && PC_ans[2]==7 || PC_ans[1]==7 && PC_ans[2]==4
			|| PC_ans[1]==4 && PC_ans[3]==7 || PC_ans[1]==7 && PC_ans[3]==4
			|| PC_ans[2]==4 && PC_ans[3]==7 || PC_ans[2]==7 && PC_ans[3]==4

			|| PC_ans[0]==5 && PC_ans[1]==9 || PC_ans[0]==9 && PC_ans[1]==5
			|| PC_ans[0]==5 && PC_ans[2]==9 || PC_ans[0]==9 && PC_ans[2]==5
			|| PC_ans[0]==5 && PC_ans[3]==9 || PC_ans[0]==9 && PC_ans[3]==5
			|| PC_ans[1]==5 && PC_ans[2]==9 || PC_ans[1]==9 && PC_ans[2]==5
			|| PC_ans[1]==5 && PC_ans[3]==9 || PC_ans[1]==9 && PC_ans[3]==5
			|| PC_ans[2]==5 && PC_ans[3]==9 || PC_ans[2]==9 && PC_ans[3]==5){
	System.out.println("AIM 1");
				ans=1;
			}
			}
			if (error[2]!=2){
			if(PC_ans[0]==1 && PC_ans[1]==3 || PC_ans[0]==3 && PC_ans[1]==1
			|| PC_ans[0]==1 && PC_ans[2]==3 || PC_ans[0]==3 && PC_ans[2]==1
			|| PC_ans[0]==1 && PC_ans[3]==3 || PC_ans[0]==3 && PC_ans[3]==1
			|| PC_ans[1]==1 && PC_ans[2]==3 || PC_ans[1]==3 && PC_ans[2]==1
			|| PC_ans[1]==1 && PC_ans[3]==3 || PC_ans[1]==3 && PC_ans[3]==1
			|| PC_ans[2]==1 && PC_ans[3]==3 || PC_ans[2]==3 && PC_ans[3]==1

			|| PC_ans[0]==8 && PC_ans[1]==5 || PC_ans[0]==5 && PC_ans[1]==8
			|| PC_ans[0]==8 && PC_ans[2]==5 || PC_ans[0]==5 && PC_ans[2]==8
			|| PC_ans[0]==8 && PC_ans[3]==5 || PC_ans[0]==5 && PC_ans[3]==8
			|| PC_ans[1]==8 && PC_ans[2]==5 || PC_ans[1]==5 && PC_ans[2]==8
			|| PC_ans[1]==8 && PC_ans[3]==5 || PC_ans[1]==5 && PC_ans[3]==8
			|| PC_ans[2]==8 && PC_ans[3]==5 || PC_ans[2]==5 && PC_ans[3]==8){
	System.out.println("AIM 2");
				ans=2;
			}
			}
			if(error[3]!=3){
			if(PC_ans[0]==1 && PC_ans[1]==2 || PC_ans[0]==2 && PC_ans[1]==1
			|| PC_ans[0]==1 && PC_ans[2]==2 || PC_ans[0]==2 && PC_ans[2]==1
			|| PC_ans[0]==1 && PC_ans[3]==2 || PC_ans[0]==2 && PC_ans[3]==1
			|| PC_ans[1]==1 && PC_ans[2]==2 || PC_ans[1]==2 && PC_ans[2]==1
			|| PC_ans[1]==1 && PC_ans[3]==2 || PC_ans[1]==2 && PC_ans[3]==1
			|| PC_ans[2]==1 && PC_ans[3]==2 || PC_ans[2]==2 && PC_ans[3]==1

			|| PC_ans[0]==5 && PC_ans[1]==7 || PC_ans[0]==7 && PC_ans[1]==5
			|| PC_ans[0]==5 && PC_ans[2]==7 || PC_ans[0]==7 && PC_ans[2]==5
			|| PC_ans[0]==5 && PC_ans[3]==7 || PC_ans[0]==7 && PC_ans[3]==5
			|| PC_ans[1]==5 && PC_ans[2]==7 || PC_ans[1]==7 && PC_ans[2]==5
			|| PC_ans[1]==5 && PC_ans[3]==7 || PC_ans[1]==7 && PC_ans[3]==5
			|| PC_ans[2]==5 && PC_ans[3]==7 || PC_ans[2]==7 && PC_ans[3]==5

			|| PC_ans[0]==6 && PC_ans[1]==9 || PC_ans[0]==9 && PC_ans[1]==6
			|| PC_ans[0]==6 && PC_ans[2]==9 || PC_ans[0]==9 && PC_ans[2]==6
			|| PC_ans[0]==6 && PC_ans[3]==9 || PC_ans[0]==9 && PC_ans[3]==6
			|| PC_ans[1]==6 && PC_ans[2]==9 || PC_ans[1]==9 && PC_ans[2]==6
			|| PC_ans[1]==6 && PC_ans[3]==9 || PC_ans[1]==9 && PC_ans[3]==6
			|| PC_ans[2]==6 && PC_ans[3]==9 || PC_ans[2]==9 && PC_ans[3]==6){
	System.out.println("AIM 3");
				ans=3;
			}
			}
			if (error[4]!=4){
			if(PC_ans[0]==1 && PC_ans[1]==7 || PC_ans[0]==7 && PC_ans[1]==1
			|| PC_ans[0]==1 && PC_ans[2]==7 || PC_ans[0]==7 && PC_ans[2]==1
			|| PC_ans[0]==1 && PC_ans[3]==7 || PC_ans[0]==7 && PC_ans[3]==1
			|| PC_ans[1]==1 && PC_ans[2]==7 || PC_ans[1]==7 && PC_ans[2]==1
			|| PC_ans[1]==1 && PC_ans[3]==7 || PC_ans[1]==7 && PC_ans[3]==1
			|| PC_ans[2]==1 && PC_ans[3]==7 || PC_ans[2]==7 && PC_ans[3]==1

			|| PC_ans[0]==5 && PC_ans[1]==6 || PC_ans[0]==6 && PC_ans[1]==5
			|| PC_ans[0]==5 && PC_ans[2]==6 || PC_ans[0]==6 && PC_ans[2]==5
			|| PC_ans[0]==5 && PC_ans[3]==6 || PC_ans[0]==6 && PC_ans[3]==5
			|| PC_ans[1]==5 && PC_ans[2]==6 || PC_ans[1]==6 && PC_ans[2]==5
			|| PC_ans[1]==5 && PC_ans[3]==6 || PC_ans[1]==6 && PC_ans[3]==5
			|| PC_ans[2]==5 && PC_ans[3]==6 || PC_ans[2]==6 && PC_ans[3]==5){
	System.out.println("AIM 4");
				ans=4;
			}
			}
			if (error[5]!=5){
			if(PC_ans[0]==1 && PC_ans[1]==9 || PC_ans[0]==9 && PC_ans[1]==1
			|| PC_ans[0]==1 && PC_ans[2]==9 || PC_ans[0]==9 && PC_ans[2]==1
			|| PC_ans[0]==1 && PC_ans[3]==9 || PC_ans[0]==9 && PC_ans[3]==1
			|| PC_ans[1]==1 && PC_ans[2]==9 || PC_ans[1]==9 && PC_ans[2]==1
			|| PC_ans[1]==1 && PC_ans[3]==9 || PC_ans[1]==9 && PC_ans[3]==1
			|| PC_ans[2]==1 && PC_ans[3]==9 || PC_ans[2]==9 && PC_ans[3]==1

			|| PC_ans[0]==2 && PC_ans[1]==8 || PC_ans[0]==8 && PC_ans[1]==2
			|| PC_ans[0]==2 && PC_ans[2]==8 || PC_ans[0]==8 && PC_ans[2]==2
			|| PC_ans[0]==2 && PC_ans[3]==8 || PC_ans[0]==8 && PC_ans[3]==2
			|| PC_ans[1]==2 && PC_ans[2]==8 || PC_ans[1]==8 && PC_ans[2]==2
			|| PC_ans[1]==2 && PC_ans[3]==8 || PC_ans[1]==8 && PC_ans[3]==2
			|| PC_ans[2]==2 && PC_ans[3]==8 || PC_ans[2]==8 && PC_ans[3]==2

			|| PC_ans[0]==3 && PC_ans[1]==7 || PC_ans[0]==7 && PC_ans[1]==3
			|| PC_ans[0]==3 && PC_ans[2]==7 || PC_ans[0]==7 && PC_ans[2]==3
			|| PC_ans[0]==3 && PC_ans[3]==7 || PC_ans[0]==7 && PC_ans[3]==3
			|| PC_ans[1]==3 && PC_ans[2]==7 || PC_ans[1]==7 && PC_ans[2]==3
			|| PC_ans[1]==3 && PC_ans[3]==7 || PC_ans[1]==7 && PC_ans[3]==3
			|| PC_ans[2]==3 && PC_ans[3]==7 || PC_ans[2]==7 && PC_ans[3]==3

			|| PC_ans[0]==4 && PC_ans[1]==6 || PC_ans[0]==6 && PC_ans[1]==4
			|| PC_ans[0]==4 && PC_ans[2]==6 || PC_ans[0]==6 && PC_ans[2]==4
			|| PC_ans[0]==4 && PC_ans[3]==6 || PC_ans[0]==6 && PC_ans[3]==4
			|| PC_ans[1]==4 && PC_ans[2]==6 || PC_ans[1]==6 && PC_ans[2]==4
			|| PC_ans[1]==4 && PC_ans[3]==6 || PC_ans[1]==6 && PC_ans[3]==4
			|| PC_ans[2]==4 && PC_ans[3]==6 || PC_ans[2]==6 && PC_ans[3]==4){
	System.out.println("AIM 5");
				ans=5;
			}
			}
			if (error[6]!=6){
			if(PC_ans[0]==9 && PC_ans[1]==3 || PC_ans[0]==3 && PC_ans[1]==9
			|| PC_ans[0]==9 && PC_ans[2]==3 || PC_ans[0]==3 && PC_ans[2]==9
			|| PC_ans[0]==9 && PC_ans[3]==3 || PC_ans[0]==3 && PC_ans[3]==9
			|| PC_ans[1]==9 && PC_ans[2]==3 || PC_ans[1]==3 && PC_ans[2]==9
			|| PC_ans[1]==9 && PC_ans[3]==3 || PC_ans[1]==3 && PC_ans[3]==9
			|| PC_ans[2]==9 && PC_ans[3]==3 || PC_ans[2]==3 && PC_ans[3]==9

			|| PC_ans[0]==4 && PC_ans[1]==5 || PC_ans[0]==5 && PC_ans[1]==4
			|| PC_ans[0]==4 && PC_ans[2]==5 || PC_ans[0]==5 && PC_ans[2]==4
			|| PC_ans[0]==4 && PC_ans[3]==5 || PC_ans[0]==5 && PC_ans[3]==4
			|| PC_ans[1]==4 && PC_ans[2]==5 || PC_ans[1]==5 && PC_ans[2]==4
			|| PC_ans[1]==4 && PC_ans[3]==5 || PC_ans[1]==5 && PC_ans[3]==4
			|| PC_ans[2]==4 && PC_ans[3]==5 || PC_ans[2]==5 && PC_ans[3]==4){
	System.out.println("AIM 6");
				ans=6;
			}
			}
			if (error[7]!=7){
			if(PC_ans[0]==1 && PC_ans[1]==4 || PC_ans[0]==4 && PC_ans[1]==1
			|| PC_ans[0]==1 && PC_ans[2]==4 || PC_ans[0]==4 && PC_ans[2]==1
			|| PC_ans[0]==1 && PC_ans[3]==4 || PC_ans[0]==4 && PC_ans[3]==1
			|| PC_ans[1]==1 && PC_ans[2]==4 || PC_ans[1]==4 && PC_ans[2]==1
			|| PC_ans[1]==1 && PC_ans[3]==4 || PC_ans[1]==4 && PC_ans[3]==1
			|| PC_ans[2]==1 && PC_ans[3]==4 || PC_ans[2]==4 && PC_ans[3]==1

			|| PC_ans[0]==5 && PC_ans[1]==3 || PC_ans[0]==3 && PC_ans[1]==5
			|| PC_ans[0]==5 && PC_ans[2]==3 || PC_ans[0]==3 && PC_ans[2]==5
			|| PC_ans[0]==5 && PC_ans[3]==3 || PC_ans[0]==3 && PC_ans[3]==5
			|| PC_ans[1]==5 && PC_ans[2]==3 || PC_ans[1]==3 && PC_ans[2]==5
			|| PC_ans[1]==5 && PC_ans[3]==3 || PC_ans[1]==3 && PC_ans[3]==5
			|| PC_ans[2]==5 && PC_ans[3]==3 || PC_ans[2]==3 && PC_ans[3]==5

			|| PC_ans[0]==8 && PC_ans[1]==9 || PC_ans[0]==9 && PC_ans[1]==8
			|| PC_ans[0]==8 && PC_ans[2]==9 || PC_ans[0]==9 && PC_ans[2]==8
			|| PC_ans[0]==8 && PC_ans[3]==9 || PC_ans[0]==9 && PC_ans[3]==8
			|| PC_ans[1]==8 && PC_ans[2]==9 || PC_ans[1]==9 && PC_ans[2]==8
			|| PC_ans[1]==8 && PC_ans[3]==9 || PC_ans[1]==9 && PC_ans[3]==8
			|| PC_ans[2]==8 && PC_ans[3]==9 || PC_ans[2]==9 && PC_ans[3]==8){
	System.out.println("AIM 7");
				ans=7;
			}
			}
			if (error[8]!=8){
			if(PC_ans[0]==2 && PC_ans[1]==5 || PC_ans[0]==5 && PC_ans[1]==2
			|| PC_ans[0]==2 && PC_ans[2]==5 || PC_ans[0]==5 && PC_ans[2]==2
			|| PC_ans[0]==2 && PC_ans[3]==5 || PC_ans[0]==5 && PC_ans[3]==2
			|| PC_ans[1]==2 && PC_ans[2]==5 || PC_ans[1]==5 && PC_ans[2]==2
			|| PC_ans[1]==2 && PC_ans[3]==5 || PC_ans[1]==5 && PC_ans[3]==2
			|| PC_ans[2]==2 && PC_ans[3]==5 || PC_ans[2]==5 && PC_ans[3]==2

			|| PC_ans[0]==7 && PC_ans[1]==9 || PC_ans[0]==9 && PC_ans[1]==7
			|| PC_ans[0]==7 && PC_ans[2]==9 || PC_ans[0]==9 && PC_ans[2]==7
			|| PC_ans[0]==7 && PC_ans[3]==9 || PC_ans[0]==9 && PC_ans[3]==7
			|| PC_ans[1]==7 && PC_ans[2]==9 || PC_ans[1]==9 && PC_ans[2]==7
			|| PC_ans[1]==7 && PC_ans[3]==9 || PC_ans[1]==9 && PC_ans[3]==7
			|| PC_ans[2]==7 && PC_ans[3]==9 || PC_ans[2]==9 && PC_ans[3]==7){
	System.out.println("AIM 8");
				ans=8;
			}
			}
			if (error[9]!=9){
			if(PC_ans[0]==7 && PC_ans[1]==8 || PC_ans[0]==8 && PC_ans[1]==7
			|| PC_ans[0]==7 && PC_ans[2]==8 || PC_ans[0]==8 && PC_ans[2]==7
			|| PC_ans[0]==7 && PC_ans[3]==8 || PC_ans[0]==8 && PC_ans[3]==7
			|| PC_ans[1]==7 && PC_ans[2]==8 || PC_ans[1]==8 && PC_ans[2]==7
			|| PC_ans[1]==7 && PC_ans[3]==8 || PC_ans[1]==8 && PC_ans[3]==7
			|| PC_ans[2]==7 && PC_ans[3]==8 || PC_ans[2]==8 && PC_ans[3]==7

			|| PC_ans[0]==1 && PC_ans[1]==5 || PC_ans[0]==5 && PC_ans[1]==1
			|| PC_ans[0]==1 && PC_ans[2]==5 || PC_ans[0]==5 && PC_ans[2]==1
			|| PC_ans[0]==1 && PC_ans[3]==5 || PC_ans[0]==5 && PC_ans[3]==1
			|| PC_ans[1]==1 && PC_ans[2]==5 || PC_ans[1]==5 && PC_ans[2]==1
			|| PC_ans[1]==1 && PC_ans[3]==5 || PC_ans[1]==5 && PC_ans[3]==1
			|| PC_ans[2]==1 && PC_ans[3]==5 || PC_ans[2]==5 && PC_ans[3]==1

			|| PC_ans[0]==6 && PC_ans[1]==3 || PC_ans[0]==3 && PC_ans[1]==6
			|| PC_ans[0]==6 && PC_ans[2]==3 || PC_ans[0]==3 && PC_ans[2]==6
			|| PC_ans[0]==6 && PC_ans[3]==3 || PC_ans[0]==3 && PC_ans[3]==6
			|| PC_ans[1]==6 && PC_ans[2]==3 || PC_ans[1]==3 && PC_ans[2]==6
			|| PC_ans[1]==6 && PC_ans[3]==3 || PC_ans[1]==3 && PC_ans[3]==6
			|| PC_ans[2]==6 && PC_ans[3]==3 || PC_ans[2]==3 && PC_ans[3]==6){
	System.out.println("AIM 9");
				ans=9;
			}
			}
	if (ans != 0){
	System.out.println("I got you !!!");
	}
			return ans;
		}



 public void run(){
    for(k=0;k< 60;k++)
    {
     for(l=0;l< 60;l++)
     {
      if(stop)
      {
       break;
      }
      NumberFormat nf = new DecimalFormat("00");
	  if(match==1){
	  		 	Time1.setText(nf.format(k)+" : "+nf.format(l));
	  }
	  else if(match==2){
	  		 	Time2.setText(nf.format(k)+" : "+nf.format(l));
	  }
	  else if(match==3){
	  	  		Time3.setText(nf.format(k)+" : "+nf.format(l));
	  }
      try{
       Thread.sleep(1000);
      }catch(Exception e){}
     }
    }
   }
}
