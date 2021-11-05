import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GameLayout{		//LOW JUN HONG

	CardLayout cardLayout = new CardLayout();
	JFrame GameFrame;
	JPanel mainPanel;

	public GameLayout(){
		GameFrame();
	}

	public void GameFrame(){
		GameFrame = new JFrame();
		mainPanel = new JPanel(cardLayout);
		GameFrame.add(mainPanel);
		GameFrame.setTitle("TIC TAC TOE");
		GameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameFrame.setSize(518,847);
		GameFrame.setLocationRelativeTo(null);
		GameFrame.setVisible(true);
	}
}