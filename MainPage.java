import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MainPage extends GameLayout implements  ActionListener{		//LOW JUN HONG

	GameSetUp1 GSU1;
	GameSetUp2 GSU2;
	GameSetUp3 GSU3;
	GameSetUp4 GSU4;
	GameSetUp5 GSU5;
	GameSetUp6 GSU6;

	Random rand = new Random();
	JPanel StartPanel = new JPanel();
	JPanel SelectionPanel = new JPanel();
	JPanel DifficultyPanel = new JPanel();
	JPanel RP1a_Panel = new JPanel();
	JPanel RP1b_Panel = new JPanel();
	JPanel RP2_Panel = new JPanel();
	JPanel DrawPanelA = new JPanel();
	JPanel DrawPanelB = new JPanel();
	JButton Start, SinglePLY, MultiPLY, Login1, Login2, Easy, Hard, Scissor1a, Paper1a, Rock1a, Scissor1b, Paper1b, Rock1b, Scissor2, Paper2, Rock2, DrawContA, DrawContB;



	private String P1, P2;
	private int P1A_ans=0, P1B_ans=0, P2_ans=0, PC_ans=0, decision, Diff, Final, Mode, randPC;

	Font f = new Font("Algerian", Font.ITALIC, 24);

	public MainPage(){
		super();
		StartPanel();
		SelectionPanel();
		DifficultyPanel();
		DrawPanelA();
		DrawPanelB();
		RP1a_Panel();
		RP1b_Panel();
		RP2_Panel();
		GamePanel1();
		GamePanel2();
		GamePanel3();
		GamePanel4();
		GamePanel5();
		GamePanel6();

		mainPanel.add(StartPanel);
		mainPanel.add(SelectionPanel, "2");
		mainPanel.add(DifficultyPanel, "3A1");
		mainPanel.add(RP1a_Panel, "4A1");
		mainPanel.add(RP1b_Panel, "4A2");
		mainPanel.add(RP2_Panel, "4B");
		mainPanel.add(DrawPanelA, "DrawA");
		mainPanel.add(DrawPanelB, "DrawB");
		mainPanel.add(GSU1.GamePanel1, "5A");
		mainPanel.add(GSU2.GamePanel2, "5B");
		mainPanel.add(GSU3.GamePanel3, "5C");
		mainPanel.add(GSU4.GamePanel4, "5D");
		mainPanel.add(GSU5.GamePanel5, "5E");
		mainPanel.add(GSU6.GamePanel6, "5F");

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Start){
			cardLayout.show(mainPanel, "2");
		}
		if(e.getSource() == SinglePLY){
			cardLayout.show(mainPanel, "3A1");
		}
		if(e.getSource() == MultiPLY){
			cardLayout.show(mainPanel, "4A2");
		}
		if(e.getSource() == Easy){
			cardLayout.show(mainPanel, "4A1");
			this.Diff = 1;					//Easy as 1
		}
		if(e.getSource() == Hard){
			cardLayout.show(mainPanel, "4A1");
			this.Diff = 2;					//Hard as 2
		}
		if(e.getSource() == DrawContA){
			cardLayout.show(mainPanel, "4A1");
		}
		if(e.getSource() == DrawContB){
			cardLayout.show(mainPanel, "4A2");
		}
		if(e.getSource() == Scissor1a){
			this.P1A_ans = 1;
			IfDraw();
		}
		if(e.getSource() == Paper1a){
			this.P1A_ans = 2;
			IfDraw();
		}
		if(e.getSource() == Rock1a){
			this.P1A_ans = 3;
			IfDraw();
		}
		if(e.getSource() == Scissor1b){
			this.P1B_ans = 1;
			cardLayout.show(mainPanel, "4B");
		}
		if(e.getSource() == Paper1b){
			this.P1B_ans = 2;
			cardLayout.show(mainPanel, "4B");
		}
		if(e.getSource() == Rock1b){
			this.P1B_ans = 3;
			cardLayout.show(mainPanel, "4B");
		}
		if(e.getSource() == Scissor2){
			this.P2_ans = 1;
			IfDraw();
		}
		if(e.getSource() == Paper2){
			this.P2_ans = 2;
			IfDraw();
		}
		if(e.getSource() == Rock2){
			this.P2_ans = 3;
			IfDraw();
		}
	}

	public void GamePanel1(){
		GSU1 = new GameSetUp1();
	}

	public void GamePanel2(){
		GSU2 = new GameSetUp2();
	}

	public void GamePanel3(){
		GSU3 = new GameSetUp3();
	}

	public void GamePanel4(){
		GSU4 = new GameSetUp4();
	}

	public void GamePanel5(){
		GSU5 = new GameSetUp5();
	}

	public void GamePanel6(){
		GSU6 = new GameSetUp6();
	}

	public void GameMode(){		//decide who start 1st n game mode
		Decision();
		if(this.Diff==1 && this.decision==0){
System.out.println("Page : 5A");
			cardLayout.show(mainPanel, "5A");			//easy n pc start 1st
		}
		else if(this.Diff==2 && this.decision==0){
System.out.println("Page : 5B");
			cardLayout.show(mainPanel, "5B");			//hard n pc start 1st
		}
		else if(this.Diff==1 && this.decision==1){
System.out.println("Page : 5C");
			cardLayout.show(mainPanel, "5C");			//easy n P1 start 1st
		}
		else if(this.Diff==2 && this.decision==1){
System.out.println("Page : 5D");
			cardLayout.show(mainPanel, "5D");			//hard n P1 start 1st
		}
		else if(this.Diff==0 && this.decision==1){
System.out.println("Page : 5E");
			cardLayout.show(mainPanel, "5E");			//P1 start 1st
		}
		else if(this.Diff==0 && this.decision==2){
System.out.println("Page : 5F");
			cardLayout.show(mainPanel, "5F");			//P2 start 1st
		}
	}

	public void Decision(){
		if(this.PC_ans==1 && this.P1A_ans==2 || this.PC_ans==2 && this.P1A_ans==3 || this.PC_ans==3 && this.P1A_ans==1){
			this.decision=0;		//PC start 1st
System.out.println("this.decision : " +this.decision);
		   }
		else if(this.PC_ans==1 && this.P1A_ans==3 || this.PC_ans==2 && this.P1A_ans==1 || this.PC_ans==3 && this.P1A_ans==2
		|| this.P2_ans==1 && this.P1B_ans==3 || this.P2_ans==2 && this.P1B_ans==1 || this.P2_ans==3 && this.P1B_ans==2){
			this.decision=1;		//P1 start 1st
System.out.println("this.decision : " +this.decision);
		   }
		else if(this.P2_ans==1 && this.P1B_ans==2 || this.P2_ans==2 && this.P1B_ans==3 || this.P2_ans==3 && this.P1B_ans==1){
			this.decision=2;		//P2 start 1st
System.out.println("this.decision : " +this.decision);
		   }
	}

	public void IfDraw(){
		this.PC_ans = Rand_PC();
System.out.println("this.PC_ans : " +this.PC_ans);
System.out.println("this.P1A_ans : " +this.P1A_ans);
System.out.println("this.P1B_ans : " +this.P1B_ans);
System.out.println("this.P2_ans : " +this.P2_ans);
		if(this.P1A_ans == this.PC_ans){
System.out.println("SinglePLY Draw !! ");
			cardLayout.show(mainPanel, "DrawA");
		}
		else if(this.P2_ans == this.P1B_ans && this.P2_ans !=0 && this.P1B_ans != 0){
System.out.println("MultiPLY Draw !! ");
			cardLayout.show(mainPanel, "DrawB");
		}
		else if(this.P1A_ans != this.PC_ans){
			GameMode();
		}
		else if(this.P1B_ans != 0 && this.P2_ans != 0){
			GameMode();
		}
	}

	public int Rand_PC(){
		randPC = rand.nextInt(3)+1;
		return randPC;
	}

	public void DrawPanelA(){
		DrawPanelA.setLayout(null);
		JLabel BackgroundDraw = new JLabel(new ImageIcon("RoshamboDraw.jpg"));
		BackgroundDraw.setBounds(0, 0, 500, 800);
		DrawContA = new JButton();
		DrawContA.setBounds(0, 0, 500, 800);
		DrawContA.setContentAreaFilled(false);
		DrawContA.setBorder(BorderFactory.createEmptyBorder());
		DrawContA.addActionListener(this);
		DrawPanelA.add(DrawContA);
		DrawPanelA.add(BackgroundDraw);
	}

	public void DrawPanelB(){
		DrawPanelB.setLayout(null);
		JLabel BackgroundDraw = new JLabel(new ImageIcon("RoshamboDraw.jpg"));
		BackgroundDraw.setBounds(0, 0, 500, 800);
		DrawContB = new JButton();
		DrawContB.setBounds(0, 0, 500, 800);
		DrawContB.setContentAreaFilled(false);
		DrawContB.setBorder(BorderFactory.createEmptyBorder());
		DrawContB.addActionListener(this);
		DrawPanelB.add(DrawContB);
		DrawPanelB.add(BackgroundDraw);
	}

	public void StartPanel(){
		StartPanel.setLayout(null);
		JLabel Background1 = new JLabel(new ImageIcon("Background1.jpg"));
		Background1.setBounds(0, 0, 500, 800);
		Start = new JButton();
		Start.setBounds(100, 450, 300, 100);
		Start.setContentAreaFilled(false);
		Start.setBorder(BorderFactory.createEmptyBorder());
		Start.addActionListener(this);
		StartPanel.add(Start);
        StartPanel.add(Background1);
	}

	public void SelectionPanel(){
		SelectionPanel.setLayout(null);
		JLabel Background2 = new JLabel(new ImageIcon("Background2.jpg"));
		Background2.setBounds(0, 0, 500, 800);
		SinglePLY = new JButton();
		SinglePLY.setBounds(75, 250, 350, 100);
		SinglePLY.setContentAreaFilled(false);
		SinglePLY.setBorder(BorderFactory.createEmptyBorder());
		SinglePLY.addActionListener(this);
		MultiPLY = new JButton();
		MultiPLY.setBounds(75, 450, 350, 100);
		MultiPLY.setContentAreaFilled(false);
		MultiPLY.setBorder(BorderFactory.createEmptyBorder());
		MultiPLY.addActionListener(this);
		SelectionPanel.add(SinglePLY);
		SelectionPanel.add(MultiPLY);
		SelectionPanel.add(Background2);
	}

	public void DifficultyPanel(){
		DifficultyPanel.setLayout(null);
		JLabel Background3A1 = new JLabel(new ImageIcon("Background3A1.jpg"));
		Background3A1.setBounds(0, 0, 500, 800);
		Easy = new JButton();
		Easy.setBounds(125, 250, 250, 100);
		Easy.setContentAreaFilled(false);
		Easy.setBorder(BorderFactory.createEmptyBorder());
		Easy.addActionListener(this);
		Hard = new JButton();
		Hard.setBounds(125, 450, 250, 100);
		Hard.setContentAreaFilled(false);
		Hard.setBorder(BorderFactory.createEmptyBorder());
		Hard.addActionListener(this);
		DifficultyPanel.add(Easy);
		DifficultyPanel.add(Hard);
		DifficultyPanel.add(Background3A1);
	}

	public void RP1a_Panel(){
		RP1a_Panel.setLayout(null);
		JLabel Background4A = new JLabel(new ImageIcon("Background4A.jpg"));
		Background4A.setBounds(0, 0, 500, 800);
		Scissor1a = new JButton();
		Scissor1a.setBounds(10, 320, 160, 160);
		Scissor1a.setContentAreaFilled(false);
		//Scissor1a.setBorder(BorderFactory.createEmptyBorder());
		Scissor1a.addActionListener(this);
		Paper1a = new JButton();
		Paper1a.setBounds(168, 320, 160, 160);
		Paper1a.setContentAreaFilled(false);
		//Paper1a.setBorder(BorderFactory.createEmptyBorder());
		Paper1a.addActionListener(this);
		Rock1a = new JButton();
		Rock1a.setBounds(328, 320, 160, 160);
		Rock1a.setContentAreaFilled(false);
		//Rock1a.setBorder(BorderFactory.createEmptyBorder());
		Rock1a.addActionListener(this);
		RP1a_Panel.add(Scissor1a);
		RP1a_Panel.add(Paper1a);
		RP1a_Panel.add(Rock1a);
		RP1a_Panel.add(Background4A);
	}

	public void RP1b_Panel(){
		RP1b_Panel.setLayout(null);
		JLabel Background4A = new JLabel(new ImageIcon("Background4A.jpg"));
		Background4A.setBounds(0, 0, 500, 800);
		Scissor1b = new JButton();
		Scissor1b.setBounds(10, 320, 160, 160);
		Scissor1b.setContentAreaFilled(false);
		//Scissor1b.setBorder(BorderFactory.createEmptyBorder());
		Scissor1b.addActionListener(this);
		Paper1b = new JButton();
		Paper1b.setBounds(168, 320, 160, 160);
		Paper1b.setContentAreaFilled(false);
		//Paper1b.setBorder(BorderFactory.createEmptyBorder());
		Paper1b.addActionListener(this);
		Rock1b = new JButton();
		Rock1b.setBounds(328, 320, 160, 160);
		Rock1b.setContentAreaFilled(false);
		//Rock1b.setBorder(BorderFactory.createEmptyBorder());
		Rock1b.addActionListener(this);
		RP1b_Panel.add(Scissor1b);
		RP1b_Panel.add(Paper1b);
		RP1b_Panel.add(Rock1b);
		RP1b_Panel.add(Background4A);
	}

	public void RP2_Panel(){
		RP2_Panel.setLayout(null);
		JLabel Background4B = new JLabel(new ImageIcon("Background4B.jpg"));
		Background4B.setBounds(0, 0, 500, 800);
		Scissor2 = new JButton();
		Scissor2.setBounds(10, 320, 160, 160);
		Scissor2.setContentAreaFilled(false);
		//Scissor2.setBorder(BorderFactory.createEmptyBorder());
		Scissor2.addActionListener(this);
		Paper2 = new JButton();
		Paper2.setBounds(168, 320, 160, 160);
		Paper2.setContentAreaFilled(false);
		//Paper2.setBorder(BorderFactory.createEmptyBorder());
		Paper2.addActionListener(this);
		Rock2 = new JButton();
		Rock2.setBounds(328, 320, 160, 160);
		Rock2.setContentAreaFilled(false);
		//Rock2.setBorder(BorderFactory.createEmptyBorder());
		Rock2.addActionListener(this);
		RP2_Panel.add(Scissor2);
		RP2_Panel.add(Paper2);
		RP2_Panel.add(Rock2);
		RP2_Panel.add(Background4B);
	}

}