package com.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.print.attribute.standard.Media;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.main.PlayerMain;
import com.views.SureFrame;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.Window.Type;

public class PlayListFrame extends JFrame {

	private JPanel contentPane;
	private int flag = 0;
	private JList list = new JList();
	private JScrollPane scrollPane;
	private JPanel panel;
	private JButton btnHistoryClear;
	private JPanel panel_1;

	/**
	 * Create the frame,to display the watched history .
	 */
	public PlayListFrame() {
		setType(Type.UTILITY);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				flag = 1;
				PlayerMain.getFrame().getbtnList().setText("List>>");
				PlayerMain.getFrame().getbtnList()
						.setText(PlayerMain.getFrame().getbtnList().getText());
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMaximizedBounds(new Rectangle((int) Toolkit.getDefaultToolkit()
				.getScreenSize().getWidth() - 400, 0, 400, (int) Toolkit
				.getDefaultToolkit().getScreenSize().getHeight()));
		setBounds(100, 100, 229, 394);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {

				}
			}
		});
		
		
		contentPane.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(getList());

		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		
		panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		
		btnHistoryClear = new JButton("Clear History");
		panel_1.add(btnHistoryClear);

		
		// 清除历史记录
		btnHistoryClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				final SureFrame sureFrame = new SureFrame();
				sureFrame.setVisible(true);
				sureFrame.setText("Are You Sure?");
				sureFrame.setBounds(PlayerMain.getFrame().getPlayListFrame()
						.getX() + 15, PlayerMain.getFrame().getPlayListFrame()
						.getY() + 100, 350, 115);
				sureFrame.getbtnNO().addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						sureFrame.setVisible(false);
					}
				});

				sureFrame.getbtnYes().addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						sureFrame.setVisible(false);
						scrollPane.setViewportView(getList());

					}
				});

			}

		});

	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public JList getList() {

		return list;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

}
