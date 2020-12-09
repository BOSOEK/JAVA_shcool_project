package Test;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.Icon;
import java.awt.Font;
import javax.swing.SwingConstants;

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
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBackground(Color.BLACK);
		frame.setBounds(600, 50, 730, 1040);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		

		JLabel Red_ball = new JLabel(new ImageIcon("./Image/Red_ball.png"));
		
		class MyMouseListener implements MouseListener, MouseMotionListener {

			@Override
			public void mouseDragged(MouseEvent arg0) {
				int Lx = arg0.getX(), Ly = arg0.getY();
				if(Lx < 80) {
					Lx = 80;
				}
				if(Lx > 630) {
					Lx = 630;
				}
				if(Ly > 540) {
					Ly = 540;
				}
				if(Ly < 935) {
					Ly = 935;
				}
				
				Red_ball.setBounds(Lx-50, Ly-50, 96, 91);
				//Red_ball.setBounds(arg0.getX()-50, arg0.getY()-50, 96, 91);
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				int Lx = arg0.getX(), Ly = arg0.getY();
				if(Lx < 80) {
					Lx = 80;
				}
				if(Lx > 630) {
					Lx = 630;
				}
				if(Ly < 540) {
					Ly = 540;
				}
				if(Ly > 935) {
					Ly = 935;
				}
				
				Red_ball.setBounds(Lx-50, Ly-50, 96, 91);		
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				int Lx = arg0.getX(), Ly = arg0.getY();
				if(Lx < 80) {
					Lx = 80;
				}
				if(Lx > 630) {
					Lx = 630;
				}
				if(Ly > 540) {
					Ly = 540;
				}
				if(Ly < 935) {
					Ly = 935;
				}
				
				Red_ball.setBounds(Lx-50, Ly-50, 96, 91);	}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				int Lx = arg0.getX(), Ly = arg0.getY();
				if(Lx < 80) {
					Lx = 80;
				}
				if(Lx > 630) {
					Lx = 630;
				}
				if(Ly > 540) {
					Ly = 540;
				}
				if(Ly < 935) {
					Ly = 935;
				}
				
				Red_ball.setBounds(Lx-50, Ly-50, 96, 91);		}

			@Override
			public void mouseExited(MouseEvent arg0) {
				int Lx = arg0.getX(), Ly = arg0.getY();
				if(Lx < 80) {
					Lx = 80;
				}
				if(Lx > 630) {
					Lx = 630;
				}
				if(Ly < 540) {
					Ly = 540;
				}
				if(Ly > 935) {
					Ly = 935;
				}
				
				Red_ball.setBounds(Lx-50, Ly-50, 96, 91);			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				int Lx = arg0.getX(), Ly = arg0.getY();
				if(Lx < 80) {
					Lx = 80;
				}
				if(Lx > 630) {
					Lx = 630;
				}
				if(Ly > 540) {
					Ly = 540;
				}
				if(Ly < 935) {
					Ly = 935;
				}
				
				Red_ball.setBounds(Lx-50, Ly-50, 96, 91);		}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				int Lx = arg0.getX(), Ly = arg0.getY();
				if(Lx < 80) {
					Lx = 80;
				}
				if(Lx > 630) {
					Lx = 630;
				}
				if(Ly > 540) {
					Ly = 540;
				}
				if(Ly < 935) {
					Ly = 935;
				}
				
				Red_ball.setBounds(Lx-50, Ly-50, 96, 91);		}
			
		}
		
		ImageIcon img = new ImageIcon("./Image/play_last_origin.jpg");
		frame.setIconImage(img.getImage());
		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(img.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		frame.setResizable(false);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 724, 1005);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel Count = new JLabel("");
		Count.setHorizontalAlignment(SwingConstants.CENTER);
		Count.setFont(new Font("Snap ITC", Font.PLAIN, 65));
		Count.setForeground(Color.WHITE);
		Count.setBounds(172, 367, 354, 261);
		panel.add(Count);
		panel.add(Red_ball);
		
		JLabel Yellow_ball = new JLabel(new ImageIcon("./Image/Yellow_ball.png"));
		Yellow_ball.setBounds(326, 476, 64, 57);
		panel.add(Yellow_ball);
		
		JLabel Blue_ball = new JLabel(new ImageIcon("./Image/Blue_ball.png"));
		Blue_ball.setBounds(310, 118, 96, 91);
		panel.add(Blue_ball);
		
		JLabel Red_bong = new JLabel(new ImageIcon("./Image/Red_bong.png"));
		Red_bong.setBounds(660, 23, 64, 1053);
		panel.add(Red_bong);
		
		JLabel Blue_bong = new JLabel(new ImageIcon("./Image/Blue_bong.png"));
		Blue_bong.setBounds(26, 23, 64, 1053);
		panel.add(Blue_bong);
		
		JLabel Yellow_bong = new JLabel(new ImageIcon("./Image/Yellow_bong.png"));
		Yellow_bong.setBounds(0, 23, 346, 83);
		panel.add(Yellow_bong);
		
		JLabel Green_bong = new JLabel(new ImageIcon("./Image/Green_bong.png"));
		Green_bong.setBounds(406, 960, 318, 83);
		panel.add(Green_bong);
		
		JLabel Perple_bong = new JLabel(new ImageIcon("./Image/Perple_bong.png"));
		Perple_bong.setBounds(420, 12, 290, 104);
		panel.add(Perple_bong);
		
		JLabel Blown_bong = new JLabel(new ImageIcon("./Image/Blown_bong.png"));
		Blown_bong.setBounds(26, 950, 297, 104);
		panel.add(Blown_bong);
		
		JLabel Own = new JLabel(new ImageIcon("./Image/Own.png"));
		Own.setBounds(214, 330, 451, 350);
		panel.add(Own);
		
		JLabel Mid_line = new JLabel(new ImageIcon("./Image/Mid_line.png"));
		Mid_line.setBounds(26, 455, 663, 194);
		panel.add(Mid_line);

		panel.addMouseListener(new MyMouseListener());
		panel.addMouseMotionListener(new MyMouseListener());
	}
}
