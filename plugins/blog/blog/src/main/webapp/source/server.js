// REST API

// GET /items       - Retrieves a list of items
// GET /items/12    - Retrieves a specific item
// POST /items      - Creates a new item
// PUT /items/12    - Updates a specific item
// PATCH /items/12  - Partially updates a specific item ! NOT IMPLEMENTED YET
// DELETE /items/12 - Deletes a specific item

"use strict";

const DATABASE_NAME = "AngularTrainingDB";
const TABLE_NAME = "GroceryListTable";

const CREATE_TABLE_QUERY="CREATE TABLE "+TABLE_NAME+"(id MEDIUMINT NOT NULL AUTO_INCREMENT,name varchar(255),description varchar(255),category varchar(255),store varchar(255),unit varchar(255),price DECIMAL(10,4),PRIMARY KEY (id));";

const SELECT_ALL_QUERY="SELECT * from "+TABLE_NAME+";";
const SELECT_ITEM_QUERY="SELECT * from "+TABLE_NAME+" WHERE id=?;";
const INSERT_QUERY="INSERT INTO "+TABLE_NAME+" (name, description, category, store, unit, price) VALUES (?, ?, ?, ?, ?, ?);";
const DELETE_QUERY="DELETE FROM "+TABLE_NAME+" WHERE id=?;";
const UPDATE_QUERY="UPDATE "+TABLE_NAME+" SET name=?, description=?, category=?, store=?, unit=?, price=? WHERE id=?;";

// server.js (Express 4.0)
var express        = require('express');
var app            = express();

var bodyParser = require('body-parser');

app.use(bodyParser.json()); // for parsing application/json
app.use(bodyParser.urlencoded({ extended: true }));

app.use(express.static('./build')); 	// set the static files location /public/img will be /img for users

var mysql      = require('mysql');
var pool  = mysql.createPool({
    connectionLimit : 10,
    host     : 'localhost',
    user     : 'angular_admin',
    password : 'pass4test',
    database : DATABASE_NAME
});

// Retrieves a list of items
app.get('/items', function(req, res) {

  console.log('SELECT *');

  pool.query(SELECT_ALL_QUERY, function(err, rows, fields) {
    if (err){
      throw err;
    }
    console.log('+++++++++SELECT * FROM');
    for(let i = 0;i < rows.length; i++){
      console.log('The grocery is: ', rows[i]);
    }
    res.send(rows);
  });
});

// Retrieves a specific item
app.get('/items/:id', function(req, res) {
  console.log('SELECT *');

  pool.query(SELECT_ITEM_QUERY, [req.params.id], function(err, rows, fields) {
    if (err){
      throw err;
    }
    console.log('+++++++++SELECT * FROM');
    for(let i = 0;i < rows.length; i++){
      console.log('The grocery is: ', rows[i]);
    }
    res.send(rows);
  });
});

// Creates a new item
app.post('/items', function(req, res) {
  var groceryItem = req.body;

  pool.query(
    INSERT_QUERY,
    [groceryItem.name, groceryItem.description, groceryItem.category, groceryItem.store, groceryItem.unit, groceryItem.price], function(err, rows) {

    if (err) {
      console.log('error while insert');
      throw err;
    }
    console.log('insert successful');
    res.sendStatus(200);
  });
});

// Deletes a specific item
app.delete('/items/:id', function(req, res) {
//  var groceryItem = req.body;
  
  pool.query(DELETE_QUERY, [req.params.id], function(err, rows, fields) {
    if (err){
      throw err;
    }
    res.sendStatus(200);
  });
});

// Updates a specific item
app.put('/items/:id', function(req, res) {
  var groceryItem = req.body;
  
  pool.query(
    UPDATE_QUERY,
    [groceryItem.name, groceryItem.description, groceryItem.category, groceryItem.store, groceryItem.unit, groceryItem.price, req.params.id], function(err, rows) {
    if (err){
      throw err;
    }
    res.sendStatus(200);
  });
});

app.listen(8000);
console.log('Open http://http://localhost:8000/index.html to access the application now'); 			// shoutout to the user