# m22aie243_cloudcomputing
This repo has assignment on cloud computing and virtualisation

Install Docker 
This code was deployed using Docker version 24.0.5(Docker desktop for Mac)

#Steps to run this project:

Please Go to the root directory  of the project in  where we have Dockerfile 
 And app.jar both( We do not need to do a new build jar is already there)

Run  below Docker command

docker build -t demo_image .

docker run -it -p 8080:8080 --name demo_container2 demo_image


Application should come up on port 8080 on the host we are running these docker commands

Application  will be accessible 

On http://localhost:8080/api/profile/   output on browser should be [] Since not data inserted  to DD.

#Functionality 

This project is based on Spring boot micro services  and it's using in memory H2 DB.
In this project I have implemented CRUD operations.
Please run in below Sequence.

curl -X POST -H "Content-Type: application/json" -d '{"name":"John Doe"}' http://localhost:8080/api/profile/

Output: {"id":1,"name":"John Doe"}

curl http://localhost:8080/api/profile/{1} 

John Doe
   

curl -X POST -H "Content-Type: application/json" -d '{"name":"M22AIE243"}' http://localhost:8080/api/profile/  

curl -X POST -H "Content-Type: application/json" -d '{"name":"CloudComputing"}' http://localhost:8080/api/profile/ 

Get All Profiles:
curl http://localhost:8080/api/profile/

[{"id":1,"name":"John Doe"},{"id":2,"name":"M22AIE243"},{"id":3,"name":"CloudComputing"}]

Update a Profile:

curl -X PUT -H "Content-Type: application/json" -d '{"name":"Roushan"}' http://localhost:8080/api/profile/1 

{"id":1,"name":"Roushan"}


Deleting a profile :
 curl -X DELETE http://localhost:8080/api/profile/1

                                                        

