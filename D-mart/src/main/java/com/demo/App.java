package com.demo;

import java.util.List;

import com.dao.Menu;
import com.dao.billDao;
import com.dao.productDao;
import com.dao.staffDao;
import com.model.bill;
import com.model.product;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    		new Menu().getMenu();
    	
    	
//    	product p= new product();
//    	p.setPname("Acume");
//    	p.setPdiscription("Medical sope");
//    	p.setPtype("SOPE");
//    	p.setPprice(50);
//    	if(new productDao().insertProduct(p)>0)
//    	{
//    		System.out.println("insert SucessFully");
//    	}
//    	
//       if(new productDao().deleteProductbyId(2)>0)
//    	   System.out.println("Delete Sucessfully");
    	//System.out.println(new staffDao().displayStaffById(9));
//   	System.out.println(new productDao().displayProductById(1));
//    	List<product> p =new productDao().displayAllProduct();
//    	for(product p1:p)
//    	{
//    		System.out.println(p1);
//    	}
    	
	//	new productDao().displayAllProduct().forEach(p1->System.out.println(p1));	
		
//    	new productDao().displayAllProductByAny("49").forEach(p1-> System.out.println(p1));
    	
  // 	 product p =new productDao().displayProductById(3);
//    	p.setPname("Sugger");
//    	 p.setPdiscription("small cut");
//    	 p.setPprice(32);
//    	 p.setPtype("2kg");
//    	if (new productDao().updateProduct(p)>0)
//    	{
//    		System.out.println("Update product"+p);
//    		
//    	}
    	
    	
    	
    	
    	
    	
    }
}
