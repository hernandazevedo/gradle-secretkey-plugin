package com.hazevedo
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder

class SecretKeyPluginTest extends GroovyTestCase {
    void testHasExtension() {
        Project project = ProjectBuilder.builder().withName("hello-world").build()
        project.pluginManager.apply SecretKeyPlugin

        def secretKeyExtension = project.extensions.getByName(SecretKeyPlugin.SECRET_KEY_EXTENSION_NAME)
        assertTrue(secretKeyExtension instanceof SecretKeyPluginExtension)
    }

    void testHasProcessKeysTask() {
        Project project = ProjectBuilder.builder().withName("hello-world2").build()
        project.pluginManager.apply SecretKeyPlugin

        assertNotNull(project.tasks.getByName(SecretKeyPlugin.SECRET_KEY_TASK_NAME))
        assertTrue(project.tasks.getByName(SecretKeyPlugin.SECRET_KEY_TASK_NAME) instanceof ProcessKeysTask)
    }
}
