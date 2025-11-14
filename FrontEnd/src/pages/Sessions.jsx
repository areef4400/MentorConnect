import Navbar from "../components/Navbar"
import Footer from "../components/Footer"
import './Sessions.css'
import SessionsCard from "../components/SessionsCard"
import { useState, useEffect } from "react"


function Sessions(){
    const [sessionList, setSessionList] = useState([]);
    const email = localStorage.getItem("email");

    useEffect(()=>{
        const fun = async()=>{
            try{
    
                const responce = await fetch(`http://localhost:8080/user/allSessions/${email}`,{
                    method : "GET",
                    headers : {
                        "Content-Type" : "application/json",
                        "Authorization": "Bearer "+localStorage.getItem("token"),
                    }
                })
    
                const data = await responce.json();
                setSessionList(data);
        
//                 console.log(data);
            }catch{
                console.log("There was an error during fetching Sessions Data")
            }
        }
        fun();
    },[]) 
    
    return (
        <>  
            <Navbar />
            <div className="sessions-page">
                <h2>Past Sessions</h2>
                <div className="past-sessions">
                    {
                        sessionList.map((item) =>(
                            item.state == "Past"?
                            <SessionsCard key = {item.id}
                            sessionDate={item.localDate}
                            mentor={item.mentorName}
                            sessionTime={item.localTime}/>
                            :null
                        ))
                    }
                </div>
                <br />
                <hr />
                <hr /><hr /><hr /><hr />
                <br />
                <h2>Upcoming Sessions</h2>
                <div className="uc-sessions">
                    {
                        sessionList.map((item) =>(
                            item.state == "Upcoming"?
                            <SessionsCard key = {item.id}
                            sessionDate={item.localDate}
                            mentor={item.mentorName}
                            sessionTime={item.localTime}/>
                            :null
                        ))
                    }
                </div>
            </div>
        </>
    )
}

export default Sessions