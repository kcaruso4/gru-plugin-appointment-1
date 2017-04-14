# Plugin Appointment

This plugin allows you to manage appointments using forms that users can access from the front office.
On back office, you will be able to define your week definition and working days and many rules to apply to the appointment.

### Prerequisites

For being able to run the plugin apppointment on you local machine, you need to have a tomcat server configured, eclipse, java 8, maven (at least 3.3.9), ant, and a mysql local instance (with mysqlWorkBench).

### Installing

You need to create a site (a new project on Eclipse) with a pom.xml that includes the plugin appointment.
Here an example :

<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/maven-v4_0_0.xsd">
    <parent>
        <artifactId>lutece-site-pom</artifactId>
        <groupId>fr.paris.lutece.tools</groupId>
        <version>2.0.4</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <groupId>fr.paris.lutece</groupId>
    <artifactId>site-rendezvous-integration</artifactId>
    <packaging>lutece-site</packaging>
    <name>Site Prise de RDV Intégration</name>
    <version>1.0.125-SNAPSHOT</version>
    <repositories>
        <repository>
            <releases>
                <enabled>true</enabled>
            </releases>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
            <id>lutece</id>
            <name>luteceRepository</name>
            <url>http://dev.lutece.paris.fr/maven_repository</url>
            <layout>default</layout>
        </repository>
        <repository>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
            <id>luteceSnapshot</id>
            <name>luteceSnapshot</name>
            <url>http://dev.lutece.paris.fr/snapshot_repository</url>
        </repository>
    </repositories>
    <pluginRepositories>
        <pluginRepository>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
            <id>luteceSnapshot</id>
            <name>luteceSnapshot</name>
            <url>http://${serverUrl}/snapshot_repository</url>
        </pluginRepository>
    </pluginRepositories>
    <dependencies>
        <dependency>
            <groupId>fr.paris.lutece</groupId>
            <artifactId>lutece-core</artifactId>
            <version>[6.0.0-SNAPSHOT,)</version>
            <type>lutece-core</type>
        </dependency>
        <dependency>
            <groupId>postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>9.0-801.jdbc4</version>
        </dependency>
        <dependency>
            <groupId>fr.paris.lutece.plugins</groupId>
            <artifactId>plugin-regularexpression</artifactId>
            <version>3.0.3</version>
            <type>lutece-plugin</type>
        </dependency>
        <dependency>
            <groupId>fr.paris.lutece.plugins</groupId>
            <artifactId>plugin-captcha</artifactId>
            <version>2.1.0</version>
            <type>lutece-plugin</type>
        </dependency>
        <dependency>
            <groupId>fr.paris.lutece.plugins</groupId>
            <artifactId>module-captcha-jcaptcha</artifactId>
            <version>2.1.8</version>
            <type>lutece-plugin</type>
        </dependency>
        <dependency>
            <groupId>fr.paris.lutece.plugins</groupId>
            <artifactId>plugin-mylutece</artifactId>
            <version>3.3.2</version>
            <type>lutece-plugin</type>
        </dependency>
        <dependency>
            <groupId>fr.paris.lutece.plugins</groupId>
            <artifactId>plugin-identity-provider</artifactId>
            <version>1.0.1</version>
            <type>lutece-plugin</type>
        </dependency>
        <dependency>
            <groupId>fr.paris.lutece.plugins</groupId>
            <artifactId>plugin-workflow</artifactId>
            <version>4.3.0</version>
            <type>lutece-plugin</type>
        </dependency>
        <dependency>
            <groupId>fr.paris.lutece.plugins</groupId>
            <artifactId>plugin-appointment</artifactId>
            <version>2.0.0-SNAPSHOT</version>
            <type>lutece-plugin</type>
        </dependency>
        <dependency>
            <groupId>fr.paris.lutece.plugins</groupId>
            <artifactId>module-workflow-appointment</artifactId>
            <version>1.1.1-SNAPSHOT</version>
            <type>lutece-plugin</type>
        </dependency>
        <dependency>
            <groupId>fr.paris.lutece.plugins</groupId>
            <artifactId>plugin-genericattributes</artifactId>
            <version>1.1.5</version>
            <type>lutece-plugin</type>
        </dependency>
        <dependency>
            <groupId>fr.paris.lutece.plugins</groupId>
            <artifactId>module-genericattributes-googlemaps</artifactId>
            <version>2.0.0</version>
            <type>lutece-plugin</type>
        </dependency>
        <dependency>
            <groupId>org.mnode.ical4j</groupId>
            <artifactId>ical4j</artifactId>
            <version>1.0.5</version>
        </dependency>
        <dependency>
            <groupId>fr.paris.lutece.plugins</groupId>
            <artifactId>plugin-resource</artifactId>
            <version>1.0.0</version>
            <type>lutece-plugin</type>
        </dependency>
        <dependency>
            <groupId>fr.paris.lutece.plugins</groupId>
            <artifactId>module-resource-adminuser</artifactId>
            <version>1.0.0</version>
            <type>lutece-plugin</type>
        </dependency>
        <dependency>
            <groupId>fr.paris.lutece.plugins</groupId>
            <artifactId>module-resource-mylutece</artifactId>
            <version>1.0.0</version>
            <type>lutece-plugin</type>
        </dependency>
        <dependency>
            <groupId>fr.paris.lutece.plugins</groupId>
            <artifactId>plugin-sitelabels</artifactId>
            <version>1.0.2</version>
            <type>lutece-plugin</type>
        </dependency>
        <dependency>
            <groupId>fr.paris.lutece.plugins</groupId>
            <artifactId>plugin-asynchronousupload</artifactId>
            <version>1.0.4</version>
            <type>lutece-plugin</type>
        </dependency>
        <dependency>
            <groupId>fr.paris.lutece.plugins</groupId>
            <artifactId>plugin-piwik</artifactId>
            <version>1.0.3</version>
            <type>lutece-plugin</type>
        </dependency>
        <dependency>
            <groupId>fr.paris.lutece.plugins</groupId>
            <artifactId>module-appt-alert</artifactId>
            <version>1.0.3-SNAPSHOT</version>
            <type>lutece-plugin</type>
        </dependency>
        <dependency>
            <groupId>fr.paris.lutece.plugins</groupId>
            <artifactId>module-workflow-notifyesirius</artifactId>
            <version>1.0.0-SNAPSHOT</version>
            <type>lutece-plugin</type>
        </dependency>
        <dependency>
            <groupId>fr.paris.lutece.plugins</groupId>
            <artifactId>module-workflow-notifyqmatic</artifactId>
            <version>1.0.0-SNAPSHOT</version>
            <type>lutece-plugin</type>
        </dependency>
        <dependency>
            <groupId>fr.paris.lutece.plugins</groupId>
            <artifactId>module-appointment-filling</artifactId>
            <version>1.0.0-SNAPSHOT</version>
            <type>lutece-plugin</type>
        </dependency>
        <dependency>
            <groupId>fr.paris.lutece.plugins</groupId>
            <artifactId>module-appointment-mydashboard</artifactId>
            <version>1.0.1-SNAPSHOT</version>
            <type>lutece-plugin</type>
        </dependency>
    </dependencies>
    <properties>
        <lutece.plugin.version>4.0.2-SNAPSHOT</lutece.plugin.version>
        <maven-lutece-plugin.version>4.0.2-SNAPSHOT</maven-lutece-plugin.version>
    </properties>
