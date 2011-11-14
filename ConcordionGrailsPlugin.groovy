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


/**
 * A plugin that integrates the Concordion acceptance testing framework into
 * a Grails application.
 *
 * @author Rafael Luque
 */
class ConcordionGrailsPlugin {
    // the plugin version
    def version = "0.1.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.3 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    def author = "OSOCO"
    def authorEmail = "rafael.luque@osoco.es"
    def title = "Grails Concordion Plugin"
    def description = "Grails Concordion Plugin"
    def documentation = "http://grails.org/plugin/concordion"
}
