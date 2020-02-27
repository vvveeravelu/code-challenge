package com.vcorp.codechallenge.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vcorp.codechallenge.config.LoadCityConfig;
import com.vcorp.codechallenge.helper.CityPathHelper;
import com.vcorp.codechallenge.vo.CityVo;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;

/**
 * @author VVEERAVELU
 *
 */
@RestController
public class CityPathController {

	@Autowired
	LoadCityConfig loadCityConfig;
	
	     /**
	     * @param origin
	     * @param destination
	     * @return
	     * Restcontoller API will help us to verify the two city paths are connected 
	     */
	    @ApiOperation(value = "To find path exist between 2 cities",
	            notes = "Returns true if cites connected and false otherwise ",
	            response = String.class)
	    @ApiResponses({
	            @ApiResponse(code = 400, message = "no", response = NullPointerException.class),
	            @ApiResponse(code = 500, message = "ERROR Contact support", response = Exception.class)
	    })
	    @GetMapping(value = "/connected", produces = "text/plain")
	    public String isConnected(
	            @ApiParam(name = "origin", value = "Origin City name", required = true) @RequestParam String origin,
	            @ApiParam(name = "destination", value = "Destination City name", required = true) @RequestParam String destination) {

	        CityVo originCity = loadCityConfig.getCity(origin.toUpperCase());
	        CityVo destCity = loadCityConfig.getCity(destination.toUpperCase());

	        Objects.requireNonNull(originCity);
	        Objects.requireNonNull(destCity);

	        return CityPathHelper.checkPath(originCity, destCity);
	   }
	     
	     
	     @ExceptionHandler(NullPointerException.class)
	     @ResponseStatus(HttpStatus.BAD_REQUEST)
	     public String notValidOrginorDest() {
	         return "no";
	     }

}
