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





public class DataBaseConn extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JLabel title_label, cid_label, FName_label, LName_label, Email_label, Address_label, phone_label, Status_label, ccNumber_label, secNumber_label, ownerName_label, ccType_label, ccAddress_label,
	  expDate_label, creditLine_label, SAname_label, receipentName_label, street_label, sNumber_label, city_label, zip_label, state_label, country_label, creditdetails_label, shipping_address;
	
	JTextField cid_field, FName_field, LName_field, Email_field, phone_field, status_field, ccNumber_field, secNumber_field, ownerName_field, ccType_field, ccAddress_field, expDate_field,
	creditLine_field, SAName_field, receipentName_field, street_field, sNumber_field, city_field, zip_field, state_field, country_field;
	
	JTextField address;
	
	JButton Register = new JButton();
	
	Container c;
	
	JScrollPane pane;
	
	String cid, fname, lname, email, addressname, phone, status, ccnumber, secnumber, ownername, cctype, ccaddress, expdate, creditline, saname, receipentname, street, snumber,
	city, zip, state, country, creditdetails, shippingaddress;
	
	Statement stmt;
	Connection con;
	 
	
	
	
	
	public DataBaseConn()
	{
		super("Customer Registration Form");
		pack();
		setLayout(null);
		pane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		c = getContentPane();
		c.setBackground(Color.yellow);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		title_label = new JLabel("CUSTOMER REGISTRATION FORM");
		title_label.setBounds(520,10 , 200, 50);
		add(title_label);
		cid_label = new JLabel("Enter your customer ID");
		cid_label.setBounds(10, 60, 150, 50);
		add(cid_label);
		cid_field = new JTextField();
		cid_field.setBounds(300, 80, 200, 20);
		add(cid_field);
		FName_label = new JLabel("Enter your First Name");
		FName_label.setBounds(700, 60, 150, 50);
		add(FName_label);
		FName_field = new JTextField();
		FName_field.setBounds(990, 80, 200, 20);
		add(FName_field);
		LName_label = new JLabel("Enter your Last Name");
		LName_label.setBounds(10, 100, 150, 50);
		add(LName_label);
		LName_field = new JTextField();
		LName_field.setBounds(300, 120,200,20 );
		add(LName_field);
		Email_label = new JLabel("Enter your Email ID");
		Email_label.setBounds(700, 100, 150, 50);
		add(Email_label);
		Email_field = new JTextField();
		Email_field.setBounds(990, 120,200,20);
		add(Email_field);
		Address_label = new JLabel("Enter your Address");
		Address_label.setBounds(10, 140, 150, 50);
		add(Address_label);
		address = new JTextField();
		address.setBounds(300, 160, 200, 40);
		add(address);
		phone_label = new JLabel("Enter your phone number");
		phone_label.setBounds(700, 140,150, 50 );
		add(phone_label);
		phone_field = new JTextField();
		phone_field.setBounds(990, 160, 200, 20);
		add(phone_field);
		Status_label = new JLabel("Enter your Cart id");
		Status_label.setBounds(10, 240, 200, 20);
		add(Status_label);
		status_field = new JTextField();
		status_field.setBounds(300, 240, 200,20);
		add(status_field);
		creditdetails_label = new JLabel("CREDIT CARD DETAILS");
		creditdetails_label.setBounds(540, 280, 200, 50);
		add(creditdetails_label);
		ccNumber_label = new JLabel("Enter your Credit card number");
		ccNumber_label.setBounds(10, 340, 200, 20);
		add(ccNumber_label);
		ccNumber_field = new JTextField();
		ccNumber_field.setBounds(300, 340,200,20);      
		add(ccNumber_field);
		secNumber_label = new JLabel("Enter your sec Number");
		secNumber_label.setBounds(700, 340, 200, 20);
		add(secNumber_label);
		secNumber_field = new JTextField();
		secNumber_field.setBounds(990, 340, 200,20);
		add(secNumber_field);
		ownerName_label = new JLabel("Enter the name of the Credit card owner");
		ownerName_label.setBounds(10, 380, 200, 20);
		add(ownerName_label);
		ownerName_field = new JTextField();
		ownerName_field.setBounds(300, 380, 200, 20);
		add(ownerName_field);
		ccType_label = new JLabel("Enter the type of the credit card");
		ccType_label.setBounds(700, 380, 200, 20);
		add(ccType_label);
		ccType_field = new JTextField();
		ccType_field.setBounds(990, 380, 200, 20);
		add(ccType_field);
		ccAddress_label = new JLabel("Enter the Address of your credit card");
		ccAddress_label.setBounds(10, 420, 200, 20);
		add(ccAddress_label);
		ccAddress_field = new JTextField();
		ccAddress_field.setBounds(300, 420, 200, 20);
		add(ccAddress_field);
		expDate_label = new JLabel("Enter the expiry date of your credit card in dd/mm/yy");
		expDate_label.setBounds(700, 420, 320, 20);
		add(expDate_label);
		expDate_field = new JTextField();
		expDate_field.setBounds(1000, 420, 200, 20);
		add(expDate_field);
		shipping_address = new JLabel("SHIPPING DETAILS");
		shipping_address.setBounds(540, 500, 200, 40);
		add(shipping_address);
		SAname_label = new JLabel("Enter the Shipping Address Name");
		SAname_label.setBounds(10, 560, 200, 20);
		add(SAname_label);
		SAName_field = new JTextField();
		SAName_field.setBounds(300, 560, 200, 20);
		add(SAName_field);
		receipentName_label  = new JLabel("Enter the receipent Name");
		receipentName_label.setBounds(700, 560, 200, 20);
		add(receipentName_label);
		receipentName_field = new JTextField();
		receipentName_field.setBounds(990, 560, 200, 20);
		add(receipentName_field);
		street_label = new JLabel("Enter your Street Name");
		street_label.setBounds(10, 600, 200, 20);
		add(street_label);
		street_field = new JTextField();
		street_field.setBounds(300, 600, 200, 20);
		add(street_field);
		sNumber_label = new JLabel("Enter your Shipping Address Number");
		sNumber_label.setBounds(700, 600, 200, 20);
		add(sNumber_label);
		sNumber_field = new JTextField();
		sNumber_field.setBounds(990, 600, 200, 20);
		add(sNumber_field);
		city_label = new JLabel("Enter your city Name");
		city_label.setBounds(10, 640, 200, 20);
		add(city_label);
		city_field = new JTextField();
		city_field.setBounds(300, 640, 200, 20);
		add(city_field);
		zip_label = new JLabel("Enter your city's zip code");
		zip_label.setBounds(700, 640, 200, 20);
		add(zip_label);
		zip_field = new JTextField();
		zip_field.setBounds(990, 640, 200, 20);
		add(zip_field);
		state_label = new JLabel ("Enter the Name of your state");
		state_label.setBounds(10, 680, 200, 20);
		add(state_label);
		state_field = new JTextField();
		state_field.setBounds(300, 680, 200, 20);
		add(state_field);
		country_label = new JLabel("Enter the Name of your Country");
		country_label.setBounds(700, 680, 200, 20);
		add(country_label);
		country_field = new JTextField();
		country_field.setBounds(990, 680, 200, 20);
		add(country_field);
		add(pane);
		Register = new JButton("REGISTER");
		Register.setBounds(540, 700, 100, 20);
		add(Register);
		connect();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		setVisible(true);
		
		 
		
		
	}
	
	
	public void db_connect() throws Exception
	{
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		 con=DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:xe","jeeva","joker13"); 
		 stmt=con.createStatement();
		 
		
	}
	
	
	
	
	
	
	public  void connect()
	{
		try
		{
	
db_connect();
 
Register.addActionListener(new AbstractAction()
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent event)
			{
				try
				{
			
			if(event.getSource() == Register)
			{
				
				if(cid_field.getText().equals(""))
				{
					JOptionPane.showMessageDialog(cid_field, "please provide customer ID");
				}		
				else if(ccNumber_field.getText().equals(""))
				{
					JOptionPane.showMessageDialog(cid_field, "Please provide ccNumber");
				}
				else if(SAName_field.getText().equals(""))
				{
					JOptionPane.showMessageDialog(cid_field, "Please provide SAName");
				}
				else
				{
				
				cid = cid_field.getText();
				fname = FName_field.getText();
				lname = LName_field.getText();
				email = Email_field.getText();
				addressname = address.getText();
				phone = phone_field.getText();
				status = status_field.getText();
				ccnumber = ccNumber_field.getText();
				secnumber = secNumber_field.getText();
				ownername = ownerName_field.getText();
				cctype = ccType_field.getText();
				ccaddress = ccAddress_field.getText();
				expdate = expDate_field.getText();
				receipentname = receipentName_field.getText();
				street = street_field.getText();
				snumber = sNumber_field.getText();
				city = city_field.getText();
				zip = zip_field.getText();
				state = state_field.getText();
				country = country_field.getText();
				saname = SAName_field.getText();
				String sql = "insert into customer values(" + cid + ", "+ "'"+ fname+ "'" + ", " + "'"+  lname + "'" +
						 ", "+ "'" + email + "'" + ", "+ "'" + addressname + "'" + ", " + "'" + phone + "'" + ", " + "null" +")";
				int i = stmt.executeUpdate(sql);
				System.out.println(i);
				sql = "insert into stored_card values(" + "'"+  ccnumber + "'" +
						 "," + cid + ")";
				int ji = stmt.executeUpdate(sql);
				System.out.println(ji);
				sql = "insert into credit_card values(" + "'" + ccnumber + "'"+ "," + secnumber +","+ "'" + ownername + "'"+ "," +"'" + cctype
						 + "'" +","+ "'" + ccaddress + "'" +"," + "'" + expdate + "'"+ ")";
			
				int j = stmt.executeUpdate(sql);
				System.out.println(j);
			
				sql = "insert into shipping_address values("  + cid + "," + "'"
						+ saname + "'" + ", "+ "'" + receipentname + "'" + ", "+ "'" + street + "'"+"," + snumber + ","+ "'" + city + "'" + ","+  "'" + zip + "'" + "," + "'" + state + "'" + "," +"'" + country + "'" +")";
				int k = stmt.executeUpdate(sql);
				System.out.println(k);
				sql = "insert into cart values(" + status +","+ cid + "," + "'" + saname + "'" + "," + "'" + ccnumber + "'"+ "," + "'null'" + "," + "'07/07/21'" + ")";
				int l = stmt.executeUpdate(sql);
				System.out.println(l);
				JOptionPane.showMessageDialog(null, "successfully registered");
				cid_field.setText("");
				FName_field.setText("");
				LName_field.setText("");
				Email_field.setText("");
				address.setText("");
				phone_field.setText("");
				status_field.setText("");
				ccNumber_field.setText("");
				secNumber_field.setText("");
				ownerName_field.setText("");
				ccType_field.setText("");
				ccAddress_field.setText("");
				expDate_field.setText("");
				receipentName_field.setText("");
				street_field.setText("");
				sNumber_field.setText("");
				city_field.setText("");
				zip_field.setText("");
				state_field.setText("");
				country_field.setText("");
				SAName_field.setText("");

				
				}
			}
				
				
				}
			catch(Exception e)
			{
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "One of your credintials is in-correct");
			}
				
				
			
			}
			
			
			
		
		}
		); 

 			  
			  
			 
			
			  
	
	
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
}
	
	public static void main(String[] args)
	{
		new DataBaseConn();
	}
	
	
	
	
	
	
}
