package me.starvii.darkurl.namerule;

import burp.BurpExtender;
import me.starvii.darkurl.INameRule;

/**
 * such as ".index.php.swp"
 */
public class AddPrefixExt implements INameRule {

	@Override
	public String transform(String url, String... args) {
		if (args.length > 1) {
			int pos = url.lastIndexOf("/");
			if (pos > -1) {
				String path = url.substring(0, pos + 1);
				String file = url.substring(pos + 1);
				file = args[0] + file + args[1];
				return path + file;
			}
		}
		BurpExtender.getErr().println("AddPrefixExt: no enough args.");
		return null;
	}
}
