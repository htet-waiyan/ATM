package com.team1a.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeTest {
	public static void main(String[] args){
		Calendar c=Calendar.getInstance();
		
		SimpleDateFormat fmt=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		try {
			Date d=fmt.parse(fmt.format(c.getTime()));
			System.out.println(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
