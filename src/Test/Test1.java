package Test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class Test1 {

	private JFrame frame;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test1 window = new Test1();
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
	private JTextField input_nickName;
	private JTextField menu_title;
	private JTextField menu_port;
	int makePort = 0;
	private String[] creater = new String[4];
	private String[] title = new String[4];
	private int[] port = new int[4];
	private int[] player = new int[4];
	private String[] ip = new String[4];
	
	Connection cont = null;
	Statement stt = null;
	ResultSet rst = null;
	
	public Test1() {
		initialize();
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1018, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setTitle("Prick");
		ImageIcon img = new ImageIcon("./Image/P_title.jpg");
		frame.setIconImage(img.getImage());
		
		ImageIcon first_backimg = new ImageIcon("./Image/first_backimg2.jpg");
		
		
		JPanel menu_panel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(first_backimg.getImage(),  0,  0,  null);
				setOpaque(false);
				super.paintComponent(g);
				

				//메뉴 패널 창이 활성화 되면 sql에서 자료 읽어옴
				String url = "jdbc:oracle:thin:@10.120.73.173:1521:xe";
				
				String sql = "select * from PRICK";
				
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					cont = DriverManager.getConnection(url, "BOSO", "12345");
					System.out.println("DB 연결 성공");
					stt = cont.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					rst = stt.executeQuery(sql);
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
						rst.previous();
					} catch(SQLException e1) {
						e1.printStackTrace();
					}
				}
				for(int i = 3; i>=0; i--) {
					try {
						if(rst.previous()) {
							creater[i] = rst.getString(1);
							title[i] = rst.getString(2);
							port[i] = rst.getInt(3);
							player[i] = rst.getInt(4);
							ip[i] = rst.getString(5);
						} else {
							creater[i] = null;
							title[i] = null;
							port[i] = 0;
							player[i] = 0;
							ip[i] = null;
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
						if(rst.next()) {
							creater[i] = rst.getString(1);
							title[i] = rst.getString(2);
							port[i] = rst.getInt(3);
							player[i] = rst.getInt(4);
							ip[i] = rst.getString(5);
						} else {
							creater[i] = null;
							title[i] = null;
							port[i] = 0;
							player[i] = 0;
							ip[i] = null;
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
		
		JPanel wait_panel = new JPanel();
		wait_panel.setBounds(0, 0, 1012, 565);
		frame.getContentPane().add(wait_panel);
		wait_panel.setVisible(false);
		wait_panel.setLayout(null);
		
		JLabel lblNewLabel_9 = new JLabel("Wait.....");
		lblNewLabel_9.setFont(new Font("Jokerman", Font.PLAIN, 40));
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setBounds(382, 141, 199, 62);
		wait_panel.add(lblNewLabel_9);
		
		JButton wait_back_button = new JButton("Back");
		wait_back_button.setFont(new Font("Jokerman", Font.PLAIN, 35));
		wait_back_button.setBounds(414, 273, 118, 52);
		wait_panel.add(wait_back_button);
		LineBorder Start_panel_border = new LineBorder(Color.WHITE, 1);
		
		wait_back_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = null;
				DBConnect dc = new DBConnect();
				con = dc.makeCon();
				ResultSet rs = null;
				PreparedStatement ps = null;
				String sql = "select * from PRICK where port=?";
				try {
					ps = con.prepareStatement(sql);
					ps.setInt(1, makePort);
					rs = ps.executeQuery();
					if(rs.next()) {
						sql = "delete from PRICK where port=?";
						ps=con.prepareStatement(sql);
						ps.setInt(1, makePort);
						if(ps.executeUpdate()>0) {
							System.out.println("delete 성공");
						} else {
							System.out.println("delete 실패");
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				makePort = 0;
				//menu 패널로 나가기
				wait_panel.setVisible(false);
				menu_panel.setVisible(true);
			}
		});

		go_inRoom_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nickName = input_nickName.getText();
				Start_panel.setVisible(false);
				menu_panel.setVisible(true);
			}
		});
		
		

		
		Hosting_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = menu_title.getText();
				int port = Integer.parseInt(menu_port.getText());
				InetAddress local = null;
				try {
					local = InetAddress.getLocalHost();
				} catch (UnknownHostException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				String IpAddress = local.getHostAddress();
				//sql에 정보 저장
				Connection con = null;
				DBConnect dc = new DBConnect();
				con = dc.makeCon();
				
				PreparedStatement ps = null;
				String sql = "insert into PRICK values(?, ?, ?, ?, ?)";
				try {
					ps = con.prepareStatement(sql);
					ps.setString(1, nickName);
					ps.setString(2,  title);
					ps.setInt(3, port);
					ps.setInt(4, 1);
					ps.setString(5, IpAddress);
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
				//창 바꿈
				menu_panel.setVisible(false);
				wait_panel.setVisible(true);
				
				
				//서버 열기
				ServerSocket serverSocket = null;
				Socket socket = null;
				
				
				
			}
		});
	}
	
}
