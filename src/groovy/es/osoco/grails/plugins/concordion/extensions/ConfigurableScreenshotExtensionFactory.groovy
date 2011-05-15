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


import java.awt.Robot

import org.concordion.api.extension.ConcordionExtension;
import org.concordion.api.extension.ConcordionExtensionFactory
import org.concordion.ext.ScreenshotExtension

import org.openqa.selenium.WebDriver


/**
 * This class implements the {@link org.concordion.api.extension.ConcordionExtensionFactory} 
 * interface to create a {@link org.concordion.api.extension.ScreenshotExtension} following 
 * the configuration values specified in the Grails application's Config.groovy.
 *
 * By default, this factory creates an extension that will add screenshots to the output 
 * whenever an assertion fails, or an uncaught Throwable occurs in the test.
 */
public class ConfigurableScreenshotExtensionFactory implements ConcordionExtensionFactory {


    private static WebDriver driver;


    public static void setDriver(WebDriver driver) {
        this.driver = driver
    }


    @Override
    public ConcordionExtension createExtension() {
        ScreenshotExtension extension = new ScreenshotExtension()
        extension.setMaxWidth(400)
        extension.setScreenshotOnAssertionFailure()
        extension.setScreenshotOnAssertionSuccess()
        extension.setScreenshotOnThrowable()
        extension.setScreenshotTaker(screenshotTaker)            
        extension
    }


}
