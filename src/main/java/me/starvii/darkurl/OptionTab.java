package me.starvii.darkurl;

import burp.BurpExtender;
import burp.ITab;

import java.awt.*;

public class OptionTab implements ITab {

	static public final String DEFAULT_RANGE = "10.*; 172.16.*; 192.168.*";


	@Override
	public String getTabCaption() {
		return BurpExtender.EXTENSION_NAME;
	}

	@Override
	public Component getUiComponent() {
		return null;
	}
}
