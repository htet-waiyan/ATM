package com.team1a.util;

import java.sql.SQLException;

public class DuplicateKeyException extends SQLException {
	
	public DuplicateKeyException(){
		super();
	}
	
	public DuplicateKeyException(String msg){
		super(msg);
	}
}
