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

import com.jcabi.xml.XMLDocument;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.NotImplementedException;
import org.rising.http.PostRequest;
import org.rising.jenkins.Credentials;
import org.rising.jenkins.Job;
import org.rising.jenkins.Jobs;

/**
 * Jenkins' jobs.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public final class RealJobs implements Jobs {

    /**
     * API URL.
     */
    private final transient String api;

    /**
     * Jobs' details request.
     */
    private final transient String request;

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
    public RealJobs(final String url, final Credentials credentials) {
        this.api = url;
        this.request = String.format(
            "%s%s", url, "&xpath=hudson/job&wrapper=jobs"
        );
        this.creds = credentials;
    }

    /**
     * List all Jenkins jobs.
     *
     * @return List of jobs.
     * @throws Exception If error occurred.
     */
    @SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
    public List<Job> list() throws Exception {
        final List<String> jobs = new XMLDocument(this.xml())
            .xpath("//job/displayName/text()");
        Collections.sort(jobs, String.CASE_INSENSITIVE_ORDER);
        final List<Job> result = new ArrayList<Job>(jobs.size());
        for (final String job : jobs) {
            result.add(new RealJob(job, this.api, this.creds));
        }
        return result;
    }

    /**
     * Find by Jenkins' job name.
     *
     * @param name Job's name.
     * @return Job.
     * @todo: Let's implement this method and solve Issue #31.
     */
    public Job findByName(final String name) {
        throw new NotImplementedException(
            String.format(
                "findByName() method is not implemented for %s.",
                this.getClass().getCanonicalName()
            )
        );
    }

    /**
     * Jobs' node XML content.
     *
     * @return XML string.
     * @throws Exception If something goes wrong.
     */
    public String xml() throws Exception {
        return new PostRequest(this.request, this.creds.headers()).execute();
    }
}
