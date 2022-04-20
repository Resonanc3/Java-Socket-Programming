# Java-Socket-Programming
Simple Example of Single and Multi Client in Java Socket Programing. You can find two folders in this repository namely [SingleClient] and [Other_Client_Socket]. The Other_Client_Socket is where the multi-client socket can be found. 

## Important Notice!!!
This may not be applicable to all. But, I use VSCode to run this code. I'll run you to some basics before running. If you have these following things, you can skip this process and go to [Usage]

- [Program Information](#Program_Information)
- [Difference between **JRE** and **JDK**]
- [Visual Studio Code]
- [Eclipse]
- [JDK and JRE Version Check]
- [Clone Repository]
- [Running the Program]
- [Running Other Program]
- [Conclusion]

<a name="Program_Information"/>

## Program Information
This program uses Socket in order for the Client to connect to the Server and communicate. If the program is only capable in single client. The Client's message will echo to the Server, and will echo back to Client.
If the program is a Multi-Client program. The Clients will communicate with each other, meaning that the message will echo from Clients only who have entered the server. The Server echo if a Client has entered from the Server. It is the same logic from a messaging app such as Messenger, WhatsApp, Telegram, etc.

## Difference between JRE and JDK
**JDK** stands for **Java Develompent Kit**, it is the full-feature SDK for java. It has the everything the JRE has, but also the compiler **"javac"** and tools like **"javadoc"** and **"jdb"**. It is capable of creating and compiling programs.

**JRE** stands for **Java Runtime Edition**. It's a package of everything necessary to run a compiled Java Program, including the **Java Virtual Machine**. The Java Class Library, the java command, and other infrastructure. Although, it cannot be used to create new programs.

More Information about JDK and JRE by [Clicking this link](https://www.techspot.com/downloads/5198-java-jre.html)

## Visual Studio Code
I use Visual Studio Code as my IDE for Java, Python, C, C++, etc. You can download Visual Studio Code [Here](https://code.visualstudio.com/download). Just Choose what Operating System you're at, after choosing there will be a step-by-step installation so be sure to read toroughly.

## Eclipse
For alternative IDE, you can use **Eclipse** which is good for java language. You can download Eclipse [Here](https://www.eclipse.org/downloads/). For a step-by-step installation you can view their [Eclipse Installer 2022-03 R](https://www.eclipse.org/downloads/packages/installer)

## Clone Repository
You can clone the repository after you have your IDE:
```
https://github.com/Resonanc3/Java-Socket-Programming.git
```

## JDK and JRE Version Check
Let's check whether you have installed both JDK and JRE in your computer.

## **1. (JDK and JRE Version Check)** Open CMD, you can either run it with or without administrator. ![image](https://user-images.githubusercontent.com/79844632/164131168-3a2077ff-c0d9-48ef-bdde-08e751278fc1.png)

## **2. (JDK and JRE Version Check)** You can type this following command to check if you have installed the Java Runtime Edition.
```
java --version
```
It should have the following output (in image shown below) if you have installed the JRE properly.
![image](https://user-images.githubusercontent.com/79844632/164131320-1c61836f-f28f-44d9-97c0-6aeb3b6edb53.png)

## **3. (JDK and JRE Version Check)** To check whether you installed the Java Development Kit properly, input the following command in the CMD
```
javac --version
```

It should have the following output (in image shown below) if you have installed the JDK properly.
![image](https://user-images.githubusercontent.com/79844632/164131675-fa5bba50-e812-4237-a60d-880972aa06c0.png)

## **4. (JDK and JRE Version Check)** You can also check in its file location to see whether you have both JDK and JRE installed.
![image](https://user-images.githubusercontent.com/79844632/164131851-25284c20-de13-4b22-bf39-9e55a18c234b.png)
Check it for yourself by going to its default location.
```
C:\Program Files\Java
```
if you've installed JDK and JRE but still get an error in inputting the commands from Step 2 or Step 3. You can click on this link for a torough step-by-step of [How to Set, Java, JRE, and JDK Home Path and Environment Variables on Windows](https://www.poftut.com/how-to-set-java-jre-and-jdk-home-path-and-environment-variables-on-windows/)

## Running the Program
Please note that I am **Not an Expert Programmer** nor have the capability like other programmer. I'm just merely sharing you the steps on how I execute the program. After downloading or cloning the repository. You can place the folder anywhere, just make sure that your IDE would find the location of the program. For this part, I will show you how I execute the program in **Visual Studio Code** IDE.

**1. (Runnning the Program)**
Make sure that your explorer in **Visual Studio Code** shows the location of where you put the downloaded program. ![image](https://user-images.githubusercontent.com/79844632/164130587-42c218ec-2ce8-431b-b63f-d93aadb5a095.png)

**2. (Runnning the Program)**
Open the file, both Server and Client.
![image](https://user-images.githubusercontent.com/79844632/164133212-b2143832-7c63-47ad-a357-46feaa56d51b.png)


**3. (Running the Program)**
Look at to the bottom, you can see a terminal. Here is where the output shows. But before running let's do a detour.
![image](https://user-images.githubusercontent.com/79844632/164133377-5c251f5f-d577-40a7-98c0-513436da0895.png)

If your **".java"** program doesn't have a **".class"** file written after the file name of the **".java"** file. 
![image](https://user-images.githubusercontent.com/79844632/164133764-dd672c50-5516-451a-8b1e-e9b56c7f3d35.png)


Then, go to your terminal then traverse to the file location of the folder. In this case, we'll traverse to the folder **"SingleClient"** by using **"cd"** command. You can see that it traverse successfully if the **"PS"** points to the location of the folder.
```
cd SingleClient
```
![image](https://user-images.githubusercontent.com/79844632/164133714-ea7dd137-a56f-4481-a939-7abc0231a268.png)

Type the following command in the terminal
```
javac *.java
```
Then the **".class"** file will appar before the **".java"** file.

**4. (Running the Program)**
Now that were at the location of the folder. We can just run the program **"Server.java"** by typing the following in the terminal:
```
java Server
```
![image](https://user-images.githubusercontent.com/79844632/164134132-0745d936-db84-4959-8903-cc3c2413adce.png)

Before running **"Client.java"** let's have a split terminal for the client side.
At the right side of the terminal click the arrow that's pointing downwards beside the plus sign. Then click **Split Terminal -> Powershell**
![image](https://user-images.githubusercontent.com/79844632/164134307-2dcaad1d-3389-4db2-bc02-94bc29c04655.png)

**5. (Running the Program)**
At the split terminal, traverse back to **"SingleClient"** by typing the same command
```
cd SingleClient
```
Then run the **"Client.java"** by typing the following command at the split terminal
```
java Client
```
![image](https://user-images.githubusercontent.com/79844632/164134676-f2940c38-3d78-47a9-94bc-210d236652a5.png)

**5. (Running the Program)**
Now that both Server and Client is running. You can see the result at both terminal. You can now start typing from the Client side of the terminal. Once you type, it will echo to the Server and echo back to the Client.
![image](https://user-images.githubusercontent.com/79844632/164134843-cc87047f-d750-49f0-89f0-76432b1c4527.png)

## Running Other Program
You can execute the multi-client server by repeating the same steps I have given at **Running the Program**.

## Conclusion
If you have **Questions** or **Suggestions** about the program, you can request a **Fork**. Thank you and Have a Good Day!
