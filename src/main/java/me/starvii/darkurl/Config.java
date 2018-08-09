package me.starvii.darkurl;

import java.util.HashSet;
import java.util.Set;

public class Config {
	static public final Set<String> OneParams = new HashSet<>();
	static public final Set<String[]> TwoParams = new HashSet<>();

	static {
		final String _t = "php php3 php5 php7 php~ " +
				"asp aspx jsp jspx " +
				"bak tar tar.gz zip rar 7z " +
				"txt html htm ";
		final String[] _ts = _t.split(" ");
		for (final String t: _ts) {
			final String n = t.trim();
			if (n.length() > 0) {
				OneParams.add("." + n);
			}
		}
		TwoParams.add(new String[]{".", ".swp"});
	}

	public void readConfig() {
		// TODO:
	}

	public void saveConfig() {
		// TODO:
	}
}
