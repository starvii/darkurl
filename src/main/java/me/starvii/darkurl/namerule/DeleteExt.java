package me.starvii.darkurl.namerule;

import me.starvii.darkurl.DarkURL;
import me.starvii.darkurl.INameRule;

/**
 * Delete Extension
 */
public class DeleteExt implements INameRule {

	@Override
	public String transform(String url, String... args) {
		int pos = url.lastIndexOf(".");
		if (pos > -1) {
			return url.substring(0, pos);
		} else {
			DarkURL.getInstance().getErr().println("DeleteExt: no extension name.");
			return null;
		}
	}
}
