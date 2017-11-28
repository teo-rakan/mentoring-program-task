### Mentoring program task
Command line options:  

Option | Definition | Default  
-------|------------|--------  
`-bu`, `--base_url` | base URL | http://www.sho.com  
`-tp`, `--target_platform` | target platform | determined by System.getProperty("os.name")  
 `-b`, `--browser` | browser name | Chrome
 `-s`, `--suite` |- suite xml file path (full, relative or file name from **suite** directory) | `/suite/desktop-smoke.xml`

#### Maven build command:
```bash
mvn clean package
```

#### For testing with Desktop/Chrome:
```bash
java -jar target/test-project-1.0-v1/test-project.jar 
```

#### For testing with Android/Chrome:
```bash
java -jar target/test-project-1.0-v1/test-project.jar -tp android -s mobile-smoke.xml
```
