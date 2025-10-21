import './MentorCard.css';

function MentorCard({name, sessions, position, img, companyName, mentorId, sessionTime}){
    
    const email = localStorage.getItem("email");
    
    const date = new Date().toISOString().split("T")[0];
    const time = new Date().toTimeString().split(" ")[0];
    const state = "Upcoming";

    const handleClick = async ()=>{
        try{
            const res = await fetch(`http://localhost:8080/user/availableSessions/${email}`,{
                method : "GET",
                headers :{
                    "Content-Type" : "application/json",
                    "Authorization": "Bearer "+localStorage.getItem("token"),
                },
            })

            const data = await res.json();
            if(data == 0){
                alert("You do not have any active Plan");
            }else{
                try{
                    const respsonce = await fetch(`http://localhost:8080/session/addSession/${email}/${mentorId}`,{
                        method : "POST",
                        headers :{
                            "Content-Type" : "application/json",
                            "Authorization": "Bearer "+localStorage.getItem("token"),
                        },
                    })

                    const data = await respsonce.text(); 
                    
                    if(data == "Session Added"){
                        alert("session added successfully");
                    }else{
                        alert("Failed to add Session");
                    }
                    
                }catch{
                    console.log("Error while adding session");
                }
            }
        }catch{
            console.log("Error while fetching available session");
        }
    }
    return (
        <>
            <div className="card">
                <div className="name-photo">
                    <img src={img} />
                    <div className="personal-info">
                        <h6>{name}</h6>
                        <h6>{companyName}</h6>
                    </div>
                </div>
                <div className="mentor-details">
                    <h6>Sessions : {sessions}</h6>
                    <h6>{position}</h6>
                    <h6>Time : {sessionTime}</h6>
                </div>
                <button onClick={handleClick}>Book Session</button>
            </div>
        </>
    )
}

export default MentorCard;