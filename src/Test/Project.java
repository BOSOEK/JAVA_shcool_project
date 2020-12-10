package Test;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Project {

	private JFrame frame;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Project window = new Project();
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
	int makingRoom = 0;
	String nickName;
	String EnemyName;
	int MyPoint = 0;
	int EnemyPoint = 0;
	int Lx, Ly;
	int Px, Py;
	private JTextField input_nickName;
	private JTextField menu_title;
	private JTextField menu_port;
	int makePort = 0;
	private String[] creater = new String[4];
	private String[] title = new String[4];
	private int[] port = new int[4];
	private int[] player = new int[4];
	
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	
	Socket socket = null;
	ServerSocket serverSocket = null;
	
	public Project() {
		initialize();
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1018, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setTitle("Prick");
		ImageIcon img = new ImageIcon("./Image/P_title.jpg");
		frame.setIconImage(img.getImage());
		
		ImageIcon first_backimg = new ImageIcon("./Image/first_backimg2.jpg");
		/*ImageIcon img1 = new ImageIcon("./Image/play_last.jpg");
		JPanel play_panel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(img1.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		play_panel.setBackground(Color.WHITE);
		play_panel.setBounds(0, 0, 832, 993);
		play_panel.setVisible(false);
		frame.getContentPane().add(play_panel);
		play_panel.setLayout(null);*/
		
		JPanel menu_panel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(first_backimg.getImage(),  0,  0,  null);
				setOpaque(false);
				super.paintComponent(g);
				

				//메뉴 패널 창이 활성화 되면 sql에서 자료 읽어옴
				String url = "jdbc:oracle:thin:@192.168.0.13:1521:xe";
				
				String sql = "select * from PRICK";
				
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection(url, "BOSO", "12345");
					System.out.println("DB 연결 성공");
					st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					rs = st.executeQuery(sql);
				} catch(ClassNotFoundException e) {
					System.out.println("jdbc 드라이버를 찾지 못함");
					e.printStackTrace();
				} catch (SQLException e) {
					System.out.println("DB 연결 실패");
					e.printStackTrace();
				}
			}
		};
		menu_panel.setBounds(0, 0, 1012, 565);
		frame.getContentPane().add(menu_panel);
		menu_panel.setBackground(Color.GRAY);
		menu_panel.setLayout(null);
		menu_panel.setVisible(false);
		
		
		
		
		JButton play_button1 = new JButton("Play");
		play_button1.setBackground(Color.RED);
		play_button1.setForeground(Color.WHITE);
		play_button1.setFont(new Font("Jokerman", Font.PLAIN, 11));
		play_button1.setBounds(323, 138, 60, 52);
		menu_panel.add(play_button1);
		
		JButton play_button2 = new JButton("Play");
		play_button2.setBackground(Color.RED);
		play_button2.setForeground(Color.WHITE);
		play_button2.setFont(new Font("Jokerman", Font.PLAIN, 11));
		play_button2.setBounds(323, 218, 60, 52);
		menu_panel.add(play_button2);
		
		JButton play_button3 = new JButton("Play");
		play_button3.setBackground(Color.RED);
		play_button3.setForeground(Color.WHITE);
		play_button3.setFont(new Font("Jokerman", Font.PLAIN, 11));
		play_button3.setBounds(323, 301, 60, 48);
		menu_panel.add(play_button3);
		
		JButton play_button4 = new JButton("Play");
		play_button4.setBackground(Color.RED);
		play_button4.setForeground(Color.WHITE);
		play_button4.setFont(new Font("Jokerman", Font.PLAIN, 11));
		play_button4.setBounds(323, 385, 60, 54);
		menu_panel.add(play_button4);
		
		menu_title = new JTextField();
		menu_title.setHorizontalAlignment(SwingConstants.CENTER);
		menu_title.setBounds(733, 150, 136, 44);
		menu_panel.add(menu_title);
		menu_title.setColumns(10);
		
		menu_port = new JTextField();
		menu_port.setHorizontalAlignment(SwingConstants.CENTER);
		menu_port.setBounds(739, 253, 130, 44);
		menu_panel.add(menu_port);
		menu_port.setColumns(10);
		
		JButton Hosting_button = new JButton("Hosting");
		Hosting_button.setBackground(new Color(205, 92, 92));
		Hosting_button.setFont(new Font("Jokerman", Font.PLAIN, 40));
		Hosting_button.setForeground(Color.WHITE);
		Hosting_button.setBounds(662, 375, 201, 52);
		menu_panel.add(Hosting_button);
		
		JLabel menu_input_title = new JLabel("title");
		menu_input_title.setFont(new Font("Jokerman", Font.PLAIN, 30));
		menu_input_title.setForeground(Color.WHITE);
		menu_input_title.setHorizontalAlignment(SwingConstants.CENTER);
		menu_input_title.setBounds(610, 136, 144, 58);
		menu_panel.add(menu_input_title);
		
		JLabel menu_input_port = new JLabel("port");
		menu_input_port.setFont(new Font("Jokerman", Font.PLAIN, 30));
		menu_input_port.setForeground(Color.WHITE);
		menu_input_port.setHorizontalAlignment(SwingConstants.CENTER);
		menu_input_port.setBounds(645, 235, 84, 74);
		menu_panel.add(menu_input_port);
		
		JLabel first_title = new JLabel("click nextbutton");
		first_title.setFont(new Font("HY그래픽M", Font.PLAIN, 20));
		first_title.setBackground(Color.DARK_GRAY);
		first_title.setForeground(Color.WHITE);
		first_title.setHorizontalAlignment(SwingConstants.CENTER);
		first_title.setBounds(87, 136, 222, 52);
		first_title.setOpaque(true);
		menu_panel.add(first_title);
		
		JLabel secont_title = new JLabel("click nextbutton");
		secont_title.setBackground(Color.DARK_GRAY);
		secont_title.setFont(new Font("HY그래픽M", Font.PLAIN, 20));
		secont_title.setForeground(Color.WHITE);
		secont_title.setHorizontalAlignment(SwingConstants.CENTER);
		secont_title.setBounds(87, 216, 222, 52);
		secont_title.setOpaque(true);
		menu_panel.add(secont_title);
		
		JLabel third_title = new JLabel("click nextbutton");
		third_title.setBackground(Color.DARK_GRAY);
		third_title.setFont(new Font("HY그래픽M", Font.PLAIN, 20));
		third_title.setForeground(Color.WHITE);
		third_title.setHorizontalAlignment(SwingConstants.CENTER);
		third_title.setBounds(87, 297, 222, 52);
		third_title.setOpaque(true);
		menu_panel.add(third_title);
		
		JLabel four_title = new JLabel("click nextbutton");
		four_title.setBackground(Color.DARK_GRAY);
		four_title.setForeground(Color.WHITE);
		four_title.setFont(new Font("HY그래픽M", Font.PLAIN, 20));
		four_title.setHorizontalAlignment(SwingConstants.CENTER);
		four_title.setBounds(87, 384, 222, 52);
		four_title.setOpaque(true);
		menu_panel.add(four_title);
		

		JButton menu_befor_button = new JButton("<");
		menu_befor_button.setBackground(Color.GRAY);
		menu_befor_button.setFont(new Font("Jokerman", Font.PLAIN, 30));
		menu_befor_button.setForeground(Color.WHITE);
		menu_befor_button.setBounds(417, 97, 60, 27);
		menu_befor_button.setBorderPainted(true);
		menu_befor_button.setFocusPainted(false);
		menu_befor_button.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		menu_befor_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i<3;i++) {
					try {
						rs.previous();
					} catch(SQLException e1) {
						e1.printStackTrace();
					}
				}
				for(int i = 3; i>=0; i--) {
					try {
						if(rs.previous()) {
							creater[i] = rs.getString(1);
							title[i] = rs.getString(2);
							port[i] = rs.getInt(3);
							player[i] = rs.getInt(4);
						} else {
							creater[i] = null;
							title[i] = null;
							port[i] = 0;
							player[i] = 0;
						}
					} catch(SQLException e1) {
						e1.printStackTrace();
					}
				}
				first_title.setText(creater[0] + " : " + title[0]);
				if(player[0] == 1) {
					play_button1.setBackground(new Color(15,233,0));
				} else {
					play_button1.setBackground(new Color(251,33,13));
				}
				secont_title.setText(creater[1] + " : " + title[1]);
				if(player[1] == 1) {
					play_button2.setBackground(new Color(15,233,0));
				} else {
					play_button2.setBackground(new Color(251,33,13));
				}
				third_title.setText(creater[2] + " : " + title[2]);
				if(player[2] == 1) {
					play_button3.setBackground(new Color(15,233,0));
				} else {
					play_button3.setBackground(new Color(251,33,13));
				}
				four_title.setText(creater[3] + " : " + title[3]);
				if(player[3] == 1) {
					play_button4.setBackground(new Color(15,233,0));
				} else {
					play_button4.setBackground(new Color(251,33,13));
				}
			}
		});
		menu_panel.add(menu_befor_button);
		
		JButton menu_next_button = new JButton(">");
		menu_next_button.setBackground(Color.GRAY);
		menu_next_button.setFont(new Font("Jokerman", Font.PLAIN, 30));
		menu_next_button.setForeground(new Color(230, 230, 250));
		menu_next_button.setBounds(507, 97, 60, 27);
		menu_next_button.setBorderPainted(true);
		menu_next_button.setFocusPainted(false);
		menu_next_button.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		menu_next_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i<4; i++) {
					try {
						if(rs.next()) {
							creater[i] = rs.getString(1);
							title[i] = rs.getString(2);
							port[i] = rs.getInt(3);
							player[i] = rs.getInt(4);
						} else {
							creater[i] = null;
							title[i] = null;
							port[i] = 0;
							player[i] = 0;
						}
					} catch(SQLException e1) {
						e1.printStackTrace();
					}
				}
				first_title.setText(creater[0] + " : " + title[0]);
				if(player[0] == 1) {
					play_button1.setBackground(new Color(15,233,0));
				} else {
					play_button1.setBackground(new Color(251,33,13));
				}
				secont_title.setText(creater[1] + " : " + title[1]);
				if(player[1] == 1) {
					play_button2.setBackground(new Color(15,233,0));
				} else {
					play_button2.setBackground(new Color(251,33,13));
				}
				third_title.setText(creater[2] + " : " + title[2]);
				if(player[2] == 1) {
					play_button3.setBackground(new Color(15,233,0));
				} else {
					play_button3.setBackground(new Color(251,33,13));
				}
				four_title.setText(creater[3] + " : " + title[3]);
				if(player[3] == 1) {
					play_button4.setBackground(new Color(15,233,0));
				} else {
					play_button4.setBackground(new Color(251,33,13));
				}
			}
		});
		menu_panel.add(menu_next_button);

		
		JPanel wait_panel = new JPanel();
		wait_panel.setBounds(0, 0, 1012, 565);
		frame.getContentPane().add(wait_panel);
		wait_panel.setVisible(false);
		wait_panel.setLayout(null);
		

		
		Hosting_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//창 바꿈
				wait_panel.setVisible(true);
				menu_panel.setVisible(false);
				
			}
		});
		
		JLabel lblNewLabel_9 = new JLabel("Wait.....");
		lblNewLabel_9.setFont(new Font("Jokerman", Font.PLAIN, 40));
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setBounds(382, 141, 199, 62);
		wait_panel.add(lblNewLabel_9);
		
		JButton wait_back_button = new JButton("Host");
		wait_back_button.setFont(new Font("Jokerman", Font.PLAIN, 35));
		wait_back_button.setBounds(414, 273, 118, 52);
		wait_panel.add(wait_back_button);
		
		wait_back_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = menu_title.getText();
				int port = Integer.parseInt(menu_port.getText());
				
				//sql에 정보 저장
				DBConnect dc = new DBConnect();
				con = dc.makeCon();
				String sql = "insert into PRICK values(?, ?, ?, ?)";
				try {
					ps = con.prepareStatement(sql);
					ps.setString(1, nickName);
					ps.setString(2,  title);
					ps.setInt(3, port);
					ps.setInt(4, 1);
					if(ps.executeUpdate()>=1) {
						System.out.println("insert 성공");
					} else {
						System.out.println("insert 실패");
					}
				} catch (SQLException e1) {
					System.out.println("오류뜸");
					e1.printStackTrace();
				}
				makePort = port;
				
				
				try {
					serverSocket = new ServerSocket(makePort);
					System.out.println("서버 대기");
					socket = serverSocket.accept();
					System.out.println("서버 연결됨");
					wait_panel.setVisible(false);
					//플레이어 값 2로 수정
					sql = "select * from PRICK where port=?";
					try {
						ps = con.prepareStatement(sql);
						ps.setInt(1, makePort);
						rs = ps.executeQuery();
						
						if(rs.next()) {
							sql = "update PRICK set player=? where port=?";
							ps = con.prepareStatement(sql);
							ps.setInt(1, 2);
							ps.setInt(2, makePort);
							if(ps.executeUpdate()>=1) {
								System.out.println("update 성공");
							} else {
								System.out.println("update 실패");
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					//게임 플레이
					gamePlayServer();
					
					
				} catch(Exception e1) {
					System.out.println(e1.getMessage());
				}
				
				
				
			}
		});
		
		JPanel Start_panel = new JPanel() {
			public void paintComponent(Graphics g) {
		g.drawImage(first_backimg.getImage(),  0,  0,  null);
		setOpaque(false);
		super.paintComponent(g);
			}
		};
		Start_panel.setBounds(0, 0, 1012, 565);
		frame.getContentPane().add(Start_panel);
		Start_panel.setLayout(null);
		Start_panel.setVisible(true);
		
		JButton go_inRoom_Button = new JButton("start!");
		go_inRoom_Button.setBackground(new Color(128, 0, 128));
		go_inRoom_Button.setForeground(Color.WHITE);
		go_inRoom_Button.setFont(new Font("Jokerman", Font.PLAIN, 40));
		go_inRoom_Button.setBounds(688, 370, 170, 58);
		Start_panel.add(go_inRoom_Button);
		go_inRoom_Button.setOpaque(false);
		go_inRoom_Button.setBorderPainted(true);
		go_inRoom_Button.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		
		
		JLabel lblNewLabel = new JLabel("Prick");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Jokerman", Font.PLAIN, 70));
		lblNewLabel.setBounds(132, 189, 202, 117);
		Start_panel.add(lblNewLabel);
		
		input_nickName = new JTextField();
		input_nickName.setHorizontalAlignment(SwingConstants.CENTER);
		input_nickName.setFont(new Font("굴림", Font.PLAIN, 20));
		input_nickName.setBounds(655, 137, 229, 58);
		Start_panel.add(input_nickName);
		input_nickName.setColumns(10);
		input_nickName.setBorder(null);
		
		JTextPane txtpnNickname = new JTextPane();
		txtpnNickname.setFont(new Font("Jokerman", Font.PLAIN, 40));
		txtpnNickname.setBackground(new Color(255, 255, 255));
		txtpnNickname.setForeground(new Color(255, 255, 255));
		txtpnNickname.setText("nickname ");
		txtpnNickname.setBounds(675, 207, 197, 58);
		Start_panel.add(txtpnNickname);
		txtpnNickname.setOpaque(false);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Jokerman", Font.PLAIN, 40));
		textPane.setForeground(new Color(255, 255, 255));
		textPane.setText("&");
		textPane.setBounds(746, 287, 112, 58);
		Start_panel.add(textPane);
		textPane.setOpaque(false);
		LineBorder Start_panel_border = new LineBorder(Color.WHITE, 1);

		go_inRoom_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nickName = input_nickName.getText();
				Start_panel.setVisible(false);
				menu_panel.setVisible(true);
			}
		});
		
		play_button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(player[0] == 1) {
					String myIp = null;
					InetAddress local = null;
					try {
						local = InetAddress.getLocalHost();
					} catch (UnknownHostException e1) {
						e1.printStackTrace();
					}
					myIp = local.getHostAddress();
					
					try {
						socket = new Socket(myIp, port[0]);
						menu_panel.setVisible(false);
						gamePlayClient();
					} catch (Exception e1) {
						System.out.println(e1.getMessage());
					}
				}
			}
		});
		
		play_button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(player[1] == 1) {
					String myIp = null;
					InetAddress local = null;
					try {
						local = InetAddress.getLocalHost();
					} catch (UnknownHostException e1) {
						e1.printStackTrace();
					}
					myIp = local.getHostAddress();
					
					try {
						socket = new Socket(myIp, port[1]);
						menu_panel.setVisible(false);
						gamePlayClient();
					} catch (Exception e1) {
						System.out.println(e1.getMessage());
					}
				}
			}
		});
		
		play_button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(player[2] == 1) {
					String myIp = null;
					InetAddress local = null;
					try {
						local = InetAddress.getLocalHost();
					} catch (UnknownHostException e1) {
						e1.printStackTrace();
					}
					myIp = local.getHostAddress();
					
					try {
						socket = new Socket(myIp, port[2]);
						menu_panel.setVisible(false);
						gamePlayClient();
					} catch (Exception e1) {
						System.out.println(e1.getMessage());
					}
				}
			}
		});
		
		play_button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(player[3] == 1) {
					String myIp = null;
					InetAddress local = null;
					try {
						local = InetAddress.getLocalHost();
					} catch (UnknownHostException e1) {
						e1.printStackTrace();
					}
					myIp = local.getHostAddress();
					
					try {
						socket = new Socket(myIp, port[3]);
						menu_panel.setVisible(false);
						gamePlayClient();
					} catch (Exception e1) {
						System.out.println(e1.getMessage());
					}
				}
			}
		});
		
	}
	
	void gamePlayServer() {
		frame.setBounds(600, 50, 730, 1040);
		ImageIcon img1 = new ImageIcon("./Image/play_last_origin.jpg");
		//맨처음 닉네음 보냄
		try {
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			writer.println(nickName);
			writer.flush();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		//맨처음 상대 닉네임 받음
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			EnemyName = reader.readLine();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		

		
		JPanel play_panel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(img1.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		play_panel.setBackground(Color.BLACK);
		play_panel.setBounds(0, 0, 832, 993);
		frame.getContentPane().add(play_panel);
		play_panel.setLayout(null);
		play_panel.setVisible(true);
		
		ImageIcon imge = new ImageIcon("./Image/play_last.jpg");
		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(imge.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel.setBounds(0, 0, 712, 993);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setVisible(false);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("휴먼매직체", Font.PLAIN, 55));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(0, 272, 712, 303);
		panel.add(lblNewLabel);
		
		//마우스 보내는 스레드
		class sendMouse extends Thread {
			public void run() {
				while(true) {
					try {
						PrintWriter writer = new PrintWriter(socket.getOutputStream());
						writer.println("" + Lx  + " " + Ly + " " + Px + " " + Py + " " + MyPoint + " " + EnemyPoint);
						writer.flush();
					} catch(Exception e) {
						System.out.println(e.getMessage());
					}
					if(MyPoint > 6 || EnemyPoint > 6) {
						break;
					}
				}
				try {
					socket.close();
				} catch(Exception ignored) {}
				play_panel.setVisible(false);
				panel.setVisible(true);
				if(MyPoint > EnemyPoint) {
					lblNewLabel.setText(nickName + "님이 승리하셨습니다.");
				} else {
					lblNewLabel.setText(EnemyName + "님이 승리하셨습니다.");
				}
				
			}
		}
		
		
		JLabel Red_ball = new JLabel(new ImageIcon("./Image/Red_ball.png"));
		class MyMouseListener implements MouseListener, MouseMotionListener {

			@Override
			public void mouseDragged(MouseEvent arg0) {
				Lx = arg0.getX();
				Ly = arg0.getY();
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
				Lx = arg0.getX();
				Ly = arg0.getY();
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
				Lx = arg0.getX();
				Ly = arg0.getY();
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
				Lx = arg0.getX();
				Ly = arg0.getY();
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
				Lx = arg0.getX();
				Ly = arg0.getY();
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
				Lx = arg0.getX();
				Ly = arg0.getY();
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
				Lx = arg0.getX();
				Ly = arg0.getY();
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
		

		
		
		play_panel.add(Red_ball);
		play_panel.addMouseListener(new MyMouseListener());
		play_panel.addMouseMotionListener(new MyMouseListener());

		JLabel Puck = new JLabel(new ImageIcon("./Image/Yellow_ball.png"));
		Puck.setBounds(326, 476, 64, 57);
		play_panel.add(Puck);
		
		JLabel Blue_ball = new JLabel(new ImageIcon("./Image/Blue_ball.png"));
		Blue_ball.setBounds(310, 118, 96, 91);
		play_panel.add(Blue_ball);
		
		JLabel Red_bong = new JLabel(new ImageIcon("./Image/Red_bong.png"));
		Red_bong.setBounds(660, 23, 64, 1053);
		play_panel.add(Red_bong);
		
		JLabel Blue_bong = new JLabel(new ImageIcon("./Image/Blue_bong.png"));
		Blue_bong.setBounds(26, 23, 64, 1053);
		play_panel.add(Blue_bong);
		
		JLabel Yellow_bong = new JLabel(new ImageIcon("./Image/Yellow_bong.png"));
		Yellow_bong.setBounds(0, 23, 346, 83);
		play_panel.add(Yellow_bong);
		
		JLabel Green_bong = new JLabel(new ImageIcon("./Image/Green_bong.png"));
		Green_bong.setBounds(406, 960, 318, 83);
		play_panel.add(Green_bong);
		
		JLabel Perple_bong = new JLabel(new ImageIcon("./Image/Perple_bong.png"));
		Perple_bong.setBounds(420, 12, 290, 104);
		play_panel.add(Perple_bong);
		
		JLabel Blown_bong = new JLabel(new ImageIcon("./Image/Blown_bong.png"));
		Blown_bong.setBounds(26, 950, 297, 104);
		play_panel.add(Blown_bong);
		
		
		JLabel myNickName = new JLabel("");
		myNickName.setFont(new Font("휴먼매직체", Font.PLAIN, 30));
		myNickName.setForeground(Color.GRAY);
		myNickName.setBounds(68, 896, 219, 83);
		myNickName.setText(nickName);
		play_panel.add(myNickName);
		
		JLabel myPoint = new JLabel("");
		myPoint.setFont(new Font("휴먼매직체", Font.PLAIN, 40));
		myPoint.setForeground(Color.GRAY);
		myPoint.setBounds(627, 896, 64, 83);
		myPoint.setText(Integer.toString(MyPoint));
		play_panel.add(myPoint);
		
		JLabel enemyName = new JLabel("");
		enemyName.setFont(new Font("휴먼매직체", Font.PLAIN, 30));
		enemyName.setForeground(Color.GRAY);
		enemyName.setBounds(57, 31, 135, 75);
		enemyName.setText(EnemyName);
		play_panel.add(enemyName);
		
		JLabel enemyPoint = new JLabel("");
		enemyPoint.setFont(new Font("휴먼매직체", Font.PLAIN, 40));
		enemyPoint.setForeground(Color.GRAY);
		enemyPoint.setBounds(627, 61, 62, 45);
		enemyPoint.setText(Integer.toString(EnemyPoint));
		play_panel.add(enemyPoint);		

		play_panel.add(Red_ball);
		play_panel.addMouseListener(new MyMouseListener());
		play_panel.addMouseMotionListener(new MyMouseListener());
		

		JLabel Own = new JLabel(new ImageIcon("./Image/Own.png"));
		Own.setBounds(214, 330, 451, 350);
		play_panel.add(Own);
		
		JLabel Mid_line = new JLabel(new ImageIcon("./Image/Mid_line.png"));
		Mid_line.setBounds(26, 455, 663, 194);
		play_panel.add(Mid_line);

		//퍽 움직이는 스레드
		class move extends Thread {
			public void run() {
				while(MyPoint < 8 || EnemyPoint < 8) {
					int X = 0, Y = 0;
					Random random = new Random();
					int ran1 = random.nextInt(2);
					int ran2 = random.nextInt(2);
					if(ran1 == 0) {
						X = 1;
					} else {
						X = -1;
					}
					if(ran2 == 0) {
						Y = 1;
					} else {
						Y = -1;
					}
					Puck.setLocation(326, 476);
					while(true) {
						//레드 볼 위치 크기
						Point redPoint = Red_ball.getLocation();
						//레드볼 왼쪽위
						int RedA_x = redPoint.x+10, RedA_y = redPoint.y+10;
						//레드볼 오른쪽위
						int RedB_x = redPoint.x + 96 -10, RedB_y = redPoint.y +10;
						//레드볼 오른쪽아래
						int RedC_x = redPoint.x + 96 -10, RedC_y = redPoint.y + 91 -10;
						//레드볼 왼쪽 아래
						int RedD_x = redPoint.x +10, RedD_y = redPoint.y + 91 -10;
						
						//블루 볼 위치 크기
						Point bluePoint = Blue_ball.getLocation();
						//불르볼 왼쪽위
						int BlueA_x = bluePoint.x+10, BlueA_y = bluePoint.y+10;
						//블루볼 오른쪽위
						int BlueB_x = bluePoint.x + 96 -10, BlueB_y = bluePoint.y +10;
						//블루볼 오른쪽아래
						int BlueC_x = bluePoint.x + 96 -10, BlueC_y = bluePoint.y + 91 -10;
						//블루볼 왼쪽 아래
						int BlueD_x = bluePoint.x +10, BlueD_y = bluePoint.y + 91 -10;
						
		
						Point puckPoint = Puck.getLocation();
						int PuckA_x = puckPoint.x+5, PuckA_y = puckPoint.y+5;
						int PuckB_x = puckPoint.x+ 64-5 , PuckB_y = puckPoint.y+5;
						int PuckC_x = puckPoint.x + 64-5, PuckC_y = puckPoint.y + 57-5;
						int PuckD_x = puckPoint.x+5, PuckD_y = puckPoint.y + 57-5;
						
						if(puckPoint.x < 45) {
							X *= -1;
							Y *= 1;
						}
						else if(puckPoint.x > 620) {
							X *= -1;
							Y *= 1;
						}
						else if(puckPoint.y > 910 && (puckPoint.x < 270 || puckPoint.x > 430)) {
							X *= 1;
							Y *= -1;
						}
						else if(puckPoint.y > 910 && (puckPoint.x > 270 || puckPoint.x < 430)) {  //내 골대에 들어갈때
							EnemyPoint++;
							break;
						}
						else if(puckPoint.y < 40 && (puckPoint.x < 270 || puckPoint.x > 430)) {
							X *= 1;
							Y *= -1;
						}
						else if(puckPoint.y < 40 && (puckPoint.x > 270 || puckPoint.x > 430)) {  //상대 골대 들어갈때
							MyPoint++;
							break;
						}
						
						//퍽이 빨간 공의 정면에 맞을때
						else if(RedA_y == PuckC_y && ((PuckC_x > RedA_x && PuckC_x < RedB_x) || (PuckD_x > RedA_x && PuckD_x < RedB_x))) {
							X *= 0;
							Y *= -1;
						}
						//퍽이 빨간 공의 오른쪽에 맞을때
						else if(RedB_x == PuckA_x && ((PuckA_y > RedB_y && PuckA_y < RedC_y) || (PuckD_y > RedB_y && PuckD_y < RedC_y))) {
							X = 1;
							Y = -1;
						}
						//퍽이 빨간 공의 왼쪽에 맞을때
						else if(RedA_x == PuckB_x && ((PuckB_y > RedA_y && PuckB_y < RedD_y) || (PuckC_y > RedA_y && PuckC_y < RedD_y))) {
							X = -1;
							Y = -1;
						}
						//퍽이 빨간 공의 아래에 맞을때
						else if(RedD_y == PuckA_y && ((PuckA_x > RedD_x && PuckA_x < RedC_x) || (PuckB_x > RedD_x && PuckB_x < RedC_x))) {
							X *= -1;
							Y *= 1;
						}
						
						//퍽이 파란 공의 정면에 맞을때
						else if(BlueA_y == PuckC_y && ((PuckC_x > BlueA_x && PuckC_x < BlueB_x) || (PuckD_x > BlueA_x && PuckD_x < BlueB_x))) {
							X *= 0;
							Y *= -1;
						}
						//퍽이 파란 공의 오른쪽에 맞을때
						else if(BlueB_x == PuckA_x && ((PuckA_y > BlueB_y && PuckA_y < BlueC_y) || (PuckD_y > BlueB_y && PuckD_y < BlueC_y))) {
							X = -1;
							Y = -1;
						}
						//퍽이 파란 공의 왼쪽에 맞을때
						else if(BlueA_x == PuckB_x && ((PuckB_y > BlueA_y && PuckB_y < BlueD_y) || (PuckC_y > BlueA_y && PuckC_y < BlueD_y))) {
							X = 1;
							Y = -1;
						}
						//퍽이 파란 공의 아래에 맞을때
						else if(BlueD_y == PuckA_y && ((PuckA_x > BlueD_x && PuckA_x < BlueC_x) || (PuckB_x > BlueD_x && PuckB_x < BlueC_x))) {
							X *= -1;
							Y *= 1;
						}
						
						
						
						Px = puckPoint.x+X;
						Py = puckPoint.y+Y;
						Puck.setLocation(puckPoint.x+X, puckPoint.y+Y);
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
				
			}
		}
		
		
		
		move mv = new move();
		mv.start();

		sendMouse sM = new sendMouse();
		sM.start();
		
		//마우스 받는 스레드
		class receiveMouse extends Thread {
			public void run() {
				try {
					BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					while(true) {
						String str = reader.readLine();
						String[] Location = str.split(" ");
						int Lx = Integer.parseInt(Location[0]);
						int Ly = Integer.parseInt(Location[1]);
						EnemyPoint = Integer.parseInt(Location[2]);
						enemyPoint.setText(Integer.toString(EnemyPoint));
						Blue_ball.setLocation(610-Lx, 915-Ly);
					}
				} catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
		
		receiveMouse rM = new receiveMouse();
		rM.start();
		
		
		
	}
	
	void gamePlayClient() {
		frame.setBounds(600, 50, 730, 1040);
		ImageIcon img1 = new ImageIcon("./Image/play_last_origin.jpg");
		
		//맨처음 닉네음 보냄
		try {
			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			writer.println(nickName);
			writer.flush();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		//맨처음 상대 닉네임 받음
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			EnemyName = reader.readLine();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		JLabel Red_ball = new JLabel(new ImageIcon("./Image/Red_ball.png"));

		JPanel play_panel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(img1.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		play_panel.setBackground(Color.WHITE);
		play_panel.setBounds(0, 0, 832, 993);
		play_panel.setVisible(true);
		frame.getContentPane().add(play_panel);
		play_panel.setLayout(null);
		
		ImageIcon imge = new ImageIcon("./Image/play_last.jpg");
		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(imge.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel.setBounds(0, 0, 712, 993);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		panel.setVisible(false);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setFont(new Font("휴먼매직체", Font.PLAIN, 55));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(0, 272, 712, 303);
		panel.add(lblNewLabel);
		
		//마우스 보내는 스레드
		class sendMouse extends Thread {
			int Lx, Ly;
			sendMouse(int Lx, int Ly) {
				this.Lx = Lx;
				this.Ly = Ly;
			}
			public void run() {
				try {
					PrintWriter writer = new PrintWriter(socket.getOutputStream());
					writer.println("" + Lx  + " " + Ly + " " + MyPoint);
					writer.flush();
				} catch(Exception e) {
					System.out.println(e.getMessage());
				} 
				
				if(MyPoint > 6 || EnemyPoint > 6) {
					try {
						socket.close();
					} catch(Exception ignored) {}
					play_panel.setVisible(false);
					panel.setVisible(true);
					if(MyPoint > EnemyPoint) {
						lblNewLabel.setText(nickName + "님이 승리하셨습니다.");
					} else {
						lblNewLabel.setText(EnemyName + "님이 승리하셨습니다.");
					}
				}
			}
		}
				
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
				sendMouse sM = new sendMouse(Lx-50, Ly-50);
				sM.start();
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
				sendMouse sM = new sendMouse(Lx-50, Ly-50);
				sM.start();
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
				sendMouse sM = new sendMouse(Lx-50, Ly-50);
				sM.start();
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
				sendMouse sM = new sendMouse(Lx-50, Ly-50);
				sM.start();
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
				sendMouse sM = new sendMouse(Lx-50, Ly-50);
				sM.start();
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
				sendMouse sM = new sendMouse(Lx-50, Ly-50);
				sM.start();
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
				sendMouse sM = new sendMouse(Lx-50, Ly-50);
				sM.start();
				Red_ball.setBounds(Lx-50, Ly-50, 96, 91);		}
			
		}
		
		
		
		play_panel.add(Red_ball);
		play_panel.addMouseListener(new MyMouseListener());
		play_panel.addMouseMotionListener(new MyMouseListener());
		
		JLabel Puck = new JLabel(new ImageIcon("./Image/Yellow_ball.png"));
		Puck.setBounds(326, 476, 64, 57);
		play_panel.add(Puck);
		
		JLabel Blue_ball = new JLabel(new ImageIcon("./Image/Blue_ball.png"));
		Blue_ball.setBounds(310, 118, 96, 91);
		play_panel.add(Blue_ball);
		
		JLabel Red_bong = new JLabel(new ImageIcon("./Image/Red_bong.png"));
		Red_bong.setBounds(660, 23, 64, 1053);
		play_panel.add(Red_bong);
		
		JLabel Blue_bong = new JLabel(new ImageIcon("./Image/Blue_bong.png"));
		Blue_bong.setBounds(26, 23, 64, 1053);
		play_panel.add(Blue_bong);
		
		JLabel Yellow_bong = new JLabel(new ImageIcon("./Image/Yellow_bong.png"));
		Yellow_bong.setBounds(0, 23, 346, 83);
		play_panel.add(Yellow_bong);
		
		JLabel Green_bong = new JLabel(new ImageIcon("./Image/Green_bong.png"));
		Green_bong.setBounds(406, 960, 318, 83);
		play_panel.add(Green_bong);
		
		JLabel Perple_bong = new JLabel(new ImageIcon("./Image/Perple_bong.png"));
		Perple_bong.setBounds(420, 12, 290, 104);
		play_panel.add(Perple_bong);
		
		JLabel Blown_bong = new JLabel(new ImageIcon("./Image/Blown_bong.png"));
		Blown_bong.setBounds(26, 950, 297, 104);
		play_panel.add(Blown_bong);
		
		
		JLabel myNickName = new JLabel("");
		myNickName.setFont(new Font("휴먼매직체", Font.PLAIN, 30));
		myNickName.setForeground(Color.GRAY);
		myNickName.setBounds(68, 896, 219, 83);
		myNickName.setText(nickName);
		play_panel.add(myNickName);
		
		JLabel myPoint = new JLabel("");
		myPoint.setFont(new Font("휴먼매직체", Font.PLAIN, 40));
		myPoint.setForeground(Color.GRAY);
		myPoint.setBounds(627, 896, 64, 83);
		myPoint.setText(Integer.toString(MyPoint));
		play_panel.add(myPoint);
		
		JLabel enemyName = new JLabel("");
		enemyName.setFont(new Font("휴먼매직체", Font.PLAIN, 30));
		enemyName.setForeground(Color.GRAY);
		enemyName.setBounds(57, 31, 135, 75);
		enemyName.setText(EnemyName);
		play_panel.add(enemyName);
		
		JLabel enemyPoint = new JLabel("");
		enemyPoint.setFont(new Font("휴먼매직체", Font.PLAIN, 40));
		enemyPoint.setForeground(Color.GRAY);
		enemyPoint.setText(Integer.toString(EnemyPoint));
		enemyPoint.setBounds(627, 61, 62, 45);
		play_panel.add(enemyPoint);

		
		play_panel.add(Red_ball);
		play_panel.addMouseListener(new MyMouseListener());
		play_panel.addMouseMotionListener(new MyMouseListener());

		JLabel Own = new JLabel(new ImageIcon("./Image/Own.png"));
		Own.setBounds(214, 330, 451, 350);
		play_panel.add(Own);
		
		JLabel Mid_line = new JLabel(new ImageIcon("./Image/Mid_line.png"));
		Mid_line.setBounds(26, 455, 663, 194);
		play_panel.add(Mid_line);
		
		//마우스 받는 스레드
		class receiveMouse extends Thread {
			public void run() {
				try {
					BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					while(true) {
						String str = reader.readLine();
						String[] Location = str.split(" ");
						int Lx = Integer.parseInt(Location[0]);
						int Ly = Integer.parseInt(Location[1]);
						Px = Integer.parseInt(Location[2]);
						Py = Integer.parseInt(Location[3]);
						EnemyPoint = Integer.parseInt(Location[4]);
						enemyPoint.setText(Integer.toString(EnemyPoint));
						MyPoint = Integer.parseInt(Location[5]);
						myPoint.setText(Integer.toString(MyPoint));
						Puck.setLocation(660-Px, 955-Py);
						Blue_ball.setLocation(610-Lx+50, 915-Ly+50);
					}
				} catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
		
		receiveMouse rM = new receiveMouse();
		rM.start();
		
		
		
	}
}