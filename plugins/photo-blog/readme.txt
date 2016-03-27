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