package it.webapp.perla.controller.test;

import java.sql.Connection;
import java.sql.DriverManager;

import it.webapp.perla.logic.UtentiLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/testOlap")
public class OlapTestController { 
	@Autowired
	private UtentiLogic utentiLogic;
	
	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model) {
		try {  
			
			/* Class.forName("org.olap4j.driver.xmla.XmlaOlap4jDriver");
			// Class.forName("org.olap4j.driver.xmla");
	        Connection connection =  DriverManager.getConnection(
	                "jdbc:xmla:"
	                    + "Server=http://localhost:8080/mondrian/xmla");
	        OlapConnection oConn = connection.unwrap(OlapConnection.class);
	        */
	        
	        /*
			Class.forName("mondrian.olap4j.MondrianOlap4jDriver");
			// Provider=mondrian;Jdbc=jdbc:odbc:mondriandb;JdbcDrivers=sun.jdbc.odbc.JdbcOdbcDriver;Catalog=/WEB-INF/queries/FoodMart.xml
			Connection conn = DriverManager.getConnection(
		                "jdbc:mondrian:"
		                    + "Jdbc='jdbc:odbc:mondriandb';"
		                    + "Catalog='file://D:/junospace/MOlap/WebContent/WEB-INF/queries/FoodMart.xml';"
		                    + "JdbcDrivers=sun.jdbc.odbc.JdbcOdbcDriver;");
			OlapConnection oConn = conn.unwrap(OlapConnection.class);
			  
	        
	        OlapStatement statement = oConn.createStatement();
	        CellSet cellSet =
	            statement.executeOlapQuery(
	                "SELECT {[Measures].[Unit Sales]} ON 0,\n"
	                    + "{[Product].Children} ON 1\n"
	                    + "FROM [Sales]");
	        for (Position row : cellSet.getAxes().get(1)) {
	            for (Position column : cellSet.getAxes().get(0)) {
	                for (Member member : row.getMembers()) {
	                    System.out.println(member.getUniqueName());
	                }
	                for (Member member : column.getMembers()) {
	                    System.out.println(member.getUniqueName());
	                }
	                final Cell cell = cellSet.getCell(column, row);
	                System.out.println(cell.getFormattedValue());
	                System.out.println();
	            }
	        }*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	return "testOlap";
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public String helloWorld(@PathVariable("id") int id, Model model) {
	return "testOlap";
	}
}
