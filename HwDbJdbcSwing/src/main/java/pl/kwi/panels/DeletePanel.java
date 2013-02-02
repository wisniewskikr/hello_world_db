package pl.kwi.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pl.kwi.entities.UserEntity;
import pl.kwi.frames.MainFrame;
import pl.kwi.services.UserService;

public class DeletePanel extends JPanel{
	
	private JTextField jTextField;
	private MainFrame frame;
	private UserService userService;
	private Long id;
	
	public DeletePanel(MainFrame frame, Long id){
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
		jPanel.add(new JLabel("Page: Delete"));
		return jPanel;
		
	}
	
	private JPanel getRequestPanel(){
		UserEntity user = null;
		JPanel jPanel = null;
		try {
			user = userService.readUser(id);
			jPanel = new JPanel();
			jPanel.add(new JLabel(MessageFormat.format("Do you really want delete user with name: {0}?", user.getName())));
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return jPanel;
		
	}
	
	private JPanel getButtonsPanel(){
		
		JPanel jPanel = new JPanel();
		
		JButton jButtonDelete = new JButton("Delete");
		jButtonDelete.addActionListener(new ActionListenerButtonDelete());
		jPanel.add(jButtonDelete);
		
		JButton jButtonCancel = new JButton("Cancel");
		jButtonCancel.addActionListener(new ActionListenerButtonCancel());
		jPanel.add(jButtonCancel);
		
		return jPanel;
		
	}
	
	private class ActionListenerButtonDelete implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				UserEntity user = userService.readUser(id);
				userService.deleteUser(user.getId());
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
