/*
 * Copyright 2005-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mckesson.ldap;


import java.util.List;

import org.springframework.stereotype.Component;


/**
 * Data Access Object interface for the Person entity.
 * 
 * @author Mattias Hellborg Arthursson
 * @author Ulrik Sandberg
 */
@Component
public interface UserDao {
   void create(User person);

   void update(User person);

   void delete(User person);

   List<String> getAllUserNames();

   List<User> findAll();

   User findByPrimaryKey(String country, String company, String fullname);
   
   User findByUserId(String uid);
}
