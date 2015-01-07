What is Document Manager?
=========================

Document Manager is a open source document management system project that enable the creation and administration of documents and templates focusing on flexibility and usability for people without background on tech. Each document will be written using Markdown Language, and the tracking of the files changes will be handled by Git.

Framework and technologies.

* [AngularJS] - For the UI client that enable user to interact with document and templates.
* [Markdown] - Is the chosen language to create and edit documents. It is extendable with HTML and its tags are user friendly.
* [Git] - Git is used as main document backing store and will help to track its changes.
* [Mongo-DB]  - Mongo store information related to documents like security, metadata and sharing permissions for a document.
* Java - The server code that provide document and templates APIs is written in Java. The server side will provide RESTful web services to access information from the UI side.


The main features for the project is to provide documents versions and history control of the changes.

First release objectives
========================

### Documents

* Users can edit/update/remove documents written in [Markdown].
* Users can see files history and changes on the documents.
* User can create document based on Templates.
* User can search documents by content or name.


### Templates

* User can define templates.
* Users can edit/update/remove templates written in [Markdown].


Getting Started With UI Develop
===============================
For begin the UI Develop (webapp folder) you first need to install some frameworks.

* [Node-Js] is an open source, cross-platform runtime environment. NodeJs enabled us to install plugins that make easier UI develop.
* [Bower] is a package manager, it is used to fetch and install front-end libraries that are used as dependencies in this project. To install it just run "npm install -g bower" on a Node-Js Console.
* [Grunt] is a task executor manager. that run different kind of task (like download dependencies) To install just execute "npm install -g grunt-cli" on a Node-Js Console.

To add bower dependencies, just follow this steps:
* Add the dependency to the bower.json.
* Add the file mappings to the Gruntfile.js in the bowercopy task.
* Add the concat mapping to the Gruntfile.js in the concat task. 

If you have any doubt related to he configuration of bowercopy mappings please see the following links: 
* [BowerCopy] 
* [ConfigureBowerCopy]

After install this frameworks. is necessary to install all dependencies. So inside webapp folder execute the following commands:
* "npm install". This command will download and install all the necessary node modules.
* "bower install". This command will download and install all dependencies defined in the bower.json file.

Finally to run the application run the grunt task "grunt server"

[AngularJS]:http://angularjs.org
[Markdown]:http://daringfireball.net/projects/markdown/
[Git]:http://git-scm.com/
[Mongo-DB]:http://www.mongodb.org/
[Node-Js]:http://nodejs.org/
[Grunt]:http://gruntjs.com/
[Bower]:http://bower.io/
[BowerCopy]:https://www.npmjs.com/package/grunt-bowercopy
[ConfigureBowerCopy]:http://simonsmith.io/managing-bower-components-with-grunt/
