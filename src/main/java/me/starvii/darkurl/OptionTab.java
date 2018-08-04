package me.starvii.darkurl;

import burp.BurpExtender;
import burp.ITab;

import java.awt.*;

public class OptionTab implements ITab {

	@Override
	public String getTabCaption() {
		return BurpExtender.EXTENSION_NAME;
	}

	@Override
	public Component getUiComponent() {
		return null;
	}
}
