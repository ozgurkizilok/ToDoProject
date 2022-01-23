package com.todo.study.config;


import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.CouchbaseClientFactory;
import org.springframework.data.couchbase.SimpleCouchbaseClientFactory;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.couchbase.core.convert.CouchbaseCustomConversions;
import org.springframework.data.couchbase.repository.config.RepositoryOperationsMapping;

import com.todo.study.model.ToDo;

import lombok.SneakyThrows;

@Configuration
public class CouchbaseConfig  extends AbstractCouchbaseConfiguration{
	
	@Autowired
	private ApplicationContext context;
	
	@Override
	public String getConnectionString() {
		return "couchbase://127.0.0.1";
	}

	@Override
	public String getUserName() {
		return "ozgurkizilok";
	}

	@Override
	public String getPassword() {
		return "ozgur1995";
	}

	@Override
	public String getBucketName() {
		return "user";
	}
	
	@Override
	public void configureRepositoryOperationsMapping(RepositoryOperationsMapping mapping) {
		mapping.mapEntity(ToDo.class, getCouchbaseTemplate("todo"));
		//mapping.mapEntity(UserTodo.class, getCouchbaseTemplate("userTodo")); // yeni bucket istersek.
	}
	
	@SneakyThrows
	private CouchbaseTemplate getCouchbaseTemplate(String bucketname) {
		CouchbaseTemplate template = new CouchbaseTemplate(couchbaseClientFactory(bucketname),
				mappingCouchbaseConverter(couchbaseMappingContext(customConversions()),
					new CouchbaseCustomConversions(Collections.emptyList())));
		
		template.setApplicationContext(context);
		return template;
	}
	
	private CouchbaseClientFactory couchbaseClientFactory(String bucketname) {
		return new SimpleCouchbaseClientFactory(couchbaseCluster(couchbaseClusterEnvironment()), bucketname, this.getScopeName());
	}

}

