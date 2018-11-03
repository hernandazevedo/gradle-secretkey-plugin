package com.hazevedo

import org.gradle.api.Plugin
import org.gradle.api.Project

class SecretKeyPlugin implements Plugin<Project> {
    void apply(Project project) {

		project.extensions.add("secretkey", SecretKeyPluginExtension)

		// The "right" way
		project.task("processKeys", type: ProcessKeysTask) {
			group = "SecretKeyPlugin"
			description = "Create myfile.txt in the build directory"
		}
    }
}
