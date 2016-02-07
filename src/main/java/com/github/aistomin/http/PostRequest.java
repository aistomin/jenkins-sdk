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
package com.github.aistomin.http;

import java.util.Map;
import org.apache.http.client.fluent.Request;

/**
 * HTTP POST Request.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class PostRequest implements HTTPRequest {

    /**
     * Request's URL.
     */
    private final transient String resource;

    /**
     * Request's headers.
     */
    private final transient Map<String, String> heads;

    /**
     * Ctor.
     *
     * @param url Request's URL.
     * @param headers Request's headers.
     */
    public PostRequest(final String url, final Map<String, String> headers) {
        this.resource = url;
        this.heads = headers;
    }

    /**
     * Execute request.
     *
     * @return Request's string result.
     * @throws Exception If request failed.
     */
    public String execute() throws Exception {
        final Request request = Request.Post(this.resource);
        for (final Map.Entry<String, String> item : this.heads.entrySet()) {
            request.addHeader(item.getKey(), item.getValue());
        }
        return request.execute().returnContent().asString();
    }
}
