package pl.kwi.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import pl.kwi.entities.UserEntity;
import pl.kwi.frames.MainFrame;
import pl.kwi.services.UserService;
import pl.kwi.table.buttons.ButtonsEditor;
import pl.kwi.table.buttons.ButtonsRenderer;


public class TablePanel extends JPanel{
	
	private JTextField jTextField;
	private MainFrame frame;	
	private UserService userService;
	
	public TablePanel(MainFrame frame){
		userService = new UserService();
		
		this.frame = frame;
		frame.setContentPane(this);	
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(getTitlePanel(), BorderLayout.NORTH);
		this.add(getSubtitlePanel(), BorderLayout.NORTH);
		this.add(getTablePanel(), BorderLayout.CENTER);
		this.add(getButtonCreatePanel(), BorderLayout.SOUTH);
		
		frame.setVisible(true);
		
	}
	
	private JPanel getTitlePanel(){
		
		JPanel jPanel = new JPanel();
		jPanel.add(new JLabel("Hello World"));
		return jPanel;
		
	}
	
	private JPanel getSubtitlePanel(){
		
		JPanel jPanel = new JPanel();
		jPanel.add(new JLabel("Page: Table"));
		return jPanel;
		
	}
	
	private JScrollPane getTablePanel(){		
				
		JScrollPane panel = new JScrollPane(getTable());
		return panel;
		
	}
	
	private JPanel getButtonCreatePanel(){
		
		JPanel jPanel = new JPanel();
		JButton buttonCreate = new JButton("Create");
		buttonCreate.addActionListener(new ActionListenerButtonCreate());
		jPanel.add(buttonCreate);
		return jPanel;
		
	}
	
	private JTable getTable(){
		
		Vector<String> columnNames = new Vector<String>();
		columnNames.add("Id");
		columnNames.add("Name");
		columnNames.add("Actions");
		
		Vector<Vector<String>> rowData = getTableData();		
		
		JTable table = new JTable(rowData, columnNames);
		table.setRowHeight(36);
		table.getColumn("Id").setMaxWidth(20);
		table.getColumn("Name").setMaxWidth(150);
		table.getColumn("Actions").setMaxWidth(330);		
		table.getColumn("Actions").setCellRenderer(new ButtonsRenderer());
		table.getColumn("Actions").setCellEditor(new ButtonsEditor(table));
		
		ButtonsEditor buttornsEditor = (ButtonsEditor)table.getColumn("Actions").getCellEditor();
		List<JButton> buttons = buttornsEditor.buttons;
		buttons.get(0).addActionListener(new ActionListenerButtonView(table));
		buttons.get(1).addActionListener(new ActionListenerButtonEdit(table));
		buttons.get(2).addActionListener(new ActionListenerButtonDelete(table));
				
		return table;
		
	}
	
	public Vector<Vector<String>> getTableData(){
		
		Vector<Vector<String>> rowData = new Vector<Vector<String>>();		
		Vector<String> row;	
		
		try {
			List<UserEntity> users = userService.getUsers();
			for (UserEntity user : users) {
				row = new Vector<String>();	
				row.add(user.getId().toString());
				row.add(user.getName());
				rowData.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return rowData;
		
	}
	
	private class ActionListenerButtonCreate implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			new CreatePanel(frame);
		}
		
	}
	
	private class ActionListenerButtonView implements ActionListener{
		
		private JTable table;
		
		public ActionListenerButtonView(JTable table){
			this.table = table;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int row = table.convertRowIndexToModel(table.getEditingRow());
			Object o = table.getModel().getValueAt(row, 0);
			new ViewPanel(frame, Long.valueOf(o.toString()));
		}
		
	}
	
	private class ActionListenerButtonEdit implements ActionListener{
		
		private JTable table;
		
		public ActionListenerButtonEdit(JTable table){
			this.table = table;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int row = table.convertRowIndexToModel(table.getEditingRow());
			Object o = table.getModel().getValueAt(row, 0);
			new EditPanel(frame, Long.valueOf(o.toString()));
		}
		
	}
	
	private class ActionListenerButtonDelete implements ActionListener{
		
		private JTable table;
		
		public ActionListenerButtonDelete(JTable table){
			this.table = table;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int row = table.convertRowIndexToModel(table.getEditingRow());
			Object o = table.getModel().getValueAt(row, 0);
			new DeletePanel(frame, Long.valueOf(o.toString()));
		}
		
	}
	
	
	
}
