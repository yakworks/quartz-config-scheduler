/*
* Copyright 2019 Yak.Works - Licensed under the Apache License, Version 2.0 (the "License")
* You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
*/
package grails.plugin.quartzconfigscheduler

import groovy.transform.CompileDynamic

import org.quartz.JobDataMap
import org.quartz.JobExecutionContext
import org.quartz.Trigger

import grails.core.GrailsApplication

/**
 * @author - Sudhir Nimavat
 */
@CompileDynamic
@SuppressWarnings(['NoDef'])
class SpringBeanJob {
    GrailsApplication grailsApplication

    def execute(JobExecutionContext context) {
        JobDataMap jobDataMap = context.mergedJobDataMap
        String beanName = jobDataMap.get('bean')
        String method = jobDataMap.get("method")
        def arguments = jobDataMap.get("arguments")

        def bean = grailsApplication.mainContext.getBean(beanName)

        context.result = bean.invokeMethod(method, makeArgs(arguments))
    }

    Object[] makeArgs(def arguments) {
        if (!arguments) return new Object[0]
        if (arguments.class.isArray()) return arguments
        if (arguments instanceof Collection) return arguments.toArray()
        //its a single argument then
        def o = new Object[1]
        o[0] = arguments
        return o
    }

    public static schedule(Trigger trigger, String bean, String method, def arguments) {
        Map params = [bean: bean, method: method, arguments: arguments]
        schedule(trigger, params)
    }
}
