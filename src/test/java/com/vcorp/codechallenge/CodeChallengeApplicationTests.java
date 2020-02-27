package com.vcorp.codechallenge;

import org.springframework.boot.test.context.SpringBootTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.vcorp.codechallenge.config.LoadCityConfig;
import com.vcorp.codechallenge.helper.CityPathHelper;
import com.vcorp.codechallenge.vo.CityVo;

import java.util.HashMap;
import java.util.Map;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class CodeChallengeApplicationTests {

	    @Autowired
	    private TestRestTemplate restTemplate;
	    @Autowired
	     private LoadCityConfig loadCityConfig;

	    @Test
	    public void fileLoad() {
	        Assert.assertFalse("File load failed", loadCityConfig.getCityMap().isEmpty());
	    }

	   /* Boston, New York
	    Philadelphia, Newark
	    Newark, Boston
	    Trenton, Albany
	    Los Angeles,San Francisco
	    Trenton,Detroit
	    San Diego,Los Angeles
	    Chicago,Columbus
	    Houston,Phoenix
	    Columbus,Indianapolis
	    San Antonio,Jacksonville
	    Dallas,San Antonio*/
	    @Test
	    public void sameCity() {
	        CityVo city = CityVo.build("Boston".toUpperCase());
	        Assert.assertTrue(CityPathHelper.checkPath(city, city).equals("yes"));
	    }

	    @Test
	    public void neighbours() {
	    	CityVo cityA = loadCityConfig.getCity("Boston".toUpperCase());
	    	CityVo cityB = loadCityConfig.getCity("New York".toUpperCase());

	        Assert.assertNotNull("Invalid test data. City not found: Boston", cityA);
	        Assert.assertNotNull("Invalid test data. City not found: New York", cityB);

	        Assert.assertTrue(CityPathHelper.checkPath(cityA, cityB).equals("yes"));
	    }

	    @Test
	    public void distantConnected() {
	    	CityVo cityA = loadCityConfig.getCity("Boston".toUpperCase());
	    	CityVo cityB = loadCityConfig.getCity("Trenton".toUpperCase());

	        Assert.assertNotNull("Invalid test data. City not found: Boston", cityA);
	        Assert.assertNotNull("Invalid test data. City not found: Trenton", cityB);

	        Assert.assertTrue(CityPathHelper.checkPath(cityA, cityB).equals("no"));
	    }

	    @Test
	    public void restConnectedIT() {

	        Map<String, String> params = new HashMap<>();
	        params.put("origin", "Philadelphia".toUpperCase());
	        params.put("destination", "Boston".toUpperCase());

	        String body = restTemplate.getForObject("/connected?origin={origin}&destination={destination}", String.class, params);
	        Assert.assertEquals("yes", body);
	    }

	    @Test
	    public void restNotConnectedIT() {

	        Map<String, String> params = new HashMap<>();
	        params.put("origin","Boston".toUpperCase());
	        params.put("destination", "San Antonio".toUpperCase());

	        String body = restTemplate.getForObject("/connected?origin={origin}&destination={destination}", String.class, params);
	        Assert.assertEquals("no", body);
	    }

	    @Test
	    public void badRequestIT() {
	        String body = restTemplate.getForObject("/connected", String.class);
	        ResponseEntity<String> response = restTemplate.exchange("/connected?origin=none&destination=none", HttpMethod.GET, HttpEntity.EMPTY, String.class);
	        Assert.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
	    }


}
