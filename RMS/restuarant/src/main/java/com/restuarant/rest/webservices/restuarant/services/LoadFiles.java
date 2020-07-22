package com.restuarant.rest.webservices.restuarant.services;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import com.restuarant.rest.webservices.restuarant.constants.RMSConstants;
import com.restuarant.rest.webservices.restuarant.modal.Restuarant;
import com.restuarant.rest.webservices.restuarant.modal.Table;
import com.restuarant.rest.webservices.restuarant.parser.CSVParser;

@Repository
@ComponentScan(basePackages = {"com.restuarant.rest.webservices.restuarant.*"})
@PropertySource("classpath:application.properties")
public class LoadFiles {
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	RestuarantService restuarantService;
	
	@Autowired
	TableService tableService;
	
	@Autowired
	private Environment env;

	public LoadFiles() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	private void init() {
		boolean isRestore = false;
		
		try {
			isRestore = Boolean.parseBoolean(env.getProperty(RMSConstants.RESTORE.getValue()));
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>Restore value : "+isRestore);
			
			if(!isRestore) {
				return;
			}
			
//			RESTUARANT
			{
				Resource resource = resourceLoader.getResource("classpath:"+"assets/csv/Restuarant.csv");
				List<Restuarant> restLst = CSVParser.readCSVFile(Restuarant.class, resource.getFile().getAbsolutePath());
				
				restLst.forEach(restaurent -> {
					restuarantService.addRestaurant(restaurent);
				});
			}
			
//			TABLE
			{
				Resource resource = resourceLoader.getResource("classpath:"+"assets/csv/Table.csv");
				List<Table> restLst = CSVParser.readCSVFile(Table.class, resource.getFile().getAbsolutePath());
				
				restLst.forEach(table -> {
					
					if(table.getAvailableTable() == 0) {
						table.setAvailabilty(RMSConstants.NO.getValue());
					} else {
						table.setAvailabilty(RMSConstants.YES.getValue());
					}
					
					tableService.addTable(table);
				});
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
