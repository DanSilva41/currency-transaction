<img align="center" src="docs/logo.png" alt="Currency Transaction">
    
<h4 align="center">
    <a href="https://rocketmusics.herokuapp.com/">Live demo</a>
</h4>

<h3 align="center">
    Simple, fast and reactive application to convert values between all currencies.
</h3>

<p align="center">
  <img alt="GitHub language count" src="https://img.shields.io/github/languages/count/dansilva41/currency-transaction?color=%2304D361">

  <img alt="Repository size" src="https://img.shields.io/github/repo-size/dansilva41/currency-transaction">

  <a href="https://github.com/dansilva41/currency-transaction/commits/main">
    <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/dansilva41/currency-transaction">
  </a>

   <img alt="License" src="https://img.shields.io/badge/license-MIT-brightgreen">
   <a href="https://github.com/dansilva41/currency-transaction/stargazers">
    <img alt="MIT License" src="https://img.shields.io/github/stars/dansilva41/currency-transaction?style=social">
  </a>

  <a href="https://dansilva41.github.io">
    <img alt="made by Danilo Silva" src="https://img.shields.io/badge/made%20by-Danilo%20Silva-blue">
  </a>

  <a href="https://developers-friends.gitbook.io/">
    <img alt="Developer's Friends" src="https://img.shields.io/badge/Blog-Developers%20Friends-orange">
    </a> 
</p>

<p align="center">
 <a href="#dizzy-about">About</a> •
 <a href="#mega-features">Features</a> • 
 <a href="#rocket-technologies">Technologies</a> •
 <a href="#install">Install</a> •
 <a href="#memo-license">License</a>

</p>

## :dizzy: About

This application is designed with currency-based value conversion in mind, where the focus is to get the conversion rates from an [external api](https://exchangeratesapi.io),
perform the conversion calculations, persist the data and return to consistent data.
In addition to offering performance due to its reactive behavior, the application provides a way to consult the conversions already carried out by the user.

---
## :mega: Features

|          Actions                                  |     Available       |
| --------------------------                        | :-----------------: |
| Converting value between all currencies           |         ✔️           |
| Get all conversions performed by user             |         ✔️           |

---

## :rocket: Technologies

The project was developed using the following technologies

- [Spring](https://spring.io/)
- [Spring WebFlux](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html)
- [Reactive MongoDB Embedded](https://github.com/flapdoodle-oss/de.flapdoodle.embed.mongo)
- [Project Reactor](https://projectreactor.io/)
- [Open API](https://swagger.io/specification/)
- [Wiremock](http://wiremock.org/)
- [Lombok](https://projectlombok.org/)
- [GitHub Actions](https://github.com/features/actions)
- [Heroku](https://www.heroku.com/what)

---

## 🗂 Install

```bash

    # Clone the repository
    $ git clone https://github.com/dansilva41/currency-transaction

    # Enter directory
    $ cd currency-transaction

    # Install dependencies
    $ mvn clean install

    # Start the project
    $ mvn springboot:run
```

---

## :memo: License
This project is under the MIT license. See the [LICENSE](https://github.com/dansilva41/currency-transaction/blob/main/LICENSE) for more information.

## Checkstyle

<details>
    <summary>01. Checking the project via maven plugin</summary>

- To run the check in the project, just use this maven command in shell/console or run via IDEA.

```bash
    mvn checkstyle:check
```
- To generate a report from the analysis of code style violations, just use this maven command in shell/console or run via IDEA.

```bash
    mvn checkstyle:checkstyle
```
Report generated in **target/site/checkstyle.html**.

</details>

<details>
    <summary>02. Installing the CheckStyle-IDEA plugin on IntelliJ</summary>
    
We can use the CheckStyle-IDEA plugin to help formatting code in the IDE.
To configure it is very simple, first install the plugin via the link above or on IntelliJ at
**File > Settings > Plugins**.

![Install plugin Checkstyle IDEA](docs/checkstyle/install-plugin-checkstyle.png)

### 02. Configuring the CheckStyle-IDEA plugin

- After installation, we need to import the settings defined in the checkstyle.xml file into the CheckStyle-IDEA plugin.
  Navigate to **File > Settings > Tools > Checkstyle** and in **Configuration File** click **Add** (+ sign on the right), indicate the path of your checkstyle.xml and click next.

![Configure the Checkstyle IDEA - First](docs/checkstyle/configure-checkstyle-intellij-first.png)

- With the file imported, don't forget to leave it selected as **Active**.

![Configure the Checkstyle IDEA - Second](docs/checkstyle/configure-checkstyle-intellij-second.png)

- Now let's add the same checkstyle file to the IntelliJ settings itself, so when we use the default formatting shortcuts it will automatically look for Checkstyle Main.
  Within settings, go to **Editor > Code Style > Java** and import the file as shown in the image below:

![Set code style look checkstyle](docs/checkstyle/set-code-style-look-checkstyle.png)

- Once these settings are finished, the CheckStyle option will appear at the bottom of IntelliJ and when clicking, the screen below will appear.
  At this point, in Rules select the one you imported in the previous steps and run the verification.

- In Intellij IDEA, select the project, package(s) or class(es) and **Right click > Analyse > Inspect Code... > OK** then plugin will indicate the problems found.

</details>

Developed by Danilo Silva :wave: [Get in touch!](https://www.linkedin.com/in/danilosilvap/)