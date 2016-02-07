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

import com.github.aistomin.jenkins.Credentials;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.codec.binary.Base64;

/**
 * Jenkins username/password credentials.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class UsernamePasswordCredentials implements Credentials {

    /**
     * Username.
     */
    private final transient String user;

    /**
     * Password.
     */
    private final transient String pass;

    /**
     * Ctor.
     * @param username Username.
     * @param password Password.
     */
    public UsernamePasswordCredentials(
        final String username, final String password
    ) {
        this.user = username;
        this.pass = password;
    }

    /**
     * Build POST request security parameters.
     * @return Parameters' map.
     * @throws Exception If something goes wrong.
     */
    public Map<String, String> headers() throws Exception {
        final Map<String, String> map = new ConcurrentHashMap<String, String>();
        map.put(
            "Authorization",
            String.format(
                "Basic %s",
                Base64.encodeBase64String(
                    String.format("%s:%s", this.user, this.pass)
                        .getBytes("UTF-8")
                )
            )
        );
        return Collections.unmodifiableMap(map);
    }
}
