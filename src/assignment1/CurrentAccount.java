package assignment1;

import java.util.Date;

public class CurrentAccount extends Account {
	
	public CurrentAccount(){};
	public CurrentAccount(String a,double b,Date d,String c,double i,String s)
	{
	super(a,b,d,c,i,s);	
	}
	
	public double Deposit(double currentBalance,double amount)
	{
		currentBalance+=amount;
		return currentBalance;
	}
	
	
	public double WithDraw(double currentBalnace,double Amount )
	{ 
		if (Amount>currentBalnace)
		throw new InsufficientBalanceException ("Insufficient Amount");
	  else
	  {
		 currentBalnace-=Amount;
		 return currentBalnace;
	  }
	
	}
	
	
	public void ViewAccountDetail(double balance)
	{
		
		System.out.println("Acount is availble and Current Balance is " +balance);
	}
	
	
	public void setAccountId(String a)
	{
		this.AccountId=a;
	}

	public String getAccountId()
	{
		return AccountId;
	}

	public void setCurrentBalance( double b)
	{
		this.CurrentBalance=b;
	}

	public double getCurrentBalance()
	{
		return CurrentBalance;
	}

	public void setCreationDate(Date d)
	{
		this.CreationDate=d;
	}

	public Date getCreationDate()
	{
		return CreationDate;
	}

	public void setCurrency(String a)
	{
		this.Currency=a;
	}

	public String getCurrency()
	{
		return Currency;
	}

	public void setInterestRate( double R)
	{
		this.InterestRate=R;
	}

	public double getInterestRate()
	{  
		return InterestRate;
	}
	public void setStatus(String s)
	{
		this.status=s;
	}
	public String getStatus()
	{
		return status;
	}
}
