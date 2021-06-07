/*package kr.spring.gg15.controller;

import org.apache.http.HttpResponse;
 
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.spring.gg15.dto.LeagueEntryDTO;
import kr.spring.gg15.dto.Summoner;

@Controller
public class SearchSummoner {
	 final String apiKey = "RGAPI-6491fe52-a13a-459c-9a9d-0ff47fc3956e";
	@RequestMapping("/search/searchResult.do")
	   public String summoner(@RequestParam String userName,Model model) {
	     
	      String url = "https://kr.api.riotgames.com" + "/lol/summoner/v4/summoners/by-name/" + userName + "?api_key=" + apiKey;
	    
	      ObjectMapper objectMapper = new ObjectMapper();
	      Summoner summoner = null;
	 
	      try {
	           HttpClient httpClient = HttpClientBuilder.create().build();
	           HttpGet httpGet = new HttpGet(url);
	           HttpResponse res = httpClient.execute(httpGet);
	           
	           if (res.getStatusLine().getStatusCode() == 200) {
	             ResponseHandler<String> handler = new BasicResponseHandler();
	             
	             String body = handler.handleResponse(res); //
	             
	             summoner = objectMapper.readValue(body, Summoner.class);
	            }
	           
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	
	      
	      LeagueEntryDTO[] dto = league(summoner.getId());
	      
	      
	      model.addAttribute("summoner", summoner);
	      model.addAttribute("leagueInfo", dto);
	      
	      return "searchResult";
	      
	   }
	
	   public LeagueEntryDTO[] league(String id) {
	
	
	      String url ="https://kr.api.riotgames.com" + "/lol/league/v4/entries/by-summoner/"+ id + "?api_key=" + apiKey;

	      ObjectMapper objectMapper = new ObjectMapper();
	      LeagueEntryDTO[] leagueInfo = null; 
	    
	      try {
	           HttpClient httpClient = HttpClientBuilder.create().build();
	           HttpGet httpGet = new HttpGet(url);
	           HttpResponse res = httpClient.execute(httpGet);
	          
	           if (res.getStatusLine().getStatusCode() == 200) {
	             ResponseHandler<String> handler = new BasicResponseHandler();
	             
	             String body = handler.handleResponse(res); //
	        
	             leagueInfo = objectMapper.readValue(body, LeagueEntryDTO[].class);
	  
	             
	            }
	           
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return leagueInfo;
	      
	   }
	
}*/