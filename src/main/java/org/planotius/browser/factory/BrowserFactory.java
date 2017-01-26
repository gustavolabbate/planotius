package org.planotius.browser.factory;

import org.planotius.browser.instances.Edge;
import org.planotius.browser.instances.InternetExplorer;
import org.planotius.browser.instances.GoogleChrome;
import org.planotius.browser.instances.PhantomJS;
import org.planotius.browser.instances.Firefox;
import org.planotius.browser.Browser;
import org.apache.log4j.Logger;

/**
 *
 * @author gustavolabbate
 */
public class BrowserFactory {

    private static final Logger LOG = Logger.getLogger(BrowserFactory.class.getName());

    private boolean remote = false;

    public Browser getBrowser(String browser) {

        if (browser == null || browser.equalsIgnoreCase("firefox")) {
            LOG.debug("Using FIREFOX browser.");
            return new Firefox();
        } else if (browser.equalsIgnoreCase("googlechrome") || browser.equalsIgnoreCase("chromium")) {
            LOG.debug("Using GOOGLE CHROME browser.");
            return new GoogleChrome();
        } else if (browser.equalsIgnoreCase("iexplore")) {
            LOG.debug("Using INTERNET EXPLORER browser.");
            return new InternetExplorer();
        } else if (browser.equalsIgnoreCase("edge")) {
            LOG.debug("Using EDGE browser.");
            return new Edge();
        } else if (browser.equalsIgnoreCase("phantomjs")) {
            LOG.debug("Using PhantomJS browser.");
            return new PhantomJS();
        }

        return null;
    }

    public boolean isRemote() {
        return remote;
    }

    public void setRemote(boolean remote) {
        this.remote = remote;
    }

}
