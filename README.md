# gradle-prerequisites-check

[![latest jitpack release](https://jitpack.io/v/warlordofmars/gradle-prerequisites-check.svg)](https://jitpack.io/#warlordofmars/gradle-prerequisites-check)

## Overview

Simple Gradle Plugin to provide a task that can check the build system for any number of custom prerequisites that must be in place on the build system in order for a Gradle build to succeed.

## Setup

To use this plugin, the following buildscript repositories and dependencies must be configured:

```gradle
buildscript {
  repositories {
    maven { url 'https://jitpack.io' }
  }
  dependencies {
    classpath 'com.github.warlordofmars:gradle-prerequisites-check:release-0.1.4'
  }
}
```

Then to apply the plugin:

```gradle
apply plugin: 'com.github.warlordofmars.gradle.prerequisites'
```

To configure prerequisites to check for (this should be a mapping of command to check PATH for to a message to be displayed as part of error instructing user on how to obtain prerequisite):

```gradle
ext.prerequisites << [
    'aws': "Install via 'brew install awscli'"
]
```

Then in your custom gradle task, depend on the task `checkPrerequisites`:

```gradle
task('customAwsTask') {
    dependsOn checkPrerequisites
    exec {
        commandLine 'aws', 'ec2', 'describe-images'
    }
}
```

## Versioning

Versioning on this project is applied automatically on all changes using the [axion-release-plugin](https://github.com/allegro/axion-release-plugin).  Git tags are created for all released versions, and all available released versions can be viewed in the [Releases](https://github.com/warlordofmars/gradle-prerequisites-check/releases) section of this project.

## Author

* **John Carter** - [warlordofmars](https://github.com/warlordofmars)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
