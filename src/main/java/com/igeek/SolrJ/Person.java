package com.igeek.SolrJ;

import org.apache.solr.client.solrj.beans.Field;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Administrator
 * @create: 19:18 2017/11/10
 * @description: 测试类
 * @version: 0.0.1 version
 */
public class Person {
	@Field
	 String id;
	@Field
	 String name;
	@Field
	 String desc;

	public Person() {

	}

	public Person(String id, String name, String desc) {
		this.id = id;
		this.name = name;
		this.desc = desc;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDesc() {
		return desc;
	}
}
