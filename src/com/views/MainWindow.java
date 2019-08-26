/*视屏播放器主界面*/

package com.views;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.EventQueue;
import java.awt.MenuBar;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.command.Command;
import com.command.ExitCommand;
import com.command.FullScreenCommand;
import com.command.JumpToCommand;
import com.command.OriginalScreenCommand;
import com.command.PauseCommand;
import com.command.PlayCommand;
import com.command.SetVolCommand;
import com.command.StopCommand;
import com.invoker.Invoker;
import com.main.PlayerMain;
import com.main.PlayerMain;
import com.views.PlayListFrame;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;

public class MainWindow extends JFrame {

	private JPanel contentPane; // 顶层容器，整个播放页面的容器
	private JMenuBar menuBar; // 菜单栏
	private JMenu mnFile, mnSetting, mnHelp; // 文件菜单
	private JMenuItem mnOpenVideo, mnExit, mnOpenSubtitle; // 文件菜单子目录，打开视屏、退出
	private JPanel panel; // 控制区域容器
	private JProgressBar progress; // 进度条
	private JPanel controlPanel; // 控制按钮容器
	private JButton btnStop, btnPlay; // 控制按钮，停止、播放、暂停
	private JSlider slider; // 声音控制块
	private JButton btnFullScreen;// 全屏按钮
	private int flag = 0;
	private KeyBoradControl kBoradControl;
	private static PlayListFrame playListFrame;

	EmbeddedMediaPlayerComponent playerComponent; // 媒体播放器组件

	String btnString = new String(">");
	private JButton btnList;
	private JButton btnBackward;
	private JButton btnForward_1;

	public static void main(String[] args) {

	}

