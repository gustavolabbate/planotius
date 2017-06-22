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

        if (browser == null || "firefox".equalsIgnoreCase(browser)) {
            LOG.debug("Using FIREFOX browser.");
            return new Firefox();
        } else if ( "googlechrome".equalsIgnoreCase(browser) || "chromium".equalsIgnoreCase(browser)) {
            LOG.debug("Using GOOGLE CHROME browser.");
            return new GoogleChrome();
        } else if ( "iexplore".equalsIgnoreCase(browser)) {
            LOG.debug("Using INTERNET EXPLORER browser.");
            return new InternetExplorer();
        } else if ( "edge".equalsIgnoreCase(browser)) {
            LOG.debug("Using EDGE browser.");
            return new Edge();
        } else if ( "phantomjs".equalsIgnoreCase(browser)) {
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
