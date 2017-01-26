package org.planotius.controller.http;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Improved for planotius framework by @gustavolabbate on 8/26/2016.
 * <p>
 * Created by amadeu.bonfante on 24/05/2016.
 * <p>
 * Class dedicated to help with http requests.
 */
public class HTTPSController extends HTTPHelper {

    private static final Logger LOG = Logger.getLogger(HTTPSController.class.getName());

    /**
     * Url address of the server to connect to
     */
    private String httpsUrl;

    /**
     * Authentication to https request header.
     */
    private String userAuthentic;

    /**
     * user login
     */
    protected String user;

    /**
     * user password
     */
    protected String password;

    /**
     * Creates a new HTTPSController with authentication
     *
     * @param httpsUrl - http url for the api
     * @param user - admin user login
     * @param password - admin user password
     */
    public HTTPSController(String httpsUrl, String user, String password) {
        this.httpsUrl = httpsUrl;
        this.userAuthentic = new String(Base64.encodeBase64((user + ':' + password).getBytes()));
        this.user = user;
        this.password = password;
    }

    /**
     * Desc: Method GET for the Rest API
     *
     * @param rest - Rest api path.
     * @return String of get response
     */
    public String get(String rest) throws Exception {
        String urlPath = String.format("%s%s", this.httpsUrl, rest);
        return doRequest("GET", new URL(urlPath), null);
    }

    /**
     * Desc: Method post for the rest api.
     *
     * @param rest - Rest api path.
     * @param data
     * @return String of post response
     */
    public String post(String rest, String data) throws Exception {
        String urlPath = String.format("%s%s", this.httpsUrl, rest);
        return doRequest("POST", new URL(urlPath), data);
    }

    /**
     * Desc: Method put for the rest api.
     *
     * @param rest - Rest api path.
     * @param data
     * @return String of put response
     */
    public String put(String rest, String data) throws Exception {
        String urlPath = String.format("%s%s", this.httpsUrl, rest);
        return doRequest("PUT", new URL(urlPath), data);
    }

    /**
     * Desc : Basic https request with authentication.
     *
     * @param method - HTTP method (GET, POST, PUT, DEL .....)
     * @param url - Full url path to send the resquest.
     * @return A String with response content.
     */
    private String doRequest(String method, URL url, String data) throws Exception {
        LOG.debug("Trying request with method " + method + " url " + url + " and data " + data);
        // Result request
        String result = "";
        // Open connection with appliance.
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        LOG.debug("Got conn...");
        // Add request header
        SSLSocketFactory sslSocketFactory = createTrustAllSslSocketFactory();
        conn.setSSLSocketFactory(sslSocketFactory);
        conn.setRequestMethod(method);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Authorization", "Basic " + this.userAuthentic);
        LOG.debug("Conn properties set...");
        //Add data with POST, PUT....
        if (data != null) {
            LOG.debug("OutputStreamWriting data = " + data);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();
        }
        LOG.debug("Data sent.");
        // Get response.
        InputStreamReader content = new InputStreamReader(conn.getInputStream());
        LOG.debug("Reading input stream...");
        BufferedReader in = new BufferedReader(content);
        String line;
        while ((line = in.readLine()) != null) {
            result += line;
        }
        in.close();
        LOG.debug("Finished reading input stream.");
        return result;
    }

    /**
     * Generate a SSL context to https conection.
     *
     * @return SSLSocketFactory with ssl context.
     * @throws Exception
     */
    private SSLSocketFactory createTrustAllSslSocketFactory() throws Exception {
        TrustManager[] byPassTrustManagers = new TrustManager[]{
            new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }
        };

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, byPassTrustManagers, new SecureRandom());

        return sslContext.getSocketFactory();
    }

}
