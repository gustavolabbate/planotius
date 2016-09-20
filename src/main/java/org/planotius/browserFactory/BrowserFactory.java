package org.planotius.browserFactory;

/**
 *
 * @author gustavolabbate
 */
public class BrowserFactory {

    public Browser getBrowser(String browser) {
        
        if (browser == null || browser.equalsIgnoreCase("firefox")) {
            return new Firefox();
        } else if (browser.equalsIgnoreCase("googlechrome") || browser.equalsIgnoreCase("chromium")) {
            return new GoogleChrome();
        } else if (browser.equalsIgnoreCase("htmlunitdriver") || browser.equalsIgnoreCase("phantom")) {
            return new HtmlUnit();
        } else if (browser.equalsIgnoreCase("iexplore")) {
            return new InternetExplorer();
        }

        return null;
    }
}
