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

public class EditPanel extends JPanel{
	
	private JTextField jTextField;
	private MainFrame frame;
	private UserService userService;
	private Long id;
	
	public EditPanel(MainFrame frame, Long id){
		userService = new UserService();
		
		this.frame = frame;
		this.id = id;
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
		jPanel.add(new JLabel("Page: Edit"));
		return jPanel;
		
	}
	
	private JPanel getRequestPanel(){
		
		UserEntity user;
		JPanel jPanel = null;
		try {
			user = userService.readUser(id);
			
			jPanel = new JPanel();
			jPanel.add(new JLabel("Type your name: "));
			jTextField = new JTextField(10);
			jTextField.setText(user.getName());
			jPanel.add(jTextField);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return jPanel;
		
	}
	
	private JPanel getButtonsPanel(){
		
		JPanel jPanel = new JPanel();
		
		JButton jButtonUpdate = new JButton("Update");
		jButtonUpdate.addActionListener(new ActionListenerButtonUpdate());
		jPanel.add(jButtonUpdate);
		
		JButton jButtonCancel = new JButton("Cancel");
		jButtonCancel.addActionListener(new ActionListenerButtonCancel());
		jPanel.add(jButtonCancel);
		
		return jPanel;
		
	}
	
	private class ActionListenerButtonUpdate implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String name = jTextField.getText();
				
				UserEntity user = userService.readUser(id);
				user.setName(name);
				userService.updateUser(user);
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
