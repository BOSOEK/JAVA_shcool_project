package Server_Test;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;

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
		frame.setBounds(100, 100, 730, 1040);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		ImageIcon backimg = new ImageIcon("./Image/play_backimg.jpg");
		JLabel kk = new JLabel("");
		
		class MyMouseListener implements MouseListener, MouseMotionListener {

			@Override
			public void mouseDragged(MouseEvent arg0) {
				kk.setText(arg0.getX() + " " + arg0.getY());
				
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				if(arg0.getX() < 70) {
					
				}
				kk.setText(arg0.getX() + " " + arg0.getY());
				
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				kk.setText(arg0.getX() + " " + arg0.getY());				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				kk.setText(arg0.getX() + " " + arg0.getY());				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				kk.setText(arg0.getX() + " " + arg0.getY());				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				kk.setText(arg0.getX() + " " + arg0.getY());				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				kk.setText(arg0.getX() + " " + arg0.getY());				
			}
			
		}
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(0, 0, 744, 995);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		kk.setForeground(Color.WHITE);
		kk.setBounds(280, 142, 151, 67);
		panel.add(kk);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		panel.addMouseListener(new MyMouseListener());
		panel.addMouseMotionListener(new MyMouseListener());
		
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.PINK);
		lblNewLabel.setBounds(14, 24, 702, 18);
		lblNewLabel.setOpaque(true);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(Color.ORANGE);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(14, 45, 21, 894);
		lblNewLabel_1.setOpaque(true);
		panel.add(lblNewLabel_1);
		
		JLabel label = new JLabel("");
		label.setOpaque(true);
		label.setForeground(Color.WHITE);
		label.setBackground(Color.ORANGE);
		label.setBounds(692, 45, 21, 894);
		panel.add(label);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(360, 268, 105, 27);
		panel.add(btnNewButton);
		
	}
}
