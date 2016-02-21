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
package com.github.aistomin.jenkins;

/**
 * Jenkins' user.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface User extends ApiObject {

    /**
     * Username. This name is ID of user that can not be changed.
     *
     * @return Username.
     * @throws Exception If error occurred.
     */
    String username() throws Exception;

    /**
     * User's full name.
     *
     * @return User's full name.
     * @throws Exception If error occurred.
     */
    String fullName() throws Exception;

    /**
     * User's email.
     *
     * @return User's email.
     * @throws Exception If error occurred.
     */
    String email() throws Exception;

    /**
     * User's Jenkins page URL.
     *
     * @return URL string.
     * @throws Exception If error occurred.
     */
    String url() throws Exception;

    /**
     * User's description.
     *
     * @return Description string.
     * @throws Exception If error occurred.
     */
    String description() throws Exception;
}
