package pl.kwi.table.buttons;

import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

class ButtonsPanel extends JPanel {
	
	
	  public final List<JButton> buttons =
	    Arrays.asList(new JButton("View"), new JButton("Edit"), new JButton("Delete"));
	
	  
	  public ButtonsPanel() {
	    super();
	    setOpaque(true);
	    for(JButton b: buttons) {
	      b.setFocusable(false);
	      b.setRolloverEnabled(false);
	      add(b);
	    }
	  }

	  
	}
