# website-crawler
This crawler can traverse through a given website and store all its contents in the local filesystem 


# build and run
You need to have Maven and at least JDK 11 on your system in order to build and run the application.
after cloning the repository, you need to navigate into the repository and build it using command below:

`mvn package`

Then you can run the application: 

`java -jar ./target/website-crawler-1.0.0.jar "your-desired-website.com"`

You can run the application without passing any argument in it so `https://tretton37.com` will be used:

`java -jar ./target/website-crawler-1.0.0.jar`

You can see downloaded contents of the website under `<USER_HOME_DIRECTORY>/<DOMAIN_NAME>/`