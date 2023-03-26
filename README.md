# Model 3D
Model 3D
     It is a small commercial project for selling 3D models online in three modules. At this point, the application uses credits to download models rather than real money.One model, one credit and every user has three credits daily, and every day at midnight, user credits are reset.
 -----------------------------------------------------------------------------------------------------------------------------------------------------------------------
Module Admin:
    It starts at http://localhost:8081/admin/ since I want it to run independently from the Customer module.
    The register endpoint in this module is disabled for security reasons, but you can sign in with email: admin@example.com and pass: admin.
    An admin user can add other admins, but they need to have registration like a customer or moderator in the Customer module.
    An admin user can add moderators, but they need to have registration like customers in the Customer module.
    Admin users can ban IP addresses for users who don't want to visit our website.
    The admin user can add new categories of 3D models or enable or disable existing ones.
    The admin module has two scheduled jobs. The first is to reset credits for all users, and the second is to send emails to all subscribers on our app.
 --------------------------------------------------------------------------------------------------------------------------
 Module Customer:
   It starts at http://localhost:8080.
   A not-logged user has access to some endpoints and can subscribe via email for app newsletters.
   The app has two users configured.One regular user (email: user@example.com, pass: user) and one moderator (email:moderator@example.com, pass: moderator),
   but every customer can register a new one.
   Regular users can upload their own models, but after successful uploading, the model waits for the moderator to approve the quality of the uploaded model. If a model      is accepted, it will be added to the All Models section.Every user can like or download a model, and that will be recorded in their account.
 -----------------------------------------------------------------------------------------------------------------------------------------------------------------------
 Module Library:
    This module contains all of the services, repositories, entities, DTOs, and views required for the Model 3D app to function normally.
 -----------------------------------------------------------------------------------------------------------------------------------------------------------------------
