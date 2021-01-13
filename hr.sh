#! /usr/bin/env bash
$mvn -B -s settings.xml -DskipTests=true clean package
$java $JAVA_OPTS -Dspring.profiles.active="postgres,heroku" -DRESTAURANT_ROOT="." -jar target/dependency/webapp-runner.jar --port $PORT target/*.war
