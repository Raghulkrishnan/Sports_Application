# SPORTS_DOMAIN_WEB_APP

USER CREDENTIALS FOR THE APPLICATION:

(USERNAME	   |       PASSWORD	   |   USER ROLE)
(admin	      |         admin	    |      ADMIN)
(coach1@gmail.com	 | coach1	  |     COACH_ROLE, ADMIN)
(coach2@gmail.com	 | coach2	    |    COACH_ROLE)
(coach3@gmail.com	|  coach3	   |    COACH_ROLE)
(coach4@gmail.com	|  coach4	   |    COACH_ROLE)
(owner1@gmail.com	 | owner1	     |  OWNER_ROLE)
(owner2@gmail.com	|  owner2   |     OWNER_ROLE)
(owner3@gmail.com |   owner3  |      OWNER_ROLE)
(owner4@gmail.com	|  owner4	   | OWNER_ROLE)



PROJECT SUMMARY

I always wanted to combine my multiple areas of interests into a single project. Sports and a web application has always been my favorite two and I wanted to create a web application in the sports domain, thus this "Sport Connect" application that I've designed and developed. This application will allow the users to network with other players and their teams by letting them register matches with any team and at a venue at a particular date. Thus the team coaches can manage the team data by setting up games and managing their profile. Venue Owners get the matches that have been registered, on their page. They also have the access to update the game result of each and every game played at their venue.


FULFILLING THE REQUIREMENTS

I have made use of composite components so that I can reuse a form page wherever required. In addition to that I have added components that provide different functionality for different user roles. Thus I have made use of JSF capabilities for code re-usability. I also have a template for the home page with navigation bars and menu options and home page content that is a constant throughout different roles in the application.

I have followed separation of concerns principle by organizing the code based on the functionality that it provides as model, controller and made use of web pages and ezcomp folder under resources as partitions of code - facelets XHTML pages in the web pages folder (VIEW), backing beans in the model package and controllers that contribute to the database operations in the controller package.

I have provided admin functionality for adding new users, teams and venues.

I have provided functionality for displaying entities based on different criteria. A coach user who has logged in can only view the coach page and can only perform actions on coach, team and game entities that the user is related to. Similarly the owner entity will only be able to access the owner page and can manage only the owner, venue and the game entities. Thus access has been partitioned based on different criteria.

I have provided tabular display of data on the page and also options to perform CRUD operations on the entities through the View, Edit and Remove options beside each row in the table in the page. These operations also are limited to the user roles as a owner user cannot view,update,create or delete coach details and vice versa.

The page name will be on the top on the navigation bar and the user has the option of navigating to any page he wants by just clicking the page name. A logout button is provided under the options menu on the navbar for the user to sign out any time from the application.

User inputs have been validated and if any data in the form is missed to be filled, a prompt will appear suggesting the user what needs to be done to complete the action. Also, bean validations and h:messages tags have been used to manage the forms throughout the application. I have included log messages in every functional operation in the code to sight the area in case of an exception.

The tables, links, buttons, navigation bar, backgrounds, alert messages (in login error page) have been designed using css and basic boostrap classes and properties to provide the user a pleasant experience.


# DESIGN

# DATABASE MODEL:

There are three types of users: Admin, Coach, and Owner. They have been given access to their respective pages only and cannot access the pages of the other user roles.

The admin has the ability to create new users for the available user roles - there are two - Coach and Venue Owners. A coach has a team and a venue owner owns a venue. The admin along with being able to register new coaches and new owners can also add the team and venue information for the coaches and owners respectively. The admin can at any time login and view, update or delete the coach and owner information and also their team and venue information (in addition to adding new data).

The coaches can login using their credentials(email ID and password provided by the admin) and enter into the application. There they will be able to see their profile information and team information. The coach can now set up a match between his/her team and any other team in the application in a date of his choice and at a venue that is available (venue that is registered in the application).

# JavaMail Resource**
Once he clicks on the save button to set up a game, he and the opponent team's coach will get a mail stating the confirmation of the match and the match details. This was done using JavaMail resource in the application. I have created a sample mail ID through which mails will be sent to coaches of both teams that have matches.(NOTE: Learnt from online resources and examples)

The coach user can also edit his information or his team's information any time he wants by clicking on the edit option in the profile table.The match that was set up can also be viewed, updated or deleted by clicking on the appropriate option beside each and every game created.

Venue Owners can login to the application and similar to the coach user can edit his/her information and the venue information. Also all the matches that have been set up in the venue that this owner owns, will be displayed on the page. The venue owner has been given the responsibility of updating the match results. Thus, the owner is provided the option of adding the game result (against every game) and can choose the team that won and team that lost the match and update it on the page.

A Bean Validation Custom Validator has been created to manage the age property of a user. The user is expected to be at least 15 years of age for this application, and if the age is entered any less than 15 will get a prompt message - courtesy the age validator (custom validator - @Age). (NOTE: Learnt from online resources and examples)

As the admin is given the option of creating new users, the users should be able to change this first time login password that has been created. Thus in the coach and owner edit page - a link button to change the password has been provided and the user can go there and change his password anytime he wants.


REQUIREMENTS

The technology requirements are as follows:

 JDK 11 or above
 Apache NetBeans IDE
 Payara Server compatible with Jakarta EE 8
 MySQL WorkBench
Download the above mentioned.

(Installation and Database setup referred from professors class lecture)
1. Open MySQL Workbench and Create a MySQL database schema named itmd4515.
2. Create a user "itmd4515" with access rights to the entire database.
3. Provide a password "itmd4515" to the user. If this user already exists, you need not create another user.
4. Provide privileges to the user by going to the Users and Privileges page and click Add Account.
5. Provide login name, password and click Schema Privileges - select ALL and click Apply.
6. Create JDBC connection from NetBeans to the MySQL database
- Go to Services Tab - Right click on database and select "New Connection"
- Select MySQL JDBC Driver and click Next and change db to itmd4515, enter username and password in the field provided and click "FINISH"

The final project folder (unzipped) can be opened in NetBeans by clicking - File - Open Project option. The zip file which has been extracted should be searched and opened from the dialog box that appears. Right click on the project folder and click on BUILD. You can also do a Clean and Build. Once the build is successful, You can right click on the project folder and click RUN or just click the RUN icon at the top (play button). The default browser which is set in your NetBeans - suggest the project will open in a new window in that browser. You can change the default browser by selecting some other from the option that pops out when you click the browser icon (which is near to the run button).
