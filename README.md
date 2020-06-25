# Alexa Skill - DeckedOut
Skill which allows dementia patients to save the storage place of certain objects and recall it later when necessary.

## Project status: 
We are currently in Sprint 1 and are trying to establish a basic functionality of our skill, so that it can be tested on the Alexa Developer Console. 
This includes the option to store the location of objects in the cloud, to change it later, as well as to recall it.
Also the basic Handlers for Help, Launching and Closing the Session should be implemented.

## Setup:
To run the skill there are two things necessary. The first is to deploy the code in a Lambda function, and the second is to configure the Alexa skill to use Lambda.  
[Developing a Skill](https://developer.amazon.com/de/docs/alexa-skills-kit-sdk-for-java/develop-your-first-skill.html)

### AWS Lambda Setup
Refer to [Hosting a Custom Skill as an AWS Lambda Function](https://developer.amazon.com/docs/custom-skills/host-a-custom-skill-as-an-aws-lambda-function.html) reference for a walkthrough on creating a AWS Lambda function with the correct role for your skill. When creating the function, select the “Author from scratch” option, and select the Java 8 runtime. 

To build the sample, open a terminal and go to the directory containing pom.xml, and run 'mvn org.apache.maven.plugins:maven-assembly-plugin:2.6:assembly -DdescriptorId=jar-with-dependencies package'. This will generate a zip file named "colorpicker-1.0-jar-with-dependencies.jar" in the target directory.

Once you've created your AWS Lambda function and configured “Alexa Skills Kit” as a trigger, upload the JAR file produced in the previous step and set the handler to the fully qualified class name of your handler function. Finally, copy the ARN for your AWS Lambda function because you’ll need it when configuring your skill in the Amazon Developer console.


### Alexa Skill Setup
If the skill code has been uploaded to AWS Lambda we're ready to configure the skill with Alexa. First, navigate to the [Alexa Skills Kit Developer Console](https://developer.amazon.com/alexa/console/ask). Click the “Create Skill” button in the upper right. Enter “ColorPicker” as your skill name. On the next page,  select “Custom” and click “Create skill”.
 
Now we're ready to define the interaction model for the skill. Under “Invocation” tab on the left side, define your Skill Invocation Name to be `deckedout`. 
 

The Developer Console  allows you to edit the entire skill model in JSON format by selecting “JSON Editor” on the navigation bar. Use the file [models/de-DE.json](models/de-DE.json).

Once you’re done editing the interaction model don't forget to save and build the model.
 
Let's move on to the skill configuration section. Under “Endpoint” select “AWS Lambda ARN” and paste in the ARN of the function you created previously. The rest of the settings can be left at their default values. Click “Save Endpoints” and proceed to the next section.
 
Finally you're ready to test the skill! In the “Test” tab of the developer console you can simulate requests, in text and voice form, to your skill. Use the invocation name along with one of the sample utterances we just configured as a guide. You should also be able to go to the [Echo webpage](http://echo.amazon.com/#skills) and see your skill listed under “Your Skills”, where you can enable the skill on your account for testing from an Alexa enabled device.
 
At this point, feel free to start experimenting with your Intent Schema as well as the corresponding request handlers in your skill's implementation. Once you're finished iterating, you can optionally choose to move on to the process of getting your skill certified and published so it can be used by Alexa users worldwide.
