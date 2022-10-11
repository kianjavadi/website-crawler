# website-crawler
This crawler can traverse through a given website and store all its contents in the local filesystem 


## build and run
You need to have Maven and at least JDK 11 on your system in order to build and run the application.
after cloning the repository, you need to navigate into the repository and build it using command below:

`mvn package`

Then you can run the application: 

`java -jar ./target/website-crawler-1.0.0.jar "your-desired-website.com"`

You can see downloaded contents of the website under `<USER_HOME_DIRECTORY>/<DOMAIN_NAME>/`

## Design considerations
- This crawler uses the breadth first search algorithm in order to explore an HTML file and extracts its URLs.
- Since we have recursive calls in breadth first search, ForkJoinPool framework has been chosen for the parallelism and speeding up the process.
- Regex has been used for discovering the URLs within the HTML file.
- All the HTML pages and static contents are stored, but there isn't any alteration in the HTML source while downloading, so most probably the downloaded HTML page won't be loaded 100% locally, because the paths using the static contents are relatives and work only on the server.
- Only internal files (in the same domain) are stored and anything outside the given domain will be ignored.
- There's a high focus on using SOLID and clean code principles through the entire application
