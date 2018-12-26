package com.oldsugar.sugar.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;

public class FileLockManager {

	public static void main(String[] args) {
		FileLockManager fileLockManager = new FileLockManager("c:\\SangforServiceClient_20181215.log");

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					fileLockManager.Lock();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				for (int j = 0; j < 100; j++) {
					try {
						System.out.println("数据:" + j);
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		try {
			fileLockManager.unLock();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private FileLock fileLock = null;

	private File file = null;

	private RandomAccessFile randomAccessFile = null;

	public FileLockManager(String fileName) {
		this.file = new File(fileName);
	}

	public FileLockManager(File file) {
		this.file = file;
	}

	/**
	 * 文件加锁并创建文件
	 * 
	 * @return
	 * @throws IOException
	 */
	public boolean Lock() throws IOException {
		if (!this.file.exists()) {
			this.file.createNewFile();
			String a = null;
			this.randomAccessFile = new RandomAccessFile(this.file, a);
			this.fileLock = this.randomAccessFile.getChannel().tryLock();
			if (this.fileLock.isValid()) {
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}

	}

	/**
	 * 解锁并删除文件
	 * 
	 * @return
	 * @throws IOException
	 */
	public boolean unLock() throws IOException {
		if (!this.file.exists()) {
			return true;
		} else {
			if (this.fileLock != null) {
				this.fileLock.release();
			}
			if (this.randomAccessFile != null) {
				this.randomAccessFile.close();
			}
			if (this.file.delete()) {
				return true;
			} else {
				return false;
			}

		}

	}

	/**
	 * @return Returns the fileLock.
	 */
	public FileLock getFileLock() {
		return this.fileLock;
	}

	/**
	 * @param fileLock
	 *            The fileLock to set.
	 */
	public void setFileLock(FileLock fileLock) {
		this.fileLock = fileLock;
	}
}
