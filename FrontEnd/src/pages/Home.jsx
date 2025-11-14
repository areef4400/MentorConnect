import Footer from "../components/Footer";
import Navbar from "../components/Navbar";
import MentorCard from "../components/MentorCard";
import { useState, useEffect } from "react";

import "./Home.css";
import Subscription from "../components/Subscription";
import UpComingCard from "../components/UpComingCard";

function Home({ setLogin, login }) {

  const [mentorsList, setMentorsList] = useState([]);
  const [sessionList, setSessionList] = useState([]);
  const [date, setDate] = useState("");
  const [time, setTime] = useState("");
  const [mentor, setMentor] = useState("");
  const [zoomLink, setZoomLonk] = useState("");
  const [check, setCheck] = useState(false);

  const email = localStorage.getItem("email");
  
  useEffect(() => {
    const fetchMentors = async () => {
      try {
        const response = await fetch("http://localhost:8080/mentor/allMentors", {
          method: "GET",
          headers: { 
            "Content-Type": "application/json",
            "Authorization": "Bearer "+localStorage.getItem("token"),
          },
        });
        const data = await response.json();
        setMentorsList(data);
      } catch {
        console.log("Error while fetching MentorsList");
      }
    };
    
    const fetchSessions = async () => {
      try {
        const response = await fetch(`http://localhost:8080/user/up-coming-session/${email}`, {
          method: "GET",
          headers: { 
            "Content-Type": "application/json",
            "Authorization": "Bearer "+localStorage.getItem("token"),
          },
        });

        const data = await response.json();

        setSessionList(data);

        if (data.length > 0) {
          setDate(data[0].localDate);
          setTime(data[0].localTime);
          setMentor(data[0].mentorName);
          setZoomLonk(data[0].zoomLink);
          setCheck(true);
        } else {
          setCheck(false);
        }
      } catch {
        console.log("Error while fetching Sessions Data");
      }
    };

    fetchMentors();
    fetchSessions();
  }, [email]);

  return (
    <>
      <Navbar setLogin={setLogin} login={login} />
      <div className="home-page">
        <div className="top-section">
          <div className="upcoming-sessions">
            <h3>Upcoming Session</h3>
            {check ? (
              <div>
                <UpComingCard time={time} date={date} mentor={mentor} zoomLink={zoomLink}/>
              </div>
            ) : (
              <h4>You don’t have any upcoming sessions</h4>
            )}
          </div>

          <div className="quote-section">
            <Subscription />
          </div>
        </div>

        <div className="bottom-section">
          <h3>Top Mentors</h3>
          <div className="fav-mentors">
            {mentorsList.map((item) => (
              <MentorCard key={item.id} name={item.mentorName} 
                  sessions={item.sessions} position={item.expertise} 
                  img={`data:image/jpeg;base64,${item.profilePicture}`} 
                  companyName={item.companyName}
                  mentorId={item.mentorId}
                  sessionTime={item.sessionTime}
                />
            ))}
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
}

export default Home;
