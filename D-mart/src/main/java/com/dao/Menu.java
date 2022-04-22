package com.dao;

import java.util.ArrayList;
import java.util.Scanner;

import com.model.bill;
import com.model.product;
import com.model.staff;

public class Menu {

	public void getMenu() {
		ArrayList<product> Bill = new ArrayList();
		int ch = 0;
		do {
			System.out.println("====================================");
			System.out.println("\t Wellcome To D-Mart");
			System.out.println("====================================");
			System.out.println("1. Generate Bill");
			System.out.println("2. Admin Login");
			System.out.println("3. Check Product");
			System.out.println("4. Exit");
			System.out.println("*************************************");

			System.out.print("Enter your Choice = ");
			ch = new Scanner(System.in).nextInt();
			System.out.println("*************************************\n\n\n\n");
			switch (ch) {

			case 1: { // generate bill
				System.out.println("====================================");
				System.out.println("\t LOG-IN STAFF");
				System.out.println("====================================\n");
				System.out.print("Ente your Email = ");
				String email = new Scanner(System.in).nextLine();
				System.out.print("Ente your Password = ");
				String password = new Scanner(System.in).nextLine();
				staff stf = new staffDao().staffLogin(email, password);
				if (stf != null) {

					int ch1 = 0;
					do {
						System.out.println("====================================");
						System.out.println("\t GENERATE BILL");
						System.out.println("====================================");
						System.out.println("1. Add Product In Bill");
						System.out.println("2. Delete Product In Bill");
						System.out.println("3. View Bill");
						System.out.println("4. Calculate Bill");
						System.out.println("5. EXIT");
						System.out.println("*************************************");

						System.out.print("Enter your Choice = ");
						ch1 = new Scanner(System.in).nextInt();
						System.out.println("*************************************\n\n\n\n");

						switch (ch1) {
						case 1: { // add product in bill
							System.out.println("====================================");
							System.out.println("\t Add Product In Bill");
							System.out.println("====================================");
							System.out.print("search product = ");
							String s = new Scanner(System.in).nextLine();
							new productDao().displayAllProductByAny(s).forEach(p1 -> System.out.println(p1));
							System.out.print("Select product by id = ");
							int n = new Scanner(System.in).nextInt();

							Bill.add(new productDao().displayProductById(n));

							System.out.println(Bill.size() + " Product Added");

							break;
						}

						case 2: { // delete product in bill
							if (Bill.size() != 0) {
								System.out.println("====================================");
								System.out.println("\t DELETE PRODUCT FROM BILL");
								System.out.println("====================================");
								System.out
										.println(new billDao().displayBill(Bill, stf.getSid() + "," + stf.getSname()));
								System.out.print("delete product by id = ");
								int n = new Scanner(System.in).nextInt();
								product temp = null;
								for (product p : Bill) {
									if (n == p.getPid())
										temp = p;
								}
								Bill.remove(temp);
								System.out.println("product Deleted sucssesfully ..!!! \n");
							} else {
								System.out.println("Bill Is Empty...!!!");
							}
							break;
						}
						case 3: { // view bill
							if (Bill.size() != 0) {
								System.out.println("====================================");
								System.out.println("\t VIEW BILL");
								System.out.println("====================================");
								System.out.println(new billDao().displayBill(Bill, stf.getSid() + "," + stf.getSname()));
							} else {
								System.out.println("Bill Is Empty...!!!");
							}
							break;
						}
						case 4: { // calculate bill
							if (Bill.size() != 0) {
								System.out.println("====================================");
								System.out.println("\t CALCULATE BILL");
								System.out.println("====================================");
								double amount = new billDao().displayBill(Bill, stf.getSid()+" " + stf.getSname());
								System.out.print("Print Bill Y/y : = ");
								String s = new Scanner(System.in).next();
								if (s.equalsIgnoreCase("y")) {
									bill b = new bill();
									s = "";
									for (product p : Bill)
										s += p.getPname() + ";";

									b.setBdiscription(s);
									b.setBamount(amount);
									b.setBgby(stf.getSid() + " " + stf.getSname());
									if (new billDao().insertBill(b) > 0)
										System.out.println("Bill Generated Sucsessfully");
									Bill.clear();

								}

							} else {
								System.out.println("Bill Is Empty...!!!");
							}
							break;
						}
						}

					} while (ch1 <= 4);
				} else {
					System.out.println("...Wrong Passwrd...");
				}
				break;

			}

			case 2: { // admin login
				int ch2 = 0;
				do {
					System.out.println("====================================");
					System.out.println("\t ADDMI LOGIN");
					System.out.println("====================================");
					System.out.println("1. STAFF");
					System.out.println("2. PRODUCT");
					System.out.println("3. BILL");
					System.out.println("4. EXIT");
					System.out.println("*************************************");

					System.out.print("Enter your Choice = ");
					ch2 = new Scanner(System.in).nextInt();
					System.out.println("*************************************\n\n\n\n");

					switch (ch2) {
					case 1: { // staff

						int ch21 = 0;
						do {
							System.out.println("====================================");
							System.out.println("\t STAFF");
							System.out.println("====================================");
							System.out.println("1. Add Staff");
							System.out.println("2. Delete Staff");
							System.out.println("3. Update Staff");
							System.out.println("4. View Staff");
							System.out.println("5. EXIT");
							System.out.println("*************************************");

							System.out.print("Enter your Choice = ");
							ch21 = new Scanner(System.in).nextInt();
							System.out.println("*************************************\n\n\n\n");

							switch (ch21) {
							case 1: { // add staff

								System.out.println("====================================");
								System.out.println("\t ADD STAFF");
								System.out.println("====================================");
								staff s = new staff();
								System.out.print("Enter Staff Name = ");
								s.setSname(new Scanner(System.in).nextLine());
								System.out.print("Enter Staff Email = ");
								s.setSemail(new Scanner(System.in).nextLine());
								System.out.print("Enter Staff Password = ");
								s.setSpassword(new Scanner(System.in).nextLine());
								if (new staffDao().insertStaff(s) > 0)
									System.out.println(" Staff Add Sucessfully...!!!");

								break;

							}
							case 2: { // delete staff
								System.out.println("====================================");
								System.out.println("\t DELETE STAFF");
								System.out.println("====================================");
								new staffDao().displayAllStaff().forEach(p1 -> System.out.println(p1));
								System.out.println("Delete staff by ID = ");
								int n = new Scanner(System.in).nextInt();
								if (new staffDao().deleteStaffbyId(n) > 0)
									System.out.println(" Staff Delete Sucessfully ");

								break;
							}
							case 3: { // update staff
								int ch211 = 0;
								do {

									System.out.println("====================================");
									System.out.println("\t UPDATE STAFF");
									System.out.println("====================================");
//									new staffDao().displayAllStaff().forEach(p1 -> System.out.println(p1));
//									System.out.println("\n\n====================================\n\n");
//									System.out.print("Select Staff by ID = ");
//									int n = new Scanner(System.in).nextInt();
//									System.out.println(new staffDao().displayStaffById(n) + "\n");
//									System.out.println("====================================");
									System.out.println("1. Update Sname");
									System.out.println("2. Update Semail");
									System.out.println("3. Update spassword");
									System.out.println("4. EXID");
									System.out.println("*************************************");
									System.out.print("Enter your Choice = ");
									ch211 = new Scanner(System.in).nextInt();
									System.out.println("*************************************");
									switch (ch211) {
									case 1: { // update sname
										System.out.println("====================================");
										System.out.println("\t UPDATE SNAME ");
										System.out.println("====================================");
										new staffDao().displayAllStaff().forEach(p1 -> System.out.println(p1));
										System.out.println("\n====================================\n");
										System.out.print("Change Staff Name By Id =  ");
										int n = new Scanner(System.in).nextInt();

										staff s = new staffDao().displayStaffById(n);
										System.out.println(s);
										System.out.print("Enter New Staff Name =  ");
										String str = new Scanner(System.in).nextLine();

										s.setSname(str);
										if (new staffDao().updateStaff(s) > 0) {
											System.out.println("Staff  Name Updated Sucessfily \n" + s);

										}

										break;
									}
									case 2: { // update semail
										System.out.println("====================================");
										System.out.println("\t UPDATE SEMAIL ");
										System.out.println("====================================");
										new staffDao().displayAllStaff().forEach(p1 -> System.out.println(p1));
										System.out.println("\n====================================\n");
										System.out.print("Change Staff Email By Id =  ");
										int n = new Scanner(System.in).nextInt();

										staff s = new staffDao().displayStaffById(n);
										System.out.println(s);
										System.out.print("Enter New Staff EMAIL =  ");
										String str = new Scanner(System.in).nextLine();

										s.setSemail(str);
										if (new staffDao().updateStaff(s) > 0) {
											System.out.println("Staff  Email Updated Sucessfily \n" + s);

										}

										break;
									}
									case 3: { // update password
										System.out.println("====================================");
										System.out.println("\t UPDATE SPASSWORD ");
										System.out.println("====================================");
										new staffDao().displayAllStaff().forEach(p1 -> System.out.println(p1));
										System.out.println("\n====================================\n");
										System.out.print("Change Staff Password By Id =  ");
										int n = new Scanner(System.in).nextInt();

										staff s = new staffDao().displayStaffById(n);
										System.out.println(s);
										System.out.print("Enter New Staff EMAIL =  ");
										String str = new Scanner(System.in).nextLine();

										s.setSpassword(str);
										if (new staffDao().updateStaff(s) > 0) {
											System.out.println("Staff  Password Updated Sucessfily \n" + s);

										}

										break;

									}

									}

								} while (ch211 <= 3);

								break;
							}
							case 4: { // view staff
								System.out.println("====================================");
								System.out.println("\t VIEW STAFF");
								System.out.println("====================================");

								new staffDao().displayAllStaff().forEach(s1 -> System.out.println(s1));
								continue;
								// break;
							}
							}

						} while (ch21 <= 4);
						break;
					}
					case 2: { // product
						int ch22 = 0;
						do {
							System.out.println("====================================");
							System.out.println("\t PRODUCT");
							System.out.println("====================================");
							System.out.println("1. Add Product");
							System.out.println("2. Delete Product");
							System.out.println("3. Update Product");
							System.out.println("4. View Product");
							System.out.println("5. EXIT");
							System.out.println("*************************************");

							System.out.print("Enter your Choice = ");
							ch22 = new Scanner(System.in).nextInt();
							System.out.println("*************************************\n\n\n\n");

							switch (ch22) {
							case 1: { // add product

								System.out.println("====================================");
								System.out.println("\t ADD_PRODUCT");
								System.out.println("====================================");
								product p = new product();

								System.out.print("Enter Pname = ");
								p.setPname(new Scanner(System.in).nextLine());
								System.out.print("Enter Pdiscription = ");
								p.setPdiscription(new Scanner(System.in).nextLine());
								System.out.print("Enter Ptype = ");
								p.setPtype(new Scanner(System.in).nextLine());
								System.out.print("Enter Pprice = ");
								p.setPprice(new Scanner(System.in).nextDouble());
								if (new productDao().insertProduct(p) > 0) {
									System.out.println("\n \tProduct Added Sucessfully...!!! \n");
								}
								break;

							}
							case 2: { // delete product

								new productDao().displayAllProduct().forEach(p1 -> System.out.println(p1));
								System.out.println("Delete product by ID = ");
								int n = new Scanner(System.in).nextInt();
								if (new productDao().deleteProductbyId(n) > 0)
									System.out.println(" Delete Sucessfully ");

								break;
							}
							case 3: { // update product
								int ch221 = 0;
								do {
									System.out.println("====================================");
									System.out.println("\t UPDATE PRODUCT");
									System.out.println("====================================\n\n");
									new productDao().displayAllProduct().forEach(p1 -> System.out.println(p1));
									System.out.println("\n====================================\n");
//									System.out.print("Select Product by ID = ");
//									int n = new Scanner(System.in).nextInt();
//									System.out.println(new productDao().displayProductById(n) + "\n");
//									System.out.println("====================================");
									System.out.println("1. Update Pname");
									System.out.println("2. Update Pdiscription");
									System.out.println("3. Update Ptype");
									System.out.println("4. Update Pprice");
									System.out.println("5. EXIT");
									System.out.println("*************************************");
									System.out.print("Enter your Choice = ");
									ch221 = new Scanner(System.in).nextInt();
									System.out.println("*************************************");
									switch (ch221) {
									case 1: { // update pname

										System.out.println("====================================");
										System.out.println("\t UPDATE PNAME ");
										System.out.println("====================================");

										System.out.print("Change Product Name By Id =  ");
										int n = new Scanner(System.in).nextInt();

										product p = new productDao().displayProductById(n);
										System.out.println(p);
										System.out.print("Enter New Product Name =  ");
										String s = new Scanner(System.in).nextLine();

										p.setPname(s);
										if (new productDao().updateProduct(p) > 0) {
											System.out.println("Product updated Sucessfily \n" + p);

										}

										break;
									}
									case 2: { // update pdiscription
										System.out.println("====================================");
										System.out.println("\t UPDATE PDISCRIPTION ");
										System.out.println("====================================");

										System.out.print("Change Product Discription By Id =  ");
										int n = new Scanner(System.in).nextInt();

										product p = new productDao().displayProductById(n);
										System.out.println(p);
										System.out.print("Enter New Product Discription =  ");
										String s = new Scanner(System.in).nextLine();

										p.setPdiscription(s);
										if (new productDao().updateProduct(p) > 0) {
											System.out.println("Product discription updated Sucessfily \n" + p);

										}
										break;
									}
									case 3: { // Update Ptype
										System.out.println("====================================");
										System.out.println("\t UPDATE PTYPE ");
										System.out.println("====================================");

										System.out.print("Change Product type By Id =  ");
										int n = new Scanner(System.in).nextInt();

										product p = new productDao().displayProductById(n);
										System.out.println(p);
										System.out.print("Enter New Product type =  ");
										String s = new Scanner(System.in).nextLine();

										p.setPtype(s);
										if (new productDao().updateProduct(p) > 0) {
											System.out.println("Product type updated Sucessfily \n" + p);

										}
										break;
									}
									case 4: { // updete pprice
										System.out.println("====================================");
										System.out.println("\t UPDATE PPRICE ");
										System.out.println("====================================");

										System.out.print("Change Product price By Id =  ");
										int n = new Scanner(System.in).nextInt();

										product p = new productDao().displayProductById(n);
										System.out.println(p);
										System.out.print("Enter New Product price =  ");
										n = new Scanner(System.in).nextInt();

										p.setPprice(n);
										if (new productDao().updateProduct(p) > 0) {
											System.out.println("Product updated Sucessfily \n" + p);

										}
										break;
									}

									}
								} while (ch221 <= 4);

								break;
							}
							case 4: { // view Product
								System.out.println("====================================");
								System.out.println("\t VIEW PRODUCT");
								System.out.println("====================================");
								new productDao().displayAllProduct().forEach(p1 -> System.out.println(p1));
								System.out.println("*************************************");

								break;
							}
							}

						} while (ch22 <= 4);

						break;
					}
					case 3: { // bill
						int ch23 = 0;
						do {
							System.out.println("====================================");
							System.out.println("\t BILL ");
							System.out.println("====================================");
							System.out.println("1. View Bill History");
							System.out.println("2. EXIT");
							System.out.println("*************************************");

							System.out.print("Enter your Choice = ");
							ch23 = new Scanner(System.in).nextInt();
							System.out.println("*************************************\n\n\n\n");

							switch (ch23) {
							case 1: { // VIEW BILL
								System.out.println("====================================");
								System.out.println("\t BILL History");
								System.out.println("====================================");
								new billDao().displayAllbill().forEach(p1 -> System.out.println(p1));
								System.out.println("*************************************");
								break;
							}

							}

						} while (ch23 <= 1);

						break;
					}

					}

				} while (ch2 <= 3);
				break;

			}
			case 3: { // check product

				int ch3 = 0;
				do {
					System.out.println("====================================");
					System.out.println("\t CHECK PRODUCT");
					System.out.println("====================================");
					System.out.println("1. Search product ");
					System.out.println("2. EXIT");
					System.out.println("*************************************");

					System.out.print("Enter your Choice = ");
					ch3 = new Scanner(System.in).nextInt();
					System.out.println("*************************************\n\n\n\n");

					switch (ch3) {
					case 1: { // search by any
						System.out.println("====================================");
						System.out.println("\t SEARCH PRODUCT");
						System.out.println("====================================");

						System.out.print(" Enter your product = ");
						String s = new Scanner(System.in).nextLine();
						new productDao().displayAllProductByAny(s).forEach(p1 -> System.out.println(p1));

						System.out.println(
								"\n Thanks \n\t for \n\t\t search hear \n you want to bay this product, plese add product in your bill ");

						break;
					}

					}

				} while (ch3 <= 1);
				break;
			}

			}

		} while (ch <= 3);
		System.out.print("THANK YOU...!!!");

	}

}
