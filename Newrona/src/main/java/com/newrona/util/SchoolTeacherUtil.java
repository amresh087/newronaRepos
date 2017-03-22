package com.newrona.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class SchoolTeacherUtil
{
	public List<Long> convertStringArrayToList(String[] stringArr){
		List<Long> studentListForEmail=new ArrayList<Long>();
		
		if(stringArr!=null){
		int L1 = stringArr.length;

		    for(int i=0;i<L1;i++)
		{ 	
		    	studentListForEmail.add(Long.parseLong(stringArr[i]));
		}
		  
	
	}
		return studentListForEmail; }
	
}
