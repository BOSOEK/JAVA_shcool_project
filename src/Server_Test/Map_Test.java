package Server_Test;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;

public class Map_Test {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Map_Test window = new Map_Test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Map_Test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 1030);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		ImageIcon backimg = new ImageIcon("./Image/play_backimg.jpg");
		
		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(backimg.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
			
		};
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 744, 995);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.PINK);
		lblNewLabel.setBounds(238, 164, 62, 18);
		lblNewLabel.setOpaque(true);
		panel.add(lblNewLabel);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
	}
}
