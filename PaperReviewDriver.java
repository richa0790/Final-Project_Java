
import java.sql.*;
import java.util.Scanner;
public class PaperReviewDriver {
	
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/paper_reviews";

	static final String USER = "root";
	static final String PASS = "123456";
	public static void main(String[] args) {
	
		Boolean value = true;
		while(value)
		{
			System.out.println("\n Select from below options:");
			System.out.println("1. Get submitted paper");
			System.out.println("2. Get all reviews by paper id:");
			System.out.println("3. Show count of submitted papers:");
			System.out.println("4. Add new paper");
			System.out.println("5. Delete author");
			System.out.println("6. Exit");
			
			Scanner reader = new Scanner(System.in);
			int i  = reader.nextInt();
			  
			switch(i) 
			{
			case 1:
				submittedPaper();
				break;
			case 2:
				review();
				break;
			case 3:
				Paper_Count();
				break;
				
			case 4:
				AddNewPaper();
				break;
			case 5:
				DeleteAuthor();
				break;
				
			case 6:
				value = false;
				break;
			}
			
		} 
		//submittedPaper();
		//review();
		//Paper_Count();
		//AddNewPaper();
		//DeleteAuthor();
		}
	
	public static void DeleteAuthor() {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		
		try {
			 //STEP 2: Register JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,USER,PASS);
		      
		    //STEP 4: Execute a query
		  System.out.println("Select author email id from list to delete:-");
		  stmt = conn.createStatement();
		  String SelectAuthor;
		 
		  SelectAuthor = "SELECT * FROM author;"; 
			 
		  ResultSet rs = stmt.executeQuery(SelectAuthor);
	      
	      //STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         String emailAddr=rs.getString("emailAddr");
	         String Fname=rs.getString("fname");
	         String Lname=rs.getString("lname");
	         
	         //Display values\
	         System.out.print(" ,Author Id: "+ emailAddr);
	         System.out.print(" ,First Name: "+ Fname);
	         System.out.println(" ,Last Name: "+ Lname);
	         
	      }
		  
	      Scanner authorEmailId = new Scanner(System.in);
		  String ac = authorEmailId.next();
		  System.out.println("Author email id " + ac);
		  
		  String DeleteQuery = "DELETE FROM author WHERE emailAddr = ?";
		  
		  PreparedStatement preparedStmt = conn.prepareStatement(DeleteQuery);
		  
		  preparedStmt.setString (1, ac);
		// execute the preparedstatement
	      preparedStmt.execute();
	      
		  System.out.println("Trying to Delete author : " + ac);
		      
		      //STEP 5: Extract data from result set
		    rs.close();
		    stmt.close();
		    conn.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			//finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
	}
	}
	


			public static void AddNewPaper() {
				// TODO Auto-generated method stub
				Connection conn = null;
				Statement stmt = null;
				
				try {
					 //STEP 2: Register JDBC driver
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					//STEP 3: Open a connection
				      System.out.println("Connecting to database...");
				      conn = DriverManager.getConnection(DB_URL,USER,PASS);
				      
				    //STEP 4: Execute a query
				  System.out.println("Creating statement...");
				  
				  String InsertIntoAuthor;
				  String InsertIntoPaper;
				  
				  System.out.println("Enter Author Email id: ");
				  Scanner authorEmailId = new Scanner(System.in);
				  String ac = authorEmailId.next();
				  System.out.println("Author email id " + ac);
				  
				  System.out.println("Enter First Name: ");
				  Scanner firstName=new Scanner(System.in);
				  String fn = firstName.next();
				  System.out.println("FN " + fn);
				  
				  System.out.println("Enter Last Name: ");
				  Scanner lastName=new Scanner(System.in);
				  String ln = lastName.next();
				  System.out.println("LN " + ln);
				  
				  
				  System.out.println("Enter Details for Paper Submission: ");
				  System.out.println("Paper Title: ");
				  Scanner ptitle=new Scanner(System.in);
				  String pt = ptitle.next();
				  System.out.println("Paper Title" + pt);
				  
				  System.out.println("Enter Abstract Details: ");
				  Scanner Abstrac=new Scanner(System.in);
				  String Abs = Abstrac.next();
				  System.out.println("Abstract Details:  " + Abs);
				  
				  System.out.println("Enter File Name: ");
				  Scanner fileName=new Scanner(System.in);
				  String filen = fileName.next();
				  System.out.println("Filen Name " + filen);
				  
				  
				  InsertIntoAuthor = "INSERT INTO author(emailAddr,fname,lname) VALUES (?,?,?)"; 
				  PreparedStatement preparedStmt = conn.prepareStatement(InsertIntoAuthor);
				  
				  preparedStmt.setString (1, ac);
			      preparedStmt.setString (2, fn);
			      preparedStmt.setString (3, ln);			  
				  preparedStmt.execute();
				  
				  InsertIntoPaper = "INSERT INTO paper(paperTitle, Abstarct, FileName,AuthorId) VALUES (?,?,?,?)"; 
				  preparedStmt = conn.prepareStatement(InsertIntoPaper);
				  preparedStmt.setString (1, pt);
			      preparedStmt.setString (2, Abs);
			      preparedStmt.setString (3, filen);
			      preparedStmt.setString (4, ac);		      
				  preparedStmt.execute();
	
				  System.out.println("New Paper submission added successfully.");
				      
				      //STEP 5: Extract data from result set
				    //rs.close();
				    //stmt.close();
				    conn.close();
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					//finally block used to close resources
				      try{
				         if(stmt!=null)
				            stmt.close();
				      }catch(SQLException se2){
				      }// nothing we can do
				      try{
				         if(conn!=null)
				            conn.close();
				      }catch(SQLException se){
				         se.printStackTrace();
				      }//end finally try
			}
			}
			

			
	
			public static void submittedPaper() {
				
				// TODO Auto-generated method stub
				Connection conn = null;
				Statement stmt = null;
				
				try {
					 //STEP 2: Register JDBC driver
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					//STEP 3: Open a connection
				      System.out.println("Connecting to database...");
				      conn = DriverManager.getConnection(DB_URL,USER,PASS);
				      
				    //STEP 4: Execute a query
				      System.out.println("Creating statement...");
				      stmt = conn.createStatement();
				      String sql;
				  
				      sql = "select paper.paperId, paper.paperTitle, paper.Abstarct, Author.emailAddr, author.fname, author.lname FROM paper INNER JOIN author ON paper.authorId=author.emailAddr";
				      ResultSet rs = stmt.executeQuery(sql);
				      
				      //STEP 5: Extract data from result set
				      while(rs.next()){
				         //Retrieve by column name
				         int PaperId  = rs.getInt("paperId");
				         String pTitle=rs.getString("paperTitle");
				         String Abstract=rs.getString("Abstarct");
				         String emailAddr=rs.getString("emailAddr");
				         String Fname=rs.getString("fname");
				         String Lname=rs.getString("lname");
				         
				       

				         //Display values
				         System.out.print("Paper ID: " + PaperId);
				         System.out.print(" ,Paper Title: "+ pTitle);
				         System.out.print(" ,Abstract: "+ Abstract);
				         System.out.print(" ,Author Id: "+ emailAddr);
				         System.out.print(" ,First Name: "+ Fname);
				         System.out.println(" ,Last Name: "+ Lname);
				         
				      }
				      rs.close();
				      stmt.close();
				      conn.close();
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					//finally block used to close resources
				      try{
				         if(stmt!=null)
				            stmt.close();
				      }catch(SQLException se2){
				      }// nothing we can do
				      try{
				         if(conn!=null)
				            conn.close();
				      }catch(SQLException se){
				         se.printStackTrace();
				      }//end finally try
			}
			}
			
			public static void review() {
				
				// TODO Auto-generated method stub
				Connection conn = null;
				Statement stmt = null;
				
				try {
					 //STEP 2: Register JDBC driver
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					//STEP 3: Open a connection
				      System.out.println("Connecting to database...");
				      conn = DriverManager.getConnection(DB_URL,USER,PASS);
				      
				    //STEP 4: Execute a query
				      System.out.println("Creating statement...");
				      stmt = conn.createStatement();
				      String sql;
				      sql = "SELECT * FROM Review WHERE recomm='Yes' ORDER BY paperId;";
				      ResultSet rs = stmt.executeQuery(sql);
				      
				      //STEP 5: Extract data from result set
				      while(rs.next()){
				         //Retrieve by column name
				         int ReviewId  = rs.getInt("ReviewId");
				         String recomm=rs.getString("recomm");
				         int MScore=rs.getInt("MeritScore");
				         int RScore=rs.getInt("MeritScore");
				         int OScore=rs.getInt("OriginalityScore");
				         int RelScore=rs.getInt("RelevenceSocre");
				         int PaperId=rs.getInt("PaperId");
				         String ReviewerId=rs.getString("ReviewerId");
				         
				       
				         //Display values
				         System.out.print("Review ID: " + ReviewId);
				         System.out.print(" ,Recommendation: "+ recomm);
				         System.out.print(" ,Merit Score: "+ MScore);
				         System.out.print(" ,Readibility Score: "+ RScore);
				         System.out.print(" ,Originality Score: "+ OScore);
				         System.out.println(" ,Relevence Score: "+ RelScore);
				         System.out.print(" ,Paper Id: "+ PaperId);
				         System.out.println(" ,Reviewer Id: "+ ReviewerId);
				         
				      }
				      rs.close();
				      stmt.close();
				      conn.close();
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					//finally block used to close resources
				      try{
				         if(stmt!=null)
				            stmt.close();
				      }catch(SQLException se2){
				      }// nothing we can do
				      try{
				         if(conn!=null)
				            conn.close();
				      }catch(SQLException se){
				         se.printStackTrace();
				      }//end finally try
			}
				
			}
			
