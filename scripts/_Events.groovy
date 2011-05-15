import org.codehaus.groovy.grails.test.support.GrailsTestMode

final String CONCORDION_OUTPUT_PROPERTY = "concordion.output.dir"
final String CONCORDION_EXTENSIONS_PROPERTY = "concordion.extensions"
final String DEFAULT_CONCORDION_REPORTS_DIR = "concordion"
final String DEFAULT_CONCORDION_EXTENSIONS = "org.concordion.ext.Extensions"

concordionTests = []

eventAllTestsStart = {
    phasesToRun << "concordion"
}

eventPackagePluginsEnd = {
    tryToLoadConcordionTestType()
}

concordionTestPhasePreparation = {
    setupConcordion()
    functionalTestPhasePreparation()
}

concordionTestPhaseCleanUp = {
    functionalTestPhaseCleanUp()
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

setupConcordion = {
    setupDefaultConcordionProperties()
}

setupDefaultConcordionProperties = {
    defaultValues = 
        [(CONCORDION_OUTPUT_PROPERTY): defaultConcordionOutput(),
         (CONCORDION_EXTENSIONS_PROPERTY): defaultConcordionExtensions()]
    defaultValues.each {
        propertyName, defaultValue ->
        setSystemPropertyIfNotExist(propertyName, defaultValue)
    }
}

defaultConcordionOutput = {
    "${testReportsDir}/${DEFAULT_CONCORDION_REPORTS_DIR}".toString()
}

defaultConcordionExtensions = {
    def extensionClassesNames = buildConfig.concordion.extensions
    def extensions = extensionClassesNames.isEmpty() ?
        DEFAULT_CONCORDION_EXTENSIONS : 
        extensionClassesNames.join(',')
}

setSystemPropertyIfNotExist = {
    property, value ->
    println "${property} -> ${value}"
    if (!System.getProperty(property)) {
        System.setProperty(property, value)
    }
}

