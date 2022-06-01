# Demo Horse Harness

This application allows you to upload a horse racing  sectional data in to the system and validated. Validation errors will be display in a ui table. It also give you some insights about the data available.

1. java 8+
2. maven
3. git
4. node
5. npm
6. angular 11

execution steps

1. clone the project
   
``` 
 git clone https://github.com/rumeshwishwa/horse-harness.git
```

2. change directory in to horse-harness

``` 
 cd horse-harness
```

3. execute mvn clean install

``` 
 mvn clean install
```

4. run the Back end application

``` 
 java -jar target/horse-harnes-1.0.0.jar
```

5. open a new terminal in same folder and go to the horse-harness-ui

``` 
    cd horse-harness-ui
```

6. install the node packages

```
    npm install
```

7. run the ui application in dev mode

```
    ng serve --proxy-config proxy.conf.json
```
