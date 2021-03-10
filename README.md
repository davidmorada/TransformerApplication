# TransformerApplication
Techincal Exam for aequilibrium

○ How to build and run the unit tests?
1. Make sure that JAVA JDK or JRE are installed to you machine. If not yet installed you can visit the site to help you setup you java environment.
    - For Microsoft Windows:
    https://docs.oracle.com/javase/10/install/installation-jdk-and-jre-microsoft-windows-platforms.htm#JSJIG-GUID-A7E27B90-A28D-4237-9383-A58B416071CA
    - For MAC OS:
    https://docs.oracle.com/javase/9/install/installation-jdk-and-jre-macos.htm#JSJIG-GUID-2FE451B0-9572-4E38-A1A5-568B77B146DE
    - For Linux:
    https://www.java.com/en/download/help/linux_x64_install.html
2. Get application project on GitHub: (Note: Make sure that Git was installed in you machine)
    
    - Git Command:
        - https://github.com/davidmorada/TransformerApplication.git

3. Go to project directory of the application.(ex. /application-exam/TransformerApplication)
4. On this directory execute the command:
    - for build command
      - ./gradlew build
    - for unit tests run
      - ./gradlew test

○ How to run the application?

1. Go to the directory /build/libs (note: you will see a jar file)
2. On this directory execute the command:
    - java -jar \<Jar File> (ex. java -jar application-0.0.1.jar)

○ Include the API endpoints used with example (JSON) payloads

- List all Transformers:
  - HTTP METHOD: GET
  - API PATH: /v1/transformers
  - Response:

    [
        {
        "transformerID": 1,
        "transformerName": "Hubcap",
        "transformerTeam": "A",
        "strength": 4,
        "intelligence": 4,
        "speed": 4,
        "endurance": 4,
        "rank": 4,
        "courage": 4,
        "firepower": 4,
        "skill": 20
        },
        {
        "transformerID": 2,
        "transformerName": "Hound",
        "transformerTeam": "A",
        "strength": 1,
        "intelligence": 4,
        "speed": 5,
        "endurance": 2,
        "rank": 2,
        "courage": 3,
        "firepower": 3,
        "skill": 15
        }
    ]   
    
- Create Transformer:
    - HTTP METHOD: POST
    - API PATH: /v1/transformers/transformer
    - Request:

        {
            "transformerName": "IronHigh",
            "transformerTeam": "A",
            "strength": 4,
            "intelligence": 4,
            "speed": 4,
            "endurance": 4,
            "rank": 4,
            "courage": 4,
            "firepower": 4
        }

    - Response:
      - "Transformer Created."

- Update Transformer:
    - HTTP METHOD: PUT
    - API PATH: /v1/transformers/transformer
    - Request:

        {
            "transformerID": 1,
            "transformerName": "Bumble Bee",
            "transformerTeam": "A",
            "strength": 4,
            "intelligence": 4,
            "speed": 4,
            "endurance": 4,
            "rank": 4,
            "courage": 4,
            "firepower": 4
        }
      
    - Response:

        {
            "transformerID": 1,
            "transformerName": "Bumble Bee",
            "transformerTeam": "A",
            "strength": 4,
            "intelligence": 4,
            "speed": 4,
            "endurance": 4,
            "rank": 4,
            "courage": 4,
            "firepower": 4,
            "skill": 20
        }

- Delete Transformer
    - HTTP METHOD: DELETE
    - API PATH: /v1/transformers/transformer/{transformerID}
    - Response:
        - "Transformer Destroyed."

- Begin Transformers Battle
    - HTTP METHOD: POST
    - API PATH: /v1/transformers
    - Request:

        [
            1,2,3,4,5,6
        ]
      
    - Response:

        {
        "result": "3 battle(s) Winning Team (Decepticons): Soundwave, Megatron, Shockwave Survivors from the losing team (Autobots): Hubcap, Hound, Mirage"
        }

○ Any assumptions or notes to the reviewer.

    - I also included swagger for documentation and easy testing of the application.