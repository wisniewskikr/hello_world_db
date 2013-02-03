package pl.kwi.db.jdbc;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class JdbcTransManagement {
	
	
	private Connection conn;
	private List<Exception> exceptions = new ArrayList<Exception>();
	
	
	public void addException(Exception e){
		exceptions.add(e);
	}
	
	public boolean isException(){
		
		boolean result = false;
		
		if(!exceptions.isEmpty()){
			result = true;
		}
		
		return result;
		
	}
	
	public Exception crateJoinedException() {
		
		StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    for (Exception exception : exceptions) {
	    	exception.printStackTrace(pw);
		}	    
	    pw.close();
	    return new Exception(sw.toString());
		
	}

	
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public List<Exception> getExceptions() {
		return exceptions;
	}
	public void setExceptions(List<Exception> exceptions) {
		this.exceptions = exceptions;
	}
		

}
