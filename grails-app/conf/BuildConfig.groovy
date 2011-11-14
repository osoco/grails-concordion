grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.dependency.resolution = {
    inherits("global") {
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        grailsPlugins()
        grailsHome()
        grailsCentral()

        mavenLocal()
        mavenCentral()
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.
        // Concordion
        test('org.concordion:concordion:1.4.1') {
            exclude 'xml-apis'
        }

        // Concordion Extensions
        test('org.concordion:concordion-extensions:1.0.2')

        // required for Concordion Selenium Extensions
        test("org.seleniumhq.selenium:selenium-api:2.12.0")
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
