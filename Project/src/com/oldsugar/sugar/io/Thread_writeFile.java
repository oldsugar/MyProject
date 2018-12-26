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
			// 对该文件加锁
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
			FileChannel fileChannel = randomAccessFile.getChannel();
			FileLock fileLock = null;
			while (true) {
				try {
					fileLock = fileChannel.tryLock();
					break;
				} catch (Exception e) {
					System.out.println("有其他线程正在操作该文件，当前线程" + Thread.currentThread().getName() + "休眠1000毫秒");
					sleep(1000);
					System.out.println("38行");
				}

			}
			for (int i = 1; i <= 1000; i++) {
				sleep(10);
				StringBuffer sb = new StringBuffer();
				sb.append("这是第" + i + "行对应的数据");
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
		System.out.println("线程:" + Thread.currentThread().getName() + ",写文件共花了"
				+ (calend.getTimeInMillis() - calstart.getTimeInMillis()) + "秒");
	}
}