public static void Paper_Count() {
				
				// TODO Auto-generated method stub
				Connection conn = null;
				Statement stmt = null;
				
				try {
					 //STEP 2: Register JDBC driver
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					//STEP 3: Open a connection
				      System.out.println("Connecting to database...");
				      conn = DriverManager.getConnection(DB_URL,USER,PASS);
				      
				    //STEP 4: Execute a query
				      System.out.println("Creating statement...");
				      stmt = conn.createStatement();
				      String sql;
				      sql = "SELECT COUNT(*) AS count FROM paper WHERE AuthorId IS NOT NULL ;";
				      ResultSet rs = stmt.executeQuery(sql);
				      while(rs.next()){
				      int ctr  = rs.getInt("count");
				       
				         //Display values
				      
				         System.out.print("Paper_Count:  " + ctr);
				         
				      }
				      
				     rs.close();
				      stmt.close();
				      conn.close();
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finally {
					//finally block used to close resources
				      try{
				         if(stmt!=null)
				            stmt.close();
				      }catch(SQLException se2){
				      }// nothing we can do
				      try{
				         if(conn!=null)
				            conn.close();
				      }catch(SQLException se){
				         se.printStackTrace();
				      }//end finally try
			}
				
			}

public static void Paper_Submit() {
	
	// TODO Auto-generated method stub
	Connection conn = null;
	Statement stmt = null;
	
	try {
		 //STEP 2: Register JDBC driver
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);
	      
	      Scanner in = new Scanner(System.in);
	      String pTitle;
	      String Abs;
	      String file;
	      String author;
	      
	      System.out.println("****New Paper Submission****");
	      System.out.println("Enter Paper Title: ");
	      pTitle = in.nextLine();
	      System.out.println("Enter Abstract: ");
	      Abs = in.nextLine();
	      System.out.println("Enter file name: ");
	      file=in.nextLine();
	      System.out.println("Enter author name: ");
	      author=in.nextLine();
	      
	      
	      
	     
	      
	    //STEP 4: Execute a query
	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      String sql;
	      sql = "SELECT * FROM Review WHERE recomm='Yes' ORDER BY paperId;";
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      //STEP 5: Extract data from result set
	      while(rs.next()){
	         //Retrieve by column name
	         int ReviewId  = rs.getInt("ReviewId");
	         String recomm=rs.getString("recomm");
	         int MScore=rs.getInt("MeritScore");
	         int RScore=rs.getInt("MeritScore");
	         int OScore=rs.getInt("OriginalityScore");
	         int RelScore=rs.getInt("RelevenceSocre");
	         int PaperId=rs.getInt("PaperId");
	         String ReviewerId=rs.getString("ReviewerId");
	         
	       
	         //Display values
	         System.out.print("Review ID: " + ReviewId);
	         System.out.print(" ,Recommendation: "+ recomm);
	         System.out.print(" ,Merit Score: "+ MScore);
	         System.out.print(" ,Readibility Score: "+ RScore);
	         System.out.print(" ,Originality Score: "+ OScore);
	         System.out.println(" ,Relevence Score: "+ RelScore);
	         System.out.print(" ,Paper Id: "+ PaperId);
	         System.out.println(" ,Reviewer Id: "+ ReviewerId);
	         
	      }
	      in.close();
	      rs.close();
	      stmt.close();
	      conn.close();
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally {
		//finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
}
	
}


}
		


