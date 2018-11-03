package com.hazevedo

import org.apache.commons.io.FileUtils
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class ProcessKeysTask extends DefaultTask {

    @TaskAction
    def action() {
        try {
            String templatefile = project.extensions.secretkey.templatefile
            def keyMap = project.extensions.secretkey.keyMap as Map<String, String>
            println("Generating secretkey file template=${templatefile} ")
            File template = new File(templatefile)
            String myFileWithoutTemplateExtension = template.getName().replaceAll(".template","")
            String content = FileUtils.readFileToString(template, "UTF-8")
            keyMap.each {
                content = content.replaceFirst(it.key, it.value)
            }
            File destinationFile = new File(template.getParent(), myFileWithoutTemplateExtension)
            FileUtils.writeStringToFile(destinationFile, content, "UTF-8")
        } catch (IOException e) {
            //Simple exception handling, replace with what's necessary for your use case!
            throw new RuntimeException("Generating file failed", e)
        }
    }
}
