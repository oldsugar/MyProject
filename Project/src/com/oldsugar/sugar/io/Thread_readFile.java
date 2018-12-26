package com.oldsugar.sugar.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Calendar;

public class Thread_readFile extends Thread {
	private static FileLock fileLock = null;

	public static void main(String[] args) {
		// Thread_readFile readFileThread=new Thread_readFile();
		// readFileThread.setName("readFileThread");
		// Thread_readFile writeFileThread2=new Thread_readFile();
		// writeFileThread2.setName("readFileThread2");
		//
		// readFileThread.start();
		// writeFileThread2.start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					File file = new File("D:/test.txt");
					RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
					FileChannel fileChannel = randomAccessFile.getChannel();
					fileLock = fileChannel.tryLock();
//					for (int j = 0; j < 100; j++) {
//						try {
//							System.out.println("数据:" + j);
//							System.out.println("fileChannel.isOpen()" + fileChannel.isOpen());
//
//							Thread.sleep(1000);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//						if (j == 10) {
//							fileLock.release();
//							System.out.println("fileChannel.isOpen()" + fileChannel.isOpen());
//							break;
//						}
//					}
				} catch (FileNotFoundException e2) {
					e2.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}).start();

	}

	public void run() {
		try {
			Calendar calstart = Calendar.getInstance();
			sleep(5000);
			File file = new File("D:/test.txt");
			// 给该文件加锁
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
				}
			}
			byte[] buf = new byte[1024];
			StringBuffer sb = new StringBuffer();
			while ((randomAccessFile.read(buf)) != -1) {
				sb.append(new String(buf, "utf-8"));
				buf = new byte[1024];
			}

			System.out.println(sb.toString());

			fileLock.release();
			fileChannel.close();
			randomAccessFile.close();
			randomAccessFile = null;

			Calendar calend = Calendar.getInstance();
			System.out.println("当前线程:" + Thread.currentThread().getName() + ",读文件共花了"
					+ (calend.getTimeInMillis() - calstart.getTimeInMillis()) + "秒");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
