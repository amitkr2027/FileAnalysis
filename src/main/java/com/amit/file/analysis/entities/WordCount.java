package com.amit.file.analysis.entities;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class WordCount {
	Map<String, Integer> wordCountMap;
}
