package com.smsapi.model;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class SMSData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Valid
	@NotBlank
	@ApiModelProperty(position = 1, required = true, value = "from (string min length 6 max length 16) .example : 91983435345.")
	private String from;
	@Valid
	@NotBlank
	@ApiModelProperty(position = 2, required = true, value = "from (string min length 6 max length 16) .example : 91983435345.")
	private String to;
	@Valid
	@NotBlank
	@ApiModelProperty(position = 1, required = true, value = "text (string min length 1 max length 120) .example : STOP , Hello World...")
	private String text;
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
}
