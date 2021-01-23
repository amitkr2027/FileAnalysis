package com.amit.file.analysis.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/home")
@CrossOrigin(origins = "http://localhost:4200")
public class DashboardController {

	@PostMapping("/upload")
	public Map<String, Integer> fileParser(@RequestPart("file") MultipartFile multiPartFile) {

		Map<String, Integer> wordCountMap = new HashMap<String, Integer>();

		try {

			Random rand = new Random();
			int int_random = rand.nextInt(100000000);
			Path root = Paths.get("uploads" + int_random);
			if (!Files.exists(root)) {
				Files.createDirectory(root);
			}

			Files.copy(multiPartFile.getInputStream(), root.resolve(multiPartFile.getOriginalFilename()));

			String filePath = System.getProperty("user.dir") + "\\" + "uploads" + "\\" + int_random + "\\"
					+ multiPartFile.getOriginalFilename();
			System.out.println(filePath);

			File file = convert(multiPartFile);
			Scanner scan = new Scanner(file);
			while (scan.hasNext()) {
				String s = scan.next();
				if (wordCountMap.containsKey(s)) {
					wordCountMap.put(s, wordCountMap.get(s) + 1);
				} else {
					wordCountMap.put(s, 1);
				}

			}
			scan.close();
		} catch (Exception e) {
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}

		return wordCountMap;
	}

	public static File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

}
