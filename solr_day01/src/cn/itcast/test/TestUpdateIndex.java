package cn.itcast.test;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class TestUpdateIndex {
	
	@Test
	public void updateTest() throws Exception{
		//创建连接的服务器地址
		String baseUrl = "http://localhost:8080/solr";
		//创建服务器对象
		HttpSolrServer server = new HttpSolrServer(baseUrl);
		//创建需要添加的document对象
		SolrInputDocument document = new SolrInputDocument();
		
		document.addField("id", "b001");
		document.addField("name", "编程思想");
		
		server.add(document);
		server.commit();
	}
	
	@Test
	public void testDel() throws SolrServerException, IOException{
		//创建连接库地址
		String baseURL = "http://localhost:8080/solr";
		//创建服务端对象
		HttpSolrServer server = new HttpSolrServer(baseURL);
		//根据id来删除
		//server.deleteById("b001");
		server.deleteByQuery("*:*");
		server.commit();
	}
	
	@Test
	public void testSearch() throws SolrServerException{
		//创建出服务器地址
		String baseURL = "http://localhost:8080/solr";
		//创建服务器对象
		HttpSolrServer server = new HttpSolrServer(baseURL);
		//创建查询条件
		SolrQuery query = new SolrQuery();
		query.setQuery("*:*");
		
		QueryResponse response = server.query(query);
		
		SolrDocumentList results = response.getResults();
		
		System.err.println("总记录数是:"+results.getNumFound());
		for (SolrDocument solrDocument : results) {
			System.err.println("id:"+solrDocument.get("id"));
			System.err.println("name:"+solrDocument.get("name"));
		}
		
	}	
}
