# API/Java:
* [User](#User)
* [Event](#Event)
* [Performances](#Performances)
* [RSVP](#RSVP)
* [Event Category](#Event_Category)
* [Event Country](#Event_Country)

## User
    * [ ] Model (1 hour)
        * id
        * Name 
        * Address 
        * Contact information
        * Username
        * Password

    * [ ] Data (2 hour)
        * [ ] Mappers
        * [ ] Create JdbcTemplate
        * [ ] Create Test
        * [ ] Repository Interface (extract from jdbcTemplate)

    * [ ] Domain (1 hour)
        * [ ] Create Service
            * Inject Repo
        * [ ] Create Test
            * AutoWire service
            * Mock repo

    * [ ] Controller (1 hour)
        * Create Controller
            * Inject Service


Vertical Traversal: 

    - Data -> Domain -> Controller
    - Testing at each step
    - Validate in Service

    * [ ] find (1 hour)
    * [ ] add (2 hour)
    * [ ] update (2 hour)
    * [ ] delete (2 hour)

## Event
    * [x] Model (1 hour)
        * id
        * EventName 
        * Location 
        * Date
        * Capacity 
        * Category 
        * Country
        * Creator

    * [x] Data (2 hour)
        * [ ] Mappers
        * [ ] Create JdbcTemplate
        * [ ] Create Test
        * [ ] Repository Interface (extract from jdbcTemplate)

    * [x] Domain (1 hour)
        * [ ] Create Service
            * Inject Repo
        * [ ] Create Test
            * AutoWire service
            * Mock repo

    * [x] Controller (1 hour)
        * Create Controller
            * Inject Service


Vertical Traversal: 

    - Data -> Domain -> Controller
    - Testing at each step
    - Validate in Service

    * [x] find (1 hour)
    * [x] add (2 hour)
    * [ ] update (2 hour)
    * [ ] delete (2 hour)

## Performances
    * [ ] Model (1 hour)
        * id
        * name
        * description
        * event id

    * [ ] Data (2 hour)
        * [ ] Mappers
        * [ ] Create JdbcTemplate
        * [ ] Create Test
        * [ ] Repository Interface (extract from jdbcTemplate)

    * [ ] Domain (1 hour)
        * [ ] Create Service
            * Inject Repo
        * [ ] Create Test
            * AutoWire service
            * Mock repo

    * [ ] Controller (1 hour)
        * Create Controller
            * Inject Service


Vertical Traversal: 

    - Data -> Domain -> Controller
    - Testing at each step
    - Validate in Service

    * [ ] find (1 hour)
    * [ ] add (2 hour)
    * [ ] update (2 hour)
    * [ ] delete (2 hour)

## RSVP
    * [ ] Model (1 hour)
        * user id
        * event id
        * approved?
        * comment if not approve

    * [ ] Data (2 hour)
        * [ ] Mappers
        * [ ] Create JdbcTemplate
        * [ ] Create Test
        * [ ] Repository Interface (extract from jdbcTemplate)

    * [ ] Domain (1 hour)
        * [ ] Create Service
            * Inject Repo
        * [ ] Create Test
            * AutoWire service
            * Mock repo

    * [ ] Controller (1 hour)
        * Create Controller
            * Inject Service


Vertical Traversal: 

    - Data -> Domain -> Controller
    - Testing at each step
    - Validate in Service

    * [ ] find (1 hour)
    * [ ] add (2 hour)
    * [ ] update (2 hour)
    * [ ] delete (2 hour)

## Event_Category
    * [ ] Model (1 hour)
        * id 
        * Category Type: Music/Food/Celebration/Carnival... 
        * Description

    * [ ] Data (4 hours)
        * [ ] Mappers
        * [ ] JdbcTemplate
            * find
            * add
            * update
            * delete
        * [ ] Repository Interface (extract from jdbcTemplate)
        * [ ] Unit Testing
        
    * [ ] Domain
    * [ ] Controller

## Event_Country
    * [ ] Model (1 hour)
        * id
        * American, Moroccan, Vietnamese...
        * Flag

    * [ ] Data (2 hour)
        * [ ] Mappers
        * [ ] Create JdbcTemplate
        * [ ] Create Test
        * [ ] Repository Interface (extract from jdbcTemplate)

    * [ ] Domain (1 hour)
        * [ ] Create Service
            * Inject Repo
        * [ ] Create Test
            * AutoWire service
            * Mock repo

    * [ ] Controller (1 hour)
        * Create Controller
            * Inject Service


Vertical Traversal: 

    - Data -> Domain -> Controller
    - Testing at each step
    - Validate in Service

    * [ ] find (1 hour)
    * [ ] add (2 hour)
    * [ ] update (2 hour)
    * [ ] delete (2 hour)