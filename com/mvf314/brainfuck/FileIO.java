package com.mvf314.brainfuck;

import java.io.*;

public class FileIO {

	public static String[] read(String path, String delim) throws IOException {
		String line = null;
		FileReader fr = new FileReader(path);
		BufferedReader br = new BufferedReader(fr);
		String result = "";
		while ((line = br.readLine()) != null) {
			result += line;
		}
		br.close();
		fr.close();
		return  result.split(delim);
	}

	public static void write (String path, String text, boolean append) throws IOException {
		FileWriter fw = new FileWriter(path, append);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(text);
		bw.close();
	}

}
