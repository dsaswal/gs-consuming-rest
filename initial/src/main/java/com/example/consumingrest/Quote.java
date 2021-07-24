package com.example.consumingrest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@RestController
@RequestMapping("/quotes")
@JsonIgnoreProperties(ignoreUnknown = true)
@Configuration
@PropertySource("classpath:application.properties")
public class Quote {

  private static final Logger log = LoggerFactory.getLogger(Quote.class);
  private String type;
  private Value value;

	@org.springframework.beans.factory.annotation.Value("${RANDOM_REST_URL}")
	String RANDOM_REST_URL;

  public Quote() {
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Value getValue() {
    return value;
  }

  public void setValue(Value value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return "Quote{" +
        "type='" + type + '\'' +
        ", value=" + value +
        '}';
  }

  @GetMapping("/quote") 
  public String getQuoteFromAPI() {
	//@org.springframework.beans.factory.annotation.Value(" ${RANDOM_REST_URL} ")
	//String RANDOM_REST_URL;
	  log.info("RANDOM_REST_URL " + RANDOM_REST_URL);
	  RestTemplate  restTemplate = new RestTemplate();
	  Quote theQuote = restTemplate.getForObject(RANDOM_REST_URL, Quote.class);
	  //return theQuote.toString();
	  //return theQuote.getValue().toString();
	  return theQuote.getValue().getQuote();
  }

}
