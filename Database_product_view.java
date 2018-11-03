import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.AbstractAction;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
public class Database_product_view extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ImageIcon dell_computer;
	ImageIcon Hp_laptop;
	ImageIcon vaio_computer;
	ImageIcon laptop_lenovo;
	ImageIcon hp_printer;
	JLabel dell_c_desc;
	JLabel HP_laptop_desc;
	JLabel vaio_c_desc;
	JLabel Hp_p_desc;
	JLabel lenovo_c_desc;
	JPanel firstPanel;
	JPanel secondPanel;
	JTabbedPane tab;
	JButton place_order_1;
	JButton place_order_2;
	JButton place_order_3;
	JButton Place_order_4;
	JButton place_order_5;
	Statement std = null;
	Connection connect = null;
	
	
Database_product_view()
{
	
	firstPanel = new JPanel();
	secondPanel = new JPanel();
   	place_order_1 = new JButton("place order");
	place_order_2 = new JButton("place order");
	place_order_3 = new JButton("place order");
	Place_order_4 = new JButton("place order");
	place_order_5 = new JButton("place order");
	firstPanel.setSize(1000, 1000);
	firstPanel.setLayout(null);
	secondPanel.setSize(1000, 1000);
	secondPanel.setLayout(null);
	tab = new JTabbedPane();
	pack();
	setLayout(new BorderLayout());
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	tab.addTab( "product view" , firstPanel);
	tab.addTab("Trace status", secondPanel);
	dell_c_desc = new JLabel("dell inspiron");
	dell_c_desc.setBounds(10, 50, 200, 30);
	place_order_1.setBounds(10, 150, 100, 20);
	HP_laptop_desc = new JLabel("Hp laptop");
	HP_laptop_desc.setBounds(300, 50, 200, 30);
	place_order_2.setBounds(300, 150, 100, 20);
	Hp_p_desc = new JLabel("Hp printer");
	Hp_p_desc.setBounds(610, 50, 200, 30);
    place_order_3.setBounds(610, 150, 100, 20);
    lenovo_c_desc = new JLabel("lenovo laptop");
    lenovo_c_desc.setBounds(920, 50, 200, 30);
	Place_order_4.setBounds(920, 150, 100, 20);
	vaio_c_desc = new JLabel("vaio computer");
	vaio_c_desc.setBounds(1230, 50, 200, 30);
	place_order_5.setBounds(1230, 150, 100, 20);
	firstPanel.add(place_order_1);
	firstPanel.add(place_order_2);
	firstPanel.add(place_order_3);
	firstPanel.add(Place_order_4);
	firstPanel.add(place_order_5);
    firstPanel.add(dell_c_desc);
	firstPanel.add(HP_laptop_desc);
	firstPanel.add(Hp_p_desc);
	firstPanel.add(lenovo_c_desc);
	firstPanel.add(vaio_c_desc);
	firstPanel.add(Hp_p_desc);
	add(tab);
	firstPanel.setVisible(true);
	secondPanel.setVisible(true);
	JLabel customer_id = new JLabel("Enter your cid to view tranaction:");
    customer_id.setBounds(500, 10, 200, 50);
    secondPanel.add(customer_id);
    JTextField area = new JTextField();
    JTextArea area1 = new JTextArea();
    area1.setLineWrap(true);
    area.setBounds(500, 80, 200, 40);
    JButton enter = new JButton("enter to check");
    enter.setBounds(500, 150, 200, 20); 
    JScrollPane pane = new JScrollPane(area1);
    pane.setBounds(500, 210, 200, 50);
    secondPanel.add(pane);
    secondPanel.add(enter);
    secondPanel.add(area);
	setVisible(true);
	
	enter.addActionListener(new AbstractAction()
			{
				public void actionPerformed(ActionEvent e)
				{
					if(e.getSource()== enter)
					{
						try
						{
						db_connect();
						String c_id = area.getText();
						String query = "select pid,Fname, pname, pricesold, quantity, tstatus, tdate from((( appears_in natural join product) join customer on cid =+" + c_id+")natural join cart)"
								+ "where cartid IN (select cartid from cart where cid =" + c_id +")";
								

							String iterate = "";


						ResultSet rs = std.executeQuery(query);
						while(rs.next())
						{
							String kate = rs.getString(2)+ ",  "+"Your product "+ rs.getString(3)+ " "+ "price: "+ String.valueOf(rs.getInt(4))+ " "+ "arrive on " +rs.getString(7)+"\r\n";
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
	

	place_order_1.addActionListener(new AbstractAction()
			{
		
				public void actionPerformed(ActionEvent e)
				{
					String cart = "";
					try
					{
						if(e.getSource() == place_order_1)
						{
						
					
					db_connect();
					
					
					String cid = JOptionPane.showInputDialog("Enter your cid");
					String quantity = JOptionPane.showInputDialog("Enter quantity");
					String cart_new_id = JOptionPane.showInputDialog("Enter a new cart id to place your order");
					
					
					 String sa_name = "";
					 String cc_number = "";
					 String sql_1 = "select saname, ccnumber from shipping_address natural join((credit_card natural join stored_card )natural join customer)where cid = " + cid;
					 ResultSet sr = std.executeQuery(sql_1);
					 while(sr.next())
					 {
						 sa_name = sr.getString(1);
						 cc_number = sr.getString(2);
					 }
					 
					 String sql_3 = "insert into cart values(" + cart_new_id +","+ cid + "," + "'" + sa_name + "'" + "," + "'" + cc_number + "'"+ "," + "'null'" + "," + "'07/07/21'" + ")";
					 int check = std.executeUpdate(sql_3);
					 System.out.println(check);
					 String sql = "select *  from cart where cid = "+cid ;
					 ResultSet rs = std.executeQuery(sql);
					 
					 
					 while(rs.next())
					 {
						 
					 cart = String.valueOf(rs.getInt(1));	 
					 System.out.println(rs.getInt(1));
					 }
					 int l = Integer.parseInt(cart)*800;
					 String insert = "insert into appears_in values(" + cart + ", " + "1" + ", "+ quantity+ "," + String.valueOf(l) + ")";
					
					 System.out.println("im here");
					int i = std.executeUpdate(insert);
					System.out.println("im here 2");
					System.out.println(i);
					JOptionPane.showConfirmDialog(null, "are you sure of your order ");
					JOptionPane.showMessageDialog(null, "successfully placed your order");
					
						}
					
					}
					catch(Exception f)
					{
						f.printStackTrace();
						JOptionPane.showMessageDialog(null, "oops something went wrong");
					}
				}
		
			});
	
	place_order_2.addActionListener(new AbstractAction()
	{

		public void actionPerformed(ActionEvent e)
		{
			String cart = "";
			try
			{
				if(e.getSource() == place_order_2)
				{
				
			
			db_connect();
			
			
			String cid = JOptionPane.showInputDialog("Enter your cid");
			String quantity = JOptionPane.showInputDialog("Enter quantity");
			String cart_new_id = JOptionPane.showInputDialog("Enter a new cart id to place your order");
			 String sa_name = "";
			 String cc_number = "";
			 String sql_1 = "select saname, ccnumber from shipping_address natural join((credit_card natural join stored_card )natural join customer)where cid = " + cid;
			 ResultSet sr = std.executeQuery(sql_1);
			 while(sr.next())
			 {
				 sa_name = sr.getString(1);
				 cc_number = sr.getString(2);
			 }
			 
			 String sql_3 = "insert into cart values(" + cart_new_id +","+ cid + "," + "'" + sa_name + "'" + "," + "'" + cc_number + "'"+ "," + "'null'" + "," + "'07/07/21'" + ")";
			 int check = std.executeUpdate(sql_3);
			 System.out.println(check);
			
			String sql = "select *  from cart where cid = "+cid ;
			 ResultSet rs = std.executeQuery(sql);
			 while(rs.next())
			 {
				 
			 cart = String.valueOf(rs.getInt(1));	 
			 System.out.println(rs.getInt(1));
			 }
			 int l = Integer.parseInt(cart)*1500;
			 String insert = "insert into appears_in values(" + cart + ", " + "2" + ", "+ quantity+ "," + String.valueOf(l) + ")";
			 System.out.println("im here");
			int i = std.executeUpdate(insert);
			System.out.println("im here 2");
			System.out.println(i);
			JOptionPane.showConfirmDialog(null, "are you sure of your order? ");
			JOptionPane.showMessageDialog(null,"Successfully placed your order");
			
			
				}
			
			}
			catch(Exception f)
			{
				f.printStackTrace();
				JOptionPane.showMessageDialog(null, "oops something went wrong");
			}
		}

	});

	place_order_3.addActionListener(new AbstractAction()
	{

		public void actionPerformed(ActionEvent e)
		{
			String cart = "";
			try
			{
				if(e.getSource() == place_order_3)
				{
				
			
			db_connect();
			
			
			String cid = JOptionPane.showInputDialog("Enter your cid");
			String quantity = JOptionPane.showInputDialog("Enter quantity");
			String cart_new_id = JOptionPane.showInputDialog("Enter a new cart id to place your order");
			 String sa_name = "";
			 String cc_number = "";
			 String sql_1 = "select saname, ccnumber from shipping_address natural join((credit_card natural join stored_card )natural join customer)where cid = " + cid;
			 ResultSet sr = std.executeQuery(sql_1);
			 while(sr.next())
			 {
				 sa_name = sr.getString(1);
				 cc_number = sr.getString(2);
			 }
			 
			 String sql_3 = "insert into cart values(" + cart_new_id +","+ cid + "," + "'" + sa_name + "'" + "," + "'" + cc_number + "'"+ "," + "'null'" + "," + "'07/07/21'" + ")";
			 int check = std.executeUpdate(sql_3);
			 System.out.println(check);
			
			String sql = "select *  from cart where cid = "+cid ;
			 ResultSet rs = std.executeQuery(sql);
			 while(rs.next())
			 {
				 
			 cart = String.valueOf(rs.getInt(1));	 
			 System.out.println(rs.getInt(1));
			 }
			 int l = Integer.parseInt(cart)*500;
			 String insert = "insert into appears_in values(" + cart + ", " + "3" + ", "+ quantity+ "," + String.valueOf(l) + ")";
			 System.out.println("im here");
			int i = std.executeUpdate(insert);
			System.out.println("im here 2");
			System.out.println(i);
			JOptionPane.showConfirmDialog(null, "successfully placed your order ");
			
				}
			
			}
			catch(Exception f)
			{
				f.printStackTrace();
				JOptionPane.showMessageDialog(null, "oops something went wrong");
			}
		}

	});
	Place_order_4.addActionListener(new AbstractAction()
	{

		public void actionPerformed(ActionEvent e)
		{
			String cart = "";
			try
			{
				if(e.getSource() == Place_order_4)
				{
				
			
			db_connect();
			
			
			String cid = JOptionPane.showInputDialog("Enter your cid");
			String quantity = JOptionPane.showInputDialog("Enter quantity");
			String cart_new_id = JOptionPane.showInputDialog("Enter a new cart id to place your order");
			 String sa_name = "";
			 String cc_number = "";
			 String sql_1 = "select saname, ccnumber from shipping_address natural join((credit_card natural join stored_card )natural join customer)where cid = " + cid;
			 ResultSet sr = std.executeQuery(sql_1);
			 while(sr.next())
			 {
				 sa_name = sr.getString(1);
				 cc_number = sr.getString(2);
			 }
			 
			 String sql_3 = "insert into cart values(" + cart_new_id +","+ cid + "," + "'" + sa_name + "'" + "," + "'" + cc_number + "'"+ "," + "'null'" + "," + "'07/07/21'" + ")";
			 int check = std.executeUpdate(sql_3);
			 System.out.println(check);
			
			String sql = "select *  from cart where cid = "+cid ;
			 ResultSet rs = std.executeQuery(sql);
			 while(rs.next())
			 {
				 
			 cart = String.valueOf(rs.getInt(1));	 
			 System.out.println(rs.getInt(1));
			 }
			 int l = Integer.parseInt(cart)*1000;
			 String insert = "insert into appears_in values(" + cart + ", " + "4" + ", "+ quantity+ "," + String.valueOf(l) + ")";
			 System.out.println("im here");
			int i = std.executeUpdate(insert);
			System.out.println("im here 2");
			System.out.println(i);
			JOptionPane.showConfirmDialog(null, "successfully placed your order ");
			
			
				}
			
			}
			catch(Exception f)
			{
				f.printStackTrace();
				JOptionPane.showMessageDialog(null, "oops something went wrong");
			}
		}

	});

	place_order_5.addActionListener(new AbstractAction()
	{

		public void actionPerformed(ActionEvent e)
		{
			String cart = "";
			try
			{
				if(e.getSource() == place_order_5)
				{
				
			
			db_connect();
			
			
			String cid = JOptionPane.showInputDialog("Enter your cid");
			String quantity = JOptionPane.showInputDialog("Enter quantity");
			String cart_new_id = JOptionPane.showInputDialog("Enter a new cart id to place your order");
			 String sa_name = "";
			 String cc_number = "";
			 String sql_1 = "select saname, ccnumber from shipping_address natural join((credit_card natural join stored_card )natural join customer)where cid = " + cid;
			 ResultSet sr = std.executeQuery(sql_1);
			 while(sr.next())
			 {
				 sa_name = sr.getString(1);
				 cc_number = sr.getString(2);
			 }
			 
			 String sql_3 = "insert into cart values(" + cart_new_id +","+ cid + "," + "'" + sa_name + "'" + "," + "'" + cc_number + "'"+ "," + "'null'" + "," + "'07/07/21'" + ")";
			 int check = std.executeUpdate(sql_3);
			 System.out.println(check);
			
			String sql = "select *  from cart where cid = "+cid ;
			 ResultSet rs = std.executeQuery(sql);
			 while(rs.next())
			 {
				 
			 cart = String.valueOf(rs.getInt(1));	 
			 System.out.println(rs.getInt(1));
			 }
			 int l = Integer.parseInt(cart)*1000;
			 String insert = "insert into appears_in values(" + cart + ", " + "5" + ", "+ quantity+ "," + String.valueOf(l) + ")";
			 System.out.println("im here");
			int i = std.executeUpdate(insert);
			System.out.println("im here 2");
			System.out.println(i);
			JOptionPane.showConfirmDialog(null, "successfully placed your order ");
			
				}
			
			}
			catch(Exception f)
			{
				f.printStackTrace();
				JOptionPane.showMessageDialog(null, "oops something went wrong");
			}
		}

	});

}


	
public  void db_connect() throws Exception
{
	
	Class.forName("oracle.jdbc.driver.OracleDriver");
	 connect=DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe","jeeva","joker13"); 
	 std=connect.createStatement();
	 
	 
	
}
	
	
	public static void main (String[] args)
	{
		new Database_product_view();
	}

}
