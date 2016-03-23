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
import org.apache.http.HttpResponse;
import org.apache.http.client.ServiceUnavailableRetryStrategy;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.protocol.HttpContext;

/**
 * HTTP POST Request.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class PostRequest implements HttpRequest {

    /**
     * Retries count on HTTP error.
     */
    public static final Integer RETRY_COUNT = 20;

    /**
     * Retries interval in milliseconds on HTTP error.
     */
    public static final Integer RETRY_INTERVAL = 100;

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
        final HttpClientBuilder builder = HttpClientBuilder.create();
        builder.setRedirectStrategy(new LaxRedirectStrategy());
        builder.setServiceUnavailableRetryStrategy(
            new ServiceUnavailableRetryStrategy() {
                @Override
                public boolean retryRequest(
                    final HttpResponse response, final int count,
                    final HttpContext context
                ) {
                    return count <= PostRequest.RETRY_COUNT;
                }

                @Override
                public long getRetryInterval() {
                    return PostRequest.RETRY_INTERVAL;
                }
            }
        );
        return Executor.newInstance(builder.build()).execute(request)
            .returnContent().asString();
    }
}
