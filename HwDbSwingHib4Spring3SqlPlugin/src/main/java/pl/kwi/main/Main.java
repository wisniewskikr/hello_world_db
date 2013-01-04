package pl.kwi.main;

import pl.kwi.frames.MainFrame;
import pl.kwi.panels.TablePanel;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		MainFrame mainFrame = new MainFrame();
		new TablePanel(mainFrame);
		
	}

}
