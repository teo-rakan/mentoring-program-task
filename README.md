### Mentoring program task
Command line options:
* `-bu`, `--base_url` - base URL. Default URL: http://www.sho.com
* `-tp`, `--target_platform` - target platform. Default: Windows
* `-b`, `--browser` - browser name. Default browser: Chrome
* `-s`, `--suite` - suite xml file path (full, relative or file name from **suite** directory). Default: `/suite/desktop-smoke.xml`

#### Maven build command:
```bash
mvn clean package
```

#### For testing with Chrome:
```bash
java -jar target/test-project-1.0-v1/test-project.jar 
```

#### For testing with Android/Chrome:
```bash
java -jar target/test-project-1.0-v1/test-project.jar -tp android -s mobile-smoke.xml
```