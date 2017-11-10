package com.igeek.SolrJ;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Administrator
 * @create: 18:38 2017/11/10
 * @description: SolrJ 测试
 * @version: 0.0.1 version
 */
public class SolrJTest {

	@Test
	public void testInsertIndexByDocument() throws Exception {
		//创建服务器对象    使用SolrJ进行索引的时候，应该使用http://localhost:8983/solr/core2，即无中间的#号。
		HttpSolrServer server = new HttpSolrServer("http://localhost:8983/solr/core2");
		//创建文档对象
		SolrInputDocument document = new SolrInputDocument();
		//添加索引数据
		document.addField("id", 1);
		document.addField("title", "这是第一条测试数据");
		//添加文档
		server.add(document);
		//提交请求，如果ID对应的数据存在则进行更新操作，如果不存在则创建索引
		server.commit();
	}

	/**
	 * 使用注解和JavaBean添加或修改数据
	 * 创建JavaBean，并且用注解标明要添加到索引库的字段，直接通过SolrServer添加JavaBean。
	 * 简单的方法就是在对象类中相应字段添加注释 @Field
	 */
	@Test
	public void testInsertIndexByBean() throws Exception {
		//创建服务器对象
		HttpSolrServer server = new HttpSolrServer("http://localhost:8983/solr/core3");
		//添加数据
		server.addBean(new Person("2", "李四", "李四是个好学生"));
		//提交请求，如果ID对应的数据存在则进行更新操作，如果不存在则创建索引
		server.commit();
	}

	/**
	 * 使用SolrJ删除索引库数据
	 * 删除索引可以根据ID删除，也可以写一个查询条件，匹配到条件的都会被删除
	 *
	 * @throws Exception
	 */
	@Test
	public void testDeleteIndex() throws Exception {
		//创建服务器对象
		HttpSolrServer server = new HttpSolrServer("http://localhost:8983/solr/core3");
		//根据条件进行删除
		server.deleteByQuery("title:change.me");
		//提交请求
		server.commit();
	}


	/**
	 * 使用SolrJ查询索引库数据
	 */

	/**
	 * 以Document形式返回查询结果
	 *
	 * @throws SolrServerException
	 */
	@Test
	public void testQueryIndex() throws SolrServerException {
		//创建服务器对象
		HttpSolrServer server = new HttpSolrServer("http://localhost:8983/solr/core2");
		//定义查询条件
		SolrQuery query = new SolrQuery("id:1");
		//进行查询处理
		QueryResponse response = server.query(query);
		SolrDocumentList results = response.getResults();
		int size = results.size();
		System.out.println("共查询到" + size + "条数据");
		//遍历数据
		for (SolrDocument result : results) {
			System.out.println("id: " + result.get("id"));
			System.out.println("title: " + result.get("title"));
		}
	}

	/**
	 * 以JavaBean形式返回查询结果
	 *
	 * @throws SolrServerException
	 */
	@Test
	public void testQueryIndexBean() throws SolrServerException {
		//创建服务器对象
		HttpSolrServer server = new HttpSolrServer("http://localhost:8983/solr/core3");
		//定义查询条件
		SolrQuery query = new SolrQuery("id:1");

		//进行查询处理
		QueryResponse queryResponse = server.query(query);
		List<Person> personList = queryResponse.getBeans(Person.class);

		//遍历数据
		for (Person person : personList) {
			System.out.println("id: " + person.getId());
			System.out.println("name: " + person.getName());
			System.out.println("desc: " + person.getDesc());
		}
	}

	/**
	 * SolrQuery实现排序
	 *
	 * @throws SolrServerException
	 */
	@Test
	public void testQueryIndexSort() throws SolrServerException {

		//创建服务器对象
		HttpSolrServer server = new HttpSolrServer("http://localhost:8983/solr/core3");
		//定义查询条件
		SolrQuery solrQuery = new SolrQuery("*:*");
		//设置按ID查询
		solrQuery.setSort("id", SolrQuery.ORDER.desc);
		//进行查询处理
		QueryResponse queryResponse = server.query(solrQuery);
		SolrDocumentList results = queryResponse.getResults();
		int size = results.size();
		System.out.println("共查找到" + size + "条数据");
		//遍历数据
		for (SolrDocument result : results) {
			System.out.println("id: " + result.get("id"));
			System.out.println("name: " + result.get("name"));
			System.out.println("desc: " + result.get("desc"));
		}

	}

	@Test
	public void testQueryIndexPage() throws SolrServerException {
		//要查询的页数
		int pageNum = 2;
		//每页显示条数
		int pageSize = 1;
		//当前页起始条数
		int start = (pageNum - 1) * pageSize;

		//创建服务器对象
		HttpSolrServer server = new HttpSolrServer("http://localhost:8983/solr/core3");
		//定义查询条件
		SolrQuery query = new SolrQuery("*:*");
		//设置按ID排序
		query.setSort("id", SolrQuery.ORDER.asc);
		//设置起始条数
		query.setStart(start);
		//设置每页条数
		query.setRows(pageSize);
		//进行查询处理
		QueryResponse queryResponse = server.query(query);
		SolrDocumentList results = queryResponse.getResults();

		System.out.println("本次共搜索到" + results.size() + "条数据");
		//遍历数据
		for (SolrDocument result : results) {
			System.out.println("id: " + result.get("id"));
			System.out.println("name: " + result.get("name"));
			System.out.println("desc: " + result.get("desc"));
		}
	}

	//	SolrQuery实现高亮显示
	@Test
	public void testQueryIndexHighLighting() throws SolrServerException {
		//创建服务器对象
		HttpSolrServer server = new HttpSolrServer("http://localhost:8983/solr/core3");
		//定义查询条件
		SolrQuery query = new SolrQuery("id:*");
		//设置高亮标签
		query.setHighlightSimplePre("<em>");
		query.setHighlightSimplePost("</em>");
		//设置高亮字段
		query.addHighlightField("desc");
		//进行查询处理
		QueryResponse queryResponse = server.query(query);
		SolrDocumentList results = queryResponse.getResults();

		//获取高亮字段
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();

		System.out.println("共找到了" + results.size() + "条数据");
		//遍历数据
		for (SolrDocument result : results) {
			System.out.println("id: " + result.get("id"));
			System.out.println("name: " + result.get("name"));
			System.out.println("desc: " + result.get("desc"));
		}

		for (String s : highlighting.keySet()) {
			System.out.println("s" + s);
			Map<String, List<String>> stringListMap = highlighting.get(s);
			for (String s1 : stringListMap.keySet()) {
				System.out.println("s1" + s1);
				List<String> stringList = stringListMap.get(s1);
				for (String s2 : stringList) {
					System.out.println("s2" + s2);
				}
			}
		}

	}


}
