grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
//grails.project.war.file = "target/${appName}-${appVersion}.war"
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        grailsPlugins()
        grailsHome()
        grailsCentral()

        // uncomment the below to enable remote dependency resolution
        // from public Maven repositories
        //mavenLocal()
        //mavenCentral()
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        // runtime 'mysql:mysql-connector-java:5.1.13'

        // Concordion
	test('org.concordion:concordion:1.4.1') {
	    exclude 'xml-apis'
	}

        // Concordion Extensions
	test('org.concordion:concordion-extensions:1.0.2')

        // required for Concordion Selenium Extensions
	test("org.seleniumhq.selenium:selenium-common:2.0a7")
	test("org.seleniumhq.selenium:selenium-support:2.0a7")

    }
}

// concordion.extensions = [ org.concordion.ext.TimestampFormatterExtension ]
// concordion.extensionFactories = [ es.osoco.grails.plugins.concordion.ConfigurableScreenshotExtensionFactory ]
// concordion.screenshotExtensionFactoryConfiguration = {
//         screenshotOnThrowable true
//         screenshotOnAssertionFailure true
//         screenshotOnAssertionSuccess false
//         maxWidth 400
//     }
// concordion.loggingExtensionConfiguration = {
//     loggerNames []
//     level Level.INFO
//     displayOnConsole false
// }
