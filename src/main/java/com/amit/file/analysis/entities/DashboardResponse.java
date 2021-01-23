package com.amit.file.analysis.entities;

import lombok.Data;

@Data
public class DashboardResponse extends CommonResponse {
	public String fileName;
	public WordCount wordCount;
}
