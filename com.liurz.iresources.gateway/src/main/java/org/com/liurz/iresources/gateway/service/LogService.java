package org.com.liurz.iresources.gateway.service;

import org.com.liurz.iresources.gateway.util.MongoDBUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

@Service
public class LogService {
	@Autowired
	private MongoDBUtil mongodbUtil;

	public void saveLogs(BasicDBObject logInformations) {
		DBCollection logTable = mongodbUtil.getTable("t_log");
		logTable.save(logInformations);
	}

}
