package com.serenitydojo.playwright.section_04.headlesschromeoptions;

import java.util.Arrays;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.junit.Options;
import com.microsoft.playwright.junit.OptionsFactory;

public class HeadlessChromeOptions implements OptionsFactory {

	@Override
	public Options getOptions() {
		return new Options().setLaunchOptions(new BrowserType.LaunchOptions()
				.setArgs(Arrays.asList("--no-sandbox", "--headless", "--disable-gpu")))
			.setHeadless(true)
			.setTestIdAttribute("data-test");
	}

}
