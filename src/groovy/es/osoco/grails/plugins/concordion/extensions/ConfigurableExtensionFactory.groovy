/*
 * ======================================================================
 *
 *    _ \   __|  _ \   __|  _ \ 
 *   (   |\__ \ (   | (    (   |    
 *  \___/ ____/\___/ \___|\___/ 
 *
 * Copyright (c) 2011 OSOCO. http://www.osoco.
 *
 * ======================================================================
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package es.osoco.grails.plugins.concordion.extensions


import org.concordion.api.extension.ConcordionExtension;
import org.concordion.api.extension.ConcordionExtensionFactory


/**
 * Creates an extension for use within Concordion. This factory mechanism is
 * typically only used for extensions that require customisation.
 *
 * @author Rafael Luque
 */
public abstract class ConfigurableExtensionFactory implements ConcordionExtensionFactory {


    public abstract ConcordionExtension build()


    @Override
    public ConcordionExtension createExtension() {
        if (extensionConfig) {
            extensionConfig.delegate = this
            extensionConfig()
        }
        build()
    }


}
