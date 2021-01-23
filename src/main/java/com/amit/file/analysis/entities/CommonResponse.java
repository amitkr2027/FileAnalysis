package com.amit.file.analysis.entities;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CommonResponse {
	public Integer responseCode;
	public String responseMessage;
}
