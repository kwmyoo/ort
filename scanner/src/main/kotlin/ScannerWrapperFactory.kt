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

package org.ossreviewtoolkit.scanner

import java.util.ServiceLoader

import org.ossreviewtoolkit.model.config.DownloaderConfiguration
import org.ossreviewtoolkit.model.config.ScannerConfiguration
import org.ossreviewtoolkit.utils.common.NamedPlugin

/**
 * A common interface for use with [ServiceLoader] that all [ScannerWrapperFactory] classes need to implement.
 */
interface ScannerWrapperFactory : NamedPlugin {
    /**
     * Create a [ScannerWrapper] using the specified [scannerConfig] and [downloaderConfig].
     */
    fun create(scannerConfig: ScannerConfiguration, downloaderConfig: DownloaderConfiguration): ScannerWrapper
}

/**
 * A generic factory class for a [ScannerWrapper].
 */
abstract class AbstractScannerWrapperFactory<out T : ScannerWrapper>(
    override val name: String
) : ScannerWrapperFactory {
    abstract override fun create(scannerConfig: ScannerConfiguration, downloaderConfig: DownloaderConfiguration): T

    /**
     * Return the scanner wrapper's name here to allow Clikt to display something meaningful when listing the scanners
     * which are enabled by default via their factories.
     */
    override fun toString() = name
}
