to show image in AngularJS - <img ng-src="data:image/JPEG;base64,{{image}}">

to upload file:
<input type="file" id="file" name="file"/>
<button ng-click="add()">Add</button>

$scope.add = function(){
  var f = document.getElementById('file').files[0],
      r = new FileReader();
  r.onloadend = function(e){
    var data = e.target.result;
    //send your binary data via $http or $resource or do anything else with it
  }
  r.readAsArrayBuffer(f);
}


MySQL:

command line:
start mySQL - mysql -u root -p 
run script - source setup.sql; 

Bower

cd /git/enterprise/plugins/blog/blog/src/main/webapp
npm install
npm start

TODO

1. Add Commentable Status - 1. No comments, 2. Comments allowed 3. Moderated comments
	Default property in Blog, concrete property in Post

2. Increase post and comment field size 


Photo-Blog

Front - AngularJS
Back - Node.js

Functions:
1. Add/edit posts
2. Add/edit advert.
3. Add/edit photos
4. Search posts by date/tag/location/name
5. Search photos by date/tag/location/name
6. Show statistics


1. Data Base
1.1 Post Table
id
Date Time
Location
Visited
Subject (255) en/ru
Body (3000) en/ru

1.2 Photo Table
id
name en/ru
description en/ru
date time
tags
location
path
1.3 Visit Table
id
Date Time
Location
Browser
OS
1.4 Advertisement Table
id
place_id
Body
Link
2. File Structure
3. Rest API

// GET /posts       - Retrieves a list of posts
// GET /posts/12    - Retrieves a specific post
// POST /posts      - Creates a new post
// PUT /posts/12    - Updates a specific post (more then one field)
// PATCH /posts/12  - Partially updates a specific post (one field)
// DELETE /posts/12 - Deletes a specific post

// GET /photos      - Retrieves a list of photos
// GET /photos/12   - Retrieves a specific photo
// POST /photos     - Creates/Upload a new photo
// PUT /photos/12   - Updates a specific photo
// PATCH /photos/12 - Partially updates (one field) a specific photo
// DELETE /photos/12- Deletes a specific photo

4. 

The LIMIT clause can be used to constrain the number of rows returned by the SELECT statement. LIMIT takes one or two numeric arguments, which must both be nonnegative integer constants (except when using prepared statements).

With two arguments, the first argument specifies the offset of the first row to return, and the second specifies the maximum number of rows to return. The offset of the initial row is 0 (not 1):

SELECT * FROM tbl LIMIT 5,10;  # Retrieve rows 6-15
To retrieve all rows from a certain offset up to the end of the result set, you can use some large number for the second parameter. This statement retrieves all rows from the 96th row to the last:

SELECT * FROM tbl LIMIT 95,18446744073709551615;
With one argument, the value specifies the number of rows to return from the beginning of the result set:

SELECT * FROM tbl LIMIT 5;     # Retrieve first 5 rows
In other words, LIMIT row_count is equivalent to LIMIT 0, row_count.