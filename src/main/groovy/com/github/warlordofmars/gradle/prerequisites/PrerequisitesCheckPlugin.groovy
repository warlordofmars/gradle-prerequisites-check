package com.github.warlordofmars.gradle.prerequisites

import org.gradle.api.Plugin
import org.gradle.api.Project

import java.io.ByteArrayOutputStream
import org.gradle.api.GradleException


class PrerequisitesCheckPlugin implements Plugin<Project> {

    void apply(Project project) {
        project.ext.prerequisites = [:]
        project.task('checkPrerequisites') {
            doLast {
                
                project.prerequisites.each { prerequisite, installNotes ->
                    def checkPrereq = project.exec {
                        commandLine 'which', prerequisite
                        ignoreExitValue true
                        standardOutput new ByteArrayOutputStream()
                    }
                    if (checkPrereq.getExitValue() != 0) {
                        throw new GradleException("It appears that prerequisite '${prerequisite}' is not installed.\n\n${installNotes}\n")
                    }

                }
            }
        }

    }

}