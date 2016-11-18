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
package com.github.aistomin.jenkins.fake;

import com.github.aistomin.jenkins.Build;

/**
 * Fake Jenkins' job build's actions. By default we do nothing.
 *
 * @author Andrei Istomin (andrej.istomin.ikeen@gmail.com)
 * @version $Id$
 * @since 0.2.1
 */
public final class DefaultBuildActions implements FakeBuildActions {

    @Override
    public void delete(final Build build) {
        // do nothing
    }

    @Override
    public void cancel(final Build build) {
        // do nothing
    }

    @Override
    public void toggleLogKeep(final Build build) {
        // do nothing
    }
}
