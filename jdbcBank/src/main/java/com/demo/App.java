package com.demo;

import java.util.Scanner;

import com.dao.userDao;
import com.model.user;

/**
 * Hello world! QWJjQDEyNA==
 */
public class App {
	public static void main(String[] args) {
		int ch = 0;
		do {
			System.out.println("______________________________________________");
			System.out.println("\t WellCome To FRIENDS BANK");
			System.out.println("______________________________________________");
			System.out.println("1. New Account");
			System.out.println("2. Existing Account");
			System.out.println("3. Forget Pin");
			System.out.println("4. Exit");
			System.out.println("______________________________________________\n");
			System.out.print("Enter your Option = ");
			ch = new Scanner(System.in).nextInt();

			switch (ch) { // New Account

			case 1: { // New Account
				int ch1 = 0;

				do {
					System.out.println("______________________________________________");
					System.out.println("\t WellCome To FRIENDS BANK");
					System.out.println("______________________________________________");
					System.out.println("1. Create New Account");
					System.out.println("2. Exit");
					System.out.println("______________________________________________\n");
					System.out.print("Enter your Option = ");
					ch1 = new Scanner(System.in).nextInt();

					switch (ch1) {
					case 1: {
						System.out.println("______________________________________________");
						System.out.println("\t FRIENDS BANK");
						System.out.println("______________________________________________");

						System.out.print("Enter A/C Holder name = ");
						user u = new user();
						u.setUacname(new Scanner(System.in).nextLine());

						System.out.print("Enter A/C Type = ");
						u.setUactype(new Scanner(System.in).nextLine());

						System.out.print("Enter A/C Opening Balance  = ");
						u.setUacamount(new Scanner(System.in).nextInt());

						String s1[] = new userDao().createAccount(u);
						if (s1 != null) {
							System.out.println(" Dera " + u.getUacname());
							System.out.println(" Your " + u.getUactype() + " account \n credeted " + " successfully with "
									+ u.getUacamount());

							System.out.println(" Account no : " + s1[0]);
							System.out.println(" Pin no : " + s1[1]);
						}
						break;
					}

					}

				} while (ch1 <= 1);

				break;
			}
			case 2: { // Existing Account

				System.out.print("Enter Account No : ");
				int Acn = new Scanner(System.in).nextInt();
				System.out.print("Enter Account PIN : ");
				String pin = new Scanner(System.in).nextLine();
				if (new userDao().checkLogin(Acn, pin)) {

					int ch2 = 0;
					do {
						System.out.println("______________________________________________");
						System.out.println("\t FRIENDS BANK");
						System.out.println("______________________________________________");
						System.out.println("1. Deposite Amount");
						System.out.println("2. Withdrawl Amount");
						System.out.println("3. Check Balance");
						System.out.println("4. Account Details");
						System.out.println("5. Change Pin");
						System.out.println("6. Exit");
						System.out.println("______________________________________________\n");

						System.out.print("Enter your Option = ");
						ch2 = new Scanner(System.in).nextInt();

						switch (ch2) {
						case 1: { // 1. Deposite Amount

							System.out.print("Enter Deposite Amount : ");
							double damt = new Scanner(System.in).nextDouble();
							double uamt = new userDao().depositeBalance(Acn, pin, damt);
							if (uamt != 0) {
								System.out.println(" Youre Amount " + damt + " is Successfully Deposite ");
								System.out.println(" Youre Current Balance is : " + uamt);
							}

							break;
						}
						case 2: { // 2. Withdrawl Amount

							System.out.print("Enter Withdrall Amount : ");
							double wamt = new Scanner(System.in).nextDouble();
							double uamt = new userDao().withdrawlBalance(Acn, pin, wamt);
							if (uamt >= 1000) {
								System.out.println(" Youre Amount " + wamt + " is Successfully Withdrawl ");
								System.out.println(" Youre Current Balance is : " + uamt);

							} else {
								System.out
										.println("Insufificiant funds!! \n\t Because minimum fixed balance is : 1000 ");
								System.out
										.println(" Youre Current Balance is : " + new userDao().checkBalance(Acn, pin));
							}

							break;
						}
						case 3: { // 3. Check Balance

							System.out.println(
									" Your current Account Balance is : " + new userDao().checkBalance(Acn, pin));
							break;
						}
						case 4: { // 4. Account Details
							user u = new userDao().acountDetails(Acn, pin);
							System.out.println("______________________________________________");
							System.out.println("\t FRIENDS BANK");
							System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
							System.out.println("Account Number     : " + u.getUacno());
							System.out.println("Account Name       : " + u.getUacname());
							System.out.println("Account Type       : " + u.getUactype());
							System.out.println("Account Balance    : " + u.getUacamount());
							System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

							break;
						}
						case 5: { // 5. Change Pin

							System.out.print("Enter New PIN : ");
							String npin = new Scanner(System.in).nextLine();
							System.out.print("Conform New PIN : ");
							String cnpin = new Scanner(System.in).nextLine();
							if (npin.equals(cnpin)) {
								if (new userDao().changePin(Acn, pin, cnpin)) {
									System.out.println("Your Pin Successfull Change");
									ch2 = 10;
								} else {
									System.out.println("Enter 4 digite Pin...!!!");
								}
							} else {
								System.out.println("Pin Missmatched...!!!");
							}
						}
						}

					} while (ch2 <= 5);
				} else {
					System.out.println("Invalid User...!!!");

				}

				break;

			}
			case 3: { // Forget pin
				int ch3 = 0;

				do {
					System.out.println("______________________________________________");
					System.out.println("\t FRIENDS BANK");
					System.out.println("______________________________________________");
					System.out.println("1. Create New Pin");
					System.out.println("2. Exit");
					System.out.println("______________________________________________\n");
					System.out.print("Enter your Option = ");
					ch3 = new Scanner(System.in).nextInt();

					switch (ch3) {
					case 1: { // create new Pin

						System.out.print("Enter Your A/C No   : ");
						int uacno = new Scanner(System.in).nextInt();
						System.out.print("Enter Your A/C Name : ");
						String uacname = new Scanner(System.in).nextLine();
						System.out.print("Enter your A/C Type : ");
						String uactype = new Scanner(System.in).nextLine();
						user u = new userDao().forgatePin(uacno, uacname, uactype);
						if (u != null) {
							System.out.println(" User Found Successfull ");
							System.out.print("Enter New PIN : ");
							String npin = new Scanner(System.in).nextLine();
							System.out.print("Conform New PIN : ");
							String cnpin = new Scanner(System.in).nextLine();
							if (npin.equals(cnpin)) {
								u.setUacpin(cnpin);
								if (new userDao().updatePin(u)) {
									System.out.println("Pin Generate SuccessFully");

								} else {
									System.out.println("New Pin Generated Faild");
								}

							} else {
								System.out.println("Pin missedmatch...!!!");
							}

						} else {
							System.out.println("User Not Fund...!!!");
						}

						break;
					}
					}

				} while (ch3 <= 1);

				break;
			}
			}

		} while (ch <= 3);

		{
			System.out.println("Thank You...!!!");
		}

		/*
		 * int ch=0; do {
		 * System.out.println("______________________________________________");
		 * System.out.println("\t WellCome To FRIENDS BANK");
		 * System.out.println("______________________________________________");
		 * System.out.println("1. New Account");
		 * System.out.println("2. Existing Account");
		 * System.out.println("3. Forget Pin"); System.out.println("4. Exit");
		 * System.out.println("______________________________________________\n");
		 * System.out.print("Enter your Option = "); ch =new
		 * Scanner(System.in).nextInt();
		 * 
		 * switch(ch) { case 1:{
		 * 
		 * break; } case 2:{
		 * 
		 * break; } case 3:{
		 * 
		 * break; } }
		 * 
		 * } while(ch<=3);
		 */

		// Account no : 11001 /11002 /11003 /11004 /11005/11007 /11008
		// Pin no :		 3899 / 5132 / 3986/ 8578/ 7470 /9569/	7575

		/*
		 * String s = "Abc@123"; String s1 = "";
		 * 
		 * for (int i = 0; i < s.length(); i++) { s1 += (char) (s.charAt(i) + 1); }
		 * 
		 * System.out.println(s1);
		 */

//		String s="Abc@123";
//		String s1 = Base64.getEncoder().encodeToString(s.getBytes());
//		System.out.println(s1);
//		String s2 = Base64.getDecoder().decode(s1.getBytes().toString());
//				

//		  user u = new user(); u.setUacname("Narendra Sing");
//		  u.setUactype("Saving");
//		  u.setUacamount(30000);
//		  
//		  
//		  String s1[] =new userDao().createAccount(u);
//		  if (s1!=null)
//		  {
//		  System.out.println("Account no :"+s1[0]);//Account no :11001 /11002
//		  System.out.println("Pin no : "+s1[1]);//Pin no : 3899 /5132
//		  }

//		
		// if(new userDao().checkLogin(11002, "5132")) ;
		// System.out.println("login Sucessfully");

//		System.out.println(new userDao().checkBalance(11002, "5132"));

		/*
		 * System.out.println(new userDao().depositeBalance(11002, "5132", 35000));
		 * 
		 */

//		
//		System.out.println(new userDao().withdrawlBalance(11002, "5132", 3000));
//
//		

	}

}
