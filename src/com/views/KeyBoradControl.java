package com.views;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;

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

public class KeyBoradControl {
	
	
	
	public void keyBordControl() {
		
		PlayerMain playerMain= new PlayerMain();
		
		Command exitCommand=new ExitCommand(playerMain);
		Command fullScreenCommand=new FullScreenCommand(playerMain);
		Command jumpToCommand=new JumpToCommand(playerMain);
		Command originalScreenCommand = new OriginalScreenCommand(playerMain);
		Command pauseCommand = new PauseCommand(playerMain);
		Command playCommand = new PlayCommand(playerMain);
		Command setVolCommand = new SetVolCommand(playerMain);
		Command stopCommand = new StopCommand(playerMain);
		
		
		final Invoker keyBordInvoker=new Invoker();
		
		keyBordInvoker.setFullScreenCommand(fullScreenCommand);
		keyBordInvoker.setOriginalScreenCommand(originalScreenCommand);
		keyBordInvoker.setExitCommand(exitCommand);
		keyBordInvoker.setJumpToCommand(jumpToCommand);
		keyBordInvoker.setPauseCommand(pauseCommand);
		keyBordInvoker.setPlayCommand(playCommand);
		keyBordInvoker.setSetVolCommand(setVolCommand);
		keyBordInvoker.setStopCommand(stopCommand);
		
		
		Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
			@Override
			public void eventDispatched(AWTEvent event) {
				// TODO Auto-generated method stub
				if (((KeyEvent) event).getID() == KeyEvent.KEY_PRESSED) {
					switch (((KeyEvent) event).getKeyCode()) {

					// 快进
					case KeyEvent.VK_RIGHT: {
						int a = PlayerMain.getFrame().getSlider().getValue();
						float to = (float) (((PlayerMain.getFrame()
								.getProgressBar().getPercentComplete()
								* PlayerMain.getFrame().getProgressBar()
										.getWidth() + 10)) / PlayerMain
								.getFrame().getProgressBar().getWidth());
						Invoker.JumpToCommand(to);
					}
						break;

					// 快退
					case KeyEvent.VK_LEFT: {
						float to = (float) ((PlayerMain.getFrame()
								.getProgressBar().getPercentComplete()
								* PlayerMain.getFrame().getProgressBar()
										.getWidth() - 5) / PlayerMain
								.getFrame().getProgressBar().getWidth());
						Invoker.JumpToCommand(to);
					}
						break;

					// 全屏
					case KeyEvent.VK_ESCAPE: {
						if (!PlayerMain.getFrame().getMediaPlayer()
								.isFullScreen())
							keyBordInvoker.FullScreen();
						else
							keyBordInvoker.OriginalScreen();

					}
						break;

					// 调大音量
					case KeyEvent.VK_UP: {
						PlayerMain
								.getFrame()
								.getSlider()
								.setValue(
										PlayerMain.getFrame().getSlider()
												.getValue() + 1);

					}
						break;

					// 调小音量
					case KeyEvent.VK_DOWN:
						PlayerMain
								.getFrame()
								.getSlider()
								.setValue(
										PlayerMain.getFrame().getSlider()
												.getValue() - 1);

						break;

					// 暂停
					case KeyEvent.VK_SPACE: {
						if (PlayerMain.getFrame().getMediaPlayer().isPlaying()) {
							keyBordInvoker.PauseCommand();
							PlayerMain
									.getFrame()
									.getbtnPlay()
									.setText(
											PlayerMain.getFrame().getbtnPlay()
													.getText());
						} else {
							keyBordInvoker.PlayCommand();
							PlayerMain
									.getFrame()
									.getbtnPlay()
									.setText(
											PlayerMain.getFrame().getbtnPlay()
													.getText());
						}
					}
						break;
					}
				}
			}
		}, AWTEvent.KEY_EVENT_MASK);
	}

}
