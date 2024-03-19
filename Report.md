# Hogwarts School Database
<!-- Improved compatibility of Back to Top link: See: https://github.com/othneildrew/Best-README-Template/pull/73 -->
<a name="readme-top"></a>
<!--



<!-- PROJECT SHIELDS -->
<!--
-->
[![TheDanielTp][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]



<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/TheDanielTp/Third_Assignment_Hogwarts">
    <img src="Hogwarts-Logo.png" alt="Logo" width="427" height="240">
  </a>
<h3 align="center">Draco Dormiens Nunquam Titillandus</h3>

  <p align="center">
    Never Tickle a Sleeping Dragon
    <br />
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#how-to-use">How to Use</a></li>
    <li><a href="#version-history">Version History</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## 🔴 About The Project

Are you tired of your wizarding school being a mess?
Do you have no datacenter to use for your students?
Are professors tired of having no where to look for courses?
Are you fed up with the disarray in your wizarding school's records?

Say goodbye to the confusion of scattered parchment and lost owls! With the Hogwarts School Database, organizing student data is as easy as waving a wand.

No more bewitched broom closets doubling as datacenters! Our cutting-edge magical infrastructure ensures that your student records are secure and easily accessible to authorized staff.

Professors, rejoice! Say farewell to the frustration of searching high and low for course information. With our intuitive interface, course details are just a flick of the wand away.

Unlock the power of seamless administration and academic excellence with the Hogwarts School Database. Enroll today and let magic streamline your school's operations!

Join Hogwarts School Database today.

### Built With 

* [![Java][Java.image]][Java-url]

<p align="right">(<a href="#readme-top">Back to Top</a>)</p>



<!-- GETTING STARTED -->
## 🟠 Getting Started

The Hogwarts School Management System represents the culmination of efforts aimed at streamlining administrative tasks within Hogwarts School of Witchcraft and Wizardry. This report outlines the tasks completed, challenges encountered, and the overall design and functionality of the system.


### Features

This program is designed to manage the data of professors, students, and courses at Hogwarts School. It provides functionality for account management, course enrollment, rating professors, and more.

#### Student Class

- **Constructor**: Initializes a new student with essential details such as username, owlmail, password, full name, and student house.
- **Account Functions**: Allows students to manage their account settings, including changing the username, password, and owlmail.
- **Validation Functions**: Validates the format and uniqueness of a username, owlmail, and password.
- **Find Functions**: Finds a student by their full name.

#### Professor Class

- **Constructor**: Initializes a new professor with essential details such as username, owlmail, password, and full name.
- **Account Functions**: Allows professors to manage their account settings, including changing the username, password, and owlmail.
- **Authority Functions**: Validates the format and uniqueness of a username, owlmail, and password. Checks password validity and retrieves user number based on owlmail.
- **Professor Functions**: Allows professors to enroll in courses, view all courses taught by them, score students, and view comments.

#### Course Class

- **Constructor**: Initializes a new course with a title and assigns a professor if provided.
- **Course Functions**: Allows adding students to the course, setting scores for students, and finding scores of students in the course.
- **Find Functions**: Finds a course by its title.

#### Hogwarts Class

- **Rate Professor**: Allows users to rate a professor and add comments.
- **View Student Info**: Displays detailed information about a student.
- **Request Course**: Allows students to request a new course.
- **Global Functions**: Displays information about all professors, students, and courses. Sets attributes and displays all professors, students, and courses.

This program provides comprehensive functionality for managing the data of Hogwarts School, facilitating smooth operations and efficient organization.

<p align="right">(<a href="#readme-top">Back to Top</a>)</p>


<!-- HOW TO USE -->
## 🟡 How to Use

*Compile*: Compile all Java files using a Java compiler.

*Run*: Run the Main class to start the Hogwarts School Datacenter program.

*Follow Prompts*: Follow the on-screen prompts to interact with the program functionalities.

<p align="right">(<a href="#readme-top">Back to Top</a>)</p>

<!-- VERSION HISTORY -->
## 🟢 Version History
### Version 1.0.0 - Initial Release
- Student and Teacher classes initialized with basic functions.
- Basic user interface and sign up menu.
- Basic functionality for managing student and professor accounts.
- Course enrollment and management features added.
- Validation for owlmails, passwords and usernames implemented.
### Version 1.0.1
- Bug fixes related to account settings.
- Enhanced validation for usernames, owlmails, and passwords.
- Improved error handling for invalid inputs.
- Assistant class initialized with basic functions and sign in.
- Fixed sign in validations for teachers being mixed with students.
### Version 1.1.0
- Added user menu for students, teachers ad assistants.
- Added functionality for professors and students to change their owlmails, usernames and passwords.
- Added enhanced password hashing for improved security.
### Version 1.1.1
- Fixed a critical bug causing occasional crashes during course enrollment.
- Optimized memory usage for improved performance.
- Resolved issues related to course availability.
- Fixed find user functions exceptions for null values.
### Version 1.2.0
- Added functionality for assistants to change their owlmails, usernames and passwords.
- Enhanced reporting capabilities for better insight into student performance.
- Added support for viewing all available courses.
- Teacher's name can also be viewed when seeing a course.
- Changed UI messages to fit more with hogwarts style.
### Version 1.2.1
- Fixed bugs related to users new password not being hashed
- Resolved an issue causing inconsistent course listing.
- Improved sorting of courses based on availability.
- Fixed minor UI glitches affecting user interactions.
### Version 1.3.0
- Implemented course management features for administrators.
- Enhanced data retrieval and display capabilities.
- Added the ability for administrators to view detailed student information.
- Replaced User class with Account class.
- Added the option to score students in teachers menu.
### Version 1.3.1
- Addressed security vulnerabilities related to password hashing.
- Improved password encryption for enhanced data protection.
- Fixed an issue causing inaccurate score calculations in analytics.
- Minor bugs related to break statement in switch fixed.
- Esc option added for menus so users can go back to previous menu.
### Version 1.4.0
- Introduced a comprehensive data center view for Hogwarts administrators.
- Enhanced user interface for better user experience.
- Implemented smoother transitions between screens.
### Version 1.4.1
- Enhanced data processing algorithms for more accurate results.
- Improved error handling for edge cases.
- Fixed issues related to course enrollment and withdrawal.
- Ability to rate professors and leave comments implemented.
### Version 1.5.0
- Added functionality for students to rate professors and leave comments.
- Implemented course request feature for professors.
### Version 1.5.1
- Fixed bugs related to data synchronization between classes.
- Improved data validation to prevent input errors.

<!-- LICENSE -->
## 🔵 License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#readme-top">Back to Top</a>)</p>



<!-- CONTACT -->
## 🟣 Contact

My Email - prof.danial4@gmail.com

Project Link: [https://github.com/TheDanielTp/2048](https://github.com/TheDanielTp/2048)

<p align="right">(<a href="#readme-top">Back to Top</a>)</p>



<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/TheDanielTp/2048.svg?style=for-the-badge
[contributors-url]: https://github.com/TheDanielTp/2048/graphs/contributors
[Java-url]: https://www.java.com/en/
[Java.image]: https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white
[forks-shield]: https://img.shields.io/github/forks/TheDanielTp/2048.svg?style=for-the-badge
[forks-url]: https://github.com/TheDanielTp/2048/network/members
[stars-shield]: https://img.shields.io/github/stars/TheDanielTp/2048.svg?style=for-the-badge
[stars-url]: https://github.com/TheDanielTp/2048/stargazers
[issues-shield]: https://img.shields.io/github/issues/TheDanielTp/2048.svg?style=for-the-badge
[issues-url]: https://github.com/TheDanielTp/2048/issues
[license-shield]: https://img.shields.io/github/license/TheDanielTp/2048.svg?style=for-the-badge
[license-url]: https://github.com/TheDanielTp/2048/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/linkedin_username
[product-screenshot]: images/screenshot.png
[Next.js]: https://img.shields.io/badge/next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white
[Next-url]: https://nextjs.org/
[React.js]: https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB
[React-url]: https://reactjs.org/
[Vue.js]: https://img.shields.io/badge/Vue.js-35495E?style=for-the-badge&logo=vuedotjs&logoColor=4FC08D
[Vue-url]: https://vuejs.org/
[Angular.io]: https://img.shields.io/badge/Angular-DD0031?style=for-the-badge&logo=angular&logoColor=white
[Angular-url]: https://angular.io/
[Svelte.dev]: https://img.shields.io/badge/Svelte-4A4A55?style=for-the-badge&logo=svelte&logoColor=FF3E00
[Svelte-url]: https://svelte.dev/
[Laravel.com]: https://img.shields.io/badge/Laravel-FF2D20?style=for-the-badge&logo=laravel&logoColor=white
[Laravel-url]: https://laravel.com
[Bootstrap.com]: https://img.shields.io/badge/Bootstrap-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white
[Bootstrap-url]: https://getbootstrap.com
[JQuery.com]: https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jquery&logoColor=white
[JQuery-url]: https://jquery.com 
