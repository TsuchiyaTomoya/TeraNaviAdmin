/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttc.util;

import ttc.exception.BusinessLogicException;

/**
 *
 * @author Masaki
 */
public abstract class Mailler {
	private String address;
	private String account;
	private String pass;
	
	public void setAddress(String address){
		this.address = address;
	}
	
	public String getAddress(){
		return address;
	}
	
	public void setAccount(String account){
		this.account = account;
	}
	
	public String getAccount(){
		return account;
	}
	
	public void setPass(String pass){
		this.pass = pass;
	}
	
	public String getPass(){
		return pass;
	}
	
	public abstract String sendMail(String toAddress,String title,String mess)throws BusinessLogicException;
}
