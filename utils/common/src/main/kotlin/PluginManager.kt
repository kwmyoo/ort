/*
 * Copyright (C) 2022 The ORT Project Authors (see <https://github.com/oss-review-toolkit/ort/blob/main/NOTICE>)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 * License-Filename: LICENSE
 */

package org.ossreviewtoolkit.utils.common

import java.util.ServiceLoader

/**
 * Return a [ServiceLoader] that is capable of loading services of type [T].
 */
inline fun <reified T : Any> getLoaderFor(): ServiceLoader<T> = ServiceLoader.load(T::class.java)

/**
 * An interface to be implemented by plugins that have a name.
 */
interface NamedPlugin {
    companion object {
        /**
         * Return instances for all named plugins of type [T].
         */
        inline fun <reified T : NamedPlugin> getAll() = getLoaderFor<T>()
            .iterator()
            .asSequence()
            .associateByTo(sortedMapOf(String.CASE_INSENSITIVE_ORDER)) {
                it.name
            }
    }

    /**
     * The name of the plugin.
     */
    val name: String
}
