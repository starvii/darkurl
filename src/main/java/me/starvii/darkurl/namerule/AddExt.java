package me.starvii.darkurl.namerule;

import me.starvii.darkurl.DarkURL;
import me.starvii.darkurl.INameRule;

public class AddExt implements INameRule {
	@Override
	public String transform(String url, String... args) {
		if (args.length > 0) {
			return url + args[0];
		}
		DarkURL.getInstance().getErr().println("AddExt: no args.");
		return null;
	}
}
