package me.starvii.darkurl.namerule;

import burp.BurpExtender;
import me.starvii.darkurl.INameRule;

/**
 * Delete Extension
 */
public class DeleteExt implements INameRule {

	@Override
	public String transform(String url, String... args) {
		int pos = url.lastIndexOf(".");
		if (pos == -1) {
			BurpExtender.getErr().println("DeleteExt: no extension name.");
			return url;
		} else {
			return url.substring(0, pos);
		}
	}
}
