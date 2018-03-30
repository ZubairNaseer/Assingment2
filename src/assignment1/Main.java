package assignment1;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

import com.sun.swing.internal.plaf.basic.resources.basic;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
  

public class Main {
        

         public static ArrayList<Customer> customers=new ArrayList<>();
         public static ArrayList<Account> Accounts=new ArrayList<>();
	  
         public void WriteCustomerFile(FileOutputStream out) throws IOException
	     {   DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
	    	 for(int i=0;i<customers.size();i++)
	         {
	        
	         out.write((customers.get(i)).getCustomerId().getBytes());
	         out.write(" ".getBytes());
	         out.write((customers.get(i)).getFName().getBytes());
	         out.write(" ".getBytes());
	         out.write((customers.get(i)).getLName().getBytes());
	         out.write(" ".getBytes());
	         out.write((customers.get(i)).getAddress().getBytes());
	         out.write(" ".getBytes());
	         out.write((customers.get(i)).getSex().getBytes());
	         out.write(" ".getBytes());
	         out.write((customers.get(i)).getPswd().getBytes());
	         out.write(" ".getBytes());
	         out.write(Integer.toString((customers.get(i)).getAge()).getBytes());
	         out.write(" ".getBytes());
	         out.write(dateFormat.format((customers.get(i)).getDOB()).getBytes());
	         out.write(" ".getBytes());
	         out.write((customers.get(i)).getOccupation().getBytes());
	         out.write("\r\n".getBytes());
	        
	         }
	     }
	     
	     public void WriteAccountFile(int customerNo) throws IOException
	     {   DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	         File Basic=new File("C:/Users/sadaf/Desktop/BasicBankingAccounts.txt");
		     File Saving=new File("C:/Users/sadaf/Desktop/SavingAccounts.txt");
		     File Current=new File("C:/Users/sadaf/Desktop/CurrentAccounts.txt");
		     FileOutputStream out;
		     
		     for(int i=0;i<Accounts.size();i++)
	         {
	         if (Accounts.get(i) instanceof BasicBankingAccount)
	         out=new FileOutputStream(Basic.getAbsolutePath(),true);
	         else if (Accounts.get(i) instanceof SavingAccount )
	         out=new FileOutputStream(Saving.getAbsolutePath(),true);	 
	         else
	         out=new FileOutputStream(Current.getAbsolutePath(),true); 
	         
	         out.write((Accounts.get(i)).getAccountId().getBytes());
	         out.write(" ".getBytes());
	         out.write(Double.toString((Accounts.get(i)).getCurrentBalance()).getBytes());
	         out.write(" ".getBytes());
	         out.write((Accounts.get(i)).getCurrency().getBytes());
	         out.write(" ".getBytes());
	         out.write(dateFormat.format((Accounts.get(i)).getCreationDate()).getBytes());
	         out.write(" ".getBytes());
	         out.write(Double.toString(Accounts.get(i).getInterestRate()).getBytes());
	         out.write(" ".getBytes());
	         out.write(Accounts.get(i).getStatus().getBytes());
	         out.write(" ".getBytes());
	         out.write((customers.get(customerNo)).getCustomerId().getBytes());
	         out.write(" ".getBytes());
	         out.write("null".getBytes());
	         out.write("\r\n".getBytes());
             out.close();
	         }
		 
	      }
	     
         public ArrayList<String> read(FileInputStream fin) throws IOException
        {
         ArrayList<String> readlines=new ArrayList<>();
         String oneLine="";
		 int content=0;
		 while ((content=fin.read())!=-1)
		 { 
		 oneLine+=((char)content);
		 if (content=='\n')
		 {
		 readlines.add(oneLine);
		 oneLine="";
		 }
	     }
		  return readlines;
       }
        
