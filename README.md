# Cultural Connection Calendar  
    * Location 
    * Calendar 
    * Event category 
    * Event creation 
    * Attendance Tracker 
    * Performance listing 
    * Translation 
    
## Problem Statement 

There are many communities with varied cultural heritages in the US, and they are often isolated from sharing their culture with each other and others because of distance or unfamiliarity with the community they live in.  There are also many people who are curious or wish to learn about the varied cultures from around the world which may already be present nearby.   

If people could share their cultural heritages with each other, it would go a long way towards facilitating understanding and respect between the different cultures from around the world we all share, and celebrating a rich cultural history that can easily be forgotten in today’s fast paced world.  Even though we have the ability to reach around the world in a few seconds in the palms of our hands, we are continuing to lose the social connection that brings communities together. 

An application that allows individuals to post cultural events on a calendar would allow people from the same culture to be made aware of events they may have no other way of knowing about.  This could be because they may not have access to the same social media applications, or they are new to the area and have not had time to make connections.  It would also allow the invitation of people from other cultures to join, learn and experience a different culture in order to foster communication and understanding. 

 

## Proposal 

Create an application that allows registered users to organize a cultural event in their area and share it with the public.  Members of the public can view the event calendar as a public service. 

## Scenarios 

### Scenario 1:  

Tuấn has recently moved to Des Moines, Iowa for a new job. He doesn’t know anyone there yet but he finds the “Cultural Connections Calendar” and looks to find some events happening around him. Upon browsing through the app, he found that there is a CelebrAsian Festival happening soon. At the event, not only can he connect with the local Vietnamese community, he can also learn about other Asian cultures which exist within this new town. 

### Scenario 2:  

Adam won the diversity visa lottery and recently moved from Morocco to Boston. His coworkers asked him for more details about his culture. Rather than showing them videos and images from Google or YouTube. Using the “${APP NAME}” to attend the Moroccan Festival in Revere, MA would be more useful. 

## Technical Requirements 

* Database - MySQL 
* Spring Boot, MVC, JdbcTemplate, Testing 
* React UI 
* Sensible layering and pattern choices 
* A full test suite that covers the domain and data layers 
* Deployment to AWS. 

 

## Security Requirements 

There are two registered user types: MEMBER and ADMIN. 

Potential members must apply for membership through an application form to be approved by an ADMIN. A MEMBER may create an event.  A MEMBER may only EDIT and DELETE an event that they have created.  A MEMBER may declare they will support an event that they did not create (e.g., RSVP). 

Access Levels: 

    * Public – Anyone may view the event calendar 
    * Member – Can create, edit and delete a new event.  Can view the event map. 
    * Admin – Full control.  Authorizes memberships and may remove members. 

 

## High-level Requirements 
    * Create an event (USER, ADMIN) 
    * Edit an event (USER, ADMIN) 
    * Cancel an event (USER, ADMIN) 
    * Approve an event 
    * Browse event (PUBLIC) 
    * Sign up for an event (authenticated) 
    * Registration form  

 

### Create an Event 

* Create a cultural event that can be either open or closed to the public. 

* Event description 

* Event location – Zip code, map pin (lat/lon) 

* Event date/time 

* Event capacity – Maximum allowed participants 

* Event contact – Member who created event 

* Event category (Music/Food/Celebration, etc.) 

* Pre-condition:  Only a MEMBER or ADMIN may create an event.  Must be logged into the account. 

* Post-condition:  After creating and applying for a new event, it must be approved by the ADMIN.  An ADMIN may post an event immediately. 

 

### Edit an Event 

* Can only edit an event in the future. 

* Pre-condition:  The user (MEMBER or ADMIN) must be logged in and can only edit an event in the future. 

* Post-condition:  A MEMBER does not need ADMIN approval to edit their own event. 

 

### Cancel an Event 

* Can only cancel a future event. 

* Pre-condition: A logged in member may cancel their own event.  An admin may cancel any event. 




 





 #   C C C - C A P S T O N E - P R O J E C T -  
 