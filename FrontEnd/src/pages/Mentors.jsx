import { useEffect, useState } from "react"
import Footer from "../components/Footer"
import Navbar from "../components/Navbar"
import MentorCard from "../components/MentorCard";
import './Mentors.css'
import img from '../assets/react.svg'

function Mentors(){
    const [search, setSearch] = useState(' ');
    const [mentorsList, setMentorsList] = useState([]);
    
    useEffect(()=>{
        const fetchMentors = async ()=>{
            try{
                const response = await fetch("http://localhost:8080/mentor/allMentors", {
                    method: "GET",
                    headers: {
                        "Content-Type": "application/json",
                        "Authorization": "Bearer "+localStorage.getItem("token"),
                    },
                });
                
                const data = await response.json();
                // console.log(data);
                setMentorsList(data);
            }catch{
                console.log("Error while fetching MentorsList")
            }
        }
        fetchMentors();
    },[])

    const filteredMentors = mentorsList.filter((item) =>
        (item.mentorName.toLowerCase()).includes(search.toLowerCase())||
        (item.expertise.toLowerCase()).includes(search.toLowerCase())
    )
    return (
        <>
            <Navbar />
                <div className="mentors-section">
                    <input type="text" name="search" onChange={(e)=>setSearch(e.target.value)} placeholder="Search for mentor OR Role"/>
                    <div className="all-mentors">
                        {
                            filteredMentors.map((item)=>(
                                <MentorCard key={item.id} name={item.mentorName} 
                                sessions={item.sessions} position={item.expertise} 
                                img={`data:image/jpeg;base64,${item.profilePicture}`} 
                                companyName={item.companyName}
                                mentorId={item.mentorId}
                                sessionTime={item.sessionTime}
                                />
                            ))
                        }
                    </div>
                </div>
            {/* <Footer /> */}
        </>
    )
}

export default Mentors