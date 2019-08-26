package com.views;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
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

import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JSlider;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dialog.ModalExclusionType;

import javax.swing.JLabel;

public class ControlFrame extends JFrame {

	private JPanel contentPane;
	private JButton btnPlay;
	private JButton btnBackward;
	private JProgressBar progress;
	private JSlider Slider;
	private JButton btnSmall;
	private JPanel progresspanel;

	/**
	 * Create the frame,a new control frame after enter full screen model.
	 */
	public ControlFrame() {
		
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
		
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setType(Type.UTILITY);
		setResizable(false);
		setUndecorated(true);
		setOpacity(0.5f);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 623, 66);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		btnBackward = new JButton("<<");
		btnBackward.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				float to = (float) ((progress.getPercentComplete()
						* progress.getWidth() - 5) / progress.getWidth());
				frameInvoker.JumpToCommand(to);
			}
		});

		// 播放
		btnPlay = new JButton(">");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPlay.addMouseListener(new MouseAdapter() {
			String btnText = ">";

			@Override
			public void mouseClicked(MouseEvent e) {
				if (btnPlay.getText() == ">") {
					frameInvoker.PlayCommand();
					btnText = "||";
					btnPlay.setText(btnText);
				} else {
					frameInvoker.PauseCommand();
					btnText = ">";
					btnPlay.setText(btnText);
				}
			}
		});

		panel.add(btnPlay);
		panel.add(btnBackward);

		// 暂停按钮
		JButton btnStop = new JButton("\u64AD\u653E");
		btnStop.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frameInvoker.StopCommand();
				btnPlay.setText(">");
			}
		});
		panel.add(btnStop);

		// 快进
		JButton btnForward = new JButton(">>");
		btnForward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				float to=(float) (((progress.getPercentComplete()
						* progress.getWidth() + 15)) / progress.getWidth());
				frameInvoker.JumpToCommand(to);
			}
		});

		panel.add(btnForward);

		// 变为原来的屏幕
		btnSmall = new JButton("\u7F29\u5C4F");
		btnSmall.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PlayerMain.originalScreen();
			}
		});
		panel.add(btnSmall);

		// 声音控件
		Slider = new JSlider();
		Slider.setPaintTicks(true);
		Slider.setSnapToTicks(true);
		Slider.setPaintLabels(true);
		panel.add(Slider);

		Slider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Slider.setValue((int) (e.getX() * ((float) Slider.getMaximum() / Slider
						.getWidth())));
				PlayerMain.getFrame().getSlider().setValue(Slider.getValue());
			}
		});
		Slider.setMaximum(120);

		// 进度条
		progresspanel = new JPanel();
		contentPane.add(progresspanel, BorderLayout.NORTH);
		progresspanel.setLayout(new BorderLayout(0, 0));
		progress = new JProgressBar();
		progresspanel.add(progress, BorderLayout.CENTER);
		progress.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				float to=((float) x / progress.getWidth());
				frameInvoker.JumpToCommand(to);

			}
		});

		Slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				frameInvoker.SetVolCommand(Slider.getValue());
			}
		});

	}

	public JProgressBar getProgressBar() {
		return progress;
	}

	public void setProgressBar(JProgressBar progressBar) {
		this.progress = progressBar;
	}

	public JButton getPlayButton() {
		return btnPlay;
	}

	public JSlider getVolumControlerSlider() {
		return Slider;
	}


}
