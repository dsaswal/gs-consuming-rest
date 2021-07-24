package com.example.consumingrest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/quotes")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {

  private String type;
  private Value value;

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
	  RestTemplate  restTemplate = new RestTemplate();
	  Quote theQuote = restTemplate.getForObject("https://quoters.apps.pcfone.io/api/random", Quote.class);
	  //return theQuote.toString();
	  //return theQuote.getValue().toString();
	  return theQuote.getValue().getQuote();
  }

}