</project>

Once you have done this, you will have to get all the maven dependencies and create the site by running these two command lines on the workspace directory of the site :
mvn lutece:clean lutece:site:assembly

Once the build is success, you will have a site-rendezvous-integration-XXX.war created on the target directory and a directory named site-rendezvous-integration-XXX.

You will have to go to this directory/WEB-INF/conf and edit the db.properties file to put the right login/password to access your local mysql instance.

To create the lutece schema, you will have to run the ant script in site-rendezvous-integration-XXX/WEB-INF/sql by running in a prompt shell the command: ant

To run the application, just put the site-rendezvous-integration-XXX.war to the webapps directory of your tomcat and launch : catalina jpda start

You will have access to the application at the urls : 
htp://localhost:8080/site-rendezvous-integration-XXX/jsp/admin/AdminLogin.jsp for the Back Office pages
http://localhost:8080/site-rendezvous-integration-XXX/jsp/site/Portal.jsp?page=appointment&view=getViewFormList for the front office pages

If you have development to make on the plugin appointment, you have to get from github the plugin directory and on eclipse import this project.
Once the development is done on the plugin, you have to build it with :
mvn clean install -Dmaven.test.skip=true
and put the plugin-appointment-XXX.jar created on the target directory site-rendezvous-integration-XXX\WEB-INF\lib of the webapp of your tomcat, to replace the old jar. It will avoid you to create again the site with the jar you've just built.

## Running the tests

The plugin appointment have junit tests suites.
!!!! You have to run them before any commit !!!! To avoid regression
(And if you make new development, you have to make new junit test that test your new development)
Here the command to launch the test (at the root of the directory) :
mvn clean lutece:exploded antrun:run test

### Break down into code

From Eclipse, you have the possibility of running a debug mode.
For that, you have to start the tomcat instance like that : catalina jpda start
On Eclipse, in the debug configurations menu, create a new Remote Java Application (project : plugin appointment, connection properties : host : localhost, port : 8000)
Click on debug.
Put breakpoint where you want and debug !

### Development and coding style 

The plugin appointment have three layers :
Business, Service and Web (controlers).
You can also see the data model on gru-plugin-appointment/src/site/xdoc/mdd.png
If you have any develepment to make on the plugin appointment, don't touch to the business part !!!!
Except if a new table have to be created or if a new column has to be added in an existing table, you must not, under any circumstances add or modify anything on the business layer.

The only part you will have to modify (or adding new classes or functions) is the service layer and the web layer.
For the web layer, you have two differents classes that implements MVCApplication in case of a front page, or MVCAdminJspBean for the Back Office Pages.
Please, if your development concerns the two parts, mutualize your code in a common class and call the same function from the two different controlers.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Laurent Payen** - *Initial work* - [lolobuell](https://github.com/lolobuell)

## License

Copyright (c) 2002-2015, Mairie de Paris
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions
are met:

   1. Redistributions of source code must retain the above copyright notice
      and the following disclaimer.
 
   2. Redistributions in binary form must reproduce the above copyright notice
      and the following disclaimer in the documentation and/or other materials
      provided with the distribution.
 
   3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
      contributors may be used to endorse or promote products derived from
      this software without specific prior written permission.
 
  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
  AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
  IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
  ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
  LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
  SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
  INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
  CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
  ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
  POSSIBILITY OF SUCH DAMAGE.
 
  License 1.0

