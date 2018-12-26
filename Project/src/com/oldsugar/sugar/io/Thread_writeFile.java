package com.oldsugar.sugar.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Calendar;

public class Thread_writeFile extends Thread {
    public static void main(String[] args) {
        Thread_writeFile writeFileThread=new Thread_writeFile();
        writeFileThread.setName("writeFileThread");
//        Thread_writeFile writeFileThread2=new Thread_writeFile(); 
//        writeFileThread2.setName("writeFileThread2");
        writeFileThread.start();  
//        writeFileThread2.start();  
    }
	
	public void run() {
		Calendar calstart = Calendar.getInstance();
		File file = new File("D:/test.txt");
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			// �Ը��ļ�����
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
			FileChannel fileChannel = randomAccessFile.getChannel();
			FileLock fileLock = null;
			while (true) {
				try {
					fileLock = fileChannel.tryLock();
					break;
				} catch (Exception e) {
					System.out.println("�������߳����ڲ������ļ�����ǰ�߳�" + Thread.currentThread().getName() + "����1000����");
					sleep(1000);
					System.out.println("38��");
				}

			}
			for (int i = 1; i <= 1000; i++) {
				sleep(10);
				StringBuffer sb = new StringBuffer();
				sb.append("���ǵ�" + i + "�ж�Ӧ������");
				sb.append("\n");
				randomAccessFile.write(sb.toString().getBytes("utf-8"));
			}
			fileLock.release();
			fileChannel.close();
			randomAccessFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Calendar calend = Calendar.getInstance();
		System.out.println("�߳�:" + Thread.currentThread().getName() + ",д�ļ�������"
				+ (calend.getTimeInMillis() - calstart.getTimeInMillis()) + "��");
	}
}
