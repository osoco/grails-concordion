import es.osoco.grails.plugins.concordion.ConcordionGrailsTestType
import org.codehaus.groovy.grails.test.support.GrailsTestMode


eventAllTestsStart = {
    phasesToRun << "concordion"
    tryToLoadConcordionTestType()
}

def mode = new GrailsTestMode(autowire: true)
def concordionTestType = new ConcordionGrailsTestType('concordion', 'concordion', mode)
concordionTests = [ concordionTestType ]

eventPackagePluginsStart = { pluginName ->
    tryToLoadConcordionTestType()
}

tryToLoadConcordionTestType = {
    def testMode = new GrailsTestMode(autowire: true)
    tryToLoadTestType(
        'concordion', 
        'cocordion', 
        'es.osoco.grails.plugins.concordion.ConcordionGrailsTestType',
        testMode)
}

tryToLoadTestType = { name, directory, typeClassName, testMode ->	
    def typeClass = softLoadClass(typeClassName)
    if (typeClass) {
        concordionTests << typeClass.newInstance(name, directory, testMode)
    }
}

softLoadClass = { className ->
    try {
        classLoader.loadClass(className)
    } catch (ClassNotFoundException e) {
        null
    }
}

concordionTestPhasePreparation = {
    functionalTestPhasePreparation()
}
concordionTestPhaseCleanUp = {
    functionalTestPhaseCleanUp()
}
