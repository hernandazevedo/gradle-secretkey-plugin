package com.hazevedo.impl

import com.hazevedo.ProcessKeysTaskExecutor

class ProcessKeysTaskExecutorImplTest extends GroovyTestCase {

    def fileName = "myfile.tmp${ProcessKeysTaskExecutor.TEMPLATE_EXTENSION}"
    def tempFile = File.createTempFile("temp", fileName)
    ProcessKeysTaskExecutor executor = new ProcessKeysTaskExecutorImpl(tempFile)

    def testOldValue = "testOldValue"
    def mockFileContent = """
                String anyVariable = ${testOldValue}
                """
    def testNewValue = "new values for the string"

    void testCreatesFileWithContent() {

        tempFile.text = mockFileContent
        String newContent = executor.fillContentWithKeys([testOldValue: testNewValue])

        assertTrue(tempFile.exists())
        assertEquals(mockFileContent.replaceAll(testOldValue, testNewValue), newContent)
    }

    void testgetFileNameWithoutTemplateExtension() {

        String newFileName = executor.getFileNameWithoutTemplateExtension()

        assertTrue(tempFile.exists())
        assertTrue(!newFileName.contains(ProcessKeysTaskExecutor.TEMPLATE_EXTENSION))
    }

    void testWriteDestinationFile() {

        def mockContent = "ANY CONTENT"
        File destinationFile = executor.writeDestinationFile(mockContent)

        assertTrue(destinationFile.exists())
        assertEquals(destinationFile.text, mockContent)
    }
}
