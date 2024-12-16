package com.wg.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class FeeResponse {
	boolean feeUpdated;
	boolean feeAdded;
	Double feeAmount;
	Double fine;
	String StudentId;
}