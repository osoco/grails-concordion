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
package es.osoco.grails.plugins.concordion


import es.osoco.grails.plugins.concordion.runner.ConcordionGrailsRunnerBuilder

import org.codehaus.groovy.grails.plugins.GrailsPluginUtils

import org.codehaus.groovy.grails.test.junit4.JUnit4GrailsTestType
import org.codehaus.groovy.grails.test.junit4.listener.SuiteRunListener
import org.codehaus.groovy.grails.test.junit4.result.JUnit4ResultGrailsTestTypeResultAdapter

import org.codehaus.groovy.grails.test.GrailsTestTypeResult
import org.codehaus.groovy.grails.test.GrailsTestTargetPattern
import org.codehaus.groovy.grails.test.support.GrailsTestTypeSupport
import org.codehaus.groovy.grails.test.support.GrailsTestMode
import org.codehaus.groovy.grails.test.event.GrailsTestEventPublisher
import org.codehaus.groovy.grails.test.report.junit.JUnitReportsFactory

import org.junit.runners.Suite
import org.junit.runner.Result
import org.junit.runner.notification.RunNotifier

import java.lang.reflect.Modifier


/**
 * An {@code GrailsTestType} implementation for Concordion specifications.
 */
class ConcordionGrailsTestType extends JUnit4GrailsTestType {

    private ClassLoader testClassLoader

    ConcordionGrailsTestType(String name, String sourceDirectory) {
        super(name, sourceDirectory)
    }

    ConcordionGrailsTestType(String name, String sourceDirectory, GrailsTestMode mode) {
        super(name, sourceDirectory, mode)
    }

    @Override
    protected createRunnerBuilder() {
        if (mode) {
            new ConcordionGrailsRunnerBuilder(mode, getApplicationContext(), testTargetPatterns)
        }
        else {
            new ConcordionGrailsRunnerBuilder(testTargetPatterns)
        }
    }

    @Override
    protected ClassLoader getTestClassLoader() {
        if (!testClassLoader) {
            def classPathAdditions = [getSourceDir()]
            if (compiledClassesDir) {
                classPathAdditions << compiledClassesDir
            }
            classPathAdditions << buildBinding.pluginClassesDir
            testClassLoader = new URLClassLoader(classPathAdditions*.toURI()*.toURL() as URL[], buildBinding.classLoader)
        }
        testClassLoader
    }


}
