# JFS-Capstone-Project-Pet-Adoption-Center
This capstone project applies core Java concepts—OOP, collections, exception handling, and file management—to develop a Pet Adoption Center system. Users can manage pets, facilitate adoptions, and maintain records. The goal is to build a structured, user-friendly Java application with practical utility and engaging features.

### Project Objective

The goal of this capstone project is to apply all core Java concepts, including object-oriented programming, collections, exception handling, file handling, and more, by building a fun and interactive application. You will design and implement a Pet Adoption Center system that allows users to manage pets, adopt them out to new owners, and maintain records of available pets. This project will showcase your ability to create a well-structured Java application with real-world utility and fun elements. By completing this capstone project, you will demonstrate your ability to build a complete Java application using core concepts, preparing you for real-world software development while having fun managing a virtual pet adoption center.

### Problem Statement

Design a Pet Adoption Center system that allows staff to manage pet records, track adoptions, and provide potential adopters with information about available pets. The program must be user-friendly, handle errors gracefully, and offer a delightful experience for users looking to adopt their next furry friend.

---

## Requirements

### Classes and Object-Oriented Concepts

- Create a class Pet with attributes such as petId, name, species, age, breed, and adoptionStatus.
- Design a class Adopter with attributes like adopterId, name, contactInfo, and adoptedPets (stored in a collection).
- Implement a class PetAdoptionCenter that holds collections of pets and adopters, providing methods to manage them.
- Utilize inheritance to create subclasses (e.g., Dog, Cat, Bird) that extend the Pet class with additional attributes or behaviors.
- Apply polymorphism by using common interfaces for different actions (e.g., Adoptable, Vaccinable).

### Key Features

#### Adding and Managing Pets
- Create methods to add new pets,
- update their information,
- mark them as adopted, and
- remove pets from the system.

#### Adoption Process
- Allow adopters to view available pets,
- select a pet to adopt, and
- register as the new owner,
- updating the pet’s adoption status.

#### Search and Filter Pets
- Implement features to search for pets based on species, age, breed, or availability, making it easy for users to find their perfect match.

#### Exception Handling
- Handle errors such as invalid pet data,
- incorrect input,
- or issues during the adoption process using custom exceptions where appropriate.

## Implementation

Write a main class AdoptionApp with a main method to demonstrate the functionality of your Pet Adoption Center. 

### Implement methods to

- Add new pets to the center and update their information.
- Register adopters and allow them to adopt pets.
- Manage pet adoption statuses, ensuring that each pet can only be adopted once.
- Save and load data to/from files to maintain a record of all pets and adopters.

### Sample Output

The main method should demonstrate the following scenarios:

- Adding new pets and displaying available ones.
- Registering new adopters and completing adoption transactions.
- Searching for pets by criteria (e.g., species, age) and displaying the results.
- Handling errors gracefully (e.g., trying to adopt a pet that’s already been adopted).
- Saving the system’s state to a file and reloading it on program restart.
