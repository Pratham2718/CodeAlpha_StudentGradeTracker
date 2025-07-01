📚 Student Grade Tracker
A Java GUI application to manage student grades with features like adding, searching, deleting, exporting data, and more — all with a modern, easy-to-use interface!

🎨 Features
✅ View and edit student marks in a dynamic table
✅ Add new students with their grades
✅ Search for a student by name and instantly see their average, highest, and lowest marks
✅ Delete students by name
✅ Export student records to a CSV file for reports
✅ Refresh the table to reload data from the file
✅ Auto-save table edits directly to the students.txt file
✅ Beautiful, modern Java Swing GUI

🚀 Screenshots
![image](https://github.com/user-attachments/assets/1bb0f830-a6ad-4980-ba6a-fd64da9d32a4)![image](https://github.com/user-attachments/assets/6d0a8e95-5d1e-498c-9364-34172c5e53c5)![image](https://github.com/user-attachments/assets/74c5aa2e-7d34-4ca1-a63c-32fbe18a1030)







📂 Project Structure
StudentGradeTracker/
├── Student.java
├── GradeTracker.java
├── StudentTrackerGUI.java
└── students.txt
🛠️ How to Run
1️⃣ Clone this repository or download the project folder.

2️⃣ Compile the Java files:

bash
javac *.java
3️⃣ Run the main GUI application:

bash

java StudentTrackerGUI
📝 students.txt Format
The first line must contain column headers, e.g.:

javascript
Name,Math,Science,English
Each subsequent line should contain student records, e.g.:


Ravi,87,92,78
Anjali,75,80,90
Manoj,88,85,84
✨ Technologies Used
Java SE 8+

Java Swing for GUI

JTable for dynamic tables

🤝 Contributing
Feel free to fork this repo and send pull requests to improve the application.


