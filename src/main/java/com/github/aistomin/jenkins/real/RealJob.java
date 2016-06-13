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
import com.github.aistomin.iterators.EntityIterator;
import com.github.aistomin.iterators.Transformation;
import com.github.aistomin.jenkins.Builds;
import com.github.aistomin.jenkins.Credentials;
import com.github.aistomin.jenkins.Job;
import com.github.aistomin.jenkins.JobDetails;
import com.github.aistomin.jenkins.JobParameter;
import com.github.aistomin.xml.XmlString;
import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.lang3.NotImplementedException;

/**
 * Jenkins' job.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 * @checkstyle ClassDataAbstractionCouplingCheck (1000 lines)
 * @todo: Let's re-design this class and solve issue #285
 */
@SuppressWarnings("PMD.TooManyMethods")
public final class RealJob implements Job {

    /**
     * API URL.
     */
    private final transient String api;

    /**
     * Job's name.
     */
    private final transient String identifier;

    /**
     * Jenkins credentials.
     */
    private final transient Credentials creds;

    /**
     * Ctor.
     *
     * @param name Job's name.
     * @param url API URL.
     * @param credentials Jenkins credentials.
     */
    public RealJob(
        final String name, final String url, final Credentials credentials
    ) {
        this.identifier = name;
        this.api = url;
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
     * @throws Exception If something goes wrong.
     */
    public JobDetails details() throws Exception {
        return new RealJobDetails(new XmlString(this.xml()));
    }

    /**
     * Job URL.
     *
     * @return Job URL.
     * @throws Exception If something goes wrong.
     */
    public String url() throws Exception {
        return new XmlString(this.xml()).field("//job/url/text()");
    }

    /**
     * Job builds.
     *
     * @return Builds.
     * @throws Exception If reading builds is not successful.
     */
    public Builds builds() throws Exception {
        return new RealBuilds(this.request(), this.creds);
    }

    /**
     * Job parameters.
     *
     * @return Job's parameters.
     * @throws Exception If something goes wrong.
     */
    public Iterator<JobParameter> parameters() throws Exception {
        return new EntityIterator<>(
            new XMLDocument(
                new PostRequest(
                    String.format(
                        "%s/%s", this.request(),
                        "action/parameterDefinition&wrapper=parameters"
                    ), this.creds.headers()
                ).execute()
            ).nodes("/parameters/*").iterator(), new ParamTransformer()
        );
    }

    /**
     * Trigger new job's build.
     *
     * @throws Exception If something goes wrong.
     */
    public void trigger() throws Exception {
        new PostRequest(
            String.format("%s/build", this.url()), this.creds.headers()
        ).execute();
    }

    /**
     * Trigger new job's build.
     *
     * @param params Build parameters map.
     * @throws Exception If something goes wrong.
     * @todo: Let's implement this method and solve issue #271.
     */
    public void trigger(final Map<String, String> params) throws Exception {
        throw new NotImplementedException(
            String.format(
                "%s.trigger() is not implemented.", this.getClass()
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
        return new PostRequest(this.request(), this.creds.headers()).execute();
    }

    /**
     * Creates API URL to request Jenkins' job data.
     *
     * @return URL string.
     * @throws Exception If error occurred.
     */
    private String request() throws Exception {
        return String.format(
            "%s%s", this.api,
            String.format(
                "&xpath=hudson/job[displayName=%s]",
                URLEncoder.encode(
                    String.format("'%s'", this.identifier), "UTF-8"
                )
            )
        );
    }

    /**
     * Transformer for getting job by his id name.
     */
    public static final class Transformer implements
        Transformation<Job, String> {

        /**
         * API URL.
         */
        private final transient String api;

        /**
         * Jenkins credentials.
         */
        private final transient Credentials creds;

        /**
         * Ctor.
         * @param url API URL.
         * @param credentials Jenkins credentials.
         */
        public Transformer(final String url, final Credentials credentials) {
            this.api = url;
            this.creds = credentials;
        }

        /**
         * Transform username to job.
         *
         * @param source Source object.
         * @return Job.
         */
        public Job transform(final String source) {
            return new RealJob(source, this.api, this.creds);
        }
    }

    /**
     * JobParameter transformer.
     */
    private static final class ParamTransformer implements
        Transformation<JobParameter, XML> {

        /**
         * Transform XML to JobParameter.
         *
         * @param source Source object.
         * @return JobParameter.
         * @checkstyle NonStaticMethodCheck (500 lines)
         */
        public JobParameter transform(final XML source) {
            return new RealJobParameter(
                new XmlString(source.toString())
            );
        }
    }
}
