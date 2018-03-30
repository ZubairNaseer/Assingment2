package assignment1;

import java.util.ArrayList;
import java.util.Date;

public class Customer {
	  private String CustomerId;
	  private String FName;
	  private  String LName;
	  private String Address;
	  private int Age;
	  private String Sex;
	  private Date DOB;
	  private String Occupation;
	  private String pswd;
	  private ArrayList<String> AccountType=new ArrayList<String>(); 
	  
	  public void setCustomerId(String c)
	  {
		  this.CustomerId=c;
	  }
	  public String getCustomerId()
	  {
		  return CustomerId;
	  }
	  
	  public void setFName(String f)
	  {
		  this.FName=f;
	  }
	  public String getFName()
	  {
		  return FName;
	  }
	  
	  
	  public void setLName(String L)
	  {
		  this.LName=L;
	  }
	  public String getLName()
	  {
		  return LName;
	  }
	  
	  public void setAddress(String A)
	  {
		  this.Address=A;
	  }
	  public String getAddress()
	  {
		  return Address;
	  }
	  
	  public void setSex(String S)
	  {
		  this.Sex=S;
	  }
	  public String getSex()
	  {
		  return Sex;
	  }
	  
	  
	  public void setAge(int a)
	  {
		  this.Age=a;
	  }
	  public int  getAge()
	  {
		  return Age;
	  }
	  
	
	  public void setDOB(Date D)
	  {
		  this.DOB=D;
	  }
	  public Date getDOB()
	  {
		  return DOB;
	  }
	  
	  public void setPswd(String p)
	  {
		  this.pswd=p;
	  }
	  public String getPswd()
	  {
		  return pswd;
	  }

	  public void setOccupation(String f)
	  {
		  this.Occupation=f;
	  }
	  public String getOccupation()
	  {
		  return Occupation;
	  }
	  
	  public void setAccountType(String f)
	  {
		  this.AccountType.add(f);
	  }
	  public ArrayList<String> getAccountType()
	  {
		  return AccountType;
	  }
	  
	  
 }
