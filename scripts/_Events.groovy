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
    def extensions = buildConcordionExtensions()
    configureExtensions(extensions)
    defaultValues = 
        [(CONCORDION_OUTPUT_PROPERTY): defaultConcordionOutput(),
         (CONCORDION_EXTENSIONS_PROPERTY): extensions.join(',')]
    defaultValues.each {
        propertyName, defaultValue ->
        setSystemPropertyIfNotExist(propertyName, defaultValue)
    }
}

defaultConcordionOutput = {
    "${testReportsDir}/${DEFAULT_CONCORDION_REPORTS_DIR}".toString()
}

buildConcordionExtensions = {
    def extensions = []
    [ buildConfig.concordion.extensions,
      buildConfig.concordion.extensionFactories ].each {
        if (!it.isEmpty()) {
            extensions.addAll(it)
        }
    }
    if (!extensions) {
        extensions << DEFAULT_CONCORDION_EXTENSIONS
    }
    extensions
}

configureExtensions = {
    extensionClasses ->
    extensionClasses.each {
        def extensionClass = softLoadClass(it)
        def isConfigurableExtensionFactory = 
            (extensionClass.superclass.name == "es.osoco.grails.plugins.concordion.extensions.ConfigurableExtensionFactory")
        if (isConfigurableExtensionFactory) {
            def configurationBlockName = extensionClass.configurationBlockName
            if (buildConfig.concordion."${configurationBlockName}") {
                setExtensionConfiguration(extensionClass, buildConfig.concordion."${configurationBlockName}")
            }
        }
    }
}

setExtensionConfiguration = {
    extensionClass, configurationClosure ->
    def method = extensionClass.getMethod('setExtensionConfig', Closure.class)
    method.invoke(null, configurationClosure)    
}

setSystemPropertyIfNotExist = {
    property, value ->
    if (!System.getProperty(property)) {
        System.setProperty(property, value)
    }
}

