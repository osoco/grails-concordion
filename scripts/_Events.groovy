import org.codehaus.groovy.grails.test.support.GrailsTestMode

concordionTests = []

eventAllTestsStart = {
    phasesToRun << "concordion"
}

eventPackagePluginsEnd = {
    tryToLoadConcordionTestType()
}

tryToLoadConcordionTestType = {
    def testMode = new GrailsTestMode(autowire: true)
    tryToLoadTestType(
        'concordion', 
        'concordion', 
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
