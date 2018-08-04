package me.starvii.darkurl.namerule;

import burp.BurpExtender;
import me.starvii.darkurl.INameRule;

public class AddExt implements INameRule {
	@Override
	public String transform(String url, String... args) {
		if (args.length > 0) {
			return url + args[0];
		}
		BurpExtender.getErr().println("AddExt: no args.");
		return null;
	}
}
