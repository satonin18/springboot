package com.lanit.dcs.diss.aacs.satonin18.hackathon.web;

import com.lanit.dcs.diss.aacs.satonin18.hackathon.SpringbootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Created with IntelliJ IDEA.
 * User: satonin18
 * Date: 9/24/19
 * Time: 11:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringbootApplication.class);
    }
}
