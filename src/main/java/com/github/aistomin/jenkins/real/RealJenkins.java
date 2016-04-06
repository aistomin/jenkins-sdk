/**
 * Copyright (c) 2016, Istomin Andrei
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.aistomin.jenkins.real;

import com.github.aistomin.http.PostRequest;
import com.github.aistomin.jenkins.Credentials;
import com.github.aistomin.jenkins.Jenkins;
import com.github.aistomin.jenkins.Jobs;
import com.github.aistomin.jenkins.Users;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * Jenkins instance.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class RealJenkins implements Jenkins {

    /**
     * Base Jenkins URL.
     */
    private final transient String base;

    /**
     * Jenkins credentials.
     */
    private final transient Credentials creds;

    /**
     * Ctor.
     *
     * @param url Base Jenkins URL.
     * @param credentials Jenkins credentials.
     */
    public RealJenkins(final String url, final Credentials credentials) {
        this.base = url;
        this.creds = credentials;
    }

    /**
     * All the jobs of this Jenkins instance.
     *
     * @return Jobs.
     * @throws Exception If reading jobs was not successful.
     */
    public Jobs jobs() throws Exception {
        return new RealJobs(this.request(), this.creds);
    }

    /**
     * All the registered users of this Jenkins instance.
     *
     * @return Users.
     * @throws Exception If reading users was not successful.
     */
    public Users users() throws Exception {
        return new RealUsers(this.base, this.creds);
    }

    /**
     * Restart Jenkins.
     * @throws Exception If reading users was not successful.
     * @todo: Let's implement this method and solve issue #274.
     */
    public void restart() throws Exception {
        throw new NotImplementedException(
            String.format(
                "%s.restart() is not implemented.", this.getClass()
            )
        );
    }

    /**
     * Jenkins' version.
     *
     * @return Version.
     * @throws Exception If reading users was not successful.
     */
    public String version() throws Exception {
        final Map<String, String> headers = new HashMap<String, String>();
        Executor.newInstance(
            HttpClientBuilder.create().build()
        ).execute(Request.Post(this.base))
            .handleResponse(
                new ResponseHandler<Object>() {
                    public Object handleResponse(
                        final HttpResponse response
                    ) throws ClientProtocolException, IOException {
                        for (final Header header : response.getAllHeaders()) {
                            headers.put(header.getName(), header.getValue());
                        }
                        return response;
                    }
                }
            );
        return headers.get("X-Jenkins");
    }

    /**
     * Root XML content of Jenkins.
     *
     * @return XML string.
     * @throws Exception If something goes wrong.
     */
    public String xml() throws Exception {
        return new PostRequest(this.request(), this.creds.headers()).execute();
    }

    /**
     * Creates API URL to request Jenkins' data.
     *
     * @return URL string.
     */
    private String request() {
        return String.format("%s/api/xml?depth=3", this.base);
    }
}
