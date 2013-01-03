package pl.kwi.frames;

import javax.swing.JFrame;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pl.kwi.services.UserService;

public class MainFrame extends JFrame{
	
	
	private UserService userSerivce;
	
	
	public MainFrame(){
		
		this.setSize(500, 300);
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/conf/spring-conf.xml");
		userSerivce = ctx.getBean(UserService.class);
		
	}


	public UserService getUserSerivce() {
		return userSerivce;
	}
	
		
}
