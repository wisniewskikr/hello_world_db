package pl.kwi.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pl.kwi.entities.UserEntity;
import pl.kwi.frames.MainFrame;
import pl.kwi.services.UserService;

public class CreatePanel extends JPanel{
	
	private JTextField jTextField;
	private MainFrame frame;
	private UserService userService;
	
	public CreatePanel(MainFrame frame){
		userService = new UserService();
		
		this.frame = frame;
		frame.setContentPane(this);	
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(getTitlePanel(), BorderLayout.NORTH);
		this.add(getSubtitlePanel(), BorderLayout.NORTH);
		this.add(getRequestPanel(), BorderLayout.CENTER);
		this.add(getButtonsPanel(), BorderLayout.SOUTH);
		
		frame.setVisible(true);
		
	}
	
	private JPanel getTitlePanel(){
		
		JPanel jPanel = new JPanel();
		jPanel.add(new JLabel("Hello World"));
		return jPanel;
		
	}
	
	private JPanel getSubtitlePanel(){
		
		JPanel jPanel = new JPanel();
		jPanel.add(new JLabel("Page: Create"));
		return jPanel;
		
	}
	
	private JPanel getRequestPanel(){
		
		JPanel jPanel = new JPanel();
		jPanel.add(new JLabel("Type your name: "));
		jTextField = new JTextField(10);
		jPanel.add(jTextField);
		return jPanel;
		
	}
	
	private JPanel getButtonsPanel(){
		
		JPanel jPanel = new JPanel();
		
		JButton jButtonOK = new JButton("Create");
		jButtonOK.addActionListener(new ActionListenerButtonCreate());
		jPanel.add(jButtonOK);
		
		JButton jButtonCancel = new JButton("Cancel");
		jButtonCancel.addActionListener(new ActionListenerButtonCancel());
		jPanel.add(jButtonCancel);
		
		return jPanel;
		
	}
	
	private class ActionListenerButtonCreate implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String name = jTextField.getText();
				UserEntity user = new UserEntity();
				user.setName(name);
				userService.createUser(user);
				new TablePanel(frame);
			} catch (Exception e1) {
				e1.printStackTrace();
			}	
		}
		
	}
	
	private class ActionListenerButtonCancel implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			new TablePanel(frame);	
		}
		
	}
	
}
