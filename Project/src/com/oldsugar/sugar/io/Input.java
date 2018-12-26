package com.oldsugar.sugar.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class Input {

	public static void main(String[] args) throws IOException {
		//FileInputStreamTest();
        //FileReaderTest();
        //FileOutputStreamTest();
        FileWriterTest();
	}

	public void inputFromTxt() {
		InputStreamReader inputStreamReader;
		try {
			inputStreamReader = new InputStreamReader(
					new FileInputStream("C:\\Users\\Administrator\\Desktop\\para.txt"), "gbk");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			bufferedReader.close();
			inputStreamReader.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void FileInputStreamTest() throws IOException {
		FileInputStream fis = new FileInputStream("tmp2.txt");
		byte[] buf = new byte[1024];
		int hasRead = 0;

		// read()���ص��ǵ����ֽ����ݣ��ֽ����ݿ���ֱ��ר��int����)������read(buf)���ص��Ƕ�ȡ�����ֽ��������������ݱ�����buf��
		while ((hasRead = fis.read(buf)) > 0) {
			// ÿ����ཫ1024���ֽ�ת�����ַ���������tmp2.txt�е��ַ�С��1024������һ�ξͶ�����
			// ѭ������ = �ļ��ַ��� ���� buf����
			System.out.println(new String(buf, 0, hasRead));
			/*
			 * ���ֽ�ǿ��ת�����ַ�������������ʵ�ֺ�����һ����Ч�����������Դ�ļ������ĵĻ����ܻ�����
			 * 
			 * for (byte b : buf) { char ch = (char)b; if (ch != '\r') System.out.print(ch);
			 * }
			 */
		}
		// ��finally����close����ȫ
		fis.close();
	}

	public static void FileReaderTest() throws IOException {

		try (
				// ��try() �д򿪵��ļ��� JVM���Զ��ر�
				FileReader fr = new FileReader("tmp2.txt")) {
			char[] buf = new char[32];
			int hasRead = 0;
			// ÿ��char��ռ�����ֽڣ�ÿ���ַ����ߺ��ֶ���ռ2���ֽڣ��������buf����Ϊ���٣������ܶ�ȡ�����ַ����ȵ�������,��������
			while ((hasRead = fr.read(buf)) > 0) {
				// ���buf�ĳ��ȴ����ļ�ÿ�еĳ��ȣ��Ϳ����������ÿ�У��������С�
				// ѭ������ = �ļ��ַ��� ���� buf����
				System.out.println(new String(buf, 0, hasRead));
				// ������Ч��һ��
				// System.out.println(buf);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void FileOutputStreamTest() throws FileNotFoundException, IOException {
		try (
				// ��try()�д��ļ����ڽ�β�Զ��ر�
				FileInputStream fis = new FileInputStream("tmp2.txt");
				FileOutputStream fos = new FileOutputStream("tmp3.txt");) {
			byte[] buf = new byte[4];
			int hasRead = 0;
			while ((hasRead = fis.read(buf)) > 0) {
				// ÿ��ȡһ�ξ�дһ�Σ������پ�д����
				fos.write(buf, 0, hasRead);
			}
			System.out.println("write success");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void FileWriterTest() throws IOException {
		try (FileWriter fw = new FileWriter("tmp4.txt")) {
			fw.write("�����ǵػ�\r\n");
			fw.write("���������\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
