package brickbreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Switcher implements ActionListener{
	JFrame frame = new JFrame();
	JFrame frame2 = new JFrame();
	JFrame frame3 = new JFrame();
	JButton b1 = new JButton("original theme");
	JButton b3 = new JButton("second theme");
	JButton b2 = new JButton("exit");
	JLabel title = new JLabel("THE BRICK BREAKER");
	JLabel title2  = new JLabel("after selecting the theme");
	JLabel title3 = new JLabel("press left or right arrow to start the game");
	SecondTheme game;
	BreakingBadPlay game2;
	public Switcher() {
		game2 = new BreakingBadPlay();
		game = new SecondTheme();
		
		//titles
		
		
		title.setFont(new Font("Serif", Font.PLAIN, 40));
		title.setForeground(Color.black);
		title.setBounds(100, 200, 600, 100);
		title2.setFont(new Font("Serif", Font.PLAIN, 20));
		title2.setForeground(Color.black);
		title2.setBounds(100, 250, 600, 100);
		title3.setFont(new Font("Serif", Font.PLAIN, 20));
		title3.setForeground(Color.black);
		title3.setBounds(100, 300, 600, 100);
		
		// pictures
		
	//	pic1.setIcon(new ImageIcon("/images/theme1.png"));
	//	pic1.setBounds(200, 400, 300, 300);
		
		
		//button 1
		
		//original theme
		b1.setBounds(90, 400, 150, 25);
		b1.addActionListener(this);
		b1.setFocusable(false);
		b1.setBackground(Color.orange);
		
		
		//button 2
		
		
		b2.setBounds(250, 500, 100, 25);
		b2.addActionListener(this);
		b2.setFocusable(false);
		b2.setBackground(Color.orange);
		
		
		
		//button 3
		
		//second theme
		b3.setBounds(350, 400, 150, 25);
		b3.addActionListener(this);
		b3.setFocusable(false);
		b3.setBackground(Color.orange);
		// first frame
		
		
		frame.setVisible(true);
		frame.setSize(600,700);
		frame.setTitle("Breaking bad");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setBackground(null);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.add(b1);
		frame.add(b2);
		frame.add(b3);
		frame.add(title);
		frame.add(title2);
		frame.add(title3);
		frame.revalidate();
		//theme 1
		
	
		frame2.setVisible(false);
		frame2.setSize(600,700);
		frame2.setTitle("Breaking bad");
		frame2.setResizable(false);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setLocationRelativeTo(null);
		
		
		
		//theme 2
		
		
		frame3.setVisible(false);
		frame3.setSize(600,700);
		frame3.setTitle("Breaking bad");
		frame3.setResizable(false);
		frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame3.setLocationRelativeTo(null);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == b1) {
			frame.dispose();
			frame2.add(game2);
			frame2.setVisible(true);
	} else if(e.getSource() == b2) {
		System.exit(0);
		
	} else if(e.getSource() == b3) {
		frame.dispose();
		frame3.add(game);
		frame3.setVisible(true);
	}
}
}