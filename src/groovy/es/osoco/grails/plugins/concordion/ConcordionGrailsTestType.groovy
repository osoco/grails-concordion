package es.osoco.grails.plugins.concordion


import es.osoco.grails.plugins.concordion.runner.ConcordionGrailsRunnerBuilder

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

}
