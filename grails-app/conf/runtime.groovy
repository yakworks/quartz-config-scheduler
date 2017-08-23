import grails.plugin.quartzconfigschedular.ClosureJob
import org.quartz.impl.triggers.SimpleTriggerImpl

grails.plugin.quartz.autoStartup = true

grails.plugin.quartz.jobSetup.job1 = {quartzScheduler, ctx ->
	println "Scheduling job"
	def trigger1 = new SimpleTriggerImpl(name:"trig1", startTime:new Date(),repeatInterval:1000, repeatCount:-1)
	ClosureJob.schedule(trigger1, [:]) {
		println "Closure job executed at : " + System.currentTimeMillis()
	}

}