package com.imudges.yardsell.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class httpSend {
	String urlString;

	/*
	 * public static void main(String[] args) throws Exception { HttpTest client
	 * = new HttpTest(��ַ); client.run(); }
	 */
	public httpSend(String urlString) {
		this.urlString = urlString;
	}

	public void send() throws Exception {
		// ����һ��URL����
		URL url = new URL(urlString);
		// ��URL
		HttpURLConnection urlConnection = (HttpURLConnection) url
				.openConnection();
		// �õ�������������÷���ֵ
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				urlConnection.getInputStream()));
		String line;
		// ��ȡ����ֵ�������ж�
		while ((line = reader.readLine()) != null) {
			int result = Integer.valueOf(line);
			if (result == 0) {
				System.out.println("���ͳɹ�");
			}
			/*
			 * else{ System.out.println(line); }
			 */
		}
	}
}