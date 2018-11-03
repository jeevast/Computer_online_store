import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Container;
import java.awt.event.ActionEvent;
public class Database_Statistics extends JFrame{
	public JLabel title_label, date1_label, date2_label,title_label1, date1_label1, date2_label1,title_label2, date1_label2, date2_label2,title_label3, date1_label3, date2_label3,title_label4, date1_label4, date2_label4;
	public JTextField date1_field, date2_field,date1_field1, date2_field1,date1_field2, date2_field2,date1_field3, date2_field3,date1_field4, date2_field4;
	public JButton place_order1, place_order2, place_order3, place_order4, place_order5;
	
	Statement std = null;
	Connection connect = null;
	public Database_Statistics()
	{
	super("Sales statstics");
	pack();
	setLayout(null);
	
	JPanel adder = new JPanel();
	adder.setLayout(null);
	adder.setSize(5000, 5000);
	
	title_label = new JLabel("most frequently sold products");
	title_label.setBounds(520,10 , 200, 50);
	adder.add(title_label);
	date1_label = new JLabel("Enter start date");
	date1_label.setBounds(10, 60, 150, 50);
	 adder.add(date1_label);
	date1_field = new JTextField();
	date1_field.setBounds(300, 80, 200, 20);
	 adder.add(date1_field);
	date2_label = new JLabel("Enter end date");
	date2_label.setBounds(700, 60, 150, 50);
	 adder.add(date2_label);
	date2_field = new JTextField();
	date2_field.setBounds(990, 80, 200, 20);
	 adder.add(date2_field);
	JTextArea area1 = new JTextArea();
    area1.setLineWrap(true);
    JScrollPane pane = new JScrollPane(area1);
    pane.setBounds(500, 150, 200, 100);
    adder. add(pane);
    
	place_order1 = new JButton("frequently sold products");
	place_order1.setBounds(550, 290, 300, 20);
	adder.add(place_order1);
    place_order2 = new JButton("products of distinct customers");
    place_order2.setBounds(550, 350, 300, 20);
    adder.add(place_order2);
	place_order3 = new JButton("best customers on money spent");
	 place_order3.setBounds(550, 410, 300, 20);
	 adder.add(place_order3);
	place_order4 = new JButton("best zip code");

	 place_order4.setBounds(550, 470, 300, 20);
		adder.add(place_order4);
	place_order5 = new JButton("best selling average");
	 place_order5.setBounds(550, 530, 300, 20);
		adder.add(place_order5);
	
	
	add(adder);
	adder.setVisible(true);
	
	
	setVisible(true);
	place_order1.addActionListener(new AbstractAction()
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()== place_order1)
			{
				try
				{
				db_connect();
				String start_date = date1_field.getText();
				String end_date = date2_field.getText();
				String query = "select  p.pname from product p, cart c, appears_in a where a.pid = p.pid and c.cartid = a.cartid and c.tdate between " + "'"+ start_date+"'" + " and " +"'"+end_date+"'"
						+" group by p.pid, p.pname order by sum(quantity) desc";
						
						
						
				
						
						
						

					String iterate = "";


				ResultSet rs = std.executeQuery(query);
				while(rs.next())
				{
					String kate = rs.getString(1)+ "\r\n";
					System.out.println(kate);
					iterate = iterate + kate;
					
				}
				area1.setText(iterate);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
	});
	place_order2.addActionListener(new AbstractAction()
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()== place_order2)
			{
				try
				{
				db_connect();
				String start_date = date1_field.getText();
				String end_date = date2_field.getText();
				String query = "select  pid,pname, count(*)\r\n" + 
						" from((( product natural join appears_in) natural join cart) natural join customer)\r\n" + 
						"where tdate between" + "'" + start_date +"'"+ " and "+"'"+ end_date+"'"+"\r\n" + 
						"group by pid, pname\r\n" + 
						"order by count(*) desc";
						
						
						
				
						
						
						

					String iterate = "";


				ResultSet rs = std.executeQuery(query);
				while(rs.next())
				{
					String kate = rs.getString(2)+ "\r\n";
					System.out.println(kate);
					iterate = iterate + kate;
					
				}
				area1.setText(iterate);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
	});
	
	place_order3.addActionListener(new AbstractAction()
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()== place_order3)
			{
				try
				{
				db_connect();
				String start_date = date1_field.getText();
				String end_date = date2_field.getText();
				String query = "select cid, fname, sum(pricesold)\r\n" + 
						"from ((appears_in natural join cart)natural join customer)\r\n" + 
						"where tdate between " +"'"+start_date+"'"+" and "+"'" + end_date+"'"+"\r\n" + 
						"group by cid, fname\r\n" + 
						"order by sum(pricesold) desc";
						
						
						
				
						
						
						

					String iterate = "";


				ResultSet rs = std.executeQuery(query);
				while(rs.next())
				{
					String kate = rs.getString(2)+ "\r\n";
					System.out.println(kate);
					iterate = iterate + kate;
					
				}
				area1.setText(iterate);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
	});
	
	
	place_order4.addActionListener(new AbstractAction()
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()== place_order4)
			{
				try
				{
				db_connect();
				String start_date = date1_field.getText();
				String end_date = date2_field.getText();
				String query = "select zip, count(*)\r\n" + 
						"from ((appears_in natural join cart)natural join shipping_address)\r\n" + 
						"where tdate between " +"'"+start_date+"'"+" and "+"'" + end_date+"'"+"\r\n" + 
						"group by zip\r\n" + 
						"order by count(*) desc";
						
						
						
				
						
						
						

					String iterate = "";


				ResultSet rs = std.executeQuery(query);
				while(rs.next())
				{
					String kate = rs.getString(1)+ "\r\n";
					System.out.println(kate);
					iterate = iterate + kate;
					
				}
				area1.setText(iterate);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
	});
	
	place_order5.addActionListener(new AbstractAction()
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource()== place_order5)
			{
				try
				{
				db_connect();
				String start_date = date1_field.getText();
				String end_date = date2_field.getText();
				String query = "select ptype, avg(pricesold)\r\n" + 
						"from ((product natural join appears_in) natural join cart)\r\n" + "where tdate between " +"'"+start_date+"'"+" and "+"'" + end_date+"'"+"\r\n"
						+ "group by ptype";
						
						
						
				
						
						
						

					String iterate = "";


				ResultSet rs = std.executeQuery(query);
				while(rs.next())
				{
					String kate = rs.getString(1)+" " + rs.getString(2) +"\r\n";
					System.out.println(kate);
					iterate = iterate + kate;
					
				}
				area1.setText(iterate);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
	});
	
	
	
	
		
	}
	public void db_connect() throws Exception
	{
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		 connect=DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe","jeeva","joker13"); 
		 std=connect.createStatement();
		 
		 
		
	}
	
	public static void main(String[] args)
	{
		new Database_Statistics();
	}

}
