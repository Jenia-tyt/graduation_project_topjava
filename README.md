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

These commands for  REST app

COMMANS USER: \
Get all menus which includes the Restaurant and today's meals: \
curl --location --request GET 'http://localhost:8080/Restaurant/rest/profile/menuToDay' \
--header 'Authorization:Basic dXNlckBtYWlsLnJ1OnBhc3N3b3JkVXNlcg'

Get all menus restaurant with id = 1 (Restaurant's id  have numbers since 1 - 5): \
curl --location --request GET 'http://localhost:8080/Restaurant/rest/profile/menuToDay/restaurant/1' \
--header 'Authorization:Basic dXNlckBtYWlsLnJ1OnBhc3N3b3JkVXNlcg'

You vote for menu with id = 13 (Menus with date to day have id = 13, 14): \
If you write this command second time, we will get exception (this user voted already) on condition what time is after 11:00 a.m. 
curl --location --request PUT 'http://localhost:8080/Restaurant/rest/profile/menuToDay/vote/13' \
--header 'Authorization:Basic YWRtaW5fdXNlckBnbWFpbC5jb206cGFzc3dvcmRBVQ'

COMMANS ADMIN: \
Get all menus which includes the Restaurant and today's meals: \
curl --location --request GET 'http://localhost:8080/Restaurant/rest/admin/menuToDay/' \
--header 'Authorization:Basic YWRtaW5AbWFpbC5ydTpwYXNzd29yZEFkbWlu'

Get all menus which includes the Restaurant and today's meals: \
curl --location --request GET 'http://localhost:8080/Restaurant/rest/admin/menuToDay/' \
--header 'Authorization:Basic YWRtaW5AbWFpbC5ydTpwYXNzd29yZEFkbWlu'

Get all menus restaurant with id = 1 (Restaurant's id  have numbers since 1 - 5): \
curl --location --request GET 'http://localhost:8080/Restaurant/rest/admin/menuToDay/1' \
--header 'Authorization:Basic YWRtaW5AbWFpbC5ydTpwYXNzd29yZEFkbWlu'

Menu with id = 1 delete (Menu's id  have numbers since 6 - 14): \
curl --location --request DELETE 'http://localhost:8080/Restaurant/rest/admin/menuToDay/6' \
--header 'Authorization:Basic YWRtaW5AbWFpbC5ydTpwYXNzd29yZEFkbWlu'

New menu create: \
curl --location --request POST 'http://localhost:8080/Restaurant/rest/admin/menuToDay' \
--header 'Authorization:Basic YWRtaW5AbWFpbC5ydTpwYXNzd29yZEFkbWlu' \
--header 'Content-Type: application/json' \
--data '{
"id_rest":1,
"dateMenu":"2021-01-22",
"menuRest":"Новое меню",
"rating":0
}'

Menu update: \
curl --location --request PUT 'http://localhost:8080/Restaurant/rest/admin/menuToDay/6' \
--header 'Authorization:Basic YWRtaW5AbWFpbC5ydTpwYXNzd29yZEFkbWlu' \
--header 'Content-Type: application/json' \
--data '{
"id":6,"id_rest":1,
"dateMenu":"2021-01-22",
"menuRest":"Новое меню",
"rating":0
}'

Get all restaurant: \
curl --location --request GET 'http://localhost:8080/Restaurant/rest/admin/restaurant/' \
--header 'Authorization:Basic YWRtaW5AbWFpbC5ydTpwYXNzd29yZEFkbWlu'

Get restaurant with id = 1 (Restaurant' id have number since 1 - 5): \
curl --location --request GET 'http://localhost:8080/Restaurant/rest/admin/restaurant/1' \
--header 'Authorization:Basic YWRtaW5AbWFpbC5ydTpwYXNzd29yZEFkbWlu'

Restaurant with id = 1 delete (Restaurant' id have number since 1 - 5): \
curl --location --request DELETE 'http://localhost:8080/Restaurant/rest/admin/restaurant/5' \
--header 'Authorization:Basic YWRtaW5AbWFpbC5ydTpwYXNzd29yZEFkbWlu'

New restaurant crete: \
curl --location --request POST 'http://localhost:8080/Restaurant/rest/admin/restaurant' \
--header 'Authorization:Basic YWRtaW5AbWFpbC5ydTpwYXNzd29yZEFkbWlu' \
--header 'Content-Type: application/json' \
--data '{
"name":"Новый ресторан",
"menu":[],
"rating":0
}'

Restaurant update: (Restaurant' id have number since 1 - 5): \
curl --location --request PUT 'http://localhost:8080/Restaurant/rest/admin/restaurant/1' \
--header 'Authorization:Basic YWRtaW5AbWFpbC5ydTpwYXNzd29yZEFkbWlu' \
--header 'Content-Type: application/json' \
--data '{
"id":1,
"name":"Новый ресторан",
"menu":[],
"rating":0
}'

Get all users: \
curl --location --request GET 'http://localhost:8080/Restaurant/rest/admin/users/' \
--header 'Authorization:Basic YWRtaW5AbWFpbC5ydTpwYXNzd29yZEFkbWlu'

Get user with id = 15: \
curl --location --request GET 'http://localhost:8080/Restaurant/rest/admin/users/15' \
--header 'Authorization:Basic YWRtaW5AbWFpbC5ydTpwYXNzd29yZEFkbWlu'

User with id = 15 delete (Users have id since 15 -17): \
curl --location --request DELETE 'http://localhost:8080/Restaurant/rest/admin/users/15' \
--header 'Authorization:Basic YWRtaW5AbWFpbC5ydTpwYXNzd29yZEFkbWlu'

User create: \
curl --location --request POST 'http://localhost:8080/Restaurant/rest/admin/users/' \
--header 'Authorization:Basic YWRtaW5AbWFpbC5ydTpwYXNzd29yZEFkbWlu' \
--header 'Content-Type: application/json' \
--data '{
"name":"newToUser",
"email":"ToUser@mail.ru",
"password":"ToUserPassword",
"role":"USER"
}'

User update with id = 15: \
curl --location --request PUT 'http://localhost:8080/Restaurant/rest/admin/users/15' \
--header 'Authorization:Basic YWRtaW5AbWFpbC5ydTpwYXNzd29yZEFkbWlu' \
--header 'Content-Type: application/json' \
--data '{
"id":15,
"name":"UPDATE",
"voteLast":"2021-01-23",
"email":"UPDATE_USER@mail.ru",
"password":"UPDATE",
"role":"ADMIN"
}'

COMMANS REGISTERED: \
New user register: \
curl --location --request POST 'http://localhost:8080/Restaurant/rest/register' \
--header 'Content-Type: application/json' \
--data '{
"name":"newToUser2",
"email":"ToUser2@mail.ru",
"password":"ToUserPassword",
"role":"USER"
}'

Renewal of the authorized user: \
curl --location --request PUT 'http://localhost:8080/Restaurant/rest/register/update' \
--header 'Authorization:Basic dXNlckBtYWlsLnJ1OnBhc3N3b3JkVXNlcg' \
--header 'Content-Type: application/json' \
--data '{
"id":15,
"name":"UPDATE",
"voteLast":"2021-01-23",
"email":"UPDATE_USER@mail.ru",
"password":"UPDATE",
"role":"USER"
}'

