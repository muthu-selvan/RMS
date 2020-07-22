package com.restuarant.rest.webservices.restuarant.parser;




import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.json.CDL;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;


public class CSVParser {
	
	private static final String FILE_LOC = "";
	
	public static <T>List<T> readCSVFile(Class<?> classType) throws JSONException, JsonSyntaxException, IOException {
		return readCSVFile(classType, null);
	}
	
	@SuppressWarnings("unchecked")
	public static <T>List<T> readCSVFile(Class<?> classType,String filePath) throws JSONException, JsonSyntaxException, IOException {
		List<T> rows=new ArrayList<T>();
		BufferedReader fileReader = null;
		try{
		int i;
		String line = "";
		fileReader = null;
		boolean initial = true;
		ArrayList<String> header = new ArrayList<String>();
			fileReader = new BufferedReader(new FileReader(filePath));
			while ((line = fileReader.readLine()) != null) {
				JSONObject record = new JSONObject();
				i = 0;
				String[] tokens = line.split(",");
				if (initial) {
					for (String token : tokens) {
						if (token.startsWith("\"")) {
							token = token.substring(1);
						}
						if (token.endsWith("\"")) {
							token = token.substring(0, token.length() - 1);
						}
						String head=getFiedName(token,classType);
						header.add(head);
					}
					initial = false;
					continue;
				} // Get all tokens available in line
				for (String token : tokens) {
					if(token.contains("\""))
						token = token.replaceAll("\"", "");
					record.put(header.get(i).toString(), token);
					i++;
					if (i == header.size())
						break;
				}
			//	System.out.println(record);
				Gson gson=new Gson();
				rows.add((T) gson.fromJson(record.toString(),classType));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			fileReader.close();
		}
		return rows;
	}
	
	public static boolean writeCSVFile(List<?> rows,Class<?> classType) {
		String json = new Gson().toJson(rows);
		String fileToParse=FILE_LOC+classType.getSimpleName()+".csv";
		File file=new File(fileToParse);
		FileOutputStream fileWrite=null;
		try {
			org.json.JSONArray array = new org.json.JSONArray(json);
			String csv[] = CDL.toString(array).split("\n");
			String header=csv[0];
			String tempheader="";
			for (String head : header.split(",")) {
				if (tempheader.isEmpty())
					tempheader = getAnnotaionLabel(head, classType);
				else
					tempheader += "," + getAnnotaionLabel(head, classType);

			}
			csv[0]=tempheader;
			String data="";
			for(String row:csv){
				if(data.isEmpty())
					data=row;
				else
					data+="\n"+row;
			}
		if(!file.exists())
			file.createNewFile();
		fileWrite=new FileOutputStream(file);
		fileWrite.write(data.getBytes());
		
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		finally{
			try {
				if(fileWrite!=null)
					fileWrite.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
public static String getFiedName(String label,Class<?> classVal){
		
		for (Field field : classVal.getDeclaredFields()) {
		    if (field.isAnnotationPresent(CSVCell.class)) {
		    	CSVCell cell= field.getAnnotation(CSVCell.class);
		          if (label.equals(cell.label())) {
		        	  return field.getName();
		          }
		    }
		}
		return null;
	}
	public static String getAnnotaionLabel(String fieldName,Class<?> classVal){
		for (Field field : classVal.getDeclaredFields()) {
		    if (field.isAnnotationPresent(CSVCell.class)) {
		    	if(fieldName.equals(field.getName())){
		    		CSVCell a = field.getAnnotation(CSVCell.class);
		    		return a.label();
		    	}
		    }
		}
		return null;
	}

}
