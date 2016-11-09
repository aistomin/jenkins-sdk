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
import com.github.aistomin.iterators.Transformation;
import com.github.aistomin.jenkins.Build;
import com.github.aistomin.jenkins.BuildDetails;
import com.github.aistomin.jenkins.BuildResult;
import com.github.aistomin.jenkins.Credentials;
import com.github.aistomin.xml.XmlString;
import java.net.URLEncoder;
import java.util.Date;

/**
 * Jenkins' job build.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class RealBuild implements Build {

    /**
     * API URL.
     */
    private final transient String api;

    /**
     * Jenkins credentials.
     */
    private final transient Credentials creds;

    /**
     * Build's identifier.
     */
    private final transient String identifier;

    /**
     * Ctor.
     * @param number Build's number.
     * @param url API URL.
     * @param credentials Jenkins credentials.
     */
    public RealBuild(
        final String number, final String url, final Credentials credentials
    ) {
        this.identifier = number;
        this.api = url;
        this.creds = credentials;
    }

    @Override
    public String number() throws Exception {
        return this.identifier;
    }

    @Override
    public BuildResult result() throws Exception {
        return BuildResult.valueOf(
            new XmlString(this.xml()).field("//build/result/text()")
        );
    }

    @Override
    public Date date() throws Exception {
        return new Date(
            Long.parseLong(
                new XmlString(this.xml()).field("//build/timestamp/text()")
            )
        );
    }

    @Override
    public String url() throws Exception {
        return new XmlString(this.xml()).field("//build/url/text()");
    }

    @Override
    public BuildDetails details() throws Exception {
        return new RealBuildDetails(new XmlString(this.xml()));
    }

    @Override
    public void delete() throws Exception {
        new PostRequest(
            String.format("%sdoDelete", this.url()), this.creds.headers()
        ).execute();
    }

    @Override
    public void cancel() throws Exception {
        new PostRequest(
            String.format("%sstop", this.url()), this.creds.headers()
        ).execute();
    }

    @Override
    public void toggleLogKeep() throws Exception {
        new PostRequest(
            String.format("%stoggleLogKeep", this.url()), this.creds.headers()
        ).execute();
    }

    @Override
    public String xml() throws Exception {
        return new PostRequest(
            String.format(
                "%s%s", this.api,
                String.format(
                    "/build[displayName=%s]",
                    URLEncoder.encode(
                        String.format("'%s'", this.identifier), "UTF-8"
                    )
                )
            ), this.creds.headers()
        ).execute();
    }

    /**
     * Transformer for getting build by it's name.
     */
    public static final class Transformer implements
        Transformation<Build, String> {

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

        @Override
        public Build transform(final String source) {
            return new RealBuild(source, this.api, this.creds);
        }
    }
}
