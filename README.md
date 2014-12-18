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


[AngularJS]:http://angularjs.org
[Markdown]:http://daringfireball.net/projects/markdown/
[Git]:http://git-scm.com/
[Mongo-DB]:http://www.mongodb.org/
