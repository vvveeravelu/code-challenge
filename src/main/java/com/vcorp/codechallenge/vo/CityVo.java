package com.vcorp.codechallenge.vo;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class CityVo {
	   private String name;

	    private Set<CityVo> nearby = new HashSet<>();

	    private CityVo(String name) {
	        Objects.requireNonNull(name);
	        this.name = name.trim().toUpperCase();
	    }

	    private CityVo() {
	    }

	    public static CityVo build(String name) {
	        return new CityVo(name);
	    }

	    @Override
	    public String toString() {

	        return "City{" +
	                "name='" + name + "'" +
	                ", nearby='" + prettyPrint() +
	                "'}";
	    }

	    public String prettyPrint() {
	        return nearby
	                .stream()
	                .map(CityVo::getName)
	                .collect(Collectors.joining(","));
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public CityVo addNearby(CityVo city) {
	        nearby.add(city);
	        return this;
	    }

	    public Set<CityVo> getNearby() {
	        return nearby;
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!(o instanceof CityVo)) return false;
	        CityVo city = (CityVo) o;
	        return Objects.equals(name, city.name);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(name);
	    }
}
