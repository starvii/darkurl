package me.starvii.darkurl.namerule;

import burp.BurpExtender;
import me.starvii.darkurl.INameRule;

public class ReplaceExt implements INameRule {
	@Override
	public String transform(String url, String... args) {
		if (args.length > 0) {
			int pos = url.lastIndexOf(".");
			if (pos != -1) {
				return url.substring(0, pos) + args[0];
			}
		}
		BurpExtender.getErr().println("ReplaceExt: no extension name.");
		return null;
	}
}
