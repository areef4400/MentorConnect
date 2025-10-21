🧑‍🏫 MentorConnect

MentorConnect is a full-stack web application designed to connect students with mentors for guidance, learning sessions, and career development.
The platform allows students to discover mentors, schedule sessions, and interact seamlessly — all in one place.

🚀 Tech Stack
Frontend

⚛️ React.js

🧭 React Router

🎨 CSS 


Backend

☕ Spring Boot (Java)

🗄️ Postgresql

🔐 JWT Authentication

🌐 RESTful APIs

Other Tools

Zoom API (for session creation)

Postman (API testing)

Git & GitHub for version control


📸 Features

✅ User Authentication

Signup and Login using JWT tokens.


✅ Mentor Browsing

Students can browse available mentors.

Filter by expertise, experience, or rating.

✅ Session Booking

One-click Zoom meeting creation.

Email or dashboard notifications for booked sessions.

✅ Responsive UI

Works smoothly across desktop and mobile devices.

⚙️ Project Structure
MentorConnect/
├── FrontEnd/           # React app
│   ├── src/
│   ├── public/
│   └── package.json
│
├── BackEnd/            # Spring Boot app
│   ├── src/
│   ├── pom.xml
│   └── application.properties
│
└── README.md

🧩 API Overview (Backend)
Endpoint	Method	Description
/api/auth/signup	POST	Register a new user
/api/auth/login	POST	Authenticate user and return JWT
/api/mentors	GET	Fetch all mentors
/api/sessions/addSession/{email}/{mentorId}	POST	Schedule a new session
/api/sessions/user/{id}	GET	Fetch sessions for a user
🧠 Zoom Meeting Integration

MentorConnect integrates with the Zoom API using Server-to-Server OAuth for automatic meeting creation.
Each scheduled session generates a Zoom link that is shared with both mentor and student.

🛠️ Setup Instructions
1. Clone the repository
git clone https://github.com/areef4400/MentorConnect.git
cd MentorConnect

2. Run the Backend
cd BackEnd
mvn spring-boot:run

3. Run the Frontend
cd ../FrontEnd
npm install
npm run dev


App will be live at:
🔗 http://localhost:5173/

💡 Future Enhancements

Integrate Google Calendar sync

Real-time chat between mentor and student

Mentor rating and feedback system

Email notifications for session reminders

👨‍💻 Developer

Mohammed Areef
💼 Computer Science Graduate | Full-Stack Developer
📧 mohammedareef35@gmail.com
