package me.starvii.darkurl;

import burp.*;
import me.starvii.darkurl.namerule.AddExt;
import me.starvii.darkurl.namerule.AddPrefixExt;
import me.starvii.darkurl.namerule.DeleteExt;
import me.starvii.darkurl.namerule.ReplaceExt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UrlDetect implements IHttpListener {

	static private final Set<String> urlCache = new HashSet<>();

	@Override
	public void processHttpMessage(int toolFlag, boolean messageIsRequest, IHttpRequestResponse messageInfo) {
		if (DarkURL.isEnable() && IBurpExtenderCallbacks.TOOL_PROXY == toolFlag) {
			// 只对Proxy的请求进行探测
			final IExtensionHelpers h = DarkURL.getHelper();
			final IRequestInfo r = h.analyzeRequest(messageInfo);
			String url = r.getUrl().toString();
			final int p = url.indexOf("?");
			if (p > -1) {
				url = url.substring(0, p);
			}
			DarkURL.getOut().println(url);
			DarkURL.getOut().println(generateDarkUrl(url));
			r.getUrl();
		}
	}

	static private List<String> generateDarkUrl(final String url) {
		final List<String> ret = new ArrayList<>();
		String newUrl = new DeleteExt().transform(url);
		if (null != newUrl) {
			ret.add(newUrl);
		}
		for (final String n : Config.OneParams) {
			newUrl = new AddExt().transform(url, n);
			if (null != newUrl) {
				ret.add(newUrl);
			}
			newUrl = new ReplaceExt().transform(url, n);
			if (null != newUrl) {
				ret.add(newUrl);
			}
		}
		for (final String[] ns : Config.TwoParams) {
			newUrl = new AddPrefixExt().transform(url, ns[0], ns[1]);
			if (null != newUrl) {
				ret.add(newUrl);
			}
		}
		return ret;
	}

	static private int sendGetRequest(final String url) {
		return 0;
	}

//	static private int sendRequest(final String url) {
//		try {
//			final URL u = new URL(url);
//			final HttpURLConnection c = (HttpURLConnection)u.openConnection();
//			c.setRequestMethod("GET");
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//
//		}
//	}
}
