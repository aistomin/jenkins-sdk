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

/**
 * Jenkins' job build details like: display name, url, duration etc.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 1.0
 * @todo: Let's create implementation of this interface and solve Issue #120.
 */
public interface BuildDetails extends APIObject {

    /**
     * Build's full display name.
     *
     * @return Full display name.
     */
    String fullDisplayName();

    /**
     * Build's display name.
     *
     * @return Display name.
     */
    String displayName();

    /**
     * Build's estimated duration in milliseconds.
     *
     * @return Build's estimated duration
     */
    Long estimated();

    /**
     * Build's duration in milliseconds.
     *
     * @return Build's duration
     */
    Long duration();

    /**
     * Is build in process?
     *
     * @return Is build in process?
     */
    Boolean building();

    /**
     * Build's queue ID.
     *
     * @return Queue ID.
     */
    Long queue();
}
