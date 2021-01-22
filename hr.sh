#! /usr/bin/env bash
$mvn -B -s settings.xml -DskipTests=true clean package
java -jar target/dependency/webapp-runner.jar target/*.war
