package me.starvii.darkurl;

import burp.BurpExtender;
import burp.IBurpExtenderCallbacks;
import burp.IHttpListener;
import burp.IHttpRequestResponse;
import me.starvii.http.HttpRequest;

public class UrlDetect implements IHttpListener {
	@Override
	public void processHttpMessage(int toolFlag, boolean messageIsRequest, IHttpRequestResponse messageInfo) {
		if (BurpExtender.isEnable() && IBurpExtenderCallbacks.TOOL_PROXY == toolFlag) {
			// 只对Proxy的请求进行探测
			HttpRequest r = HttpRequest.makeup(messageInfo);
			if (null != r) {
				final String log = r.getRequestURL();
				BurpExtender.getOut().println(log);
			}
		}
	}
}
