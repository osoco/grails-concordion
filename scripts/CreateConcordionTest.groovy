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

import grails.util.GrailsNameUtils


/**
 * Gant script that generates a new general-purpose Concordion test.
 *
 * @author Rafael Luque
 */

includeTargets << grailsScript("_GrailsInit")
includeTargets << grailsScript("_PluginDependencies")
includeTargets << grailsScript("_GrailsCreateArtifacts")

target(main: "Creates a skeleton of a general-purpose Concordion test with the given name") {
    depends(checkVersion, parseArguments)

    promptForName(type: "Concordion Test")
    def name = argsMap["params"][0]
    def web = argsMap["web"]
    def superClass = argsMap["superClass"]
    def artifactPath = "test/concordion"
    def testArtifactType = web ? "ConcordionWebTest" : "ConcordionTest"
    def specArtifactType = web ? "ConcordionWebSpec" : "ConcordionSpec"
    createArtifact(name: name, suffix: "Test", type: testArtifactType, path: artifactPath, superClass: superClass)
    createArtifact(name: name, suffix: "", type: specArtifactType, path: artifactPath)
    def pkg = null
    def pos = name.lastIndexOf('.')
    if (pos != -1) {
        pkg = name[0..<pos]
        name = name[(pos + 1)..-1]
        if (pkg.startsWith("~")) {
            pkg = pkg.replace("~", createRootPackage())
        }
    }
    else {
        pkg = skipPackagePrompt ? '' : createRootPackage()
    }

    // Convert the package into a file path.
    def pkgPath = pkg.replace('.' as char, '/' as char)

    // Future use of 'pkgPath' requires a trailing slash.
    pkgPath += '/'

    def specArtifactFile = renameSpecArtifact(name, artifactPath, pkgPath)

    ant.replace(file: specArtifactFile,
        token: "@artifact.capitalizedName@", value: toCapitalize(className))
}

renameSpecArtifact = { name, artifactPath, pkgPath ->
    // Convert the given name into class name and property name representations.
    className = GrailsNameUtils.getClassNameRepresentation(name)
    println "className $className"

    def specArtifactPath =  "${basedir}/${artifactPath}/${pkgPath}${className}.groovy"
    def newSpecArtifactPath = "${basedir}/${artifactPath}/${pkgPath}${className}.html"
    ant.move(file: specArtifactPath, tofile: newSpecArtifactPath)

    newSpecArtifactPath
}

toCapitalize = { name ->
    def capitalized = []
    name.eachWithIndex { letter, idx -> 
        if (idx && (letter ==~ /[A-Z]/)) {
	    capitalized << " "
	}
	capitalized << letter
    }
    capitalized.join()
}

setDefaultTarget(main)
