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
Copy
Edit
StudentGradeTracker/
├── Student.java
├── GradeTracker.java
├── StudentTrackerGUI.java
└── students.txt
🛠️ How to Run
1️⃣ Clone this repository or download the project folder.

2️⃣ Compile the Java files:

bash
Copy
Edit
javac *.java
3️⃣ Run the main GUI application:

bash
Copy
Edit
java StudentTrackerGUI
📝 students.txt Format
The first line must contain column headers, e.g.:

javascript
Copy
Edit
Name,Math,Science,English
Each subsequent line should contain student records, e.g.:

Copy
Edit
Ravi,87,92,78
Anjali,75,80,90
Manoj,88,85,84
✨ Technologies Used
Java SE 8+

Java Swing for GUI

JTable for dynamic tables

🤝 Contributing
Feel free to fork this repo and send pull requests to improve the application.

📄 License
MIT License

Copyright (c) 2025 [Pratham Kumar]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the “Software”), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
