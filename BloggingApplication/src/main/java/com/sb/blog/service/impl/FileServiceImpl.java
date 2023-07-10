package com.sb.blog.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sb.blog.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String UploadImage(String path, MultipartFile file) throws IOException {

//	File Name
		String name = file.getOriginalFilename();
//		abc.png

//	Random Name Generate File
		String randomID = UUID.randomUUID().toString();
		String fileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));

//		full Path
		String filePath = path + File.separator + fileName1;

//		create folder if not created
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}

//		file copy
		Files.copy(file.getInputStream(), Paths.get(filePath));

		return name;

	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String filePath = path + File.separator + fileName;
		FileInputStream fileInputStream = new FileInputStream(filePath);
		return fileInputStream;

	}

}
