[![Codacy Badge](https://app.codacy.com/project/badge/Grade/6b374ef8a3674ad8901e23b4a54b4ff3)](https://www.codacy.com/gh/Jenia-tyt/graduation_project_topjava/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Jenia-tyt/graduation_project_topjava&amp;utm_campaign=Badge_Grade)

Hi, this is my graduation project from the course TopJava.

There are two types of users:

User;

Admin;

User votes fo the restaurant's menu where he will diner.

Admin updates a restaurant's menu every day.

Technology stack:

Spring framework;
Spring data;
Spring MVC;
Spring security;
Maven;
Git;
JPA(Hibernate);
Postgresql;
Junit 5.

COMMON USER:

Get all menus which includes the Restaurant and today's meals:
curl --location --request GET 'http://localhost:8080/Restaurant/rest/profile/menuToDay' \
--header 'Authorization:Basic dXNlckBtYWlsLnJ1OnBhc3N3b3JkVXNlcg'

Get all menus restaurant with id = 1 (Restaurant's id  have numbers since 1 - 5): 
curl --location --request GET 'http://localhost:8080/Restaurant/rest/profile/menuToDay/restaurant/1' \
--header 'Authorization:Basic dXNlckBtYWlsLnJ1OnBhc3N3b3JkVXNlcg'

You vote for menu with id = 13 (Menus with date to day have id = 13, 14).
If you write this command second time, we will get exception (this user voted already) on condition what time is after 11:00 a.m. 
curl --location --request PUT 'http://localhost:8080/Restaurant/rest/profile/menuToDay/vote/13' \
--header 'Authorization:Basic YWRtaW5fdXNlckBnbWFpbC5jb206cGFzc3dvcmRBVQ'