       public Set newCustomerID(FileInputStream fin,int timeLimit) throws IOException,ParseException
       {
         int SpaceCounter=0;
         String ID="";
         String CreationDate="";
         Set IdSet=new HashSet();
   	 	 int content=0;
   	     DateFormat dateformat=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss" );
   	 	 while ((content=fin.read())!=-1)
    	 {     
   	 	 if(((char)content)==' ')
    	 SpaceCounter++;
         if(SpaceCounter==7)
         ID+=(char)content;
         if (SpaceCounter==3 || SpaceCounter==4)
         CreationDate+=((char)content);
         if((char)content=='\n')
         { 
         SpaceCounter=0;
    	 CreationDate=CreationDate.replaceFirst(" ","");
         Date stdate=(Date)dateformat.parse(CreationDate) ;
    	 Date date=new Date();
    	 String d1=dateformat.format(date);
    	 Date EndDate=(Date)dateformat.parse(d1);
    	 long diff=EndDate.getTime()-stdate.getTime();
    	 long diffhours=diff/(60*60*1000);
         if (diffhours<timeLimit)
    	 {   
         ID=ID.replace(" ", "");
    	 IdSet.add(ID);
    	 }
    	 ID="";
    	 CreationDate="";
    	 }
        }
   	 	 return IdSet; 
       }//end newCustomerID function
        
