package com.vcorp.codechallenge.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.vcorp.codechallenge.vo.CityVo;



/**
 * @author VVEERAVELU
 * Config Files reads the comma sepated cites txy files from the resource folder and construct the MAP
 *
 */

@Component
public class LoadCityConfig {
	 private final Log LOG = LogFactory.getLog(getClass());

	    private Map<String, CityVo> cityMap = new HashMap<>();

	    @Value("${data.file:classpath:city.txt}")
	    private String CITIES;

	    @Autowired
	    private ResourceLoader resourceLoader;


	    public Map<String, CityVo> getCityMap() {
	        return cityMap;
	    }
	    
	    /**
	     * @throws IOException
	     */
	    @PostConstruct
	    private void read() throws IOException {

	        LOG.info("Reading data");

	        Resource resource = resourceLoader.getResource(CITIES);

	        InputStream is;

	        if (!resource.exists()) {
	            // file on the filesystem path
	            is = new FileInputStream(new File(CITIES));
	        } else {
	            // file is a classpath resource
	            is = resource.getInputStream();
	        }

	        Scanner scanner = new Scanner(is);

	        while (scanner.hasNext()) {

	            String line = scanner.nextLine();
	            if (StringUtils.isEmpty(line)) continue;

	            LOG.info(line);

	            String[] split = line.split(",");
	            String Akey = split[0].trim().toUpperCase();
	            String Bkey = split[1].trim().toUpperCase();

	            if (!Akey.equals(Bkey)) {
	            	CityVo A = cityMap.getOrDefault(Akey, CityVo.build(Akey));
	            	CityVo B = cityMap.getOrDefault(Bkey, CityVo.build(Bkey));

	                A.addNearby(B);
	                B.addNearby(A);

	                cityMap.put(A.getName(), A);
	                cityMap.put(B.getName(), B);
	            }
	        }

	        LOG.info("Map: " + cityMap);
	    }

	    public CityVo getCity(String name) {
	        return cityMap.get(name);
	    }
}
