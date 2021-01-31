package com.restuarant.rest.webservices.restuarant.table.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.restuarant.rest.webservices.restuarant.constants.RMSConstants;
import com.restuarant.rest.webservices.restuarant.constants.ReturnResultStatus;
import com.restuarant.rest.webservices.restuarant.modal.Table;
import com.restuarant.rest.webservices.restuarant.returnresult.ReturnResult;
import com.restuarant.rest.webservices.restuarant.services.TableService;

@Component
public class TableServiceImpl implements TableService {

	@Autowired
	TableJPARepository repository;
	
	@Override
	public ReturnResult addTable(Table table) {
		ReturnResult result = new ReturnResult(ReturnResultStatus.SUCCESS);
		try {
			repository.save(table);
			result = new ReturnResult(ReturnResultStatus.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ReturnResult(ReturnResultStatus.ERROR, "Internal Server Error");
		}
		return result;
	}

	@Override
	public List<Table> getAllRestuarants() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public ReturnResult deleteTable(int restId) {
		ReturnResult result = new ReturnResult(ReturnResultStatus.SUCCESS);
		try {
			Table table = getTableById(restId);
			if(table == null) {
				result = new ReturnResult(ReturnResultStatus.ERROR, "Table Not Found for Id "+restId);
				return result;
			}
			repository.delete(table);
			result = new ReturnResult(ReturnResultStatus.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ReturnResult(ReturnResultStatus.ERROR, "Internal Server Error");
		}
		return result;
	}

	@Override
	public ReturnResult updateTable(Table table) {
		ReturnResult result = new ReturnResult(ReturnResultStatus.SUCCESS);
		try {
			result = deleteTable(table.getRestId());
			if(!result.getStatus().equals(ReturnResultStatus.SUCCESS)) {
				return result;
			}
			
			if(table.getAvailableTable() == 0) {
				table.setAvailabilty(RMSConstants.NO.getValue());
			} else {
				table.setAvailabilty(RMSConstants.YES.getValue());
			}
			
			result = addTable(table);
			if(!result.getStatus().equals(ReturnResultStatus.SUCCESS)) {
				return result;
			}
			result = new ReturnResult(ReturnResultStatus.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ReturnResult(ReturnResultStatus.ERROR, "Internal Server Error");
		}
		return result;
	}

	@Override
	public Table getTableById(int restId) {
		// TODO Auto-generated method stub
		final Optional<Table> restuarantOptional = repository.findByRestId(restId);
		if(restuarantOptional != null && !restuarantOptional.isPresent()) {
			return restuarantOptional.get();
		}
		return null;
	}

}
