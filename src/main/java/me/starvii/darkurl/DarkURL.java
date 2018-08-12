package me.starvii.darkurl;

import burp.IBurpExtender;
import burp.IBurpExtenderCallbacks;
import burp.IExtensionHelpers;
import burp.IExtensionStateListener;

import javax.swing.*;
import java.io.PrintWriter;

/**
 * 检测规则
 * 有扩展名：		1、去掉扩展名(0)
 * 				2、替换扩展名(1)
 * 				3、添加扩展名(1)
 * 				4、添加前缀与扩展名(2)
 * 无扩展名：		1、添加扩展名(1)
 * 				2、添加前缀与扩展名(2)
 * <p>
 * 默认扩展名列表：
 * php
 * php3
 * php5
 * php7
 * php~
 * asp
 * aspx
 * jsp
 * jspx
 * bak
 * tar
 * tar.gz
 * zip
 * rar
 * 7z
 * html
 * txt
 */
public class DarkURL implements IBurpExtender, IExtensionStateListener {
	static public final String EXTENSION_NAME = "DARK_URL";
	static private DarkURL instance = null;
	private IBurpExtenderCallbacks callbacks = null;
	private PrintWriter out = null;
	private PrintWriter err = null;
	private boolean enable = true;

	static public void main(String[] args) {
		System.out.println("DarkURL!");
	}

	@Override
	public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
		instance = this;
		this.callbacks = callbacks;
		callbacks.setExtensionName(EXTENSION_NAME);
		out = new PrintWriter(callbacks.getStdout(), true);
		err = new PrintWriter(callbacks.getStderr(), true);
		callbacks.registerExtensionStateListener(this);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				OptionTab tab = new OptionTab(callbacks);
				callbacks.customizeUiComponent(tab);
				callbacks.addSuiteTab(tab);
			}
		});

		callbacks.registerHttpListener(new UrlDetect());

	}

	@Override
	public void extensionUnloaded() {
		if (null != out) {
			out.close();
		}
		if (null != err) {
			err.close();
		}
		instance = null;
	}

	public IBurpExtenderCallbacks getCallbacks() {
		assert null != callbacks;
		return callbacks;
	}

	public PrintWriter getOut() {
		assert null != out;
		return out;
	}

	public PrintWriter getErr() {
		assert null != err;
		return err;
	}

	public  boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public IExtensionHelpers getHelper() {
		return callbacks.getHelpers();
	}

	public static DarkURL getInstance() {
		return instance;
	}
}

