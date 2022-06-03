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




 





 




<!-- # Getting Started with Create React App

This project was bootstrapped with [Create React App](https://github.com/facebook/create-react-app).

## Available Scripts

In the project directory, you can run:

### `npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in your browser.

The page will reload when you make changes.\
You may also see any lint errors in the console.

### `npm test`

Launches the test runner in the interactive watch mode.\
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

### `npm run build`

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.\
Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.

### `npm run eject`

**Note: this is a one-way operation. Once you `eject`, you can't go back!**

If you aren't satisfied with the build tool and configuration choices, you can `eject` at any time. This command will remove the single build dependency from your project.

Instead, it will copy all the configuration files and the transitive dependencies (webpack, Babel, ESLint, etc) right into your project so you have full control over them. All of the commands except `eject` will still work, but they will point to the copied scripts so you can tweak them. At this point you're on your own.

You don't have to ever use `eject`. The curated feature set is suitable for small and middle deployments, and you shouldn't feel obligated to use this feature. However we understand that this tool wouldn't be useful if you couldn't customize it when you are ready for it.

## Learn More

You can learn more in the [Create React App documentation](https://facebook.github.io/create-react-app/docs/getting-started).

To learn React, check out the [React documentation](https://reactjs.org/).

### Code Splitting

This section has moved here: [https://facebook.github.io/create-react-app/docs/code-splitting](https://facebook.github.io/create-react-app/docs/code-splitting)

### Analyzing the Bundle Size

This section has moved here: [https://facebook.github.io/create-react-app/docs/analyzing-the-bundle-size](https://facebook.github.io/create-react-app/docs/analyzing-the-bundle-size)

### Making a Progressive Web App

This section has moved here: [https://facebook.github.io/create-react-app/docs/making-a-progressive-web-app](https://facebook.github.io/create-react-app/docs/making-a-progressive-web-app)

### Advanced Configuration

This section has moved here: [https://facebook.github.io/create-react-app/docs/advanced-configuration](https://facebook.github.io/create-react-app/docs/advanced-configuration)

### Deployment

This section has moved here: [https://facebook.github.io/create-react-app/docs/deployment](https://facebook.github.io/create-react-app/docs/deployment)

### `npm run build` fails to minify

This section has moved here: [https://facebook.github.io/create-react-app/docs/troubleshooting#npm-run-build-fails-to-minify](https://facebook.github.io/create-react-app/docs/troubleshooting#npm-run-build-fails-to-minify) -->
