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
package es.osoco.grails.plugins.concordion.extensions


import org.concordion.api.extension.ConcordionExtension
import org.concordion.api.extension.ConcordionExtensionFactory

import org.concordion.ext.ScreenshotExtension
import org.concordion.ext.ScreenshotTaker

import org.concordion.ext.selenium.SeleniumScreenshotTaker

import org.openqa.selenium.WebDriver


/**
 * This class implements the {@link org.concordion.api.extension.ConcordionExtensionFactory} 
 * interface to create a {@link org.concordion.api.extension.ScreenshotExtension} following 
 * the configuration values specified in the Grails application's Config.groovy.
 *
 * By default, this factory creates an extension that will add screenshots to the output 
 * whenever an assertion fails, or an uncaught Throwable occurs in the test.
 *
 * @author Rafael Luque
 */
public class ConfigurableScreenshotExtensionFactory extends ConfigurableExtensionFactory {


    private static final String CONFIGURATION_BLOCK_NAME = "screenshotExtensionFactoryConfiguration"


    private static Closure extensionConfig
    private static WebDriver webDriver


    private boolean screenshotOnAssertionFailure
    private boolean screenshotOnAssertionSuccess
    private boolean screenshotOnThrowable
    private int maxWidth
    private ScreenshotTaker screenshotTaker


    public static String getConfigurationBlockName() {
        CONFIGURATION_BLOCK_NAME
    }

    public static void setExtensionConfig(Closure extensionConfig) {
        this.extensionConfig = extensionConfig
    }


    public static void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver
    }


    @Override
    public ConcordionExtension build() {
        def extension = new ScreenshotExtension()
        [ 'screenshotOnAssertionFailure',
          'screenshotOnAssertionSuccess',
          'screenshotOnThrowable',
          'maxWidth',
          'screenshotTaker' ].each {

            property ->
            if (this."$property") {
                extension."$property" = this."$property"
            }

        }
	if (webDriver) {
	    extension.screenshotTaker = new SeleniumScreenshotTaker(webDriver)
	}
        extension
    }


    /** 
     * Sets whether screenshots will be embedded in the output when assertions fail. 
     * Defaults to <b><code>true</code></b>.
     * 
     * @param takeShot <code>true</code> to take screenshots on assertion failures, 
     * <code>false</code> to not take screenshots.
     */
    public void screenshotOnAssertionFailure(boolean takeShot) {
        screenshotOnAssertionFailure = takeShot
    }

    /** 
     * Sets whether screenshots will be embedded in the output when assertions pass. 
     * Defaults to <b><code>false</code></b>.
     *
     * @param takeShot <code>true</code> to take screenshots on assertion success, 
     * <code>false</code> to not take screenshots.
     */
    public void screenshotOnAssertionSuccess(boolean takeShot) {
        screenshotOnAssertionSuccess = takeShot
    }

    /** 
     * Sets whether screenshots will be embedded in the output when uncaught Throwables occur in the test. 
     * Defaults to <b><code>true</code></b>.
     *
     * @param takeShot <code>true</code> to take screenshots on uncaught Throwables, 
     * <code>false</code> to not take screenshots.
     */
    public void screenshotOnThrowable(boolean takeShot) {
        screenshotOnThrowable = takeShot
    }

    /**
     * Sets the maximum width that will be used for display of the images in the output.
     */
    public void maxWidth(int maxWidth) {
        this.maxWidth = maxWidth
    }

    /**
     * Set a custom screenshot taker. If not set, the extension will default to using {@link Robot}
     * which will take a shot of the full visible screen.
     * 
     * @param screenshotTaker
     */
    public void screenshotTaker(ScreenshotTaker screenshotTaker) {
        this.screenshotTaker = screenshotTaker
    }

}
