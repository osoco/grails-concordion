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
package es.osoco.grails.plugins.concordion.runner


import org.junit.runner.Runner



import org.codehaus.groovy.grails.test.GrailsTestTargetPattern
import org.codehaus.groovy.grails.test.junit4.runner.GrailsTestCaseRunnerBuilder
import org.codehaus.groovy.grails.test.support.GrailsTestMode
import org.springframework.context.ApplicationContext


class ConcordionGrailsRunnerBuilder extends GrailsTestCaseRunnerBuilder {

    ConcordionGrailsRunnerBuilder(GrailsTestTargetPattern[] testTargetPatterns) {
        super(testTargetPatterns)
    }

    ConcordionGrailsRunnerBuilder(GrailsTestMode mode, ApplicationContext appCtx, GrailsTestTargetPattern[] testTargetPatterns) {
        super(mode, appCtx, testTargetPatterns)
    }

    @Override
    Runner runnerForClass(Class testClass) {
        if (mode) {
            new ConcordionGrailsRunner(testClass, mode, appCtx, *testTargetPatterns)
        } else {
            new ConcordionGrailsRunner(testClass, *testTargetPatterns)
        }
    }

}