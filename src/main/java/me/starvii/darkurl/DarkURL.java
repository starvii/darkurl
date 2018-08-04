package me.starvii.darkurl;

import burp.IBurpExtender;
import burp.IBurpExtenderCallbacks;

import java.io.PrintWriter;

/**
 * 检测规则
 * 有扩展名：	1、去掉扩展名
 * 2、替换扩展名
 * 3、添加扩展名
 * 4、添加前缀与扩展名
 * 无扩展名：	1、添加扩展名
 * 2、添加前缀与扩展名
 * <p>
 * 默认扩展名列表：
 * php
 * php3
 * php5
 * php7
 * php~
 * bak
 * tar
 * tar.gz
 * zip
 * rar
 * 7z
 * html
 * txt
 */
public class DarkURL implements IBurpExtender {
	static public final String EXTENSION_NAME = "DARK_URL";
	static private IBurpExtenderCallbacks callbacks = null;
	static private PrintWriter out = null;
	static private PrintWriter err = null;
	static private boolean enable = true;

	static public void main(String[] args) {
		System.out.println("DarkURL!");
	}

	@Override
	public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
		DarkURL.callbacks = callbacks;
		callbacks.setExtensionName(EXTENSION_NAME);
		out = new PrintWriter(callbacks.getStdout(), true);
		err = new PrintWriter(callbacks.getStderr(), true);

//		SwingUtilities.invokeLater();


		callbacks.registerHttpListener(new UrlDetect());
	}

	public static IBurpExtenderCallbacks getCallbacks() {
		assert null != callbacks;
		return callbacks;
	}

	public static PrintWriter getOut() {
		assert null != out;
		return out;
	}

	public static PrintWriter getErr() {
		assert null != err;
		return err;
	}

	public static boolean isEnable() {
		return enable;
	}

	public static void setEnable(boolean enable) {
		DarkURL.enable = enable;
	}
}

