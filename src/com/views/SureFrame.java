package com.views;
/*´_¶¨í“Ãæ*/
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Color;

public class SureFrame extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtAreYouSure;
	private JButton btnYes;
	private JButton btnNO;

	public SureFrame() {
		setAlwaysOnTop(true);
		setIconImage(new ImageIcon("picture/icon.png").getImage());
		setSize(350, 115);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			txtAreYouSure = new JTextField();
			txtAreYouSure.setText("are you sure?");
			txtAreYouSure.setEditable(false);
			txtAreYouSure.setForeground(new Color(255, 0, 0));
			txtAreYouSure.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(txtAreYouSure);
			txtAreYouSure.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BorderLayout(0, 0));
			{
				JPanel panel = new JPanel();
				buttonPane.add(panel);
				{
					btnYes = new JButton("Yse");
					panel.add(btnYes);
					btnYes.setActionCommand("OK");
					getRootPane().setDefaultButton(btnYes);
				}
				{
					btnNO = new JButton("NO");
					panel.add(btnNO);
					btnNO.setActionCommand("Cancel");
				}
			}
		}
	}

	public JButton getbtnYes() {
		return btnYes;
	}

	public JButton getbtnNO() {
		return btnNO;
	}

	public void setText(String string) {
		txtAreYouSure.setText(string);
	}

}