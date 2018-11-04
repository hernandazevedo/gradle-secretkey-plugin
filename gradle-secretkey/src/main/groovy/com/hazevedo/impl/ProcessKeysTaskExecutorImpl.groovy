package com.hazevedo.impl

import com.hazevedo.ProcessKeysTaskExecutor

import java.nio.charset.StandardCharsets

class ProcessKeysTaskExecutorImpl implements ProcessKeysTaskExecutor{

    ProcessKeysTaskExecutorImpl(File templateFileOriginal) {
        this.templateFileOriginal = templateFileOriginal
    }

    File templateFileOriginal

    String fillContentWithKeys(Map<String, String> keyMap) {
        String content = templateFileOriginal.getText(StandardCharsets.UTF_8.name())
        keyMap.each {
            content = content.replaceFirst(it.key, it.value)
        }
        content
    }

    String getFileNameWithoutTemplateExtension() {
        String myFileWithoutTemplateExtension = templateFileOriginal.getName().replaceAll(TEMPLATE_EXTENSION, "")
        myFileWithoutTemplateExtension
    }

    File writeDestinationFile(String content) {
        File destinationFile = new File(templateFileOriginal.getParent(),
                getFileNameWithoutTemplateExtension())
        destinationFile.createNewFile()
        destinationFile.text = content
        return destinationFile
    }
}
