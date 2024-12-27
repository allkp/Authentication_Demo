package com.soapapplicationdemo_1.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;

@Configuration
@EnableWs
public class WebServiceConfig extends WsConfigurerAdapter {

	private static final String NAMESPACE_URI = "http://apis_pack.soapapplicationdemo_1.com";
	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
			ApplicationContext applicationContext) {
		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(applicationContext);
		messageDispatcherServlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<>(messageDispatcherServlet, "/ws/*");
	}

	@Bean(name = "employeeService")
	public DefaultWsdl11Definition defaultWsdl11Definition() {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("EmployeeServicePort");
		wsdl11Definition.setLocationUri("/ws"); // The URL pattern where the service will be accessible
		wsdl11Definition.setTargetNamespace(NAMESPACE_URI); // The target namespace from the XSD schema
		wsdl11Definition.setSchema(employeeSchema()); // Link to your XSD schema
		return wsdl11Definition;
	}

	 @Bean
	    public SimpleXsdSchema employeeSchema() {
	        return new SimpleXsdSchema(new ClassPathResource("employee.xsd"));
	    }

//	@Bean
//	public EmployeeEndpoint employeeEndpoint() {
//		return new EmployeeEndpoint();
//	}

}
//
//
///**
// * Configure the message factory for creating SOAP messages.
// * SaajSoapMessageFactory is used for handling SOAP messages.
// */
//@Bean
//public WebServiceMessageFactory messageFactory() {
//    return new SaajSoapMessageFactory();
//}
//
///**
// * Configure JAXB marshaller to map Java objects to XML and vice-versa.
// * You will use JAXB to convert between Java objects and XML when handling SOAP messages.
// */
//@Bean
//public Jaxb2Marshaller marshaller() {
//    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//    marshaller.setPackagesToScan("com.springbootsoap"); // Package where your JAXB classes are located
//    return marshaller;
//}
//
///**
// * Configure the endpoint mapping (soap operation to endpoint).
// * This step ties the SOAP request to the actual implementation.
// */
//@Bean
//public SoapUriEndpointMapping endpointMapping() {
//    SoapUriEndpointMapping mapping = new SoapUriEndpointMapping();
//    mapping.setDefaultEndpoint(employeeEndpoint());
//    return mapping;
//}
//
///**
// * Define the actual SOAP endpoint that will handle the service requests.
// * This is where the @Endpoint class is used for handling incoming requests.
// */
//@Bean
//public EmployeeEndpoint employeeEndpoint() {
//    return new EmployeeEndpoint();
//}