	// MainWindow构造方法，创建视屏播放的主界面
	public MainWindow() {
		
PlayerMain playerMain= new PlayerMain();
		
		Command exitCommand=new ExitCommand(playerMain);
		Command fullScreenCommand=new FullScreenCommand(playerMain);
		Command jumpToCommand=new JumpToCommand(playerMain);
		Command originalScreenCommand = new OriginalScreenCommand(playerMain);
		Command pauseCommand = new PauseCommand(playerMain);
		Command playCommand = new PlayCommand(playerMain);
		Command setVolCommand = new SetVolCommand(playerMain);
		Command stopCommand = new StopCommand(playerMain);
		
		
		final Invoker frameInvoker=new Invoker();
		
		frameInvoker.setFullScreenCommand(fullScreenCommand);
		frameInvoker.setOriginalScreenCommand(originalScreenCommand);
		frameInvoker.setExitCommand(exitCommand);
		frameInvoker.setJumpToCommand(jumpToCommand);
		frameInvoker.setPauseCommand(pauseCommand);
		frameInvoker.setPlayCommand(playCommand);
		frameInvoker.setSetVolCommand(setVolCommand);
		frameInvoker.setStopCommand(stopCommand);
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\94910\\Documents\\java\\MyVideoPlayer\\picture\\timg_image&quality=80&size=b9999_10000&sec=1515490171016&di=b82b5de72a9701a78326931f24ee533f&imgtype=0&src=http%3A%2F%2Fwww.uimaker.com%2Fuploads%2Fallimg%2F131212%2F1_131212141935_1.jpg.jpg"));
		playListFrame = new PlayListFrame();

		setTitle("   VideoPlayer   ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 80, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		// 菜单栏
		menuBar = new JMenuBar();
		menuBar.setForeground(Color.BLACK);
		setJMenuBar(menuBar);

		mnFile = new JMenu("文件"); // 设置菜单名
		menuBar.add(mnFile);
		mnSetting = new JMenu("设置");
		menuBar.add(mnSetting);
		mnHelp = new JMenu("帮助");
		menuBar.add(mnHelp);

		mnOpenVideo = new JMenuItem("打开文件"); // 设置文件菜单子目录打开文件
		mnFile.add(mnOpenVideo);

		mnOpenSubtitle = new JMenuItem("打开字幕");
		mnFile.add(mnOpenSubtitle);

		mnExit = new JMenuItem("退出"); // 设置文件菜单子目录退出
		mnFile.add(mnExit);

		// 打开文件
		mnOpenVideo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PlayerMain.openVideo();
			}
		});

		mnOpenSubtitle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PlayerMain.openSubtitle();
			}
		});

		// 退出
		mnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frameInvoker.ExitCommand();
			}
		});

		kBoradControl = new KeyBoradControl();
		kBoradControl.keyBordControl();

		// 播放界面
		JPanel videoPane = new JPanel();
		contentPane.add(videoPane, BorderLayout.CENTER);
		videoPane.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		videoPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		playerComponent = new EmbeddedMediaPlayerComponent();
		videoPane.add(playerComponent);

		// 添加进度条
		progress = new JProgressBar();
		videoPane.add(progress, BorderLayout.SOUTH);
		progress.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { // 点击进度条调整视屏播放进度
				int x = e.getX();
				frameInvoker.JumpToCommand((float) x / progress.getWidth());
			}
		});
		progress.setStringPainted(true);

		controlPanel = new JPanel();
		contentPane.add(controlPanel, BorderLayout.SOUTH);
		controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// 添加播放按钮

		btnPlay = new JButton(">");
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnPlay.getText() == ">") {
					frameInvoker.PlayCommand();
					btnString = "||";
					btnPlay.setText(btnString);
				} else {
					frameInvoker.PauseCommand();
					btnString = ">";
					btnPlay.setText(btnString);
				}

			}
		});

		// 添加停止按钮
		btnStop = new JButton("停止");
		btnStop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				frameInvoker.StopCommand();
			}
		});
		controlPanel.add(btnStop);

		btnBackward = new JButton("<<");
		btnBackward.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				frameInvoker.JumpToCommand((float) ((progress.getPercentComplete()
						* progress.getWidth() - 5) / progress.getWidth()));
			}
		});
		controlPanel.add(btnBackward);
		controlPanel.add(btnPlay);

		// 添加声音控制块
		slider = new JSlider();
		slider.setValue(80);
		slider.setMaximum(100);
		slider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				slider.setValue((int) (e.getX() * ((float) slider.getMaximum() / slider
						.getWidth())));
				// volumLabel.setText("" + volumControlerSlider.getValue());
			}

		});
		slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				frameInvoker.SetVolCommand(slider.getValue());
			}
		});

		btnForward_1 = new JButton(">>");
		btnForward_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frameInvoker.JumpToCommand((float) (((progress.getPercentComplete()
						* progress.getWidth() + 10)) / progress.getWidth()));
			}
		});
		controlPanel.add(btnForward_1);
		controlPanel.add(slider);

		btnFullScreen = new JButton("全屏");
		btnFullScreen.addMouseListener(new MouseAdapter() {
			int flag = 0;

			public void mouseClicked(MouseEvent arg0) {
				PlayerMain.fullScreen();
			}
		});
		btnFullScreen.setFont(new Font("SimSun", Font.PLAIN, 15));
		controlPanel.add(btnFullScreen);

		btnList = new JButton("List>>");
		if (playListFrame.getFlag() == 1) {
			btnList.setText("List>>");
		} else if (playListFrame.getFlag() == 0) {
			btnList.setText("<<List");
		}
		btnList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (btnList.getText() == "List>>") {
					playListFrame.setVisible(true);
					if (Math.abs(PlayerMain.getFrame().getWidth()
							- Toolkit.getDefaultToolkit().getScreenSize().width) <= 20)
						playListFrame.setBounds(Toolkit.getDefaultToolkit()
								.getScreenSize().width - 400, 0, 400,
								PlayerMain.getFrame().getHeight());
					else
						playListFrame.setBounds(PlayerMain.getFrame().getX()
								+ PlayerMain.getFrame().getWidth() - 15,
								PlayerMain.getFrame().getY(), 400, PlayerMain
										.getFrame().getHeight());
					playListFrame.setFlag(0);
					btnList.setText("<<List");
				} else if (btnList.getText() == "<<List") {
					playListFrame.setVisible(false);
					btnList.setText("List>>");
				}
			}
		});
		controlPanel.add(btnList);
		final Canvas videoSurface = playerComponent.getVideoSurface();
		videoSurface.addMouseListener(new MouseAdapter() {
			String btnText = ">";
			String btnText1 = "Full";
			Timer mouseTime;

			@Override
			public void mouseClicked(MouseEvent e) {
				int i = e.getButton();
				if (i == MouseEvent.BUTTON1) {
					if (e.getClickCount() == 1) {
						mouseTime = new Timer(350, new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								if (btnPlay.getText() == ">") {
									frameInvoker.PlayCommand();
									btnText = "||";
									btnPlay.setText(btnText);
								} else {
									frameInvoker.PauseCommand();
									btnText = ">";
									btnPlay.setText(btnText);
								}
								mouseTime.stop();
							}
						});
						mouseTime.restart();
					} else if (e.getClickCount() == 2 && mouseTime.isRunning()) {
						mouseTime.stop();
						if (flag == 0) {
							PlayerMain.fullScreen();
						} else if (flag == 1) {
							PlayerMain.originalScreen();
						}
					}
				}
			}

		});

	}

	// 获取播放媒体实例（某个视频）
	public EmbeddedMediaPlayer getMediaPlayer() {
		return playerComponent.getMediaPlayer();
	}

	// 获取进度条实例
	public JProgressBar getProgressBar() {
		return progress;
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public JMenuBar getmenuBar() {
		return menuBar;
	}

	public JPanel getPanel() {
		return panel;
	}

	public JProgressBar getProgress() {
		return progress;
	}

	public JPanel getControlPanel() {
		return controlPanel;
	}

	public JButton getbtnStop() {
		return btnStop;
	}

	public JButton getbtnPlay() {
		return btnPlay;
	}

	public JSlider getSlider() {
		return slider;
	}

	public JButton btnFullScreen() {
		return btnFullScreen;
	}

	public Object getCurrentLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getFlag() {
		return flag;
	}

	public EmbeddedMediaPlayerComponent getPlayComponent() {
		return playerComponent;
	}

	public JButton getbtnList() {
		// TODO Auto-generated method stub
		return btnList;
	}

	public static PlayListFrame getPlayListFrame() {
		return playListFrame;
	}

}
