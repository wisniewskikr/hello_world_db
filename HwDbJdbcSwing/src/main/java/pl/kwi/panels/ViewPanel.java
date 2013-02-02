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

public class ViewPanel extends JPanel{
	
	private JTextField jTextField;
	private MainFrame frame;
	private UserService userService;
	private Long id;
	
	public ViewPanel(MainFrame frame, Long id){
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
		jPanel.add(new JLabel("Page: View"));
		return jPanel;
		
	}
	
	private JPanel getRequestPanel(){
		
		JPanel jPanel = null;
		
		try {
			UserEntity user = userService.readUser(id);
					
			jPanel = new JPanel();
			jPanel.add(new JLabel("Type your name: "));
			jTextField = new JTextField(10);
			jTextField.setText(user.getName());
			jTextField.disable();
			jPanel.add(jTextField);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jPanel;
		
	}
	
	private JPanel getButtonsPanel(){
		
		JPanel jPanel = new JPanel();
		JButton jButtonBack = new JButton("Back");
		jButtonBack.addActionListener(new ActionListenerButtonBack());
		jPanel.add(jButtonBack);
		return jPanel;
		
	}
	
	private class ActionListenerButtonBack implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			new TablePanel(frame);	
		}
		
	}
	
}
