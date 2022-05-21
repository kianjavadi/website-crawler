# website-crawler
This crawler can traverse through a given website and store all its content in the local filesystem 


# build and run
You need to have at least JDK 11 on your system in order to build and run application.
after cloning the repository, you need to navigate into the repository and build it using command below:

`mvn package`

Then you can run the application: 

`java -jar ./target/website-crawler-1.0.0.jar "your-desired-website.com"`

You can run the application without passing any argument in it so `https://tretton37.com` will be used:

`java -jar ./target/website-crawler-1.0.0.jar`
