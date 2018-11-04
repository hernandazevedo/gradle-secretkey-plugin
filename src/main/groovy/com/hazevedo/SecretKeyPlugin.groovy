package com.hazevedo

import org.gradle.api.Plugin
import org.gradle.api.Project

class SecretKeyPlugin implements Plugin<Project> {
	static final String SECRET_KEY_TASK_NAME = "processKeys"
	static final String SECRET_KEY_EXTENSION_NAME = "secretkey"
	static final String GROUP = "security"
	static final String DESCRIPTION = "Creates a file based on a template, replacing strings as configured for the plugin using a parameter map."

    void apply(Project project) {

		project.extensions.add(SECRET_KEY_EXTENSION_NAME, SecretKeyPluginExtension)

		project.task(SECRET_KEY_TASK_NAME, type: ProcessKeysTask) {
			group = SecretKeyPlugin.GROUP
			description = SecretKeyPlugin.DESCRIPTION
		}
    }
}
