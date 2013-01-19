package it.webapp.perla;

import java.util.Locale;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

public class MessageBundleCustom extends ReloadableResourceBundleMessageSource {
	private Locale loc;

	
	public MessageBundleCustom() {
		super();
	}

	public Locale getLoc() {
		return loc;
	}

	public void setLoc(Locale loc) {
		this.loc = loc;
	} 
	
	public void setLocaleLang(String lang){
		loc=new Locale(lang);
	}
	
	public String getMessage(String code){
		return getMessage(code, null, loc);
	}
}
