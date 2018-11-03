# gradle-secretkey-plugin
The gradle-secretkey-plugin is a plugin to create a file based on a template, replacing strings as configured for the plugin using a parameter map.

## Getting Started
For the example we want to create a file in build time, with the value of some strings as parameter, one of them comes from an enviroment variable.

## 1 You  need to define the template file with extension ".template" on you code like this:
```
---------------------- teste.h.template ---------------

String anyVariable = "TEST_PRIVATE_KEY_REPLACE"
String anyOtherVariable = "TEST_PRIVATE_KEY_REPLACE2"

---------------------- teste.h.template ---------------
```
## 2 Apply the plugin on gradle:
```
apply plugin: 'gradle-secretkey'
```

## 3 Configure the plugin by initializing the variables like: 
```
secretkey {
    String privateKeyFromEnv = System.env.PRIVATE_KEY2 != null ? System.env.PRIVATE_KEY2 : "default value"
    templateFile = "${project.projectDir}/src/main/cpp/teste.h.template"
    keyMap = [TEST_PRIVATE_KEY_REPLACE: "11111", TEST_PRIVATE_KEY_REPLACE2: privateKeyFromEnv]
}
```

## 4 Make the task processKeys run before the task you need. Example: 
```
//In this configuration, the keys will be  run before gradle start the build process for the project
project.tasks.preBuild.dependsOn project.tasks.processKeys
```
See the example here: https://github.com/hernandazevedo/gradle-secretkey-plugin-demo

License
This project is licensed under the MIT License - see the LICENSE file for details
