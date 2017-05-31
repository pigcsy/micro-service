package com.core.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class FileStreamUtils {

	private static Logger logger = LoggerFactory.getLogger(FileStreamUtils.class);
	
	public static List<String> readListFromFile(String filePath) {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		List<String> list = new ArrayList<>();
		try {
			File f = new File(filePath);
			if (!f.exists() || !f.isFile()){
				return list;
			}
			String str = "";
			fis = new FileInputStream(filePath);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			while ((str = br.readLine()) != null) {
				list.add(str);
			}
		} catch (Exception e) {
			logger.error("读取文件失败,文件路径："+filePath, e);
		} finally {
			try {
				if (br != null)
					br.close();
				if (isr != null)
					isr.close();
				if (fis != null)
					fis.close();
			} catch (IOException e) {
			}
		}
		return list;
	}
	
	
	
	
	
}
