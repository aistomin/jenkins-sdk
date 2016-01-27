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
package org.rising.jenkins;

import java.util.List;

/**
 * Jenkins' users.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 1.0
 */
public interface Users extends APIObject {

    /**
     * List all Jenkins users.
     *
     * @return List of users.
     */
    List<User> list();

    /**
     * Find by Jenkins' user name.
     *
     * @param username Username(aka ID).
     * @return User.
     */
    User findByUsername(String username);

    /**
     * Find by Jenkins' user email.
     *
     * @param email Username(aka ID).
     * @return List of users who match the criteria.
     */
    List<User> findByEmail(String email);

    /**
     * Find by Jenkins' user full name.
     *
     * @param name Full name or part of it.
     * @return List of users who match the criteria.
     */
    List<User> findByFullName(String name);
}
