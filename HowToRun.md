#1 Install JavaFX
1. Download the JavaFX SDK, either Windows or MacOS X, from:
https://gluonhq.com/products/javafx/
2. Unzip this into a directory that you'll remember for Step 5. I created a folder inside Java folder. Once 
unzipped, checkout the JAR files inside lib folder. You should see 8 of them.
![htr1](https://user-images.githubusercontent.com/81070935/180624834-1b4f3776-b694-448b-a209-85c989e0b659.jpg)
3. In Eclipse, create a project as usual. I called it HW2
4. Right click on Project folder, go to Build Path -> Add External Archives
5. Navigate to the path where you unzipped JavaFX and go to lib folder to see those 8 JARs. Select all 8 of them and 
include them to show up in the build path
![htr2](https://user-images.githubusercontent.com/81070935/180624892-a934c421-5f9a-4e9f-ba5d-ce9e60eb1d38.jpg)
6. Now, run the CyberCop file. You should get an error like this:
"Error: JavaFX runtime components are missing, and are required to run this application"
7. 8. Right click on HelloWorld.java in the left panel. Choose Run As  Run Configurations. Check the 
Project and Main class names (Fig 3). Then go to Arguments tab and type in the string shown below in VM 
Arguments box. (Fig 4). Note that the path name is of those 8 jar files from step 2. 
"--module-path="C:\Program Files\Java\JavaFX\javafx-sdk-11.0.2\lib" --add-modules javafx.controls,javafx.fxml,javafx.web"
![htr3m](https://user-images.githubusercontent.com/81070935/180624918-b443b12e-8e1c-4249-8e87-b44f606cac6a.jpg)
8. Click Run. You should see the CyberCop wizard.
![htr4](https://user-images.githubusercontent.com/81070935/180624955-96c94977-bb87-4a75-9f1d-d2468ac6280a.jpg)

The final document structure should look something like this:
![htr5](https://user-images.githubusercontent.com/81070935/180625040-e04f17d8-62fd-4f9a-a997-f9c8a7960bcb.jpg)

