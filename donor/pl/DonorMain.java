package com.cg.donor.pl;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.cg.donor.bean.DonorBean;
import com.cg.donor.exception.DonorException;
import com.cg.donor.service.DonorServiceImpl;
import com.cg.donor.service.IDonorService;

public class DonorMain {
	
	static Scanner sc=new Scanner(System.in);
	static IDonorService donorService=null;
	static DonorServiceImpl donorServiceimpl=null;
	

	public static void main(String[] args) {
		
		DonorBean donorBean=null;
		
		String donorId=null;
		int option=0;
		while(true)
		{
			System.out.println();
			System.out.println();
			System.out.println(" ICARE CAPGEMINI TRUST");
			System.out.println("........................................\n");
			
			System.out.println("1.Add Donator");
			System.out.println("2.View Donator");
			System.out.println("3.RetrieveAll");
			System.out.println("4.Exit");
			System.out.println("...........................................");
			System.out.println("Select an option");
			
			
			try
			{
			  option=sc.nextInt();
			  switch (option) {
			case 1:
				while(donorBean==null)
				{
					donorBean=populateDonorBean();
					
				}
				try
				{
					donorService=new DonorServiceImpl();
					donorId=donorService.addDonor(donorBean);
					System.out.println("Donator details has been successfully registered");
					System.out.println("Donator Id is:"+donorId);
				}
				catch(DonorException donorException)
				{
					System.err.println("ERROR :"+donorException.getMessage());
				}
				finally
				{
					donorId=null;
					donorService=null;
					donorBean=null;
				}
				
				break;
            case 2:
				
				break;
            case 3:
	
	           break;
            case 4:
	
	           break;


			default:
				break;
			}
			}
			catch(Exception e)
			{
				
			}
		}
		
		
	}


	private static DonorBean populateDonorBean() {
		DonorBean donorBean=new DonorBean();
		
		System.out.println("\n Enter Details");
		
		System.out.println("Enter donor name:");
		donorBean.setDonorName(sc.next());
		
		System.out.println("Enter donor contact");
		donorBean.setPhoneNumber(sc.next());
		
		System.out.println("Enter donor address");
		donorBean.setAddress(sc.next());
		
		System.out.println("enter donation amount:");
		try
		{
			donorBean.setDonationAmount(sc.nextFloat());
		}
		catch(InputMismatchException ime)
		{
			sc.nextLine();
			System.err.println("please enter a numeric value for donation amount, try again");
		}
		donorServiceimpl=new DonorServiceImpl();
		try
		{
			donorServiceimpl.validateDonor(donorBean);
			return donorBean;
		}
		catch(DonorException donorException)
		{
			System.err.println("Invalid data:");
			System.err.println(donorException.getMessage()+"\n try again..");
			System.exit(0);
		}
		
		return null;
	}
	
}
