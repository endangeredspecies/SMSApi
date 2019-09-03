# SMS API 

This API allows you to validate the SMS inbound and outbound request.

## Prerequisites

- A Java 8 Runtime
- Postgres DB
- Redis for windows

## Getting Started

To use the SMS Api service, perform the following:

Download “Service” from <https://github.com/endangeredspecies/SMSApi/releases> and extract its content to a location on your computer.

Run the following command to start the service on the default port (8080):

```sh
java -jar SMSApi-0.0.1-SNAPSHOT.jar
```

Choosing a different port is as follows:

```sh
java -jar -Dserver.port=7777 SMSApi-0.0.1-SNAPSHOT.jar
```

The server starts in a few seconds a. Open a browser, and access REST API doc at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) :

![swagger screenshot](images/SMSApi_Swagger.png?raw=true)

The service provides two resources:

1. POST /SMS/inbound- 
2. POST /SMS/outbound- 

==========================================
TO Test the Api

the service can be tested in 3 ways :

1. from the swagger UI
2. Postman client
3. curl command line utility 
The following curl helps you submit jobs to the service: (the format need to be changed based on OS you are using)

```sh
curl -u azr1:20S0KPNOIM http://localhost:7777/inbound/sms -H "Content-Type: application/json" -H "Accept: application/json" -X POST -d @test.json
```

this command gives the output :

{"message":"inbound sms ok","error":""}


This command uses a JSON file.Refer the following example:

test.json:

```json
{
  "from": "441224980094",
  "text": "string",
  "to": "4924195509198"
}
```

=======================================

SOME OF THE TESTS THAT I RAN AND THE OUTPUT THAT I GOT:

1. invalid username OR passoword

it should not allow to login

2. valid username and passoword

azr1
20S0KPNOIM 

succesful login

=======================================================
**SMS/inbound**: 

3. "to" valid , "from" invalid :


{
  "from": "",
  "text": "string",
  "to": "4924195509198"
}

output :

{
  "message": "",
  "error": "from length should be between 6 and 16"
}



4. "from" valid and "to" invalid
{
  "from": "4924195509198",
  "text": "string",
  "to": "123"
}

output :
{
  "message": "",
  "error": "to length should be between 6 and 16"
}


5. required parameter not present  : I have check of NotNull in the model class so it will not hit the service and throw the error much before.


6. length fine but parameter invalid (not present in the DB)

{
  "from": "1234567",
  "text": "string",
  "to": "4924195509198"
}


output :

{
  "message": "",
  "error": "from parameter is invalid"
}


7. both to and from are present but to doesnt belong to account which is currently logged in :

{
  "from": "4924195509198",
  "text": "string",
  "to": "441224980094"
}

output :

{
  "message": "",
  "error": "to parameter not found"
}


8. all valid :
{
  "from": "441224980094",
  "text": "string",
  "to": "4924195509198"
}

output:

{
  "message": "inbound sms ok",
  "error": ""
}

========================================

**SMSOutbound**:

9. STOP case
using /SMS/inbound

{
  "from": "441224980099",
  "text": "STOP",
  "to": "441224980100"
}


{
  "message": "inbound sms ok",
  "error": ""
}

using /SMS/outbound-

{
  "from": "441224980099",
  "text": "STOP",
  "to": "441224980100"
}

{
  "message": "",
  "error": "sms from441224980099 to 441224980100 blocked by STOP request"
}


similary other cases can be run based on the requirement.
## Limitations:
-password protection and encryption not used 
-many more
## License

All files found in this project are licensed under the [Apache License 2.0](LICENSE).
