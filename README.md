# ImageManager
Dynamic resizing of images using builtin Java library

### Application 
- Run the application with gradle build
- And execute the jar file as a microservice in your local

Sample#1: Scaling Image to required dimensions
- http://localhost:8080/rescaleImage?width=500&height=500&imageUrl=https://cdn.pixabay.com/photo/2016/02/17/15/37/laptop-1205256_1280.jpg

Sample#2: Crop specific part of image from centre
- http://localhost:8080/cropImage?width=500&height=500&imageUrl=https://cdn.pixabay.com/photo/2016/02/17/15/37/laptop-1205256_1280.jpg
