/*������*/

package com.main;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.SwingWorker;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.views.ControlFrame;
import com.views.MainWindow;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;
import uk.co.caprica.vlcj.player.embedded.DefaultAdaptiveRuntimeFullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

public class PlayerMain {

	static MainWindow frame;
	static ControlFrame controlFrame;

	// private static final String NATIVE_LIBRARY_SEARCH_PATH =
	// "C:\\Program Files\\VideoLAN\\VLC";

	public static void main(String[] args) {

//		 if(RuntimeUtil.isWindows()){ }
//		 NativeLibrary.addSearchPath(
//		 RuntimeUtil.getLibVlcLibraryName(),
//		 "C:\\Program Files\\VideoLAN\\VLC"); //�����·����vlc�İ�װ·��
//		 Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(),LibVlc.class);
//		 System.out.println(LibVlc.INSTANCE.libvlc_get_version());
		boolean found = new NativeDiscovery().discover();
		System.out.println(found);
		System.out.println(LibVlc.INSTANCE.libvlc_get_version());

		// ���������������  �д���
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					frame = new MainWindow();
					frame.setVisible(true);
					controlFrame = new ControlFrame();
					// frame.getMediaPlayer().playMedia("D:\\BaiduYunDownload\\S04\\01.mkv");
					String[] optionDecode = { "--subsdec-encoding=GB18030" };
					frame.getMediaPlayer().prepareMedia(
							"D:\\BaiduYunDownload\\S04\\01.mkv",optionDecode); // ���Ʋ�������
					
					new SwingWorker<String, Integer>() {

						@Override
						protected String doInBackground() throws Exception {
							// TODO Auto-generated method stub
							while (true) { 
								// ��ȡ��Ƶ���Ž��Ȳ��Ұ��ٷֱ���ʾ
								long total = frame.getMediaPlayer().getLength();
								long curr = frame.getMediaPlayer().getTime();
								float percent = (float) curr / total;
								publish((int) (percent * 100));
								Thread.sleep(100);
							}
						}

						protected void process(java.util.List<Integer> chunks) {
							for (int v : chunks) {
								frame.getProgressBar().setValue(v);
								controlFrame.getProgressBar().setValue(v);
							}
						}
					}.execute();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// ���ļ�
	public static void openVideo() {
		JFileChooser chooser = new JFileChooser();
		int v = chooser.showOpenDialog(null);
		if (v == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			frame.getMediaPlayer().playMedia(file.getAbsolutePath());
		}
	}

	// ����Ļ
	public static void openSubtitle() {
		JFileChooser chooser = new JFileChooser();
		int v = chooser.showOpenDialog(null);
		if (v == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			frame.getMediaPlayer().setSubTitleFile(file);
		}
	}

	// �˳�����
	public void exit() {
		frame.getMediaPlayer().release();
		System.exit(0);
	}

	// ʵ�ֲ��Ű�ť�ķ���
	public void play() {
		frame.getMediaPlayer().play();
	}

	// ʵ����ͣ��ť�ķ���
	public void pause() {
		frame.getMediaPlayer().pause();
	}

	// ʵ��ֹͣ��ť�ķ���
	public void stop() {
		frame.getMediaPlayer().stop();
	}

	// ʵ�ֵ����������ת�ķ���
	public void jumpTo(float to) {
		frame.getMediaPlayer().setTime(
				(long) (to * frame.getMediaPlayer().getLength()));
	}

	// ʵ�ֿ��������ķ���
	public void setVol(int v) {
		frame.getMediaPlayer().setVolume(v);
	}

	

	// ʵ��ȫ������
	public static void fullScreen() {
		frame.getMediaPlayer().setFullScreenStrategy(
				new DefaultAdaptiveRuntimeFullScreenStrategy(frame));
		frame.getProgressBar().setVisible(false);
		frame.getControlPanel().setVisible(false);
		frame.getSlider().setVisible(false);
		frame.getJMenuBar().setVisible(false);
		frame.getMediaPlayer().setFullScreen(true);


		frame.setFlag(1);
		frame.getPlayComponent().getVideoSurface()
				.addMouseMotionListener(new MouseMotionListener() {

					@Override
					public void mouseMoved(MouseEvent e) {
						// TODO Auto-generated method stub
						if (frame.getFlag() == 1) {
							controlFrame.setLocation(
									(frame.getWidth() - controlFrame.getWidth()) / 2,
									frame.getHeight()
											- controlFrame.getHeight());
							controlFrame.setVisible(true);
							controlFrame.getVolumControlerSlider().setValue(
									frame.getSlider().getValue());
							if (frame.getMediaPlayer().isPlaying())
								controlFrame.getPlayButton().setText("||");
							else
								controlFrame.getPlayButton().setText(">");

						}

					}

					@Override
					public void mouseDragged(MouseEvent e) {
						// TODO Auto-generated method stub

					}
				});

	}

	public static void originalScreen() {
		frame.getProgressBar().setVisible(true);
		frame.getControlPanel().setVisible(true);
		frame.getSlider().setVisible(true);
		frame.getJMenuBar().setVisible(true);
		frame.getMediaPlayer().setFullScreen(false);
		frame.setFlag(0);
		if (frame.getMediaPlayer().isPlaying())
			frame.getbtnPlay().setText("||");
		else
			frame.getbtnPlay().setText(">");

		if (frame.getPlayListFrame().getFlag() == 1) {
			frame.getbtnList().setText("List>>");
		} else if (frame.getPlayListFrame().getFlag() == 0) {
			frame.getbtnList().setText("<<List");
		}
		controlFrame.setVisible(false);

	}
	
	public static MainWindow getMainWindow() {
		return frame;
	}

	public static MainWindow getFrame() {

		return frame;
	}

}
