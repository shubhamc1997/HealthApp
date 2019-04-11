# Purlieus

This application is our team's submission for Microsoft code.fun.do ONLINE The Qualifiers. It aims to connect people, in need of a particular service.

Team:
==

* [Anurag Choudhary](https://github.com/anurag-23/): Android Developer & Designer
* [Shaurya Malik](https://github.com/shauryamalik): Android Developer & Designer
* [Gautham Vinod](https://github.com/demonic-bling/): Android Developer & Designer

Introduction:
==

Purlieus is an application founded on the concept of location-based pairing. Currently, it aims to implement categories such as Health, Community, Books.

* Category: Health (: Blood Donation)<br/>
For instance, there is an urgent requirement for a rare blood type. The question lies in finding the most suitable donor, in the least amount of time. 	A tool like Purlieus makes this search more efficient, by listing all available donors in the vicinity. 

* Category: Community<br/>
For instance, one wants to donate to an NGO nearby. Or wants to find a family of little means, and provide them support, in his/her own little way. 	Purlieus aims to pin-point such locations on the basis of details provided by the user, thereby easing the search.

* Category: Books<br/>
In case, one needs a particular book or has extra books to share, that might benefit others, there is no way to know the availability of the book, 	nearby. Purlieus aims at creating a readers-network, thereby connecting people with a similar passion of reading.

Features:
==

Initially, the application stores the user's data. Thereafter, it provides two options of Seek and Donate.
On selecting Seek, the user will be asked for Blood Group and whether the user's seek request is urgent or not. On clicking the "Continue" button, the 	user will be provided with a list of available donors within a radius of 10 kms. This list will contain donors only with PUBLIC profile visibility. Clicking 		the "Continue" Button also pushes a copy of the seeker's data to our MICROSOFT AZURE database tables.
On selecting Donate, the user will be asked for Blood Group and whether his profile visibility is to be set to Public or Private. Thus, on clicking the 	"Donate" button, the user will be provided with a list of all seekers, sorted based on whether their seek requests are URGENT or not. Clicking 	the "Donate" 	Button also pushes a copy of the seeker's data to our MICROSOFT AZURE database tables.
Both lists contain details such as Name, Sex, Age, Phone Number, and a Call Option. The Call Option makes a call to the available phone number from 		list item.

* Features, to be implemented in the future:

1. Push Notifications
2. Identity Verification
3. Category : Communiity - Provision of list of NGO, Orphanages, Underpriviliged schools requiring assistance around you.
4. Category : Books - List of books that someone wants to borrow or lend.

Notes
==

1. The app needs to access location information for the map.
2. Calling APIs require an active internet connection.
3. MapsAPI and PlacesAPI is used to find the user's address.
4. All database operations are stored and retrieved in/from the MICROSOFT AZURE Database Tables. 
5. Currently the application is only designed to implement the Health Category.

Known Issues
==

1. New data from the same user is not updated, and instead, pushed as a new item.
2. There is no option to remove your donation/seek request once placed. 
3. There is no current check for network availabilty.
4. Private mode not tested
