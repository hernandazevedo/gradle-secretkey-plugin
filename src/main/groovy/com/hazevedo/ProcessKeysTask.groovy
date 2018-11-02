package com.hazevedo

import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class ProcessKeysTask extends DefaultTask {

    @TaskAction
    def action() {

        try {
            String templatefile = project.extensions.secretkey.templatefile
            String keyname = project.extensions.secretkey.keyname
            String keyvalue = project.extensions.secretkey.keyvalue

            println("Generating secretkey file template=${templatefile} keyname=${keyname} keyvalue=${keyvalue}")
            File template = new File(templatefile)

            String baseUrl = template.getParent()
            String filename = template.getName()
            println("baseUrl ${baseUrl}")
            println("filename ${filename}")
            String myFileWithoutTemplateExtension = filename.replaceAll(".template","")

            println("myFileWithoutTemplateExtension ${myFileWithoutTemplateExtension}")
            println("baseUrl ${baseUrl}")



            String content = FileUtils.readFileToString(template, "UTF-8")
            content = content.replaceAll(keyname, keyvalue)
            File tempFile = new File(baseUrl, myFileWithoutTemplateExtension)
            FileUtils.writeStringToFile(tempFile, content, "UTF-8")
        } catch (IOException e) {
            //Simple exception handling, replace with what's necessary for your use case!
            throw new RuntimeException("Generating file failed", e)
        }
//        outputFile.parentFile.mkdirs()
//        outputFile.createNewFile()
//        outputFile.text = project.extensions.myplugin.fileContent
    }
}
