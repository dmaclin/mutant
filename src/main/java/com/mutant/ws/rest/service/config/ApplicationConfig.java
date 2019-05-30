package com.mutant.ws.rest.service.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.mutant.ws.rest.service.MutantService;

import java.util.HashSet;
import java.util.Set;

	@ApplicationPath("/")
	public class ApplicationConfig extends Application {
	    @Override
	    public Set<Class<?>> getClasses() {
	        Set<Class<?>> classes = new HashSet<Class<?>>();
	        classes.add(MutantService.class);
	        
	        return classes;
	    }
	}