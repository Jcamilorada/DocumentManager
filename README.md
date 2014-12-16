DocumentManager
===============

Open Document Management System

The document manager is a open source document manager system project that enable the creation and administration of documents and templates. The document will be write using Markdown Language, and the tracking of the files changes will be handle by Git. 

Framework and technologies.

* [AngularJS] - For the ui client that enable user to interact with document and templates.
* [Markdown] - Is the chosen language for create and edit documents. Is extendible with html and the tags are user friendly.
* [Git] - Git will help the backend to track the changes on the files.
* [Mongo-DB]  - Mongo store information related to documents like security, document sharing a document etc.
* [Java] - The server code that provide document and templates APIs is writted in Java. The server side will provide rest based web services to access information from the ui side.


The main features for the project is to provide documents versions and history control of the changes.

First release objectives
___
### Documents

* Users can edit/update/remove documents writted on [Markdown].
* Users can see files history and changes on the documents.
* User can create document based on Templates.
* User can search documents by content or name.

___
### Templates

* User can define templates. 
* Users can edit/update/remove templates writted on [Markdown].



[AngularJS]:http://angularjs.org
[Markdown]:http://daringfireball.net/projects/markdown/
[Git]:http://git-scm.com/
[Mongo-DB]:http://www.mongodb.org/
