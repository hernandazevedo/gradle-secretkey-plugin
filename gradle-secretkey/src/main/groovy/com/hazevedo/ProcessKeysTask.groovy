package com.hazevedo

import com.hazevedo.impl.ProcessKeysTaskExecutorImpl
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class ProcessKeysTask extends DefaultTask {

    ProcessKeysTaskExecutor taskExecutor

    @TaskAction
    def action() {
        try {
            String templateFileName = project.extensions.secretkey.templateFile

            println("Generating secretKeys ${templateFileName}")
            taskExecutor = new ProcessKeysTaskExecutorImpl(new File(templateFileName))

            //Retrieves the template content filled with the new string values
            String content = taskExecutor.fillContentWithKeys(project.extensions.secretkey.keyMap)

            //Writes the new file without the .template extension
            taskExecutor.writeDestinationFile(content)
        } catch (Exception e) {
            //Simple exception handling, replace with what's necessary for your use case!
            throw new RuntimeException("Generating secretKeys failed", e)
        }
    }
}
