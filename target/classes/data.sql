


INSERT INTO users (createddate,email,firstname,gender,lastname,password,username) VALUES(CURRENT_DATE(),'ravi@abc.com','Ravi','MALE','Sadam','$2a$12$WewTghpISWht3OrxfLLdrOEDSCkEps.AuypXUnGGd85Xv1eP8r7su','ravi123');
INSERT INTO users (createddate,email,firstname,gender,lastname,password,username) VALUES(CURRENT_DATE(),'rama@abc.com','Rama','MALE','Sadam','$2a$12$txjMdEGeeaeBahZKhzqR9eRHlmS411NRC8dxxBNe2da7szVH4.hMG','rama123');

INSERT INTO roles (roleid,rolename) VALUES(1,'ROLE_ADMIN');
INSERT INTO roles (roleid,rolename) VALUES(2,'ROLE_USER');

INSERT INTO user_roles (username,roleid) VALUES('ravi123',1);
INSERT INTO user_roles (username,roleid) VALUES('ravi123',2);
INSERT INTO user_roles (username,roleid) VALUES('rama123',2);

INSERT INTO tags(name) VALUES('C++');
INSERT INTO tags(name) VALUES('Java');
INSERT INTO tags(name) VALUES('Python');

INSERT INTO blogs (auther,created_date,content,explanation,blogid,tag,title,status) VALUES('ravi123',CURRENT_DATE(),'In Java, variables are essential components used to store and manipulate data within a program. They act as containers that hold different types of information, such as numbers, text, or boolean values. Variables are declared using a specific data type followed by a unique name, and they can be initialized with an initial value. Throughout the execution of a program, variables may change their values as needed, allowing developers to perform various computations and operations.','Give brief explanation about the java varaibles with good examples and samples code','Blog-865857cb7af34c708b1d99fff0b4857a','Java','Java variables','POSTED');
INSERT INTO blogs (auther,created_date,content,explanation,blogid,tag,title,status) VALUES('ravi123',CURRENT_DATE(),'Keywords in Java are reserved words that have predefined meanings and functionalities within the Java programming language. These keywords cannot be used as identifiers (such as variable names or function names) in Java code. Heres a list of Java keywords:abstract: Used to declare abstract classes and methods.assert: Used to assert the truth of an expression.boolean: Defines a boolean data type.break: Used to break out of loops or switch statements.byte: Defines a byte data type.','Give brief explanation about the java keywords with good exmples and samples codes','Blog-edf08f5b742d4286b9ad5fe926c3a528','Java','Java Keywords','POSTED');
INSERT INTO blogs (auther,created_date,content,explanation,blogid,tag,title,status) VALUES('ravi123',CURRENT_DATE(),'Keywords in C++ are reserved words that have predefined meanings and cannot be used as identifiers (such as variable names or function names). These keywords are part of the languages syntax and serve specific purposes. Heres a list of C++ keywords:auto: Specifies automatic type deduction for variables.break: Terminates the current loop or switch statement.case: Marks a specific case within a switch statement.char: Declares a character data type.const: Specifies that a variables value cannot be modified.','Give brief explanation about the c++ keywords with good exmples and samples codes','Blog-08dc7d1ac8e249bdae8f6ba45457617f','C++','C++ Keywords','POSTED');
INSERT INTO blogs (auther,created_date,content,explanation,blogid,tag,title,status) VALUES('rama123',CURRENT_DATE(),'Keywords in Python are reserved words that have predefined meanings and cannot be used as identifiers (such as variable names or function names). These keywords are part of the languages syntax and serve specific purposes. Heres a list of Python keywords:False: Boolean value representing false.None: Represents the absence of a value or null.True: Boolean value representing true.and: Logical operator for boolean AND.','Give brief explanation about the python keywords with good exmples and samples codes','Blog-722f3d6f98b14693b7f5b749b6f4f44b','Python','Python Keywords','POSTED');
INSERT INTO blogs (auther,created_date,content,explanation,blogid,tag,title,status) VALUES('rama123',CURRENT_DATE(),'Integer Types: These include int, short, long, and long long, which represent different sizes of integers, allowing storage of whole numbers such as -10, 0, or 42.Floating-Point Types: C++ offers float, double, and long double for representing decimal numbers with different levels of precision.Character Types: The char data type holds single characters.Boolean Type: C++ has a bool type, which can hold either true or false, useful for logical operations.','Give brief explanation about the c++ data types with good exmples and samples codes','Blog-41985578677343ed986d3ece9b442486','C++','C++ Data types','POSTED');
INSERT INTO blogs (auther,created_date,content,explanation,blogid,tag,title,status) VALUES('rama123',CURRENT_DATE(),'In Python, functions are blocks of code that perform specific tasks. They encapsulate reusable logic, making code modular and easier to manage. Functions are defined using the def keyword, followed by a function name and parentheses (). They can accept parameters, which act as inputs to the function, and return values, which are the results of the functions execution.','Give brief explanation about the Python Functions with good exmples and samples codes','Blog-a6e08b7dce7142fd8144603907d57bc9','Python','Python Functions','POSTED');

INSERT INTO blogs (created_date,explanation,blogid,tag,title) VALUES(CURRENT_DATE(),'Give brief explanation about the java OOPs with good exmples and samples codes','Blog-63d2b9743fd34bc6af1c42c143dc51f2','Java','Java OOPS');
INSERT INTO blogs (created_date,explanation,blogid,tag,title) VALUES(CURRENT_DATE(),'Give brief explanation about the java functions with good exmples and samples codes','Blog-052d3b82f5f741b6986d8e7e4253cfc1','Java','Java Functions');
INSERT INTO blogs (created_date,explanation,blogid,tag,title) VALUES(CURRENT_DATE(),'Give brief explanation about the java exception handling with good exmples and samples codes','Blog-42892ef87f3144c1a7f91c2922d7473e','Java','Java Exception handling');
INSERT INTO blogs (created_date,explanation,blogid,tag,title) VALUES(CURRENT_DATE(),'Give brief explanation about the c++ oops with good exmples and samples codes','Blog-c627e8a458754a84a91f4cb6f4a418c2','C++','C++ OPPs');
INSERT INTO blogs (created_date,explanation,blogid,tag,title) VALUES(CURRENT_DATE(),'Give brief explanation about the python oops with good exmples and samples codes','Blog-9a7438c1e78d4574b5f76e2c895ca18d','Python','Python OOps');
INSERT INTO blogs (created_date,explanation,blogid,tag,title) VALUES(CURRENT_DATE(),'Give brief explanation about the python exception handling with good exmples and samples codes','Blog-d5c774f7f11d4ad4b62dbdb4c6dbce21','Python','Python Exception Handling');

