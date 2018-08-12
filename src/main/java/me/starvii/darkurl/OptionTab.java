package me.starvii.darkurl;

import burp.BurpExtender;
import burp.IBurpExtenderCallbacks;
import burp.ITab;
import burp.ITextEditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionTab extends JPanel implements ITab {

	private IBurpExtenderCallbacks callbacks;
	private ITextEditor textEditor = null;
	private JButton btnOk = null;

	@Override
	public String getTabCaption() {
		return BurpExtender.EXTENSION_NAME;
	}

	@Override
	public Component getUiComponent() {
		return this;
	}

	public OptionTab(IBurpExtenderCallbacks callbacks) {
		this.callbacks = callbacks;
		add(new JLabel("url pattern:"));

		textEditor = this.callbacks.createTextEditor();
		add(textEditor.getComponent());
		textEditor.setText(Config.getRangePattern().getBytes());

		btnOk = new JButton("OK");
		this.add(btnOk);
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String p = new String(textEditor.getText());
				DarkURL.getInstance().getOut().println(p);
				Config.setRangePattern(p);
			}
		});
	}
}

