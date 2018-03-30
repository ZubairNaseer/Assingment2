package assignment1;

import java.util.Date;

abstract class Account {
	public String AccountId;
	public double CurrentBalance;
	public Date CreationDate;
	public String Currency;
	public double InterestRate;
	public Date WithDrawDate;
	public String status;
	
	public Account(){};
	public Account(String a,double b,Date d,String c,double i,String s)
	{   
		this.AccountId=a;
		this.CurrentBalance=b;
		this.CreationDate=d;
		this.Currency=c;
		this.InterestRate=i;
		this.status=s;
		this.WithDrawDate=null;
	}
	
	abstract public double Deposit(double currentBalance,double amount);
	abstract public double WithDraw(double CurrentBalnace,double Amount );
	abstract public void ViewAccountDetail(double balance);
	abstract public  void setAccountId(String a);
	abstract public String getAccountId();
	abstract public void setCurrentBalance( double b);
	abstract public double getCurrentBalance();
	abstract public void setCreationDate(Date d);
	abstract public Date getCreationDate();
	abstract public void setCurrency(String a);
	abstract public String getCurrency();
	abstract public void setInterestRate( double R);
	abstract public double getInterestRate();
	abstract public void setStatus(String s);
	abstract public String getStatus(); 

}
	
	class InsufficientBalanceException extends RuntimeException
	{
	   public InsufficientBalanceException  (){}
	
	   public InsufficientBalanceException(String msg)
	  {
	   super(msg);	
	  }
	}



