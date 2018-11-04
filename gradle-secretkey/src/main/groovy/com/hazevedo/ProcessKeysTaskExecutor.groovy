package com.hazevedo

interface ProcessKeysTaskExecutor {
    static final String TEMPLATE_EXTENSION = ".template"
    String fillContentWithKeys(Map<String, String> keyMap)
    File writeDestinationFile(String content)
    String getFileNameWithoutTemplateExtension()
}