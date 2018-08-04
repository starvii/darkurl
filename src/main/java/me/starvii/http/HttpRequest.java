package me.starvii.http;

import burp.IHttpRequestResponse;
import burp.IHttpService;
import me.starvii.darkurl.DarkURL;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
	static private final String split = "\r\n";
	private String method = "GET";
	private String url = "";
	private String protocol = "HTTP";
	private String version = "1.1";
	private String host = "";
	private int port = 80;
	private String body = "";
	private Map<String, String> others = new HashMap<>();

	static public HttpRequest makeup(IHttpRequestResponse m) {
		final byte[] requestBytes = m.getRequest();
		final IHttpService service = m.getHttpService();
		final String request = new String(requestBytes);
		final int p1 = request.indexOf(split + split);
		if (p1 < 0) {
			DarkURL.getErr().println("not an integrity http request.");
			return null;
		}
		final int p0 = request.indexOf(split);
		final String firstLine = request.substring(0, p0);
		final String[] f0 = firstLine.split("\\s+");
		HttpRequest r = new HttpRequest();
		r.protocol = service.getProtocol();
		r.host = service.getHost();
		r.port = service.getPort();
		r.method = f0[0];
		final String url = f0[1];
		final int pq = url.indexOf("?");
		if (pq > 0) {
			r.url = url.substring(0, pq);
		} else {
			r.url = url;
		}
		final String[] f02 = f0[2].split("/");
//		DarkURL.getOut().println(String.format("DEBUG: protocol: %s, %s", r.protocol, f02[0]));
		r.version = f02[1];


		final String requestHeader = request.substring(p0, p1).trim();

		final String[] h = requestHeader.split(split);
		for (final String line: h) {
			final int pc = line.indexOf(":");
			if (pc > -1) {
				final String key = line.substring(0, pc).trim();
				final String val = line.substring(pc + 1).trim();
//				if (key.toLowerCase().equals("host")) {
//					final int pc0 = val.indexOf(":");
//					String host, port = "";
//					if (pc0 > 0) {
//						host = val.substring(0, pc0).trim();
//						port = val.substring(pc0 + 1).trim();
//					} else {
//						host = val;
//					}
////					DarkURL.getOut().println(String.format("DEBUG: host: %s, %s", r.host, host));
////					DarkURL.getOut().println(String.format("DEBUG: port: %d, %s", r.port, port));
//				} else {
//					r.others.put(key, val);
//				}
				r.others.put(key, val);
			}
		}

		final String b = request.substring(p1).trim();
		if (b.length() > 0) {
			r.body = b;
		}

		return r;
	}

	public String getRequestURL() {
		if (80 == port) {
			return String.format("%s://%s%s", protocol, host, url);
		} else {
			return String.format("%s://%s:%d%s", protocol, host, port, url);
		}
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Map<String, String> getOthers() {
		return others;
	}
}
