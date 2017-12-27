package com.ims.webstore;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebstoreAppInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {
        XmlWebApplicationContext context = new XmlWebApplicationContext();
        context.setConfigLocation("/WEB-INF/DispatcherServlet-servlet.xml");

        ServletRegistration.Dynamic dispatcher
                = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));

        dispatcher.addMapping("/");
    }
}
