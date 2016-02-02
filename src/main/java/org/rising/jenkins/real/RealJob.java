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
package org.rising.jenkins.real;

import java.net.URLEncoder;
import java.util.List;
import org.apache.commons.lang3.NotImplementedException;
import org.rising.http.PostRequest;
import org.rising.jenkins.Builds;
import org.rising.jenkins.Credentials;
import org.rising.jenkins.Job;
import org.rising.jenkins.JobDetails;
import org.rising.jenkins.JobParameter;

/**
 * Jenkins' job.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class RealJob implements Job {

    /**
     * Job's name.
     */
    private final transient String identifier;

    /**
     * Job's details request.
     */
    private final transient String request;

    /**
     * Jenkins credentials.
     */
    private final transient Credentials creds;

    /**
     * Ctor.
     *
     * @param name Job's name.
     * @param url API URL that returns users' details.
     * @param credentials Jenkins credentials.
     * @throws Exception If error occurred.
     */
    public RealJob(
        final String name, final String url, final Credentials credentials
    ) throws Exception {
        this.identifier = name;
        this.request = String.format(
            "%s%s", url,
            String.format(
                "&xpath=hudson/job[displayName=%s]",
                URLEncoder.encode(String.format("'%s'", name), "UTF-8")
            )
        );
        this.creds = credentials;
    }

    /**
     * Job name.
     *
     * @return Job name.
     * @throws Exception If something goes wrong.
     */
    public String name() throws Exception {
        return this.identifier;
    }

    /**
     * Job details.
     *
     * @return Job details.
     * @todo: Let's implement this method and solve Issue #46.
     */
    public JobDetails details() {
        throw new NotImplementedException(
            String.format(
                "details() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Job URL.
     *
     * @return Job URL.
     * @todo: Let's implement this method and solve Issue #45.
     */
    public String url() {
        throw new NotImplementedException(
            String.format(
                "url() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Job builds.
     *
     * @return Builds.
     * @todo: Let's implement this method and solve Issue #44.
     */
    public Builds builds() {
        throw new NotImplementedException(
            String.format(
                "build() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Job parameters.
     *
     * @return Job's parameters.
     * @todo: Let's implement this method and solve Issue #43.
     */
    public List<JobParameter> parameters() {
        throw new NotImplementedException(
            String.format(
                "parameters() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Job's XML representation.
     *
     * @return XML's string.
     * @throws Exception If something goes wrong.
     */
    public String xml() throws Exception {
        return new PostRequest(this.request, this.creds.headers()).execute();
    }
}