	    public static void main(String[] args) throws ParseException {
	    try {
	    	
	    File customer=new File("C:/Users/sadaf/Desktop/Customer.txt");
	    File Basic=new File("C:/Users/sadaf/Desktop/BasicBankingAccounts.txt");
	    File Saving=new File("C:/Users/sadaf/Desktop/SavingAccounts.txt");
	    File Current=new File("C:/Users/sadaf/Desktop/CurrentAccounts.txt");
	    
	    if (!customer.exists())
	    customer.createNewFile();
	    if (!Basic.exists())
	    Basic.createNewFile(); //To create new file if file not exist
	    if (!Saving.exists())
	    Saving.createNewFile();
	    if (!Current.exists())
	    Current.createNewFile();
	    
	    Main mn=new Main();//create main class object
	    DateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
	    String dateTostring="";
	    Date stdate=new Date();
	    Date date=new Date();
	    Scanner ds=new Scanner(System.in);
	    Scanner cr=new Scanner(System.in);
	    String str=null;
	    StringTokenizer st=new StringTokenizer("yes");
		ArrayList<String> readlines =new ArrayList<>();
	
	    String id=null;
	    int content=0;
	    String oneLine="";
	    double depositAmount=0;
	    int input=0;
	
	    for(int k=0;k<1000;k++) {//loop for manu	   
	    System.out.println("1.Create account");
		System.out.println("2.perform some opertion Using Existing account");
		System.out.println("3.Output information of All Customer");
		System.out.println("4.Output account details of the customers registered within last 24 hours");
		System.out.println("5.Detail Report On daily Basis");
        System.out.println("6.Exit");
		
		Scanner sc=new Scanner(System.in);
		input=sc.nextInt();
		if(input==6) // condition to exit the loop
		k=1000;
		Account ac=new BasicBankingAccount();
		if (input==1) //type 1 to create new cutomer
	    {
		
		int registerCustomer=0;
		while (registerCustomer<10) {
			Customer c=new Customer();//create customer class object
		DateFormat dateFormat1=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss" );
		String dateToId=dateFormat1.format(date);
		id="";
		for (int j = 0; j < dateToId.length(); j++) {//create Unique Id by using system date
		if (dateToId.charAt(j)!=':' &&dateToId.charAt(j)!='/' && dateToId.charAt(j)!=' ')
	    id+=dateToId.charAt(j);
		} 
		 c.setCustomerId(id); //Take input and set Customer info
		 System.out.println("Enter First Name:");
		 Scanner l=new Scanner(System.in);
		 String f=l.nextLine();
		 c.setFName(f);
		 System.out.println("Enter Last Name:");
		 f=l.nextLine();
		 c.setLName(f);
		 System.out.println("Enter your address:");
		 f=l.nextLine();
		 c.setAddress(f);
		 System.out.println("Enter Gender:");
		 f=l.nextLine();
		 c.setSex(f);
		 System.out.println("Enter your 6 digit pswd:");
		 f=l.nextLine();
		 c.setPswd(f);
		 System.out.println("Enter Occupation");
    	 f=l.nextLine();
    	 c.setOccupation(f);
		 System.out.println("Enter your age:");
		 int age=sc.nextInt();
		 c.setAge(age);
		 System.out.println("Enter Your Date Of Birth in DD/MM/yyyy format");
		 String std=ds.nextLine();
		 stdate=(Date)dateFormat.parse(std);
    	 c.setDOB(stdate);
    	 LinkedList<String> AcountTypes =new LinkedList(); 
    	 AcountTypes.add(" 1.Basic Bankin account");
    	 AcountTypes.add(" 2.Saving acount");
    	 AcountTypes.add(" 3.Current account");
    	 int acc_iterator=0;
    	 
    	 while (acc_iterator<3)
    	 {
    	 System.out.println("Select account type");
		 for (int i=0;i<AcountTypes.size();i++)  
		 System.out.println(AcountTypes.get(i));//print types of accounts
		 
		 int a_type=sc.nextInt();
		 if (a_type==1)
		 {
          c.setAccountType("BasicBankingAccount");//add one acount type in customer account type array
          AcountTypes.remove(" 1.Basic Bankin account");//reomve Basic Acount type from option if customer create one Basic Acount
		 }
		 else if (a_type==2)
		 { c.setAccountType("SavingAccount");  //add one acount type in customer account type array
		   AcountTypes.remove(" 2.Saving acount");//reomve Basic Acount type from option if customer create one Saving Acount
		 }
		 else if (a_type==3)
		 {
		 c.setAccountType("CurrentAccount");
		 AcountTypes.remove(" 3.Current account");
		 }
		 System.out.println(" 1.you want to create account");
   	     System.out.println(" 2.No");
   	     int more=sc.nextInt();
   	     if (more==1)           //Give option to create more account
   	     acc_iterator++;
   	     else
   	     acc_iterator=3;
    	}//end while loop 
    	 Scanner sc1=new Scanner(System.in);
		 ArrayList<String> arr=c.getAccountType();
		 for (int i = 0; i < arr.size(); i++) {
		 String type=arr.get(i);	
		 
	     if (type.equals("BasicBankingAccount")) //create basic type Account 
	     {  
	     dateFormat1=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		 date=new Date();
		 dateToId=dateFormat1.format(date);
		 id="B"; //start id with B for Basic account
		 for (int j = 0; j <  dateToId.length(); j++) //create Unique Id by using system date
		 {
	     if ( dateToId.charAt(j)!=':' &&  dateToId.charAt(j)!='/' &&  dateToId.charAt(j)!=' ')
		 id+= dateToId.charAt(j);
		 } 
	     System.out.println("Desposit One thosand to create Basic Account");
	     depositAmount=sc1.nextDouble();
	     while(depositAmount!=1000)//loop to ReEnter amount if user enter less than 1000
		 {  
	     System.out.println("You enter less amount: plz enter 1000");
		 depositAmount=sc1.nextDouble();
		 }
	       
	     dateFormat1=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	     date=new Date();
	 	 String s1=dateFormat1.format(date);
	 	 stdate=(Date)dateFormat1.parse(s1);
	 	 System.out.println("Enter type of Currency"); 
         s1=cr.nextLine(); //call Basic Account permitrized consrtuctor
         ac=new BasicBankingAccount(id,depositAmount,stdate,s1,0.06,"Open");
         System.out.println("Congratulations your account created,Account type is BasicAccount and accountId is:"+id);
  	     //writing into Basic account file
         Accounts.add(ac);
  		
	     }
	     if  (type.equals("SavingAccount"))//create saving Account
	     {  
	     dateFormat1=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss" );
	     date=new Date();
		 dateToId=dateFormat1.format(date);
		 id="S"; //start id with S for Saving account
		 for (int j = 0; j <  dateToId.length(); j++) {//create Unique Id by using system date
		 if (dateToId.charAt(j)!=':' && dateToId.charAt(j)!='/' &&  dateToId.charAt(j)!=' ')
		 id+= dateToId.charAt(j);
		 }
		 System.out.println();
	     System.out.println("Desposit five thosand to create Saving Account");
		 depositAmount=sc1.nextDouble();
		 while(depositAmount!=5000)//loop to ReEnter amount if user enter less than 1000
		 { 
	     System.out.println("You enter less amount: plz enter 5000");
		 depositAmount=sc1.nextDouble();
		 }
		 dateFormat1=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss" );
		 stdate=new Date();
	     System.out.println("Enter type of Currency"); 
	     String s1=cr.nextLine();
	     ac=new SavingAccount(id,depositAmount,stdate,s1,0.10,"open");
	     System.out.println("Congratulations your account created,Account type is SavingAccount and accountId is:"+id); 
	     Accounts.add(ac);
	     }//end saving account type
	     if (type.equals("CurrentAccount"))
	     {   
	     dateFormat1=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss" );
		 date=new Date();
	     dateToId=dateFormat1.format(date);
	     id="C";// start account id with C for current account
		 for (int j = 0; j < dateToId.length(); j++) {//create Unique Id by using system date
		 if (dateToId.charAt(j)!=':' && dateToId.charAt(j)!='/' && dateToId.charAt(j)!=' ')
	     id+=dateToId.charAt(j);
		 }
	     dateFormat1=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		 stdate=new Date();
		 System.out.println();
	     System.out.println("Enter type of Currency for Current Account"); 
	     String s1=cr.nextLine();
         ac=new CurrentAccount(id,0,stdate,s1,0.05,"open");
         System.out.println("Congratulations your account created,Account type is CurrentAccount and accountId is:"+id);
         Accounts.add(ac);
	     }//end current account
	    }//end account type loop
		 customers.add(c);
		 mn.WriteAccountFile(registerCustomer);
		 Accounts.clear();
		 
		 System.out.println("1.you want to Register More Customer");
	     System.out.println("2.No");
		 int n=sc.nextInt(); 
		 if(n==1)
		 registerCustomer++;
		 else
	     registerCustomer=10;
		 }//end while loop 
		 
    	 FileOutputStream out=new FileOutputStream((customer.getAbsoluteFile()),true);
	     mn.WriteCustomerFile(out);
	     out.close();
	     customers.clear();
	  }//end if
   	    
	      if(input==2)
	      {   
	        boolean PsFlag=false; //PSFlag and IdFlag use to athenticate the user
	        boolean IdFlag=false;  
	        int m=0;
	    	Scanner sc3=new Scanner(System.in);
	    	System.out.println("Enter accountId");
		    id=sc3.nextLine();
		    FileInputStream fin;
	   		if(id.charAt(0)=='B')
	   	    fin=new FileInputStream(Basic);//open file according to ID fst charactar whic show Account Type
	    	else if(id.charAt(0)=='S')
	        fin=new FileInputStream(Saving);
	        else
	        fin=new FileInputStream(Current);
	   		readlines=mn.read(fin);
			fin.close();
		    ArrayList <String> tmp=new ArrayList<>();
		    for (int i = 0; i < readlines.size(); i++) 
		   {
			oneLine=readlines.get(i);
		    st=new StringTokenizer(oneLine);
		    for (int j= 0; j <oneLine.length(); j++) 
		    {  
		    if (st.hasMoreTokens())
			str=st.nextToken();//get Account Id from file
			if (str.equals(id))//compare it with user entered input ID
		    {
			IdFlag=true;
			j=oneLine.length();//break loop if Id find
			while(st.hasMoreTokens())
		   	tmp.add(st.nextToken());//fill tmp array with account info whose ID match
			}
		    } 
		   } 
		    
		    readlines.clear();//clear Array to use it for other File reading
		    FileInputStream fin2=new FileInputStream(customer); 
		    readlines=mn.read(fin2);//read customer file
			fin2.close();
		    System.out.println("Enter Password");
		    String pswd=sc3.nextLine();
		    PsFlag=false;
		    String custID=tmp.get(6);//get customer id from Account file
		    tmp.clear();
		    for (int i = 0; i < readlines.size(); i++) 
		   {
			oneLine=readlines.get(i); 
		    st=new StringTokenizer(oneLine); 
		    if(st.hasMoreTokens())
			{   
		    str=st.nextToken(); //get customer ID
    	    if (str.equals(custID))//compare it with customer id written in accounts file
		    {
    	    while(st.hasMoreTokens())
    		tmp.add(st.nextToken());
    	    if(pswd.equals(tmp.get(4)))//compare pswd
    		PsFlag=true;
    		}
		    }
		   }
		     	  
		    
		    
	    	if (PsFlag)//if user Id and pswd correct
	       {
	        int mTransections=0;
	        while (mTransections<10){//perform multiple transection
	    	DateFormat dateFormat1=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	        readlines.clear();
	        ArrayList<String> writeLines=new ArrayList();//array to write in files
	        FileInputStream fin3;
	   		if(id.charAt(0)=='B')
	   	    { fin3=new FileInputStream(Basic);
	   	      ac=new BasicBankingAccount();
	   	    }
	   		else if(id.charAt(0)=='S') //set file variable with file that need to open
		    {fin3=new FileInputStream(Saving);
		    ac=new SavingAccount();
		    }
		    else
		    {fin3=new FileInputStream(Current);
		     ac=new CurrentAccount();
		    }
		    readlines=mn.read(fin3);
			fin3.close();
		    st=new StringTokenizer("Y");
	        String ID="";  
	   	    tmp=new ArrayList<>();
	   	    int Amount=0;
	   		for (int i = 0; i < readlines.size(); i++) 
	   		{
	   		oneLine=readlines.get(i);
	   		st=new StringTokenizer(oneLine);
	   		if(st.hasMoreTokens())
	   		{  
	   		ID=st.nextToken();
	   		while((st.hasMoreTokens() && id.equals(ID)))
	   		tmp.add(st.nextToken());
	   		
	   		if(id.equals(ID) && tmp.get(5).equals("open")){
	   		System.out.println("1.WithDraw");
		    System.out.println("2.Deposit amount");
		    System.out.println("3.view AccountDetail");
		    m=sc.nextInt();
		    if(m==1)
			{
			System.out.println("Enter WithDraw Amount:");
			Amount=sc.nextInt();//perform withdraw operation and update Withdraw date in file
		   	double remaining=ac.WithDraw(Double.parseDouble(tmp.get(0)),Amount);
		   	oneLine=oneLine.replace(tmp.get(0), Double.toString(remaining));
		   	dateFormat1=new SimpleDateFormat("dd/MM/yyyy");
			date=new Date();
			String s1=dateFormat1.format(date);
		   	oneLine=oneLine.replace(tmp.get(7),s1);//update balance
			}
				   
		    else if (m==2)
		    {   
		    System.out.println("Enter Deposit Amount:");
		    Amount=sc.nextInt();
			double newBalance=ac.Deposit(Double.parseDouble(tmp.get(0)),Amount);
			//set balance after depositing
			oneLine=oneLine.replace(tmp.get(0), Double.toString(newBalance)) ;
		    }
		    else if(m==3)
			{//display current balance
			ac.ViewAccountDetail(Double.parseDouble(tmp.get(0)));
			}
		    writeLines.add(oneLine);//write line after updating record  
	   		}
		    else
	   		writeLines.add(oneLine);//else write without updating any record
	   		}
	   	   }     
	   	    fin3.close();
	   	    FileOutputStream out;
	   	    if(id.charAt(0)=='B')
	   	    out=new FileOutputStream(Basic);
	   		else if(id.charAt(0)=='S')
	   	    out=new FileOutputStream(Saving);
	   	    else
	        out=new FileOutputStream(Current);
	        for (int j = 0; j < writeLines.size(); j++) //writing after performing transection
		    {
		    out.write(writeLines.get(j).getBytes());
		    }
		    out.close();	
		    System.out.println("1.you want more transection");
		    System.out.println("2.NO");
		    if(sc.nextInt()==1)
		    mTransections++;
		    else
		    mTransections=10;
	        }
	   	    }//end PSflag_if
	        else
	    	System.out.println("Id or Password incorrect select option 2 and try again");
	       }//end if
	   
	     
	    
	    if (input==3)
	    {

	    FileInputStream in=new FileInputStream((customer.getAbsoluteFile()));
	    content=0;
	    while ((content=in.read())!=-1)
	     System.out.print((char)content);
	    in.close();
	    }	
	  
	       if(input==4){
	       Set IdSet=new HashSet();
 		   
 		   DateFormat dateformat1=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss" );
 		   readlines.clear();
 		   System.out.println("Enter time limit in hours to show register customer's account Detail ");
 		   Scanner tm=new Scanner(System.in);
 		   int limit=tm.nextInt();
 		   FileInputStream in=new FileInputStream((Basic.getAbsoluteFile()));
 		   IdSet.addAll(mn.newCustomerID(in, limit));
 		   in=new FileInputStream((Saving.getAbsoluteFile()));
		   IdSet.addAll(mn.newCustomerID(in, limit));
		   in=new FileInputStream((Current.getAbsoluteFile()));
 		   IdSet.addAll(mn.newCustomerID(in, limit));
		   in.close();
	 	   FileInputStream inputst=new FileInputStream((customer.getAbsoluteFile()));
		   readlines=mn.read(inputst);
		   inputst.close();
		   
		   String ID="";
			for (int j= 0; j < readlines.size(); j++) 
		    {  Iterator <String> iterator=IdSet.iterator();
		   	   oneLine=readlines.get(j);
			   st=new StringTokenizer(oneLine);
			   if(st.hasMoreTokens())
			   ID=st.nextToken();
			   
			   while (iterator.hasNext())
			   { 
			     if(ID.equals(iterator.next()))
	             System.out.println(oneLine);
			   }
		   }
		   
 		 }
	       
	      if(input==5)
	      { 
	    	  Main m=new Main();
	          readlines.clear();
	    	  FileInputStream fin3=new FileInputStream(Basic);	  
			  readlines.addAll(m.read(fin3));  //read Basic Accounts File and add into readline arraylist	
	          fin3.close();
	         
	    	  fin3=new FileInputStream(Saving);
   			  readlines.addAll(m.read(fin3));//read Saving Accounts File and add into readlines arrayList 	
			  fin3.close();
				
			  fin3=new FileInputStream(Current); 
   		      readlines.addAll(m.read(fin3)); //read Current Accounts File and add into readlines arraylist
			  fin3.close();
			  
			   ArrayList <String> tmp=new ArrayList<>();
			   Set custID=new HashSet();//Hash set to count customer ID from 3 files which create new account today
		       int withDrawCounter=0;
		       double closingBalance=0;
		       int NewCustCounter=0;
		   	   for (int i = 0; i < readlines.size(); i++) 
		   		  {
		   		     oneLine=readlines.get(i);
		   			 st=new StringTokenizer(oneLine);
		   		     while(st.hasMoreTokens())
		   		   	   tmp.add(st.nextToken());
		   		     DateFormat dateFormat1=new SimpleDateFormat("dd/MM/yyyy");
			         date=new Date();
			 	     String s1=dateFormat1.format(date);
		   		     if(tmp.get(8).equals(s1))//compare today date and customer withDraw date
		   		      withDrawCounter++;
		   		     if(tmp.get(3).equals(s1))// compare today date and customer account creation date;
		   		      custID.add(tmp.get(7));
		   		     		   		  
		   		     closingBalance+=Double.parseDouble(tmp.get(1));
		   		     tmp.clear();
		   		} 
		   	
		   	   
		   	   System.out.println("1.Number of Customer open Account Today");
		   	   System.out.println("2.Number of withDraws Today");
		   	   System.out.println("3.Closing Balance Today");
		   	   int n=sc.nextInt();
		   	   readlines.clear();
	  	       if(n==1)
		  	   {
	  	       fin3=new FileInputStream(customer); 
	   		   readlines=m.read(fin3);//read customer file in arraylist
	   		   fin3.close();
	   		   for (int i = 0; i < readlines.size(); i++) 
	   		   {
	   		   Iterator <String> iterator=custID.iterator();
	   		   oneLine=readlines.get(i);
	   		   st=new StringTokenizer(oneLine);
		   	   if(st.hasMoreTokens())
		   	   {
		   	    String cust=st.nextToken(); 
		   		while (iterator.hasNext())
				if (cust.equals(iterator.next()))//compare customer Id to count new customer
		   		NewCustCounter++;
		   		}
		      }
	   		    System.out.println("Number of Customer open Account Today:"+NewCustCounter);
		  	  }
		   	   if (n==2)
		   	   System.out.println("No of WithDraws:"+withDrawCounter);
		   	   if(n==3)
		   	   System.out.println("Today Closing Balance:"+closingBalance);
	           }
	      
	         
	        }
	        }
	        //end try block
	         catch (FileNotFoundException e)
	        {
	        	 System.out.println("File not found Exception");
	        }
	        catch (EOFException e)
            {
        	 System.out.println("End Of File Exception");
            }	        
	        catch(IOException e)
	        {
	        	System.out.println("IOException");
	        }
          }  
	   }


 

class InputOutputException extends IOException
{
   public InputOutputException (){}

   public InputOutputException(String msg)
  {
   super(msg);	
  }
}

